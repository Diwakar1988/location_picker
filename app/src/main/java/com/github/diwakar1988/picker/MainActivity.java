package com.github.diwakar1988.picker;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final int RC_PERMISSIONS = 0X1F;
    private static final int PLACE_PICKER_REQUEST = 0X2F;

    private TextView tvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResults = (TextView) findViewById(R.id.tv_results);
        findViewById(R.id.btn_pick).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMaps();
            }
        });
    }
    private String[] anyPermissionsRequired() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        ArrayList<String> requiredPermissions = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            int permissionCheck = ContextCompat.checkSelfPermission(this,
                    permissions[i]);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                requiredPermissions.add(permissions[i]);
            }
        }
        permissions = new String[requiredPermissions.size()];
        for (int i = 0; i < requiredPermissions.size(); i++) {
            permissions[i] = requiredPermissions.get(i);
        }
        return permissions;
    }

    private void launchMaps() {

        //ask permission to launch map
        String[] requiredPermissions = anyPermissionsRequired();
        if (requiredPermissions.length == 0) {
            //no permissions required
            startPlacePicker(null);
        } else {
            ActivityCompat.requestPermissions(this,
                    requiredPermissions,
                    RC_PERMISSIONS);
        }

    }

    private void startPlacePicker(LatLng latLng) {
        try {
            PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
            if (latLng != null) {
                LatLngBounds.Builder b = LatLngBounds.builder();
                b.include(latLng);
                builder.setLatLngBounds(b.build());
            }
            startActivityForResult(builder.build(this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            setResults("Google PlayServices need to update, please update it from play store.");
        } catch (GooglePlayServicesNotAvailableException e) {
            setResults("Google PlayServices Not found, please install it from play store.");
        } catch (Exception e) {
            setResults("Oops, something went wrong. ERROR: " + e);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_PERMISSIONS) {
            boolean allAllowed = true;
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                    allAllowed = false;
                    break;
                }
            }
            if (allAllowed) {
                //all permissions granted now start app
                startPlacePicker(null);
            } else {
                setResults("Please grant LOCATION permissions and try again.");
            }
        }
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this,data);
                StringBuilder sb = new StringBuilder();
                if (place!=null){
                    sb.append("LAT = ");
                    sb.append(place.getLatLng().latitude);
                    sb.append("\n");
                    sb.append("LNG = ");
                    sb.append(place.getLatLng().longitude);
                    sb.append("\n");
                    sb.append("Address = ");
                    sb.append(place.getAddress());
                }else {
                    sb.append("Location not found.");
                }
                setResults(sb.toString());

            } else {
                setResults("No LOCATION picked.");
            }
        }
    }
    private void setResults(String msg) {
        this.tvResults.setText(msg);
    }
}
