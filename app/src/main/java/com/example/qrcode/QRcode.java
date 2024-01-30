package com.example.qrcode;

        import androidx.activity.result.ActivityResultLauncher;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import android.app.Instrumentation;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.google.zxing.integration.android.IntentIntegrator;
        import com.google.zxing.integration.android.IntentResult;
        import com.journeyapps.barcodescanner.CaptureActivity;
        import com.journeyapps.barcodescanner.ScanOptions;

public class QRcode extends AppCompatActivity {


    ImageView scan_btn;

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        scan_btn = findViewById(R.id.scanner);
        textView = findViewById(R.id.text);


        scan_btn = (ImageView) findViewById(R.id.scanner);
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(QRcode.this);
                intentIntegrator.setOrientationLocked(true);
                intentIntegrator.setPrompt("Scan a QR code");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.initiateScan();
                intentIntegrator.setOrientationLocked(true);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(intentResult != null)
        {
            String contents = intentResult.getContents();
            if(contents != null)
            {
                textView.setText(intentResult.getContents());
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }


    }
}