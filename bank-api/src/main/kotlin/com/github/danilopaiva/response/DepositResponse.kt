package com.github.danilopaiva.response

/**
 * Deposit Response
 */
data class DepositResponse(

    /**
     * Transaction number of deposit
     */
    val transactionId: String?,

    /**
     * Status of deposit
     */
    val status: String?
)