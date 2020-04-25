package com.tarafi.velolibreapp.dashboard.data.entities

data class StationJson(val number:Long,
                       val contract_name:String,
                       val name:String,
                       val address:String,
                       val position:PositionJson,
                       val bike_stands:Int,
                       val available_bike_stands:Int,
                       val status:String,
                       val last_update:Long)

data class PositionJson(val lat:Double,
                        val lng:Double)