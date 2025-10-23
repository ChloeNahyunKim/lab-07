package com.example.androiduitesting;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    public static final String EXTRA_CITY = "extra_city";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        String name = getIntent().getStringExtra(EXTRA_CITY);

        TextView cityText = findViewById(R.id.text_city);
        cityText.setText(name != null ? name : "");

        Button back = findViewById(R.id.button_back);
        back.setOnClickListener(v -> finish());  // return to MainActivity
    }
}