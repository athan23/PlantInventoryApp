package edu.calpoly.jgunawan.plantinventory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scanPlant(View view) {
        Intent intent = new Intent(this, PlantInformationActivity.class);
        startActivity(intent);
    }

    public void addPlant(View view) {
        Intent intent = new Intent(this, AddPlantActivity.class);
        startActivity(intent);
    }

    public void searchPlant(View view) {
        Intent intent = new Intent(this, SearchPlantActivity.class);
        startActivity(intent);
    }

    public void sendForm(View view) {
        Toast.makeText(this, "Excel datasheet successfully created!", Toast.LENGTH_LONG).show();
    }
}
