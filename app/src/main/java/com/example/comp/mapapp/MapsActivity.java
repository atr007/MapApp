package com.example.comp.mapapp;

import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements
        OnMapReadyCallback,
        GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private double yPosition;
    private double xPosition;
    private LatLng startPos;

    private Marker mSydney;
    private Marker mBrisbane;
    private Marker mPerth;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        startPos = new LatLng(0, 0);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        startPos = new LatLng(savedInstanceState.getDouble("yPosition"),
                savedInstanceState.getDouble("xPosition"));
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        startPos = mMap.getCameraPosition().target;
        yPosition = startPos.latitude;
        xPosition = startPos.longitude;
        savedInstanceState.putDouble("yPosition", yPosition);
        savedInstanceState.putDouble("xPosition", xPosition);
        super.onSaveInstanceState(savedInstanceState);
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
        mMap.moveCamera(CameraUpdateFactory.newLatLng(startPos));
        loadMapMarkers();
        mMap.setOnMarkerClickListener(this);
    }

    public void loadMapMarkers(){
        LatLng sydney = new LatLng(-34, 151);
        LatLng perth = new LatLng(-31.952854, 115.857342);
        LatLng brisbane = new LatLng(-27.47093, 153.0235);
        mSydney = mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mPerth = mMap.addMarker(new MarkerOptions().position(perth).title("Marker in Perth"));
        mBrisbane = mMap.addMarker(new MarkerOptions().position(brisbane).title("Marker in Brisbane"));
        mSydney.setTag("Сидней");
        mPerth.setTag("Перт");
        mBrisbane.setTag("Брисбен");
    }

    public boolean onMarkerClick(Marker marker){
        Toast.makeText(this, marker.getTitle() + " " + marker.getTag(), Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Maps Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
