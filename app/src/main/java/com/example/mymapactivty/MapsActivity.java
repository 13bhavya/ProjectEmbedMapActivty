package com.example.mymapactivty;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Spinner spinnerStore;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    private static final String TAG = "MapsActivity";
    private Context context;
    public static String MapArea = "Markham";
    public static String Store;
    public static String City;

    EditText mSearchText;

    public MapsActivity(Context ctx) {
        context = ctx;
    }

    public MapsActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mSearchText = findViewById(R.id.input_search);

        spinnerStore = findViewById(R.id.spinner_store);
        ArrayList<String> arraylist = new ArrayList<>();
        arraylist.add("Walmart");
        arraylist.add("Longos");
        arraylist.add("Freshco");
        arraylist.add("Cosco");
        arraylist.add("No-Frills");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.select_dialog_item, arraylist);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStore.setAdapter(arrayAdapter);
        spinnerStore.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String Storeselect = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), Storeselect, Toast.LENGTH_LONG).show();
                //onMapReady(Storeselect);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mapFragment.getMapAsync(this);
    }

    private void init(){
        Log.d(TAG, "init");

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        || event.getAction() == KeyEvent.KEYCODE_ENTER){

                    //execute our method for searching
                    geoLocate();
                }
                return false;
            }
        });
    }

    private void geoLocate(){
        Log.d(TAG, "geolocate:");

        String searchString = mSearchText.getText().toString();
        Geocoder geocoder = new Geocoder(MapsActivity.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchString, 1);
        } catch (IOException e){
            Log.e(TAG, "geoLocate: 11" + e.getMessage());
        }

        if (list.size()> 0) {
            Address address = list.get(0);

            Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();
            Log.d(TAG,"geoLocate: found a location: " + address.toString());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        //enableMyLocation();

        final Geocoder coder = new Geocoder(getApplicationContext());

        String placeName = MapArea;

        Toast.makeText(getApplicationContext(), MapArea, Toast.LENGTH_LONG).show();

        //mMap.clear();

        init();
        // Add a marker in Sydney and move the camera
       /* try {
            //using geo coder object getting maximum 5 addresses for given place name / address
            List<Address> geocodeResults = coder.getFromLocationName(placeName, 3);
            Iterator<Address> locations = geocodeResults.iterator();

            // get location names and relative address and show them on google map
            String locInfo = "";
            while (locations.hasNext()) {
                Address loc = locations.next();

                LatLng store = new LatLng(loc.getLatitude(), loc.getLongitude());
                int addIdx = loc.getMaxAddressLineIndex();
                for (int idx = 0; idx <= addIdx; idx++) {
                    String addLine = loc.getAddressLine(idx);
                    locInfo = String.format("%s", addLine);
                }

                MarkerOptions markerOptions = new MarkerOptions();
                Marker m1 = mMap.addMarker(new MarkerOptions().
                        position(store)
                        .snippet("")
                        .title(locInfo));

                InfoWindowData info = new InfoWindowData();
                info.setImg("ccc");
                info.setStore("");
                info.setAddress(locInfo);
                //info.setCity(City);
                info.setPhone("986 545 8532");

                CustomInfoWindowGoogleMap customInfoWindow = new CustomInfoWindowGoogleMap(this);
                mMap.setInfoWindowAdapter(customInfoWindow);

                m1.setTag(info);
                m1.showInfoWindow();
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(store, 12f));
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Failed to get location info " + e, Toast.LENGTH_LONG).show();
        }*/
    }

    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        }
    }
}
