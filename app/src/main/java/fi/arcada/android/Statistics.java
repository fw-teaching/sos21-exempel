package fi.arcada.android;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Statistics {


    // en simpel metod för att sortera vår datamängd
    // Den här kan vi använda i andra metoder som behöver sorterade värden
    public static ArrayList<Double> getSorted(ArrayList<Double> dataValues) {
        // Skapa en kopia av datamängd-ArrayListen
        ArrayList<Double> sorted = new ArrayList<>(dataValues);
        Collections.sort(sorted);

        return sorted;
    }

    // Medelvärde
    public static Double calcAverage(ArrayList<Double> dataValues) {
        Double sum = 0.0;
        for (int i = 0; i < dataValues.size(); i++) {
            sum += dataValues.get(i);
        }
        return sum / dataValues.size();

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

    // Typvärde
    public static double calcMode(ArrayList<Double> dataValues) {
        //Skapa en HashMap (key/value-datastruktur) för räknaren
        HashMap<Double,Integer> valueCount = new HashMap<>();

        // Loopa igenom värdena och öka varje värdes count med 1
        for (Double dataValue : dataValues) {
            Integer count = valueCount.get(dataValue);

            // Lägg till 1 till nuvarande count för värdet
            // "ternary"-operation: om count är null, sätt till 0, annars använd count
            // Kolla t.ex. https://www.baeldung.com/java-ternary-operator
            valueCount.put(dataValue, (count == null ? 0 : count) +1);
        }

        int maxCount = 0;
        Double modeValue = 0.0;

        for (Double dataValue : valueCount.keySet()) {
            Integer curCount = valueCount.get(dataValue);
            if (curCount != null && curCount > maxCount) {
                maxCount = curCount; // t.ex. 5 st
                modeValue = dataValue; // t.ex. 8.0
            }
        }

        return modeValue;
    }

    // Standardavvikelse (Standard Deviation)
    public static Double calcSD(ArrayList<Double> dataValues) {

        double sumDiff = 0;
        double avg = calcAverage(dataValues);

        //Summan av de enskilda skillnaderna från medelvärdet i kvadrat:
        for (Double dataVal: dataValues) {
            sumDiff += Math.pow(dataVal - avg, 2);
        }

        // Kvadratroten av variansen delat med antalet värden
        return Math.sqrt(sumDiff / dataValues.size());

    }



}
