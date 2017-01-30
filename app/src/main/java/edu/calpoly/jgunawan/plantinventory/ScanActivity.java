package edu.calpoly.jgunawan.plantinventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScanActivity extends AppCompatActivity {

    protected TextView mBarcodeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        mBarcodeTextView = (TextView) findViewById(R.id.barcodeTextView);
    }
}
