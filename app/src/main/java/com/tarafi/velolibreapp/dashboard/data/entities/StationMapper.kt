package com.tarafi.velolibreapp.dashboard.data.entities

import com.tarafi.velolibreapp.dashboard.domain.bean.Position
import com.tarafi.velolibreapp.dashboard.domain.bean.STATUS
import com.tarafi.velolibreapp.dashboard.domain.bean.Station
import javax.inject.Inject

class StationMapper @Inject constructor(){
    fun toStationsBo(stationsListJson: List<StationJson>?): ArrayList<Station> {
        val stationsList = ArrayList<Station>()
        stationsListJson?.mapTo(stationsList){ toStationBO(it) }
        return stationsList
    }

    private fun toStationBO(stationJson: StationJson): Station {
        return Station(number = stationJson.number,
            address = stationJson.address,
            available_bike_stands = stationJson.available_bike_stands,
            bike_stands = stationJson.bike_stands,
            contract_name = stationJson.contract_name,
            last_update = stationJson.last_update,
            name = stationJson.name,
            position = toPositionBO(stationJson.position),
            status = toStationStatusBO(stationJson.status)
            )
    }

    private fun toStationStatusBO(status: String): STATUS {
        return when (status) {
            "OPEN" -> STATUS.OPEN
            "CLOSED" -> STATUS.CLOSED
            else -> STATUS.UNKNOWN
        }
    }

    private fun toPositionBO(positionJson: PositionJson): Position {
        return Position(lat = positionJson.lat,
            lng = positionJson.lng)
    }

}