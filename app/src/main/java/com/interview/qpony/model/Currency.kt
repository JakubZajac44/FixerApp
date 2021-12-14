package com.interview.qpony.model

import com.squareup.moshi.Json

data class Currency(@field:Json(name = "USD") val USD: Float,
                    @field:Json(name = "AUD") val AUD: Float,
                    @field:Json(name = "PLN") val PLN: Float,
                    @field:Json(name = "CAD") val CAD: Float,
                    @field:Json(name = "CHF") val CHF: Float,
                    @field:Json(name = "CNY") val CNY: Float,
                    @field:Json(name = "GBP") val GBP: Float,
                    @field:Json(name = "JPY") val JPY: Float)
