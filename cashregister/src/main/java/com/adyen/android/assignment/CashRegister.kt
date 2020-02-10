package com.adyen.android.assignment

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
        if (remainder > change.total) throw TransactionException("Not enough cash in register")

        // Figure out the minimal amount of change to return
        val monetaryElements: List<MonetaryElement> =
            listOf(*Bill.values()) + listOf(*Coin.values())

        val calculatedChange = Change()
        while (change.total > 0) {
            monetaryElements.forEach {
                val amountOfMonetaryElement = (remainder / it.minorValue)
                // Check how much of those is left in the register,
                // if not enough just return the amount which is in there.
                if (change.getCount(it) >= amountOfMonetaryElement && amountOfMonetaryElement > 0) {
                    remainder -= (it.minorValue * amountOfMonetaryElement)
                    calculatedChange.add(it, amountOfMonetaryElement.toInt())
                } else if (change.getCount(it) < amountOfMonetaryElement) {
                    remainder -= (it.minorValue * change.getCount(it))
                    calculatedChange.add(it, change.getCount(it))
                }

                if (remainder == 0L) {
                    return calculatedChange
                }
            }
        }

        return calculatedChange
    }

    class TransactionException(message: String, cause: Throwable? = null) :
        Exception(message, cause)
}
