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
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movingAvg();

    }


    /** Att räkna glidande medelvärde i kod
     *
     */
    public void movingAvg() {
        // För exemplets skull deklarerar vi allt inom den här metoden, vi behöver inte
        // komma åt textViewMA utanför metoden, och vill hålla resten av koden städad
        TextView textViewMA;
        textViewMA = findViewById(R.id.textViewMA);
        // vår datamängd  0   1   2   3
        int[] dataset = {10, 22, 29, 2, 20, 41, 10, 33, 12, 24};
        // arrayList för glidande medelvärde
        ArrayList<Integer> ma = new ArrayList<>();
        ArrayList<Integer> betterMa = new ArrayList<>();

        int window = 4; // fönsterstorlek

        for (int i = window-1; i < dataset.length; i++) {
            // Ett sätt att göra det, men hur funkar den här metoden med större fönster
            // eller om vi vill ha en app där vi kan ändra fönsterstorlek?
            ma.add((dataset[i-0]
                    +dataset[i-1]
                    +dataset[i-2]
                    +dataset[i-3])/4);
        }

        // Mycket bättre sätt, nu kan vi enkelt använda olika fönsterstorlekar!
        for (int i = window-1; i < dataset.length; i++) {
            int sum = 0;
            // Inre loop för fönstret
            for (int j = 0; j < window; j++) {
                sum += dataset[i-j];
            }
            betterMa.add(sum / window);
        }

        textViewMA.setText(String.format("%s\n%s\n%s",
                Arrays.toString(dataset),
                ma.toString(),
                betterMa.toString()
        ));

    }


}