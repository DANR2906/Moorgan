package com.moorgan;


import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.Model.StatusTask;
import com.moorgan.Model.User;
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
import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StatusTaskRepositoryTest {

    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    private WalletRepository walletRepository = new WalletRepository(this.appContext);

    private JobRepository jobRepository = new JobRepository(appContext);

    private ClientRepository clientRepository = new ClientRepository(appContext);

    private BalanceHistoryRepository balanceHistoryRepository = new BalanceHistoryRepository(appContext);

    private StatusRepository statusRepository = new StatusRepository(appContext);

    private StatusTaskRepository statusTaskRepository = new StatusTaskRepository(appContext);

    @Test
    public void AInsertData(){

        Assert.assertTrue(walletRepository.insert(1,1000));

        Assert.assertTrue(clientRepository.insert("Pedro Ramirez", "3214657765",1));

        Assert.assertTrue(clientRepository.insert("Maria Ramirez", "3214645765",1));


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
    }

    @Test
    public void BFindData(){
        StatusTask st = statusTaskRepository.findById(1);

        assertThat(st.getName(), equalTo("Planteamiento del problema"));

        assertThat(st.getDescription(), equalTo("Se plantea el asunto a resolver y als soluciones"));

        assertThat(st.getApprove(), equalTo(0));


    }

    @Test
    public void ZDeleteAll(){
        this.appContext.deleteDatabase(AdminDBHelper.MOORGAN_DB_NAME);
    }

}
