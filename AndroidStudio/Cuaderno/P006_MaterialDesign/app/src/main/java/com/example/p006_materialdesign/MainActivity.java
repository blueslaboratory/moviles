package com.example.p006_materialdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button myPSP, myPMDM, myPSP_PMDM;
    TextView mytv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mytv = (TextView) findViewById(R.id.tv);

        myPSP = (Button) findViewById(R.id.btnPSP);
        myPMDM = (Button) findViewById(R.id.btnPMDM);
        myPSP_PMDM = (Button) findViewById(R.id.btnPSP_PMDM);

        myPSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytv.setText("Me encanta PSP");
            }
        });

        myPMDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytv.setText("Me encanta PMDM");
            }
        });

        myPSP_PMDM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytv.setText("Adoro PSP+PMDM");
            }
        });

    }
}