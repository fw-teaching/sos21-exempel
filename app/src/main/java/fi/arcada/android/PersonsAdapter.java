package fi.arcada.android;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonsAdapter extends RecyclerView.Adapter<PersonsAdapter.PersonViewHolder> {

    Context context;
    ArrayList<Person> persons;

    public PersonsAdapter(Context context, ArrayList<Person> persons) {
        this.context = context;
        this.persons = persons;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class PersonViewHolder extends RecyclerView.ViewHolder {

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
