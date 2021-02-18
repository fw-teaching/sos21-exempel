package fi.arcada.android;

import java.util.ArrayList;

public class Statistics {


    public static Double calcAverage(ArrayList<Double> dataValues) {

        Double sum = 0.0;

        for (int i = 0; i < dataValues.size(); i++) {
            sum += dataValues.get(i);
        }


        return sum / dataValues.size();

    }

}
