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

    EditText editTextName;
    EditText editTextAge;
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
        errorOut = findViewById(R.id.errorOutput);
        textViewCalcOut = findViewById(R.id.textViewCalcOut);

        recyclerViewPersons = findViewById(R.id.recyclerViewPersons);

        personsAdapter = new PersonsAdapter(this, persons);
        recyclerViewPersons.setAdapter(personsAdapter);
        recyclerViewPersons.setLayoutManager(new LinearLayoutManager(this));

        persons.add(new Person("Pelle", 10));
        persons.add(new Person("Kalle", 11));
        persons.add(new Person("Lisa", 12));
        persons.add(new Person("Anna", 13));

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

            persons.add(new Person(personName, personAge));
            personsAdapter.notifyItemInserted(persons.size()-1);

        }

    }

    @SuppressLint("DefaultLocale")
    public void calculate(View view) {

        ArrayList<Double> dataset = new ArrayList<>();

        for (Person person : persons) {
            dataset.add((double) person.getAge());
        }

        // Vi använder String.format för att skriva ut alla våra uträkningar i vår View
        textViewCalcOut.setText(String.format("%s: %.2f\n%s: %.2f\n%s: %.2f\n%s: %.2f",
                "Medelvärde", Statistics.calcAverage(dataset),
                "Medianvärde", Statistics.calcMedian(dataset),
                "Typvärde:", Statistics.calcMode(dataset),
                "Standardavvikelse", Statistics.calcSD(dataset)
        ));
    }



}