package com.github.danilopaiva.api.response

import java.time.LocalDateTime

/**
 * Deposit Response
 */
data class DepositResponse(

    /**
     * Transaction number of deposit
     */
    val transactionId: String?,

    /**
     * Deposit created at
     */
    val createdAt: LocalDateTime?,

    /**
     * Status of deposit
     */
    val status: String?
)