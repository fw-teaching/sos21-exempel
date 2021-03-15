package fi.arcada.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText myNumber;
    TextView textViewOut;

    double myNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myNumber = findViewById(R.id.numberInput);
        textViewOut = findViewById(R.id.textView);

    }

    @SuppressLint("DefaultLocale")
    public void buttonClick(View view) {

        try {

            // Double.parseDouble kan ge en NumberFormatException-error och crasha appen
            // Genom att sätt in den i ett try/catch-block kan vi i stället behandla
            // errorn själv och förhindra en crash.
            myNum = Double.parseDouble(myNumber.getText().toString());
            textViewOut.setText(String.format("Du skrev: %.2f", myNum ));

        // i catch-uttrycket anger vi vilket felmeddelande vi vill fånga,
        // NumberFormatException i det här fallet
        } catch (NumberFormatException e) {

            // Här skriver vi den kod som ska köras om
            // vi fångar en error, i det här fallet vill vi bara skriva ett meddelande åt användaren
            textViewOut.setText("Du skrev inte ett tal!");

            // Hela felmeddelandet finns i variabeln e som definieras ovan i (NumberFormatException e),
            // den kan vi vid behov använda för att få alla detaljer om felet.
        }


    }

}