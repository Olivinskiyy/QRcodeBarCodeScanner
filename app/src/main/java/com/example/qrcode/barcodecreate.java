package com.example.qrcode;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class barcodecreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcodecreate);

        EditText editText = findViewById(R.id.edit_text);
        Button button = findViewById(R.id.button);
        ImageView imageView = findViewById(R.id.barcode_image);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(editText.getText().toString(), BarcodeFormat.CODE_128, 600, 300);

                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

                    imageView.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
