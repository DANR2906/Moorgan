package com.moorgan;


import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.Database.DatabaseConnection;
import com.moorgan.Model.BalanceHistory;
import com.moorgan.Model.Wallet;
import com.moorgan.Repositories.BalanceHistoryRepository;
import com.moorgan.Repositories.ExpenseRepository;
import com.moorgan.Repositories.IncomeRepository;
import com.moorgan.Repositories.StatusRepository;
import com.moorgan.Repositories.UserRepository;
import com.moorgan.Repositories.WalletRepository;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;


/**
 *
 */
@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WalletRepositoryTest {


    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    private WalletRepository walletRepository = new WalletRepository(appContext);

    private BalanceHistoryRepository balanceHistoryRepository = new BalanceHistoryRepository(appContext);

    private IncomeRepository incomeRepository = new IncomeRepository(appContext);

    private ExpenseRepository expenseRepository = new ExpenseRepository(appContext);


    @Test
    public void AInsertData(){

        Assert.assertTrue("Test Failed", walletRepository.insert(1,0));

        Assert.assertTrue(balanceHistoryRepository.insert(10000, "30/01/2021", "Prueba Ingreso", 1, 2));

        Assert.assertTrue(incomeRepository.insert("Ingreso prueba", 1, 1));

        Assert.assertTrue(balanceHistoryRepository.insert(5000, "6/01/2021", "Prueba Egreso", 1, 3));

        Assert.assertTrue(expenseRepository.insert("Egreso prueba", 1, 2));


    }

    @Test
    public void BFindData(){


        Wallet wallet = walletRepository.findByID(1);

        assertThat(wallet.getBalance(), equalTo((long)0));

        assertThat(wallet.getBalanceHistory().size(), equalTo(2));

        assertThat(wallet.getBalanceHistory().get(0).getAmount(), equalTo((long)10000));

        assertThat(wallet.getBalanceHistory().get(1).getAmount(), equalTo((long)5000));

        assertThat(incomeRepository.findById(1).getWallet(), equalTo(wallet.getId()));

        assertThat(expenseRepository.findById(1).getWallet(), equalTo(wallet.getId()));

        Assert.assertTrue(walletRepository.updateBalance(1, 4000));

        wallet = walletRepository.findByID(1);

        assertThat(wallet.getBalance(), equalTo((long)4000));

    }

    @Test
    public void ZDeleteAll(){
        this.appContext.deleteDatabase(AdminDBHelper.MOORGAN_DB_NAME);
    }




}
