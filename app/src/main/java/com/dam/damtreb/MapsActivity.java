package com.dam.damtreb;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static final int CHECK = -1;

    private GoogleMap mMap;
    private Marker miUbicacion;
    private  Marker ubicacionDestino;
    private LatLng myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        actualizarMapa();
        // marcador en ubicacion actual
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        Intent i1 = getIntent();
        if (i1.getBooleanExtra("ver", false)){
LatLng destino = new LatLng(i1
.getDoubleExtra("d_lat", 0.0),i1.getDoubleExtra("d_lng", 0.0));
            ubicacionDestino = mMap.addMarker(new MarkerOptions().position(destino)
.title("Ubicación destino").draggable(true)
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

        }
        else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                LocationServices.getFusedLocationProviderClient(this).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Toast.makeText(MapsActivity.this, "name: " + location.getProvider(), Toast.LENGTH_SHORT).show();

                        Intent data = new Intent(MapsActivity.this, LocationActivity.class);
                        data.putExtra("latitude", location.getLatitude());
                        data.putExtra("longitude", location.getLongitude());
                        setResult(RESULT_OK, data);
                        finish();

                    }
                });
            }
        }//cierra else
/*
        //LatLng sydney = new LatLng(-32.9590056, -60.6409738);
ubicacion = mMap.addMarker(new MarkerOptions().position(myLocation)
        .title("Mi ubicacion ")
        .draggable(true)
.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));



        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocation));

mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
    @Override
    public boolean onMarkerClick(Marker marker) {
if (marker.equals(ubicacion)){
    Intent data = new Intent(MapsActivity.this, LocationActivity.class);
    data.putExtra("latitude", marker.getPosition().latitude);
    data.putExtra("longitude", marker.getPosition().longitude);
    setResult(RESULT_OK, data);
    finish();
}
        return false;
    }
});
*/
    }

    public void actualizarMapa() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 9999);
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setCompassEnabled(true);
            mMap.getUiSettings().setZoomControlsEnabled(true);
            mMap.getUiSettings().setTiltGesturesEnabled(true);

            return;
        }

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setCompassEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setTiltGesturesEnabled(true);

    }
}

