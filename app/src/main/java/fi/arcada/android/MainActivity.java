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

    /**
     *    DETTA GICKS IGENOM PÅ NYTT PÅ LEKTION 7, finns i slutet av lektionsvideon!
     */

    // Vi deklarerar ett GraphView-objekt
    // OBS för att detta ska funka måste vi lägga till biblioteket i app/build.gradle (Module)!
    // ( implementation 'com.jjoe64:graphview:4.2.2' )
    GraphView graph;

    // En exempeldatamängd
    // Med Arrays.asList() kan vi initialisera en Arraylist med flera värden på en gång
    // (samma idé som: int[] dataset = {10, 22, 29, 2, 20, 41, 10, 33, 12, 24};
    ArrayList<Double> graphData = new ArrayList<>(
            Arrays.asList(10.0, 22.0, 29.0, 2.0, 20.0, 41.0, 10.0, 33.0, 12.0, 24.0)
    );
    // En 3 element kortare exempeldatamängd
    // (för att simulera en kortare datamängd som vi skulle få t.ex. när vi räknar glidande medelvärde...)
    ArrayList<Double> graphDataShorter = new ArrayList<>(
            Arrays.asList(10.0, 22.0, 29.0, 2.0, 20.0, 41.0, 10.0)
    );



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisera graph med rätt View-objekt
        graph = findViewById(R.id.graph);

        // Kör buildGraph()-metoden
        buildGraph(graph, graphData);

        // Kör uträkningen för glidande medelvärde
        movingAvg();
    }

    /** Metod som tar emot ett GraphView-objekt och en datamängd
     */
    public void buildGraph(GraphView graph, ArrayList<Double> dataset) {

        // Olika inställningar, kan lämnas bort eller utökas med flera
        // Kolla dokumentationen: https://github.com/jjoe64/GraphView/wiki
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(60);

        // Skapa en dataserie (alltså en linje för diagrammet)
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();



        // Vi loopar igenom vår datamängd och lägger varje datapunkt till serien
        for (int i = 0; i < dataset.size(); i++) {
            // Datapoint tar parametrar för X och Y: Datapoint(x-värde, y-värde)
            series.appendData(new DataPoint(i, dataset.get(i)),true, dataset.size());
        }


        // Vi skapar en dataserie till för vår kortare datamängd
        LineGraphSeries<DataPoint> otherSeries = new LineGraphSeries<>();
        // Loopa vår kortare datamängd
        otherSeries.setColor(Color.GREEN);

        for (int i = 0; i < graphDataShorter.size(); i++) {

            // Om vi vill att vår kortare datamänd ska gå ändra till X-axelns slut
            // och i stället inte visa någonting i börejan (som t.ex. glidande medelvärde skulle bete sig)
            // så måste vi "knuffa framåt" vår datamängd.
            // I det här fallet väljer jag att räkna ut skillnaden och spara i "int offset",
            int offset = dataset.size() - graphDataShorter.size(); // = 3 i det här fallet

            // I new DataPoint(x, y) lägger vi nu till skillnaden (i+offset), så då kommer den här linjen att flyttas framåt
            // Första punkten i serien skulle alltså då vara DataPoint(0+3, 2.0) om offset = 3
            otherSeries.appendData(new DataPoint(i+offset, graphDataShorter.get(i)),true, dataset.size());
        }

        // Töm grafen från eventuella tidigare linjer (viktigt om vi vill t.ex. "refresha" grafen med ny data!)
        graph.removeAllSeries();

        // Slutligen lägger vi till dataserierna till vår graf!
        graph.addSeries(series);
        graph.addSeries(otherSeries);

    }

    /** Att räkna glidande medelvärde i kod
     *
     * OBS: Detta exempel går inte att använda direkt i Projekt 3, men logiken är samma.
     *      (t.ex. utgår vi ju i projektet inte från en int-array utan en ArrayList<Double>
     */
    public void movingAvg() {
        // För exemplets skull deklarerar vi allt inom den här metoden, vi behöver inte
        // komma åt textViewMA utanför metoden, och vill hålla resten av koden städad
        TextView textViewMA;
        textViewMA = findViewById(R.id.textViewMA);

        // vår datamängd  0   1   2   3
        int[] dataset = {10, 22, 29, 2, 20, 41, 10, 33, 12, 24};

        // ArrayLists för glidande medelvärde
        ArrayList<Integer> ma = new ArrayList<>();
        ArrayList<Integer> betterMa = new ArrayList<>();

        int window = 4; // fönsterstorlek


        // Vi loopar igenom börjandes från det första värdet där alla tidigare
        // värden ryms med i samma dataset, dvs fjärde värdet (index 3) om vårt fönster är 4
        /*
        for (int i = window-1; i < dataset.length; i++) {
            // Ett sätt att göra det, men hur funkar den här metoden med större fönster
            // eller om vi vill ha en app där vi kan ändra fönsterstorlek?
            ma.add((dataset[i]
                    +dataset[i-1]
                    +dataset[i-2]
                    +dataset[i-3])/4);
        }*/

        // Mycket bättre sätt, nu kan vi enkelt använda olika fönsterstorlekar!
        for (int i = window-1; i < dataset.length; i++) {
            // Variabel för summan
            int sum = 0;
            // Inre loop för alla värden som hör till fönstret

            for (int j = 0; j < window; j++) {
                // Gå j steg bakåt och addera det värdet till summan
                sum += dataset[i-j];
            }
            // dividera summan med antalet värden (fönstretsa storlek)
            // Lägg till ArrayListen (sen fortsätter yttre loopen till nästa värde)
            betterMa.add(sum / window);
        }

        // Vi dumpar ut vår datamängd och våra MA
        textViewMA.setText(String.format("%s\n%s\n%s",
                Arrays.toString(dataset),
                ma.toString(),
                betterMa.toString()
        ));
    }


}