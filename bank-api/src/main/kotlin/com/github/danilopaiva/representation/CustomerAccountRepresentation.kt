package com.github.danilopaiva.representation

import java.time.LocalDateTime

/**
 * Customer Account Representation
 */
data class CustomerAccountRepresentation(
    /**
     * Customer's Account Id
     */
    val accountId: String,

    /**
     * Customer's Name
     */
    val name: String?,

    /**
     * Customer's Document
     */
    val document: DocumentRepresentation,

    /**
     * Date of registration
     */
    val createAt: LocalDateTime,

    /**
     * Customer situation
     */
    val status: String
) {
    /**
     * Document Representation
     */
    data class DocumentRepresentation(
        /**
         * Document Type
         */
        val type: String?,

        /**
         * Document Id
         */
        val id: String?
    )
}
