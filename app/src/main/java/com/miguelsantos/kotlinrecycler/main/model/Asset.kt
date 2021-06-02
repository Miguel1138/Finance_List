package com.miguelsantos.kotlinrecycler.main.model

import com.mooveit.library.Fakeit
import java.math.BigDecimal

// Campos do item asset do xml
// isProfit é boolean pois seu valor vai alterar o icone do item na lista.
// Modelo de dado
data class Asset(
    var isProfit: Boolean,
    var name: String,
    var value: BigDecimal,
    var date: String,
    // isSelected will be used to onLongClick listeners
    var isSelected: Boolean = false
)

// Construtor da classe
class AssetBuilder {
    var isProfit: Boolean = false
    var name: String = ""
    var value: BigDecimal = BigDecimal("0.0")
    var date: String = ""

    fun build(): Asset = Asset(isProfit, name, value, date)
}

// DSL
fun asset(block: AssetBuilder.() -> Unit): Asset = AssetBuilder().apply(block).build()

// Mock
fun fakeAssets() = mutableListOf(
    asset {
        isProfit = (0..1).random() == 0
        name = Fakeit.name().name()
        value = "${10.0 * (1..10).random()}".toBigDecimal()
        // data aleatoria exemplo 24/3/2021
        date = "${(1..31).random()}/${(1..12).random()}/2021"
    },
    asset {
        isProfit = (0..1).random() == 0
        name = Fakeit.name().firstName()
        value = "${10.0 * (1..10).random()}".toBigDecimal()
        date = "${(1..31).random()}/${(1..12).random()}/2021"
    },
    asset {
        isProfit = (0..1).random() == 0
        name = Fakeit.name().name()
        value = "${10.0 * (1..10).random()}".toBigDecimal()
        date = "${(1..31).random()}/${(1..12).random()}/2021"
    },
    asset {
        isProfit = (0..1).random() == 0
        name = Fakeit.name().name()
        value = "${10.0 * (1..10).random()}".toBigDecimal()
        date = "${(1..31).random()}/${(1..12).random()}/2021"
    },
    asset {
        isProfit = (0..1).random() == 0
        name = Fakeit.name().name()
        value = "${10.0 * (1..10).random()}".toBigDecimal()
        date = "${(1..31).random()}/${(1..12).random()}/2021"
    }
)
