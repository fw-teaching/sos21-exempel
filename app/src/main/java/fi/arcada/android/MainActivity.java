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
            myNum = Double.parseDouble(myNumber.getText().toString());
            textViewOut.setText(String.format("Du skrev: %.2f", myNum ));

        } catch (NumberFormatException e) {

            textViewOut.setText("Du skrev inte ett tal!");
        }


    }

}