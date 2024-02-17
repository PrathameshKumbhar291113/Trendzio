package com.trendzio.get_products_list_feature.presentation.manager

import kotlin.math.round
import kotlin.math.roundToInt

fun calculateOriginalPrice(discountedPrice: Double?, discountPercentage: Double?): Double {
    var originPrice : Double = 0.0
    discountedPrice?.let {
        discountPercentage?.let {
            val discountDecimal = discountPercentage / 100.0
            originPrice = discountedPrice / (1 - discountDecimal)
        }
    }
    return round(originPrice)
}