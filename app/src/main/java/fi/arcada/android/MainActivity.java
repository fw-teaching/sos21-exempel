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

    // https://medium.com/@doyouseeitmyway/initialize-views-inside-of-oncreate-d72237b6f870
    EditText editTextName;
    EditText editTextAge;
    TextView textViewOut;
    TextView textViewCalcOut;
    TextView errorOut;

    RecyclerView recyclerViewPersons;
    PersonsAdapter personsAdapter;

    ArrayList<Person> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.editTextName);
        editTextAge = findViewById(R.id.editTextAge);
        textViewOut = findViewById(R.id.textViewOut);
        errorOut = findViewById(R.id.errorOutput);
        textViewCalcOut = findViewById(R.id.textViewCalcOut);

        recyclerViewPersons = findViewById(R.id.recyclerViewPersons);

        personsAdapter = new PersonsAdapter(this, persons);
        recyclerViewPersons.setAdapter(personsAdapter);
        recyclerViewPersons.setLayoutManager(new LinearLayoutManager(this));

    }

    public void addPerson(View view) {

        errorOut.setText("");

        if (TextUtils.isEmpty(editTextName.getText()) || TextUtils.isEmpty(editTextAge.getText())) {

            Toast message = Toast.makeText(this, "Fyll i båda fälten!", Toast.LENGTH_SHORT);
            message.setGravity(Gravity.TOP, 0, 100);
            message.show();

            // Alternativt sätt att visa felmeddelandet
            errorOut.setText("Fyll i båda fälten!");

        } else {

            String personName = editTextName.getText().toString();
            int personAge =  Integer.parseInt(editTextAge.getText().toString());

            editTextName.setText("");
            editTextAge.setText("");

            //typ   variabel new  klasskonstruktor
            Person person = new Person(personName, personAge);
            persons.add(person);

            // Kortare sätt att göra samma sak:
            //persons.add(new Person(personName, personAge));
        }


    }

    @SuppressLint("DefaultLocale")
    public void calculate(View view) {

        ArrayList<Double> dataset = new ArrayList<>();

        for (Person person : persons) {
            dataset.add((double) person.getAge());
        }

        // Visa vår nuvarande datamängd
        textViewOut.setText(String.format("datamängden:\n %s", dataset.toString()));

        // Vi använder String.format för att skriva ut alla våra uträkningar i vår View
        textViewCalcOut.setText(String.format("%s: %.2f\n%s: %.2f\n%s: %.2f\n%s: %.2f",
                "Medelvärde", Statistics.calcAverage(dataset),
                "Medianvärde", Statistics.calcMedian(dataset),
                "Typvärde:", Statistics.calcMode(dataset),
                "Standardavvikelse", Statistics.calcSD(dataset)
        ));
    }



    // Körs när man trycker på Reset
    public void resetView(View view) {
        // Töm datamängdsfältet
        textViewOut.setText("");
        // Töm resultaten
        textViewCalcOut.setText("");
        // Nollställ datamängden
    }


}