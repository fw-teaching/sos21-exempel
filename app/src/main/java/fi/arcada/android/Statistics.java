package fi.arcada.android;

import java.util.ArrayList;
import java.util.Collections;

public class Statistics {


    public static Double calcAverage(ArrayList<Double> dataValues) {
        Double sum = 0.0;
        for (int i = 0; i < dataValues.size(); i++) {
            sum += dataValues.get(i);
        }
        return sum / dataValues.size();

    }

    // en simpel metod för att sortera vår datamängd
    // Den här kan vi använda i andra metoder som behöver sorterade värden
    public static ArrayList<Double> getSorted(ArrayList<Double> dataValues) {
        // Skapa en kopia av datamängd-ArrayListen
        ArrayList<Double> sorted = new ArrayList<>(dataValues);
        Collections.sort(sorted);

        return sorted;
    }

    // Median - mittersta värdet i en sorterad datamängd
    public static Double calcMedian(ArrayList<Double> dataValues) {
        ArrayList<Double> sortedSet = getSorted(dataValues);

        Double median;
        int mid = sortedSet.size()/2;

        if (sortedSet.size() % 2 == 0) {
            // Om antalet värden är jämnt, ta medelvädet av de två mittersta
            median = (sortedSet.get(mid-1)+sortedSet.get(mid))/2;
        } else {
            // Annars, returnera mittersta värdet
            median = sortedSet.get(mid);
        }

        return median;
    }

}
