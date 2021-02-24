package com.moorgan;


import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.IRepositories.IBalanceHistoryRepository;
import com.moorgan.IRepositories.IClientRepository;
import com.moorgan.IRepositories.IExpenseRepository;
import com.moorgan.IRepositories.IJobRepository;
import com.moorgan.IRepositories.IUserRepository;
import com.moorgan.IRepositories.IWalletRepository;
import com.moorgan.Model.Client;
import com.moorgan.Model.Expense;
import com.moorgan.Repositories.BalanceHistoryRepository;
import com.moorgan.Repositories.ClientRepository;
import com.moorgan.Repositories.ExpenseRepository;
import com.moorgan.Repositories.JobRepository;
import com.moorgan.Repositories.UserRepository;
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
public class ClientRepositoryTest {


    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    private IClientRepository clientRepository = new ClientRepository(appContext);

    private IUserRepository userRepository = new UserRepository(appContext);

    private IWalletRepository walletRepository = new WalletRepository(appContext);

    private IJobRepository jobRepository = new JobRepository(appContext);



    @Test
    public void AInsertData(){

        Assert.assertTrue(walletRepository.insert(1, 0));

        Assert.assertTrue(userRepository.insert("David Alejandro",
                "Nuñez Rangel",
                "29/06/2001",
                "Ingenieria en sistemas",
                1));

        Assert.assertTrue(clientRepository.insert("Pedro Ramirez", "3102345681", 1));

        Assert.assertTrue(clientRepository.insert("Maria Ramirez", "3102343211", 1));


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


    }

    @Test
    public void BFindData(){

        Client client = clientRepository.findById(1);

        assertThat(client.getName(), equalTo("Pedro Ramirez"));

        assertThat(client.getPhoneNumber(), equalTo("3102345681"));

        assertThat(client.getJobs().size(), equalTo(1));

        assertThat(client.getJobs().get(0).getName(), equalTo("Trabajo de prueba 1"));

        assertThat(client.getJobs().get(0).getPayment(), equalTo((long)50000));

    }

    @Test
    public void ZDeleteAll(){
        this.appContext.deleteDatabase(AdminDBHelper.MOORGAN_DB_NAME);
    }
}
