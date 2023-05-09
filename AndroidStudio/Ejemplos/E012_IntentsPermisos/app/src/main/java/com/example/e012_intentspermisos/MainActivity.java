package com.example.e012_intentspermisos;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    // 19/04/2023
    // UT6.2
    // Ver el AndroidManifest.xml
    private static final int CODIGO_PERMISOS_CAMARA = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ActivityResultLauncher<Intent> myActivityResultLauncher;
    ImageView imv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imv1 = findViewById(R.id.ImageView);

        myActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            Intent my_intent_vuelta = result.getData();
                            Bundle extras = my_intent_vuelta.getExtras();
                            Bitmap imageBitmap = (Bitmap) extras.get("data");
                            imv1.setImageBitmap(imageBitmap);
                        }
                        else if(result.getResultCode() == Activity.RESULT_CANCELED){
                            Log.i("Mensaje", "Ha fallado la camara");
                        }
                    }
                }
        );
    }


    // 19/04/2023
    // UT6.2
    // declararlos en el boton del activity_main.xml
    public void intentsImplicitas(View v){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:666666666"));
        startActivity(intent);
    }

    public void abrirWeb(View vista){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://iesclaradelrey.es"));
        startActivity(intent);
    }

    public void abrirCamara(View vista) {
        int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA);

        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            Log.i("Mensaje", "No se tiene permiso para la camara!");
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, CODIGO_PERMISOS_CAMARA);

        }
        else {
            llamarCamara();
        }
    }

    public void llamarCamara(){
        Intent myPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (myPhoto.resolveActivity(getPackageManager()) != null) {
            myActivityResultLauncher.launch(myPhoto);
        }
        else if(myPhoto.resolveActivity(getPackageManager()) == null){
            Log.i("Mensaje", "No hay Actividad para la camara");
        }
    }
}