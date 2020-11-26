package com.aditya.mapdemo;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.aditya.mapdemo.Api.ApiClient;
import com.aditya.mapdemo.Api.ApiInterface;
import com.aditya.mapdemo.Api.users;
import com.aditya.mapdemo.model.Mapmodel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.function.Consumer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public static ApiInterface apiInterface;
    List<Mapmodel> maplist;
    Double lat;
    Double longs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Log.i("createmethod->","create call");
        apiInterface= ApiClient.getApiClient().create(ApiInterface.class);
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
        Call<users> mapcall=apiInterface.getNewarrival();
        mapcall.enqueue(new Callback<users>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<users> call, Response<users> response) {
                lat=Double.parseDouble(response.body().getNewarrival().get(1).getLatitude());
                longs=Double.parseDouble(response.body().getNewarrival().get(1).getLongitude());
                Log.i("lat->" + lat,"Longs->" + longs);
                LatLng sydney = new LatLng(lat, longs);

                mMap.addMarker(new MarkerOptions().position(sydney).title("Dont know"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            }

            @Override
            public void onFailure(Call<users> call, Throwable t) {

            }
        });
        Log.i("onmapready","mapreadycall");
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(19.075983, 72.877655);


    }
}