package com.example.indianspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

// Complete Tutorial | Create State-District Dependent (Nested) Spinner | DropDown List | Android Java

public class MainActivity extends AppCompatActivity {

    // Vars to hold the values of selected State and District
    private String selectedState, selectedDistrict;
    // Declaring TextView to show errors
    private TextView tvStateSpinner, tvDistrictSpinner;

    private Spinner stateSpinner, districtSpinner;

    // The adapter acts as a bridge between the UI Component and the Data Source
    // It converts data from the data sources into view items that can be displayed into the UI Component
    // Data Source: Arrays, HashMap, Database...
    // Components: ListView, GridView, Spinner
    // Only declaration (no definition)
    private ArrayAdapter<CharSequence> stateAdapter, districtAdapter;

    private Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // State Spinner Initialization
        stateSpinner = findViewById(R.id.spinner_indian_states);

        // Populate ArrayAdapter using string array and a spinner layout that we will define
        // * this can be used to refer current class instance variable
        // * this can be used to invoke current class method (implicitly)
        // * this can be passed as an argument in the method call
        // * this can be used to return the current class instance from the method
        stateAdapter = ArrayAdapter.createFromResource(this, R.array.array_indian_states, R.layout.spinner_layout);

        // Specify the layout to use when the list of choices appear
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the spinner to populate the state spinner
        stateSpinner.setAdapter(stateAdapter);

        // When any item of the stateSpinner is selected
        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Define City Spinner but we will populate the options through the selected State
                districtSpinner = findViewById(R.id.spinner_indian_districts);

                // Obtain the selectedTopicOption
                selectedState = stateSpinner.getSelectedItem().toString();

                int parentID = parent.getId();
                if (parentID == R.id.spinner_indian_states) {
                    // createFromResource(Context context, int textArrayResId, int textViewResid)
                    // Creates a new ArrayAdapter from external resources

                    // Fragments do not really have a context. When working in a fragment and you need a context,
                    // normally you need to call getActivity(). In this specific case, the context
                    // is passed down from the activity to the fragment to the AdapterView
                    // remember - a view takes a contex in it's constructor - and so when you call getContext()
                    // it returns the activity

                    switch (selectedState) {
                        case "Select Your State": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_default_districts, R.layout.spinner_layout);
                        break;
                        case "Bihar": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_bihar_districts, R.layout.spinner_layout);
                        break;
                        case "Chandigarh": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_chandigarh_districts, R.layout.spinner_layout);
                        break;
                        case "Delhi": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_delhi_districts, R.layout.spinner_layout);
                        break;
                        case "Goa": districtAdapter = ArrayAdapter.createFromResource(parent.getContext(),
                                R.array.array_goa_districts, R.layout.spinner_layout);
                        break;
                        default: break;
                    }

                    // Specify the layout to use when the list of choices appears
                    districtAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // Populate the Districts according to the Selected State
                    districtSpinner.setAdapter(districtAdapter);

                    // Obtain the selected District from the districtSpinner
                    districtSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            selectedDistrict = districtSpinner.getSelectedItem().toString();
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // Button Submit y errores
        submitButton = findViewById(R.id.button_submit);
        tvStateSpinner = findViewById(R.id.textView_indian_states);
        tvDistrictSpinner = findViewById(R.id.textView_indian_districts);


        // Case 1: no state was selected
        // Action: show error on textview of stateSpinner and display a toast message asking
        //         the user to select a state from the spinner list
        // Case 2: state was selected but no district was selected
        // Action: show error on textview of districtSpinner and display a Toast message asking
        //         the user to select a district from the Spinner List.
        //         Remove error from TextView of stateSpinner
        // Case 3: State & District were selected
        //         But no District was Selected
        // Action: display a toast message showing the selected state and district
        //         remove error from TextView of stateSpinner & districtSpinner

        submitButton.setOnClickListener(v -> {
            // case 1
            if(selectedState.equals("Select Your State")){
                Toast.makeText(MainActivity.this, "Please select your state", Toast.LENGTH_LONG).show();
                // Set error when submit is pressed without any state
                tvStateSpinner.setError("State is required!");
                tvStateSpinner.requestFocus();

            }
            // case 2
            else if(selectedDistrict.equals("Select Your District")){
                Toast.makeText(MainActivity.this, "Please select your district", Toast.LENGTH_LONG).show();
                tvDistrictSpinner.setError("District is required!");
                tvDistrictSpinner.requestFocus();
                // Remove error from TextView of State
                tvStateSpinner.setError(null);
            }
            // case 3
            else{
                tvStateSpinner.setError(null);
                tvDistrictSpinner.setError(null);
                Toast.makeText(MainActivity.this,
                          "\nSelected State: " +selectedState
                              +"\nSelectedDistrict" +selectedDistrict,
                               Toast.LENGTH_LONG).show();
            }
        });
    }
}