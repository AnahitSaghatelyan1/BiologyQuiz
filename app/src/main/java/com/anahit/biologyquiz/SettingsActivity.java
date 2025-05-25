package com.anahit.biologyquiz;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;
import android.widget.ImageButton;

public class SettingsActivity extends AppCompatActivity {

    private SwitchMaterial showHintsSwitch;
    private SwitchMaterial showExplanationsSwitch;
    private SwitchMaterial soundSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Add exit button click listener
        ImageButton exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> finish());

        initializeViews();
    }

    private void initializeViews() {
        showHintsSwitch = findViewById(R.id.showHintsSwitch);
        showExplanationsSwitch = findViewById(R.id.showExplanationsSwitch);
        soundSwitch = findViewById(R.id.soundSwitch);
    }
}