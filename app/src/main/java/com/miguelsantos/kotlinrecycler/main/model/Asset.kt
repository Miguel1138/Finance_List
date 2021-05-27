package com.miguelsantos.kotlinrecycler.main.model

import com.mooveit.library.Fakeit

// Campos do item asset do xml
// icon é boolean pois sua imagem vai depender sé é um ganho ou perda.
// Modelo de dado
data class Asset(
    var icon: Boolean,
    var name: String,
    var value: String,
    var date: String
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
        icon = (0..1).random() == 0
        name = Fakeit.name().name()
        value = "R$ ${(1..100).random()}"
        // data aleatoria exemplo 24/3/2021
        date = "${(1..31).random()}/${(1..12).random()}/2021"
    },
    asset {
        icon = (0..1).random() == 0
        name = Fakeit.name().name()
        value = "R$ ${(1..100).random()}"
        date = "${(1..31).random()}/${(1..12).random()}/2021"
    },
    asset {
        icon = (0..1).random() == 0
        name = Fakeit.name().name()
        value = "R$ ${(1..100).random()}"
        date = "${(1..31).random()}/${(1..12).random()}/2021"
    },
    asset {
        icon = (0..1).random() == 0
        name = Fakeit.name().name()
        value = "R$ ${(1..100).random()}"
        date = "${(1..31).random()}/${(1..12).random()}/2021"
    },
    asset {
        icon = (0..1).random() == 0
        name = Fakeit.name().name()
        value = "R$ ${(1..100).random()}"
        date = "${(1..31).random()}/${(1..12).random()}/2021"
    },
    asset {
        icon = (0..1).random() == 0
        name = Fakeit.name().name()
        value = "R$ ${(1..100).random()}"
        date = "${(1..31).random()}/${(1..12).random()}/2021"
    }
)
