package fi.arcada.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // https://medium.com/@doyouseeitmyway/initialize-views-inside-of-oncreate-d72237b6f870
    EditText editTextName;
    EditText editTextNumberSigned;
    TextView textViewOut;
    TextView textViewAverageOut;

    ArrayList<Double> dataset = new ArrayList<>();
    ArrayList<String> dataLabels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextNumberSigned = findViewById(R.id.editTextNumberSigned);
        textViewOut = findViewById(R.id.textViewOut);
        textViewAverageOut = findViewById(R.id.textViewAverageOut);

    }

    public void calculate(View view) {

        String labelText = editTextName.getText().toString();
        double numberToCalculate = Double.parseDouble(editTextNumberSigned.getText().toString());

        editTextName.setText("");
        editTextNumberSigned.setText("");

        dataset.add(numberToCalculate);
        dataLabels.add(labelText);

        textViewOut.setText(String.format("datamängden:\n %s", dataset.toString()));

        // Medelvärde
        Double avg = Statistics.calcAverage(dataset);
        textViewAverageOut.setText(String.format("%.2f", avg));

    }
}