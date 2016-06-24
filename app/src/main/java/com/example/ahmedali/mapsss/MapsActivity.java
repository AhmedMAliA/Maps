package com.example.ahmedali.mapsss;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        tv= (TextView) findViewById(R.id.texvt);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    public void change_map(View view)
    {
        if(mMap.getMapType() == GoogleMap.MAP_TYPE_NORMAL)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

    public void on_search(View view)
    {
        EditText e= (EditText) findViewById(R.id.edit);
        String str= e.getText().toString();
        List<Address> getadd=null;
        Geocoder geocoder =new Geocoder(this);
        try {
            getadd=geocoder.getFromLocationName(str,1);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        LatLng latandLng = new LatLng(getadd.get(0).getLatitude(),getadd.get(0).getLongitude());
        mMap.addMarker(new MarkerOptions().position(latandLng).title("Marker"));
        mMap.animateCamera(CameraUpdateFactory.newLatLng(latandLng));

    }


    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }


    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.setMyLocationEnabled(true);

        // class to get alt
     /*   LocationManager locman= (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria cri=new Criteria();

       Location loc= locman.getLastKnownLocation(locman.getBestProvider(cri,false));

        double lat=loc.getLatitude();
        double longt=loc.getLongitude();

        List<Address> alladdress= null;

        Geocoder geo = new Geocoder(this);
        try {
            alladdress = geo.getFromLocation(lat,longt,1);

        } catch (IOException e) {
            e.printStackTrace();
        }

        String str=alladdress.get(0).getAddressLine(0);
        String str1=alladdress.get(0).getAddressLine(1);



        tv.setText(str+" "+str1); */






    }
}
