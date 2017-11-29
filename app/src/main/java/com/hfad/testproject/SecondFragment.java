package com.hfad.testproject;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import java.util.zip.Inflater;

import static android.support.v4.content.PermissionChecker.checkSelfPermission;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {
 ImageView image;
 SeekBar seekbar;
 final int permission_Id =20;

 int[] pictures={android.R.drawable.btn_default, android.R.drawable.arrow_up_float, android.R.drawable.btn_star, android.R.drawable.ic_media_play, android.R.drawable.button_onoff_indicator_on, android.R.drawable.gallery_thumb, android.R.drawable.ic_lock_idle_low_battery, android.R.drawable.picture_frame, android.R.drawable.ic_menu_my_calendar, android.R.drawable.divider_horizontal_bright, android.R.drawable.ic_delete};


    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_second, container, false);
        image=(ImageView)view.findViewById(R.id.image_view);
        seekbar=(SeekBar)view.findViewById(R.id.seek_bar);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //Check if permission is granted
                if (checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[] {Manifest.permission.CAMERA}, permission_Id);
                }
                else Toast.makeText(getContext(), "Permission is already granted.", Toast.LENGTH_SHORT).show();

                // If Permission is Granted display images:

                if(checkSelfPermission(getContext(), Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED) {
                    int i=progress%10;
                        image.setImageResource(pictures[i]);
                    }
                else
                {
                    if(shouldShowRequestPermissionRationale( Manifest.permission.ACCESS_FINE_LOCATION))
                    {
                        Toast.makeText(getContext(), "permission is disabled", Toast.LENGTH_SHORT).show();

                    }
                    else requestPermissions(new String[]{Manifest.permission.CAMERA}, permission_Id);
                }


            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return view;
    }

    //Now when the user allow or deny the permission then response will be check via onRequestPermissionResult()


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case permission_Id: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "permission is granted", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "permission is denied", Toast.LENGTH_SHORT).show();
                return;
            }
        }
    }
}
