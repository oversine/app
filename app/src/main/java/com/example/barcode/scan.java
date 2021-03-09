package com.example.barcode;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class scan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         IntentIntegrator brScan;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);

        brScan = new IntentIntegrator(this);
        brScan.setOrientationLocked(false);
        brScan.setPrompt("바코드를 화면에 인식시켜 주세요.");
        brScan.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "다시 실행하여 주십시오.", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(this, barcode_res.class);
                intent.putExtra("바코드값", result.getContents());
                startActivity(intent);
            }
            finish();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}