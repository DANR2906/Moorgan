package com.moorgan;


import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.Model.BalanceHistory;
import com.moorgan.Model.Client;
import com.moorgan.Model.User;
import com.moorgan.Repositories.BalanceHistoryRepository;
import com.moorgan.Repositories.ClientRepository;
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
import static org.hamcrest.Matchers.*;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTest {

    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    private UserRepository userRepository = new UserRepository(this.appContext);

    private WalletRepository walletRepository = new WalletRepository(this.appContext);

    private JobRepository jobRepository = new JobRepository(appContext);

    private ClientRepository clientRepository = new ClientRepository(appContext);

    private BalanceHistoryRepository balanceHistoryRepository = new BalanceHistoryRepository(appContext);

    @Test
    public void AinsertData(){

        Assert.assertTrue(walletRepository.insert(1,1000));

        Assert.assertTrue(clientRepository.insert("Pedro Ramirez", 1));

        Assert.assertTrue(clientRepository.insert("Maria Ramirez", 1));


        List<Integer> clients = new ArrayList<>();

        clients.add(1);
        clients.add(2);

        Assert.assertTrue(balanceHistoryRepository.insert(0, "30/01/2021", "Prueba Ingreso tb 1", 1, 1));

        Assert.assertTrue(jobRepository.insert("Trabajo de prueba 1",
                                            "12/02/2021",
                                            "15/02/2020",
                                                50000,
                                                0,
                                                clients,
                                                1,
                                                1));

        Assert.assertTrue(userRepository.insert("David Alejandro",
                                                "Nuñez Rangel",
                                                "29/06/2001",
                                                  "Ingenieria en sistemas",
                                                    1));

    }

    @Test
    public void BFindData(){
        User u = userRepository.findByID(1);

        assertThat(u.getName().trim(), equalTo("David Alejandro"));

        assertThat(u.getLastName().trim(), equalTo("Nuñez Rangel"));

        assertThat(u.getBirthDate().trim(), equalTo("29/06/2001"));

        assertThat(u.getCareer().trim(), equalTo("Ingenieria en sistemas"));

        assertThat(u.getWallet().getId(), equalTo(1));

        assertThat(u.getWallet().getBalance(), equalTo((long)1000));

        assertThat(u.getJobs().size(), equalTo(1));

        assertThat(u.getJobs().get(0).getName(), equalTo("Trabajo de prueba 1"));

        assertThat(balanceHistoryRepository.findByID(u.getJobs().get(0).getBalanceHistory().get(0)).getAmount(), equalTo((long)0));

        assertThat(u.getClients().size(), equalTo(2));

        assertThat(u.getClients().get(0).getName(), equalTo("Pedro Ramirez"));


    }

    @Test
    public void ZDeleteAll(){
        this.appContext.deleteDatabase(AdminDBHelper.MOORGAN_DB_NAME);
    }



}
