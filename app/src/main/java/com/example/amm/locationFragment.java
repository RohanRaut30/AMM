package com.example.amm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class locationFragment extends Fragment {

    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_location, container, false);

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap map) {
                googleMap = map;

                LatLng location = new LatLng(18.511712685704023, 73.8561904856803);
                MarkerOptions markerOptions = new MarkerOptions()
                        .position(location)
                        .title(getString(R.string.shri_sharada_ganesh_mandir_mandai_ganpati))
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                        .anchor(0.5f, 0.5f) // Center the marker icon
                        .alpha(0.8f); // Adjust the transparency

                googleMap.addMarker(markerOptions);

                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 16f));
            }
        });

        return root;
    }
}
