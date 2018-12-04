package com.example.thebest.hellogooglemap.ui.post

import android.content.Context
import android.content.pm.PackageManager
import android.content.pm.PermissionInfo
import android.databinding.DataBindingUtil
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import com.example.thebest.hellogooglemap.R
import com.example.thebest.hellogooglemap.base.BaseActivity
import com.example.thebest.hellogooglemap.base.BasePresenter
import com.example.thebest.hellogooglemap.base.BaseView
import com.example.thebest.hellogooglemap.databinding.ActivityPostBinding
import com.example.thebest.hellogooglemap.ui.photo.PhotoFragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Activity displaying the list of posts
 */
class PostActivity : BaseActivity<BasePresenter<BaseView>>() , OnMapReadyCallback {

    override var presenter: BasePresenter<BaseView>?
        get() = null
        set(value) {}

    override fun instantiatePresenter(): BasePresenter<BaseView>? {
        return presenter
    }

    /**
     * DataBinding instance
     */
    private lateinit var binding: ActivityPostBinding

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_post)

        if (savedInstanceState == null) {
            /*supportFragmentManager.beginTransaction().apply {
                replace(R.id.content_container_post, PostFragment.newInstance())
                addToBackStack(null)
            }.commit()*/

            /*supportFragmentManager.beginTransaction().apply {
                replace(R.id.content_container_photo, PhotoFragment.newInstance())
            }.commit()*/

            /*if (Build.VERSION.SDK_INT >= 23) {
                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions( arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 123)
                    return
                }
            }*/

            showMap()
        }
    }

    fun showMap() {
        val mapFragment = MapFragment.newInstance()
        mapFragment.getMapAsync(this)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.content_container_post, mapFragment)
        }.commit()
    }

    /*override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode == 123) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showMap()
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }*/

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney").icon(BitmapDescriptorFactory.fromResource(R.drawable.blue_location_icon)))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney , 14f))
    }
}