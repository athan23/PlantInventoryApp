package edu.calpoly.jgunawan.plantinventory;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialAutoCompleteTextView;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.Collections;

public class AddPlantActivity extends AppCompatActivity {

    protected MaterialBetterSpinner availabilitySpinner;

    protected ArrayAdapter<String> availabilityAdapter;

    protected ArrayList<String> availabilityArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Resources resources = this.getResources();
        availabilityArrayList = new ArrayList<>();
        Collections.addAll(availabilityArrayList, resources.getStringArray(R.array.available_array));

        initSpinners();
    }

    public void addPlant(View view) {

        // Add Plant to Excel code here

        Toast.makeText(this, "Successfully added the new plant", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void initSpinners() {
        availabilitySpinner = (MaterialBetterSpinner) findViewById(R.id.availabilityAddPlantSpinner);
        //saleableSpinner = (MaterialBetterSpinner) findViewById(R.id.saleableAddPlantSpinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        availabilityAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, availabilityArrayList);

        // Specify the layout to use when the list of choices appears
        availabilitySpinner.setAdapter(availabilityAdapter);
        availabilitySpinner.setFloatingLabel(MaterialAutoCompleteTextView.FLOATING_LABEL_NORMAL);
        availabilitySpinner.setFloatingLabelTextSize((int) getResources().getDimension(R.dimen.text_size));
        availabilitySpinner.setFloatingLabelText(getResources().getString(R.string.availability));

        availabilitySpinner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("Text", availabilitySpinner.getText().toString());

                if (availabilitySpinner.getText().toString().equals("Other")) {
                    final EditText input = new EditText(AddPlantActivity.this);

                    new AlertDialog.Builder(AddPlantActivity.this)
                            .setMessage("Enter your Point here")
                            .setView(input)
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Editable editable = input.getText();

                                    availabilityArrayList.add(editable.toString());

                                    ArrayList<String> newAvailabilityList =
                                            new ArrayList<String>(availabilityArrayList);

                                    availabilityAdapter.clear();
                                    availabilityAdapter.addAll(newAvailabilityList);
                                    availabilityAdapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // Do nothing.
                                }
                            }).show();
                }
            }
        });
    }
}
