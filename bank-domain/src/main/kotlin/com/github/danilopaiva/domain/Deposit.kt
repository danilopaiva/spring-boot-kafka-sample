package com.github.danilopaiva.domain

import java.time.LocalDateTime
import java.util.*

data class Deposit(
    val id: Id = Id(),
    val accountId: Account.Id,
    val amount: Amount,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    var status: Status = Status.PROCESSING,
    var completedAt: LocalDateTime? = null
) {
    data class Id(val value: String = UUID.randomUUID().toString())

    data class Amount(val value: Double)

    enum class Status {
        PROCESSING,
        COMPLETED
    }
}