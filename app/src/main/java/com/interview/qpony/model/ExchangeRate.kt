package com.interview.qpony.model

import com.squareup.moshi.Json

data class ExchangeRate(@field:Json(name = "success") val success: Boolean,@field:Json(name = "date") val date : String,@field:Json(name = "rates") val rates : Currency)
