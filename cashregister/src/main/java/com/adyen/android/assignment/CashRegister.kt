package com.adyen.android.assignment

import kotlin.system.exitProcess

/**
 * The CashRegister class holds the logic for performing transactions.
 *
 * @param change The change that the CashRegister is holding.
 */
class CashRegister(private val change: Change) {
    /**
     * Performs a transaction for a product/products with a certain price and a given amount.
     *
     * @param price The price of the product(s).
     * @param amountPaid The amount paid by the shopper.
     *
     * @return The change for the transaction.
     *
     * @throws TransactionException If the transaction cannot be performed.
     */
    fun performTransaction(price: Long, amountPaid: Long): Change {
        if (price == amountPaid) return Change.none()
        if (price > amountPaid) throw TransactionException("Can't pay with less than what the price is")

        var remainder = amountPaid - price
        val change = Change()

        val monetaryElements: List<MonetaryElement> = listOf(*Bill.values()) + listOf(*Coin.values())

        while (remainder > 0) {
            monetaryElements.forEach {
                val amountOfMonetaryElement = (remainder / it.minorValue)
                if (amountOfMonetaryElement > 0) {
                    remainder -= (it.minorValue * amountOfMonetaryElement)
                    change.add(it, amountOfMonetaryElement.toInt())
                }
            }
        }

        return change
    }

    class TransactionException(message: String, cause: Throwable? = null) :
        Exception(message, cause)
}
