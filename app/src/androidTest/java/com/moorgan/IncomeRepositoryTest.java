package com.moorgan;


import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.Model.BalanceHistory;
import com.moorgan.Model.Income;
import com.moorgan.Model.Job;
import com.moorgan.Repositories.BalanceHistoryRepository;
import com.moorgan.Repositories.IncomeRepository;
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
public class IncomeRepositoryTest {

    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    private IncomeRepository incomeRepository = new IncomeRepository(appContext);

    private BalanceHistoryRepository balanceHistoryRepository = new BalanceHistoryRepository(appContext);

    private WalletRepository walletRepository = new WalletRepository(appContext);



    @Test
    public void AInsertData(){

        Assert.assertTrue(walletRepository.insert(1, 0));

        Assert.assertTrue(balanceHistoryRepository.insert(100000,
                "29/10/1999", "Pago de prueba", 1, 2));

        Assert.assertTrue(incomeRepository.insert("Ingreso por trabajo x", 1, balanceHistoryRepository.getLastID()));


    }

    @Test
    public void BFindData(){

        Income income = incomeRepository.findById(1);

        assertThat(income.getTitle(), equalTo("Ingreso por trabajo x"));

        assertThat(income.getWallet(), equalTo(1));

        assertThat(income.getBalanceHistory().getAmount(), equalTo((long)100000));

        assertThat(income.getBalanceHistory().getDescription(), equalTo("Pago de prueba"));

        assertThat(income.getBalanceHistory().getEntryDate(), equalTo("29/10/1999"));

        assertThat(income.getBalanceHistory().getType(), equalTo(2));

    }

    @Test
    public void ZDeleteAll(){
        this.appContext.deleteDatabase(AdminDBHelper.MOORGAN_DB_NAME);
    }


}
