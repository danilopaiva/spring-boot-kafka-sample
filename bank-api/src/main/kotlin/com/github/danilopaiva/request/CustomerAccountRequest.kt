package com.github.danilopaiva.request

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Customer Account Request
 */
data class CustomerAccountRequest(
    /**
     * Customer Name
     */
    @field:[NotNull Size(min = 1, max = 50)]
    val name: String?,

    /**
     * Customer Document
     */
    @field:[NotNull]
    val document: DocumentRequest?
) {

    /**
     * Document Request
     */
    data class DocumentRequest(

        /**
         * Document Type
         */
        @field:[NotNull Size(min = 1, max = 10)]
        val type: String?,

        /**
         * Document Id
         */
        @field:[NotNull Size(min = 1, max = 15)]
        val id: String?
    )
}