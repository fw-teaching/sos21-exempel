package fi.arcada.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // https://medium.com/@doyouseeitmyway/initialize-views-inside-of-oncreate-d72237b6f870
    EditText editTextName;
    EditText editTextNumberSigned;
    TextView textViewOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextNumberSigned = findViewById(R.id.editTextNumberSigned);
        textViewOut = findViewById(R.id.textViewOut);

    }

    public void calculate(View view) {

        String labelText = editTextName.getText().toString();
        double numberToCalculate = Double.parseDouble(editTextNumberSigned.getText().toString());

        textViewOut.setText(String.format("label är %s, siffran är %.2f", labelText, numberToCalculate));

    }
}