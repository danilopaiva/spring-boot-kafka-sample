package com.github.danilopaiva.domain

import java.time.LocalDateTime
import java.util.*

class Account(
    val id: Account.Id,
    val amount: Amount = Amount.zero(),
    val document: Document,
    val status: Status = Status.ACTIVE,
    val createdAt: LocalDateTime = LocalDateTime.now()
) {

    data class Amount(val value: Double) {
        companion object {
            fun zero(): Amount =
                Amount(0.0)

        }
    }

    data class Id(val value: String = UUID.randomUUID().toString())

    data class Document(val name: Name, val type: Type, val number: Number) {

        data class Number(val value: String)

        enum class Type {
            CPF,
            RG
        }

        data class Name(val value: String)
    }

    enum class Status {
        ACTIVE,
        INACTIVE
    }
}