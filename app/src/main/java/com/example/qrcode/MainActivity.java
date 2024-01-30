package com.example.qrcode;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Instrumentation;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {



    private static final String PREFS_NAME = "MyPrefs";
    private static final String THEME_KEY = "themeKey";
    private static final int THEME_LIGHT = 0;
    private static final int THEME_DARK = 1;
    Button qrcode;

    TextView textView;
    Button barcode;
    Button qrcodecreate;

    Button barcodecreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setAppTheme();

        Button buttonChangeBackground = findViewById(R.id.buttonChangeBackground);
        buttonChangeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentTheme = getCurrentTheme();
                if (currentTheme == THEME_LIGHT) {
                    // Устанавливаем черный цвет фона
                    setAppTheme(THEME_DARK);
                } else {
                    // Устанавливаем белый цвет фона
                    setAppTheme(THEME_LIGHT);
                }
                recreate();
            }
        });

        qrcode = findViewById(R.id.qrcode);
        barcode = findViewById(R.id.barcode);
        qrcodecreate = findViewById(R.id.qrcodecreate);
        barcodecreate = findViewById(R.id.barcodecreate);

        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, QRcode.class));
        }
        });
        barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, barcode.class));
            }
        });
        qrcodecreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, qrcodecreate.class));
            }
        });
        barcodecreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, barcodecreate.class));
            }
        });
    }

    private void setAppTheme() {
        int theme = getCurrentTheme();
        if (theme == THEME_DARK) {
            setTheme(R.style.Theme_QRcode);
        } else {
            setTheme(R.style.Theme_QRcode);
        }
        updateBackgroundColor();
    }

    private void updateBackgroundColor() {
        int theme = getCurrentTheme();
        int colorResId = (theme == THEME_DARK) ? R.color.black : R.color.white;
        RelativeLayout rootView = findViewById(R.id.rootView);
        if (rootView != null) {
            rootView.setBackgroundColor(getResources().getColor(colorResId));
        }
    }

    private void setAppTheme(int theme) {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(THEME_KEY, theme);
        editor.apply();
    }

    private int getCurrentTheme() {
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        return prefs.getInt(THEME_KEY, THEME_LIGHT);
    }
}