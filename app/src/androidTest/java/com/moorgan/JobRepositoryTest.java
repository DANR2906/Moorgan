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
import com.moorgan.Model.Job;
import com.moorgan.Model.Status;
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
public class JobRepositoryTest {

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

        Assert.assertTrue(clientRepository.insert("Pedro Ramirez", "3214653765",1));

        Assert.assertTrue(clientRepository.insert("Maria Ramirez", "3114163765",1));


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

        Assert.assertTrue(statusRepository.insert("Fase Media",
                0,
                0,
                1));
    }

    @Test
    public void BFindData(){

        Assert.assertTrue(balanceHistoryRepository.insert(10000, "29/08/2019",
                "Buenas BUenas", 1, 1));

        Assert.assertTrue(jobRepository.insertJobBalanceHistory(balanceHistoryRepository.getLastID(), 1));

        Assert.assertTrue(balanceHistoryRepository.insert(20000, "9/08/2019",
                "Buenas Malas", 1, 1));

        Assert.assertTrue(jobRepository.insertJobBalanceHistory(balanceHistoryRepository.getLastID(), 1));


        Job job = jobRepository.findById(1);

        assertThat(job.getName(), equalTo("Trabajo de prueba 1"));

        assertThat(job.getCreationDate(), equalTo("12/02/2021"));

        assertThat(job.getEndDate(), equalTo("15/02/2020"));

        assertThat(job.getFinished(), equalTo(0));

        assertThat(job.getPayment(), equalTo((long)50000));

        assertThat(job.getClients().size(), equalTo(2));

        assertThat(clientRepository.findById(job.getClients().get(0)).getName(), equalTo("Pedro Ramirez"));

        assertThat(job.getBalanceHistory().size(), equalTo(2));

        assertThat(balanceHistoryRepository.findByID(job.getBalanceHistory().get(0))
                    .getDescription(), equalTo("Buenas BUenas"));

        assertThat(job.getStatus().size(), equalTo(2));

        assertThat(job.getStatus().get(0).getName(), equalTo("Fase inicial"));

        assertThat(job.getStatus().get(0).getStatusTasks().size(), equalTo(2));

        assertThat(job.getStatus().get(0).getStatusTasks().get(0).getDescription(), equalTo("Se plantea el asunto a resolver y als soluciones"));

        assertThat(job.getStatus().get(1).getName(), equalTo("Fase Media"));
    }

    @Test
    public void ZDeleteAll(){
        this.appContext.deleteDatabase(AdminDBHelper.MOORGAN_DB_NAME);
    }
}
