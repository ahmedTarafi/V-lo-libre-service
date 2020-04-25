package com.tarafi.velolibreapp.dashboard.domain.bean

import java.io.Serializable

const val STATION_OBJECT = "com.tarafi.velolibreapp.dashboard.domain.bean"

data class Station(
    val number: Long,
    val contract_name: String,
    val name: String,
    val address: String,
    val position: Position,
    val bike_stands: Int,
    val available_bike_stands: Int,
    val status: STATUS,
    val last_update: Long
):Serializable

data class Position(
    val lat: Double,
    val lng: Double
)

enum class STATUS{OPEN,CLOSED, UNKNOWN }