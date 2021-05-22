package com.miguelsantos.kotlinrecycler.model

// Campos do item asset do xml
// icon é boolean pois sua imagem vai depender sé é um ganho ou perda.
// Modelo de dado
data class Asset(
    var icon: Boolean,
    var name: String,
    var value: String,
    var date: String,
    var selected: Boolean = false
)

// Construtor da classe
class AssetBuilder {
    var icon: Boolean = false
    var name: String = ""
    var value: String = ""
    var date: String = ""

    fun build(): Asset = Asset(icon, name, value, date)
}

// DSL
fun asset(block: AssetBuilder.() -> Unit): Asset = AssetBuilder().apply(block).build()

fun fakeAssets() = mutableListOf(
    asset {
        icon = false
        name = "Mercado Nunes"
        value = "R$ 24,85"
        date = "22/03/2021"
    },

    asset {
        icon = true
        name = "Joalheria"
        value = "R$ 250,75"
        date = "22/03/2021"
    },

    asset {
        icon = false
        name = "Mercado Nunes"
        value = "R$ 24,85"
        date = "22/03/2021"
    },

    asset {
        icon = true
        name = "Mercado Nunes"
        value = "R$ 24,85"
        date = "22/03/2021"
    },

    asset {
        icon = false
        name = "Joalheria"
        value = "R$ 250,75"
        date = "22/03/2021"
    },

    asset {
        icon = false
        name = "Mercado Nunes"
        value = "R$ 24,85"
        date = "22/03/2021"
    },
    asset {
        icon = false
        name = "Mercado Nunes"
        value = "R$ 24,85"
        date = "22/03/2021"
    },

    asset {
        icon = true
        name = "Joalheria"
        value = "R$ 250,75"
        date = "22/03/2021"
    },

    asset {
        icon = false
        name = "Mercado Nunes"
        value = "R$ 24,85"
        date = "22/03/2021"
    },

    asset {
        icon = true
        name = "Mercado Nunes"
        value = "R$ 24,85"
        date = "22/03/2021"
    },

    asset {
        icon = false
        name = "Joalheria"
        value = "R$ 250,75"
        date = "22/03/2021"
    },

    asset {
        icon = false
        name = "Mercado Nunes"
        value = "R$ 24,85"
        date = "22/03/2021"
    })
