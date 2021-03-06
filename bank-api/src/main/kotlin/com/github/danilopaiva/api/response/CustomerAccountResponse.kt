package com.github.danilopaiva.api.response

import java.time.LocalDateTime

/**
 * Customer Account Representation
 */
data class CustomerAccountResponse(

    /**
     * Customer's Account Id
     */
    val accountId: String,

    /**
     * Valor in account
     */
    val amount: Double?,

    /**
     * Customer's Document
     */
    val document: DocumentRepresentation,

    /**
     * Date of registration
     */
    val createdAt: LocalDateTime,

    /**
     * Customer situation
     */
    val status: String,

    /**
     * Deposits in account
     */
    val deposits: List<DepositResponse>
) {
    /**
     * Document Representation
     */
    data class DocumentRepresentation(

        /**
         * Customer's Name
         */
        val name: String?,

        /**
         * Document Type
         */
        val type: String?,

        /**
         * Document number Id
         */
        val number: String?
    )
}
