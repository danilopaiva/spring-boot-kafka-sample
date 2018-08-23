package com.github.danilopaiva.request

/**
 * Deposit request
 */
data class DepositRequest(

    /**
     * Account Id
     */
    val accountId: String?,

    /**
     * Amount to deposit
     */
    val amount: Double?
)