package com.moorgan;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.moorgan.Database.AdminDBHelper;
import com.moorgan.IRepositories.IBalanceHistoryRepository;
import com.moorgan.IRepositories.IExpenseRepository;
import com.moorgan.IRepositories.IWalletRepository;
import com.moorgan.Model.Expense;
import com.moorgan.Model.Income;
import com.moorgan.Repositories.BalanceHistoryRepository;
import com.moorgan.Repositories.ExpenseRepository;
import com.moorgan.Repositories.WalletRepository;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExpenseRepositoryTest {

    private Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

    private IExpenseRepository expenseRepository = new ExpenseRepository(appContext);

    private IBalanceHistoryRepository balanceHistoryRepository = new BalanceHistoryRepository(appContext);

    private IWalletRepository walletRepository = new WalletRepository(appContext);



    @Test
    public void AInsertData(){

        Assert.assertTrue(walletRepository.insert(1, 0));

        Assert.assertTrue(balanceHistoryRepository.insert(100000,
                "29/10/1999", "Pago de prueba", 1, 3));

        Assert.assertTrue(expenseRepository.insert("EGRESO por trabajo x", 1, balanceHistoryRepository.getLastID()));


    }

    @Test
    public void BFindData(){

        Expense expense = expenseRepository.findById(1);

        assertThat(expense.getTitle(), equalTo("EGRESO por trabajo x"));

        assertThat(expense.getWallet(), equalTo(1));

        assertThat(expense.getBalanceHistory().getAmount(), equalTo((long)100000));

        assertThat(expense.getBalanceHistory().getDescription(), equalTo("Pago de prueba"));

        assertThat(expense.getBalanceHistory().getEntryDate(), equalTo("29/10/1999"));

        assertThat(expense.getBalanceHistory().getType(), equalTo(3));

    }

    @Test
    public void ZDeleteAll(){
        this.appContext.deleteDatabase(AdminDBHelper.MOORGAN_DB_NAME);
    }
}
