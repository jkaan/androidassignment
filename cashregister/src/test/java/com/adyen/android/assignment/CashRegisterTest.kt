package com.adyen.android.assignment

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class CashRegisterTest {
    private lateinit var cashRegister: CashRegister

    @Test
    fun testTransactionWithSamePriceAsAmountPaidReturnsNone() {
        assertEquals(Change.none(), cashRegister.performTransaction(10L, 10L))
    }

    @Test
    fun testTransactionWithPriceMoreThanWhatIsPaidThrowsException() {
        assertThrows(CashRegister.TransactionException::class.java) {
            cashRegister.performTransaction(1L, 0L)
        }
    }

    @Test
    fun testTransactionWithPriceFiveEurosLessThanAmountPaidReturnsChangeWithBillOfFive() {
        assertEquals(Change().apply {
            add(Bill.FIVE_EURO, 1)
        }, cashRegister.performTransaction(1000L, 1500L))
    }

    @Test
    fun testTransactionWithPriceFiveCentsLessThanAmountPaidReturnsChangeWithCoinOfFive() {
        assertEquals(Change().apply {
            add(Coin.FIVE_CENT, 1)
        }, cashRegister.performTransaction(10L, 15L))
    }

    @Test
    fun testTransactionWithPriceFiveCentsAndFiveEurosLessThanAmountPaidReturnsChangeWithBillOfFiveAndCoinOfFive() {
        assertEquals(Change().apply {
            add(Coin.FIVE_CENT, 1)
            add(Bill.FIVE_EURO, 1)
        }, cashRegister.performTransaction(1000L, 1505L))
    }

    @Test
    fun testTransactionWithPriceThousandEuroLessThanAmountPaidReturnsChangeWithTwoBillsOfFiveHundred() {
        assertEquals(Change().apply {
            add(Bill.FIVEHUNDRED_EURO, 2)
        }, cashRegister.performTransaction(100000L, 200000L))
    }

    @Before
    fun setUp() {
        cashRegister = CashRegister(Change.none())
    }
}
