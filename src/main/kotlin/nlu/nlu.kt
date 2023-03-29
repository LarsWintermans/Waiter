import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.util.Language

class Drinks : EnumEntity(stemming = true, speechRecPhrases = true) {
    override fun getEnum(lang: Language): List<String> {
        return listOf("beer", "wine", "soda", "water")
        // maybe we can hook up this list to an actual online bar menu?
    }
}

// Our OrderDrink intent
class OrderDrink(val drinks : Drinks? = null) : Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("@drinks", "I want a beer", "I would like an wine", "I want to order a @drinks")
    }
}

class RequestOptions: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("What options do you have?",
                "What fruits do you have?",
                "What are the alternatives?",
                "What do you have?")
    }
}