package com.tarafi.velolibreapp.dashboard.app.fragment

import android.Manifest
import android.content.Context.LOCATION_SERVICE
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.tarafi.velolibreapp.R
import com.tarafi.velolibreapp.common.app.activity.MainActivity
import com.tarafi.velolibreapp.common.app.fragment.BaseFragment
import com.tarafi.velolibreapp.common.di.ApplicationComponent
import com.tarafi.velolibreapp.dashboard.app.iviews.DashboardIView
import com.tarafi.velolibreapp.dashboard.app.presenter.DashboardPresenter
import com.tarafi.velolibreapp.dashboard.di.DaggerDashboardComponent
import com.tarafi.velolibreapp.dashboard.di.DashboardModule
import com.tarafi.velolibreapp.dashboard.domain.bean.STATION_OBJECT
import com.tarafi.velolibreapp.dashboard.domain.bean.Station
import com.tarafi.velolibreapp.detail.app.fragment.DetailFragment
import java.util.*
import javax.inject.Inject


class DashboardFragment : BaseFragment(), DashboardIView, OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {
    @Inject
    lateinit var dashboardPresenter: DashboardPresenter
    private lateinit var locationManager: LocationManager
    private var googleMap: GoogleMap? = null
    private lateinit var mStationsList: ArrayList<Station>


    override fun initializeInjector(applicationComponent: ApplicationComponent) {
        val dashboardComponent =
            DaggerDashboardComponent.builder().applicationComponent(applicationComponent)
                .dashboardModule(DashboardModule(activity as MainActivity)).build()
        dashboardComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dashboardPresenter.setView(this)
        dashboardPresenter.fetchStations()
        locationManager = mainActivity.getSystemService(LOCATION_SERVICE) as LocationManager
    }

    override fun onMapReady(gMap: GoogleMap?) {
        googleMap = gMap
        googleMap?.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap?.clear()
        getCurrentLocation()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    private fun getCurrentLocation() {
        // Request location updates
        if (ContextCompat.checkSelfPermission(
                mainActivity.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                0L,
                0f,
                locationListener
            )
        } else {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
        }
    }

    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            if (isAdded)
                updateCurrentLocation(location)
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    private fun updateCurrentLocation(location: Location) {
        //googleMap?.clear()
        googleMap?.addMarker(
            MarkerOptions()
                .position(LatLng(location.latitude, location.longitude))
                .title(getString(R.string.current_location_text))
        )

        googleMap?.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    location.latitude,
                    location.longitude
                ), 15.0f
            )
        )

        googleMap?.setOnMarkerClickListener(this)
    }

    private fun showDetail(stationClicked: Station) {
        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putSerializable(STATION_OBJECT, stationClicked)
        fragment.arguments = bundle
        mainActivity.switchFragment(fragment)
    }

    private fun getStationsInfo(position: LatLng?): Station? {
        for (station in mStationsList) {
            if (position?.latitude == station.position.lat && position?.longitude == station.position.lng)
                return station
        }
        return null
    }

    override fun showStationsOnMap(stationsList: ArrayList<Station>) {
        mStationsList = stationsList
        for (station in stationsList) {
            googleMap?.addMarker(
                MarkerOptions()
                    .position(LatLng(station.position.lat, station.position.lng))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.velo_icon))
                    .title(station.name)
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        getCurrentLocation()
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        val stationClicked = getStationsInfo(marker?.position)
        if (stationClicked != null) {
            showDetail(stationClicked)
        }
        return true
    }

    override fun onPause() {
        locationManager.removeUpdates(locationListener)
        super.onPause()
    }
}