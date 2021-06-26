package dev.luischang.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.maps.android.PolyUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolygonOptions
import dev.luischang.maps.databinding.ActivityMapsBinding
import java.security.KeyStore

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))


        val islaSL = LatLng(-12.077817064831377, -77.23412584816806)
        mMap.addMarker(MarkerOptions()
            .position(islaSL)
            .title("Isla San Lorenzo - La Punta")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.googleg_standard_color_18))
            .draggable(true)
            .snippet("La Isla San Lorenzo queda en el la provincia constitucional del Callao"))

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(islaSL, 10F))
        mMap.isTrafficEnabled=true
        mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN

        val lima = LatLng(-11.742259630249553, -76.61071659031951)
        val caracas = LatLng(10.419013111211049, -66.94274790061945)
        val bahia = LatLng(-12.258077293217212, -41.71813882866549)
        val baires = LatLng(-33.7886909260691, -62.514350460329794)

//        val lapaz = LatLng(-16.794498998309066, -64.00849105028374)
        val lapaz = LatLng(9.935898473765306, -83.95966287580686)

        val polygon = mMap.addPolygon(PolygonOptions().add(lima,caracas,bahia, baires))
        polygon.tag = "DPA UESAN"


        val puntoValidar = PolyUtil.containsLocation(lapaz,polygon.points,false)

        if(puntoValidar)
            Toast
                .makeText(baseContext
                    ,"El punto SI se encuentra dentro del polígono"
                    ,Toast.LENGTH_LONG).show()
        else
            Toast
                .makeText(baseContext
                    ,"El punto NO se encuentra dentro del polígono"
                    ,Toast.LENGTH_LONG).show()


    }
}