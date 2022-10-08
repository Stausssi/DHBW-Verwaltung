package dhbw.verwaltung.ui.professors;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import dhbw.verwaltung.R;

public class ProfessorsFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder> adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_professors, container, false);

        recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new ProfessorAdapter(getProfessors());
        recyclerView.setAdapter(adapter);

        return root;
    }

    private Professor[] getProfessors() {
        SharedPreferences prefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = prefs.getString(getString(R.string.key_professors), "");
        if(!json.isEmpty())
            return gson.fromJson(json, Professor[].class);
        Professor[] profs = {
               new Professor("Prof. Dr. Jan Hladik", "Rotebühlplatz 41\n" +
                       "Raum: 0.03\n" +
                       "70178 Stuttgart\n"),
                new Professor("Prof. Dr. Janko Dietzsch", "Rotebühlplatz 41\n" +
                        "Raum: 1.10/1\n" +
                        "70178 Stuttgart\n"),
                new Professor("Prof. Dr. Mario Babilon", "Studiengangsleiter Informatik", "Rotebühlplatz 41\n" +
                        "Raum: U1.02\n" +
                        "70178 Stuttgart\n"),
                new Professor("Prof. Dr. Monika Kochanowski", "Professorin für Informatik", "Rotebühlplatz 41\n" +
                        "Raum: 0.03\n" +
                        "70178 Stuttgart\n"),
                new Professor("Prof. Dr. Dirk Reichardt", "Studiengangsleiter Informatik", "Rotebühlplatz 41\n" +
                        "Raum 1.10/4\n" +
                        "70178 Stuttgart\n"),
               new Professor("Prof. Dr. Doris Nitsche-Ruhland", "Studiengangsleiterin Informatik", "Rotebühlplatz 41\n" +
                       "Raum 1.10\n" +
                       "70178 Stuttgart\n"),
               new Professor("Prof. Dr. rer. nat. Stephan Schulz", "Rotebühlpaltz 41\n" +
                       "Raum 0.01\n" +
                       "70178 Stuttgart\n"),
               new Professor("Prof. Dr. Bernd Schwinn", "Studiengangsleiter Informatik", "Rotebühlplatz 41\n" +
                       "Raum 1.10/2\n" +
                       "70178 Stuttgart\n"),
                new Professor("Prof. Dr.-Ing. Alfred Strey", "Rotebühlplatz 41\n" +
                        "Raum 0.05\n" +
                        "70178 Stuttgart\n"),
                new Professor("Prof. Dipl.-Ing. Friedemann Stockmayer", "Studiengangsleiter Informatik", "Rotebühlplatz 41\n" +
                        "Raum 1.07\n" +
                        "70178 Stuttgart\n"),
                new Professor("Prof. Dr. Carmen Winter", "Studiengangsleitung Informatik", "Rotebühlplatz 41\n" +
                        "Raum 3.05\n" +
                        "70178 Stuttgart\n"),
                new Professor("Prof. Dr. Zoltán Ádam Zomotor", "Studiengangsleiter Informatik", "Rotebühlplatz 41\n" +
                        "Raum 0.07\n" +
                        "70178 Stuttgart\n")
        };
        prefs.edit().putString(getString(R.string.key_professors), gson.toJson(profs)).apply();
        return profs;
    }
}
