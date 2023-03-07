package com.example.e007_mistabs;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalcFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class CalcFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalcFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalcFragment newInstance(String param1, String param2) {
        CalcFragment fragment = new CalcFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public CalcFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // 6/03/2023
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista;
        EditText myedt1, myedt2;
        TextView mytv1;
        Button mySuma;

        vista = inflater.inflate(R.layout.fragment_calc, container, false);

        myedt1 = vista.findViewById(R.id.edt1);
        myedt2 = vista.findViewById(R.id.edt2);
        mytv1 = vista.findViewById(R.id.tv);
        mySuma = vista.findViewById(R.id.btn_result);

        mySuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Float n1, n2, n3;
                n1 = Float.valueOf(myedt1.getText().toString());
                n2 = Float.valueOf(myedt2.getText().toString());
                n3 = n1 + n2;
                mytv1.setText(n3.toString());
            }
        });

        return vista;

    }
}