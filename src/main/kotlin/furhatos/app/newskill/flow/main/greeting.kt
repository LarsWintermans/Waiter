package furhatos.app.newskill.flow.main

import furhatos.app.newskill.flow.Parent
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.onResponse
import furhatos.flow.kotlin.state
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes

val Greeting = state(Parent) {
    onEntry {
        random(
                {   furhat.ask("Hello") },
                {   furhat.ask("Oh, hello there") },
                {   furhat.ask("Good evening")}
        )}
        onResponse<Yes>{
            furhat.say("Welcome to Peter's inn, can I take your order?")
            goto(TakingOrder)
        }
        //need to change that to actual triggers
        onResponse<No>{
            furhat.say("Good to see you, welcome to Peter's inn! How are you doing today?")
            goto(TakingOrder)
        }


    }

