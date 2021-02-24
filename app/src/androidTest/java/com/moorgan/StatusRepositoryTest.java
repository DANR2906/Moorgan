package com.moorgan;


import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.IRepositories.IBalanceHistoryRepository;
import com.moorgan.IRepositories.IClientRepository;
import com.moorgan.IRepositories.IJobRepository;
import com.moorgan.IRepositories.IStatusRepository;
import com.moorgan.IRepositories.IStatusTaskRepository;
import com.moorgan.IRepositories.IWalletRepository;
import com.moorgan.Model.Status;
import com.moorgan.Model.StatusTask;
import com.moorgan.Repositories.BalanceHistoryRepository;
import com.moorgan.Repositories.ClientRepository;
import com.moorgan.Repositories.JobRepository;
import com.moorgan.Repositories.StatusRepository;
import com.moorgan.Repositories.StatusTaskRepository;
import com.moorgan.Repositories.WalletRepository;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StatusRepositoryTest {

    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    private IWalletRepository walletRepository = new WalletRepository(this.appContext);

    private IJobRepository jobRepository = new JobRepository(appContext);

    private IClientRepository clientRepository = new ClientRepository(appContext);

    private IBalanceHistoryRepository balanceHistoryRepository = new BalanceHistoryRepository(appContext);

    private IStatusRepository statusRepository = new StatusRepository(appContext);

    private IStatusTaskRepository statusTaskRepository = new StatusTaskRepository(appContext);

    @Test
    public void AInsertData(){

        Assert.assertTrue(walletRepository.insert(1,1000));

        Assert.assertTrue(clientRepository.insert("Pedro Ramirez", "3204653765", 1));

        Assert.assertTrue(clientRepository.insert("Maria Ramirez", "3114653765", 1));


        List<Integer> clients = new ArrayList<>();

        clients.add(1);
        clients.add(2);


        Assert.assertTrue(jobRepository.insert("Trabajo de prueba 1",
                "12/02/2021",
                "15/02/2020",
                50000,
                0,
                clients,
                1));

        Assert.assertTrue(statusRepository.insert("Fase inicial",
                10000,
                0,
                1));

        Assert.assertTrue(statusTaskRepository.insert("Planteamiento del problema",
                "Se plantea el asunto a resolver y als soluciones",
                0,
                1));

        Assert.assertTrue(statusTaskRepository.insert("Pensamiento ritmico",
                "se piensa ritmicamenter",
                0,
                1));
    }

    @Test
    public void BFindData(){
        Status st = statusRepository.findById(1);

        assertThat(st.getName(), equalTo("Fase inicial"));

        assertThat(st.getAdvancePayment(), equalTo((long)10000));

        assertThat(st.getApproved(), equalTo(0));

        assertThat(st.getStatusTasks().size(), equalTo(2));

        assertThat(st.getStatusTasks().get(0).getName(), equalTo("Planteamiento del problema"));

        assertThat(st.getStatusTasks().get(1).getName(), equalTo("Pensamiento ritmico"));

        assertThat(st.getStatusTasks().get(1).getDescription(), equalTo("se piensa ritmicamenter"));

        balanceHistoryRepository.insert(5000, "19/07/2000", "BH PRueba", 1, 4);

        statusRepository.insertStatusBalanceHistory(balanceHistoryRepository.getLastID(), statusRepository.getLastID());

        balanceHistoryRepository.insert(5000, "29/07/2000", "BH PRueba", 1, 4);

        statusRepository.insertStatusBalanceHistory(balanceHistoryRepository.getLastID(), statusRepository.getLastID());

        st = statusRepository.findById(1);

        assertThat(st.getBalanceHistory().size(), equalTo(2));

        assertThat(st.getBalanceHistory().get(0).getDescription(), equalTo("BH PRueba"));

        assertThat(st.getBalanceHistory().get(0).getEntryDate(), equalTo("19/07/2000"));

        assertThat(st.getBalanceHistory().get(0).getAmount(), equalTo((long) 5000));

        assertThat(st.getBalanceHistory().get(1).getEntryDate(), equalTo("29/07/2000"));

        Assert.assertTrue(statusRepository.insert("Fase Media",
                0,
                0,
                1));

        st = statusRepository.findById(2);

        assertThat(st.getName(), equalTo("Fase Media"));

        assertThat(st.getAdvancePayment(), equalTo((long)0));

        assertThat(st.getApproved(), equalTo(0));


    }

    @Test
    public void ZDeleteAll(){
        this.appContext.deleteDatabase(AdminDBHelper.MOORGAN_DB_NAME);
    }
}
