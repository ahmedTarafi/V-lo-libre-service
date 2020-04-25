package com.tarafi.velolibreapp.detail.app.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.common.util.Strings.capitalize
import com.tarafi.velolibreapp.R
import com.tarafi.velolibreapp.common.app.fragment.BaseFragment
import com.tarafi.velolibreapp.dashboard.domain.bean.STATION_OBJECT
import com.tarafi.velolibreapp.dashboard.domain.bean.STATUS
import com.tarafi.velolibreapp.dashboard.domain.bean.Station
import kotlinx.android.synthetic.main.fragment_detail.*
import java.util.*

class DetailFragment : BaseFragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val station = arguments?.get(STATION_OBJECT) as Station

        stationText.text = station.name.toLowerCase(Locale.getDefault()).capitalize()
        nbreVeloText.text = station.bike_stands.toString()
        contratText.text = station.contract_name.toLowerCase(Locale.getDefault()).capitalize()
        nbreVeloDispoText.text = station.available_bike_stands.toString()
        addresseText.text = station.address.toLowerCase(Locale.getDefault()).capitalize()
        when (station.status) {
            STATUS.CLOSED -> statusText.text = getString(R.string.closed_text)
            STATUS.OPEN -> statusText.text = getString(R.string.open_text)
            STATUS.UNKNOWN -> statusText.text = getString(R.string.unknown_text)
        }
    }
}