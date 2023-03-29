package furhatos.app.newskill.flow.main

import Drinks
import OrderDrink
import RequestOptions
import furhatos.app.newskill.flow.Parent
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.util.Language

val TakingOrder = state {
    onEntry {
        random(
                { furhat.ask("Can I help you?") },
                { furhat.ask("Can I take your order?") },
                { furhat.ask("Can I get you anything?") }
        )
    }

    onResponse<Yes> {
        random(
                { furhat.ask("What will it be for you to day?") },
                { furhat.ask("What would you like?") }
        )
    }

    onResponse<No> {
        furhat.say("Okay, that's a shame. Have a splendid day!")
        goto(Idle)
    }

    onResponse<OrderDrink> {
        furhat.say("${it.intent.drinks}, what a lovely choice!")
    }

    onResponse<RequestOptions> {
        furhat.say("We have ${Drinks().getEnum(Language.ENGLISH_US).joinToString(", ")}")
        furhat.ask("Do you want some?")
    }
}