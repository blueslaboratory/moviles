package com.example.p003_navigationdrawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;


public class FragmentWeb extends Fragment {


    public FragmentWeb() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista;
        WebView myWeb;

        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_web, container, false);
        myWeb = vista.findViewById(R.id.webView);
        myWeb.loadUrl("https://iesclaradelrey.es/portal/index.php/es");

        return vista;
    }
}