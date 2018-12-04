package com.example.thebest.hellogooglemap.ui.post

import android.os.Bundle
import com.google.android.gms.maps.SupportMapFragment

class MapFragment : SupportMapFragment() {

    companion object {
        fun newInstance() : MapFragment {
            val args = Bundle()
            val fragment = MapFragment()
            fragment.arguments = args
            return fragment
        }
    }
}