package com.example.e013_hilos;

import android.content.Context;
import android.widget.ProgressBar;
import android.widget.Toast;

public class Contador extends Thread{
    int duracion;
    ProgressBar mypb;
    Context micontexto;

    public Contador (ProgressBar pb, int duracion){
        this.duracion = duracion;
        this.mypb = pb;
        this.mypb.setMax(this.duracion );
    }

    public void run(){
        this.mypb.setProgress(0);

        for (int i=0; i<this.duracion; i++){
            try {
                this.sleep(1000);
            } catch(InterruptedException e) {
                return;
            }
            this.mypb.incrementProgressBy(1);
        }
    }
}
