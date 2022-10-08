package dhbw.verwaltung.ui.professors;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import dhbw.verwaltung.R;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder> {

    private final Professor[] professors;

    static class ProfessorViewHolder extends RecyclerView.ViewHolder {
        public TextView name, role, office, email, site;
        public ProfessorViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.professor_name);
            role = v.findViewById(R.id.professor_role);
            office = v.findViewById(R.id.professor_office);
            email = v.findViewById(R.id.professor_email);
            site = v.findViewById(R.id.professor_site);
        }
    }

    public ProfessorAdapter(Professor[] professors) {
        this.professors = professors;
    }

    @NonNull
    @Override
    public ProfessorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.professor_list_row, parent, false);
        return new ProfessorViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        Professor prof = professors[position];
        holder.name.setText(prof.name);
        holder.role.setText(prof.role);
        holder.office.setText(prof.office);
        holder.email.setText(prof.email);
        holder.site.setText(prof.site);
    }

    @Override
    public int getItemCount() {
        return professors.length;
    }
}
