package fi.arcada.android;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    TextView textViewCalcOut;

    ArrayList<Double> dataset;
    ArrayList<String> dataLabels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextNumberSigned = findViewById(R.id.editTextNumberSigned);
        textViewOut = findViewById(R.id.textViewOut);

        initData();

        //En "slarvig" samlingsview för all vår output
        textViewCalcOut = findViewById(R.id.textViewCalcOut);

    }

    @SuppressLint("DefaultLocale")
    public void calculate(View view) {

        String labelText = editTextName.getText().toString();
        double numberToCalculate = Double.parseDouble(
                editTextNumberSigned.getText().toString()
        );

        editTextName.setText("");
        editTextNumberSigned.setText("");

        dataset.add(numberToCalculate);
        dataLabels.add(labelText);

        // Visa vår nuvarande datamängd
        textViewOut.setText(String.format("datamängden:\n %s", dataset.toString()));

        // Vi använder String.format för att skriva ut alla våra uträkningar i vår View
        textViewCalcOut.setText(String.format("%s: %.2f\n%s: %.2f\n%s: %.2f",
                "Medelvärde",
                Statistics.calcAverage(dataset),
                "Medianvärde",
                Statistics.calcMedian(dataset),
                "Standardavvikelse",
                Statistics.calcSD(dataset)
        ));
    }

    // Körs när man trycker på Reset
    public void resetView(View view) {
        // Töm datamängdsfältet
        textViewOut.setText("");
        // Töm resultaten
        textViewCalcOut.setText("");
        // Nollställ datamängden
        initData();
    }

    // Initialiserar (och nollställer) vår datamängd
    public void initData() {
        dataset = new ArrayList<>();
        dataLabels = new ArrayList<>();
    }


}