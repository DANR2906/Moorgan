package com.moorgan;


import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.IRepositories.IBalanceHistoryRepository;
import com.moorgan.IRepositories.IWalletRepository;
import com.moorgan.Model.BalanceHistory;
import com.moorgan.Model.Client;
import com.moorgan.Repositories.BalanceHistoryRepository;
import com.moorgan.Repositories.WalletRepository;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BalanceHistoryRepositoryTest {


    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    private IBalanceHistoryRepository balanceHistoryRepository = new BalanceHistoryRepository(appContext);

    private IWalletRepository walletRepository = new WalletRepository(appContext);



    @Test
    public void AInsertData(){

        Assert.assertTrue(walletRepository.insert(1, 1000));

        Assert.assertTrue(balanceHistoryRepository.insert(10000, "29/10/2000", "Pago", 1, 2));

    }

    @Test
    public void BFindData(){
        BalanceHistory bh = balanceHistoryRepository.findByID(1);

        assertThat(bh.getType(), equalTo(2));

        assertThat(bh.getEntryDate(), equalTo("29/10/2000"));

        assertThat(bh.getDescription(), equalTo("Pago"));

        assertThat(bh.getAmount(), equalTo((long)10000));

        balanceHistoryRepository.deleteByID(1);

        assertThat(balanceHistoryRepository.findByID(1), equalTo(null));

    }

    @Test
    public void ZDeleteAll(){
        this.appContext.deleteDatabase(AdminDBHelper.MOORGAN_DB_NAME);
    }
}
