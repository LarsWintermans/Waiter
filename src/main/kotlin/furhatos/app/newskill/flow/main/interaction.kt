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
    //friendly
    onResponse<Yes> {
        random(
                { furhat.ask("Should I help you choose a drink, or should I just list our most popular options") },
                { furhat.ask("So what flavors do you usually like?") }
        )
    }
    //unfriendly
    onResponse<No> {
        furhat.say("You can look at the menu to see what we have")
        furhat.say ( "We obviously have beverages, maybe you can look into the menu to see what we have")
        furhat.say ("I think anything you order is good")
        furhat.say ("I think everythung is good. But please make it quick, there are a lot of customers which I need to handle")
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