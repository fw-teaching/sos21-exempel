package fi.arcada.android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    TextView myText;
    TextView textSetting;

    // 1. Deklarera ett SharedPreferences-objekt
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myText = findViewById(R.id.editTextView);
        textSetting = findViewById(R.id.textSetting);

        // 2. Instansiera prefs
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        // 3. Uppdatera värdet i myText-fältet med värdet från preferences-variabeln myOtherText
        myText.setText(
                // Om variabeln myOtherText inte finns i SharedPreferences används default-värdet ("Morjens") i stället
                prefs.getString("myOtherText", "Morjens")
        );
        textSetting.setText(
                prefs.getString("textSetting", "def val")
        );

    }


    /** En samlingsmetod för hantering av knapptryck
     * Man kan förstås också ha skilda metoder för varje knapp om man tycker det är klarare.
     */
    public void buttonClick(View view) {

        // If-satser för att kontrollera vilken knapp vi tyckt på
        // view.getId() returnerar id för knappen vi tryckte på.
        if (view.getId() == R.id.buttonSave) {
            // 4. Instansiera ett Editor-objekt för att kunna ändra på preferences
            SharedPreferences.Editor prefsEditor = prefs.edit();

            // 5. spara värdet från myText-fältet som "myOtherText" i prefs
            prefsEditor.putString("myOtherText", myText.getText().toString());

            // 6. Spara alla ändringar i minnet och i xml-filen som innehåller SharedPreferences
            prefsEditor.apply();

        } else if (view.getId() == R.id.btnOtherActivity) {
            // Starta vår Other Activity
            Intent intent = new Intent(this, OtherActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.btnSettings) {
            // Starta vår Settings Activity
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);

        }


    }


}