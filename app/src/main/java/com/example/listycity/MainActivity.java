package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    TextInputEditText textField;
    int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textField = findViewById(R.id.editText);
        cityList = findViewById(R.id.city_list);
        String[] cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Set global variable position to selected element
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                Toast.makeText(MainActivity.this, "Selected: " + dataList.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteCity(View view) {
        if (selectedPosition >= 0 && selectedPosition < dataList.size()) {
            dataList.remove(selectedPosition);
            cityAdapter.notifyDataSetChanged();
            selectedPosition = -1; // Reset selection
            Toast.makeText(this, "City Deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No City Selected", Toast.LENGTH_SHORT).show();
        }
    }

    public void addCity(View view) {
        // Check if the text in the TextInputEditText is null
        String input = textField.getText() != null ? textField.getText().toString().trim() : "";

        if (!input.isEmpty()) {
            dataList.add(input);
            cityAdapter.notifyDataSetChanged();
            textField.setText(""); // Clear the input field
            Toast.makeText(this, "City Added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Enter a valid city name", Toast.LENGTH_SHORT).show();
        }
    }

}
