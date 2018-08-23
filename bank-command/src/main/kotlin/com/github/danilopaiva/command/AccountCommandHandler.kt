package com.github.danilopaiva.command

import com.github.danilopaiva.domain.Account
import org.springframework.stereotype.Component

@Component
class AccountCommandHandler {

    fun handler(command: CreateCustomerAccount): Account =
        Account(
            id = Account.Id(),
            document = command.document
        )

}