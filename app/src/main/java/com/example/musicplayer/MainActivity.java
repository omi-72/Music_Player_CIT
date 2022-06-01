package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Toast;
import android.widget.Toolbar;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimPerm();
    }

    private void runTimPerm() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // permission is granted, open the camera
                        Toast.makeText(MainActivity.this, "Permission Accepted", Toast.LENGTH_SHORT).show();
                        getData();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        // check for permanent denial of permission
                        if (response.isPermanentlyDenied()) {
                            // navigate user to app settings

                            Toast.makeText(MainActivity.this, "Permission Not Accepted", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    private void getData() {

        String[] projection = new String[]
                {
                        MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.DATE_MODIFIED,
                        MediaStore.Audio.Media.BUCKET_DISPLAY_NAME,
                        MediaStore.Audio.Media.DISPLAY_NAME

                };
        Uri contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
    }
}