package com.example.contracts;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    Button btnFinish;
    TextView tv;

    String output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnFinish = findViewById(R.id.btnFinish);
        tv = findViewById(R.id.tv);

        String firstName = getIntent().getStringExtra(SecondActivityParameters.EXTRA_FIRST_NAME);
        String secondName = getIntent().getStringExtra(SecondActivityParameters.EXTRA_SECOND_NAME);
        output = firstName + " " + secondName;
        tv.setText(output);

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SecondActivity.this, "Finish button clicked", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    public void finish() {
        Toast.makeText(SecondActivity.this, "output: " + output, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setData(Uri.parse(output));
        setResult(RESULT_OK, intent);
        super.finish();
    }

}
