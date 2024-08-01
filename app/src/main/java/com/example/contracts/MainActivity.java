package com.example.contracts;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public static final int SECOND_ACTIVITY_RESULT_CODE = -1;
    Button btnSecondActivity, btnThirdActivity;
    ImageView ivMain;

    ActivityResultLauncher<Void> launcher;
    ActivityResultLauncher<SecondActivityParameters> secondActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSecondActivity = findViewById(R.id.btnSecondActivity);
        btnThirdActivity = findViewById(R.id.btnThirdActivity);
        ivMain = findViewById(R.id.ivMain);

        //Registering for Activity Results:
        // Initialize the class-level variables, not local ones
        //launcher: Uses ActivityResultContracts.TakePicturePreview to take a picture preview and set it to the ImageView.
        //secondActivityResultLauncher: Uses a custom contract (SecondActivityContract) to start SecondActivity
        // and handle the result by displaying a toast with the returned data.
        launcher = registerForActivityResult(new ActivityResultContracts.TakePicturePreview(), new ActivityResultCallback<Bitmap>() {
            @Override
            public void onActivityResult(Bitmap o) {
                ivMain.setImageBitmap(o);
            }
        });
//// Registering the launcher with the contract and callback
        secondActivityResultLauncher = registerForActivityResult(new SecondActivityContract(), new ActivityResultCallback<String>() {
            @Override
            public void onActivityResult(String output) {
                Toast.makeText(MainActivity.this, "Output: " + output, Toast.LENGTH_SHORT).show();
            }
        });
//This sets up a click listener for btnSecondActivity to launch SecondActivity with the parameters "Ali" and "Raza".
        // Set click listeners for buttons
        btnSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch SecondActivity with parameters
                secondActivityResultLauncher.launch(new SecondActivityParameters("Ali", "Raza"));
            }
        });
//click listener for camera preview
        btnThirdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Launch camera preview
                launcher.launch(null);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECOND_ACTIVITY_RESULT_CODE) {
            if (resultCode == RESULT_OK) {
                // Handle the result from SecondActivity if needed
            }
        }
    }
}


