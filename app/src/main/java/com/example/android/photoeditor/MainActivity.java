package com.example.android.photoeditor;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.gabrielbb.cutout.CutOut;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    Button removeBackgroundButton;
    ImageView savedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        savedImage=(ImageView) findViewById(R.id.imageView);
        removeBackgroundButton=(Button)findViewById(R.id.removeBackgroundButton);
        removeBackgroundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CutOut.activity().start(MainActivity.this);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CutOut.CUTOUT_ACTIVITY_REQUEST_CODE) {

            switch (resultCode) {
                case Activity.RESULT_OK:
                    Uri imageUri = CutOut.getUri(data);
                    savedImage.setImageURI(imageUri);
                    // Save the image using the returned Uri here
                    break;
                case CutOut.CUTOUT_ACTIVITY_RESULT_ERROR_CODE:
                    Exception ex = CutOut.getError(data);
                    break;
                default:
                    System.out.print("User cancelled the CutOut screen");
            }
        }
    }
}
