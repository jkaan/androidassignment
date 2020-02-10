package com.adyen.android.assignment

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class CashRegisterTest {

    @Test
    fun testTransactionWithSamePriceAsAmountPaidReturnsNone() {
        assertEquals(Change.none(), CashRegister(Change.none()).performTransaction(10L, 10L))
    }

    @Test
    fun testTransactionWithPriceMoreThanWhatIsPaidThrowsException() {
        assertThrows(CashRegister.TransactionException::class.java) {
            CashRegister(Change.none()).performTransaction(1L, 0L)
        }
    }

    @Test
    fun testTransactionWithNoChangeThrowsExceptionIfAmountPaidIsHigherThanPrice() {
        assertThrows(CashRegister.TransactionException::class.java) {
            CashRegister(Change.none()).performTransaction(1L, 2L)
        }
    }

    @Test
    fun testTransactionWithChangeLowerThanWhatIsNeededThrowsException() {
        assertThrows(CashRegister.TransactionException::class.java) {
            CashRegister(Change().apply {
                add(Coin.ONE_CENT, 1)
            }).performTransaction(1L, 3L)
        }
    }

    @Test
    fun testTransactionWithPriceFiveEurosLessThanAmountPaidReturnsChangeWithBillOfFive() {
        assertEquals(Change().apply {
            add(Bill.FIVE_EURO, 1)
        }, CashRegister(Change().apply {
            add(Bill.FIVE_EURO, 1)
        }).performTransaction(1000L, 1500L))
    }

    @Test
    fun testTransactionWithPriceFiveCentsLessThanAmountPaidReturnsChangeWithCoinOfFive() {
        assertEquals(Change().apply {
            add(Coin.FIVE_CENT, 1)
        }, CashRegister(Change().apply {
            add(Coin.FIVE_CENT, 1)
        }).performTransaction(10L, 15L))
    }

    @Test
    fun testTransactionWithPriceFiveCentsAndFiveEurosLessThanAmountPaidReturnsChangeWithBillOfFiveAndCoinOfFive() {
        assertEquals(Change().apply {
            add(Coin.FIVE_CENT, 1)
            add(Bill.FIVE_EURO, 1)
        }, CashRegister(Change().apply {
            add(Coin.FIVE_CENT, 1)
            add(Bill.FIVE_EURO, 1)
        }).performTransaction(1000L, 1505L))
    }

    @Test
    fun testTransactionWithPriceThousandEuroLessThanAmountPaidReturnsChangeWithTwoBillsOfFiveHundred() {
        assertEquals(Change().apply {
            add(Bill.FIVEHUNDRED_EURO, 2)
        }, CashRegister(Change().apply {
            add(Bill.FIVEHUNDRED_EURO, 2)
        }).performTransaction(100000L, 200000L))
    }
}
