package com.github.danilopaiva.domain

import com.github.danilopaiva.domain.repository.AccountRepository
import java.time.LocalDateTime
import java.util.*

class Account(
    val id: Account.Id,
    var balance: Balance = Balance.zero(),
    val document: Document,
    val status: Status = Status.ACTIVE,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val deposits: MutableMap<Deposit.Id, Deposit> = mutableMapOf()
) {
    fun create(repository: AccountRepository) {
        repository.save(this)
    }

    fun registerDeposit(deposit: Deposit, repository: AccountRepository) {
        repository.deposit(this, deposit)
    }

    fun doDeposit(deposit: Deposit, repository: AccountRepository): Deposit {
        balance = Balance(balance.value + deposit.amount.value)
        deposit.completedAt = LocalDateTime.now()
        deposit.status = Deposit.Status.COMPLETED
        repository.deposit(this, deposit)
        return deposit
    }

    data class Balance(val value: Double) {
        companion object {
            fun zero(): Balance =
                Balance(0.0)

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