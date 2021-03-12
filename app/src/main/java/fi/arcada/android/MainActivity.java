package fi.arcada.android;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    GraphView graph;
    ArrayList<Double> graphData = new ArrayList<>(
            Arrays.asList(10.0, 22.0, 29.0, 2.0, 20.0, 41.0, 10.0, 33.0, 12.0, 24.0)
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graph = findViewById(R.id.graph);

        buildGraph(graph, graphData);

        movingAvg();

    }


    public void buildGraph(GraphView graph, ArrayList<Double> dataset) {

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(60);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        LineGraphSeries<DataPoint> otherSeries = new LineGraphSeries<>();
        otherSeries.setColor(Color.GREEN);


        for (int i = 0; i < dataset.size(); i++) {
            series.appendData(new DataPoint(i, dataset.get(i)),true, dataset.size());
        }

        for (int i = 0; i < dataset.size(); i++) {
            otherSeries.appendData(new DataPoint(i, dataset.get(i)+5),true, dataset.size());
        }

        graph.addSeries(series);
        graph.addSeries(otherSeries);

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
            ma.add((dataset[i]
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