package dhbw.verwaltung.ui.secretariat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import com.google.gson.Gson;
import dhbw.verwaltung.R;

import java.util.HashMap;

public class SecretariatFragment extends Fragment {

    private SecretariatViewModel secretariatViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        secretariatViewModel =
                ViewModelProviders.of(this).get(SecretariatViewModel.class);
        View root = inflater.inflate(R.layout.fragment_secretariat, container, false);

        final TextView textView = root.findViewById(R.id.text_secretariat);
        SharedPreferences mPrefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        if (mPrefs.getString(getString(R.string.key_secretariat), "").equals("")) createEntries();

        String mail = mPrefs.getString(getString(R.string.key_mail), "example@example.com");

        if(mail.contains("inf"))
            textView.setText(getEntry(getString(R.string.key_INF)));
        else
            textView.setText(getString(R.string.sec_none));

        textView.setMovementMethod(new ScrollingMovementMethod());
        return root;
    }

   private void createEntries() {
        HashMap<String, String> secreteriat = new HashMap<>();
        secreteriat.put(getString(R.string.key_INF), getString(R.string.sec_INF));

        SharedPreferences mPrefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(secreteriat);
        prefsEditor.putString(getString(R.string.key_secretariat), json);
        prefsEditor.commit();
    }

    private String getEntry(String key){
        Gson gson = new Gson();
        SharedPreferences mPrefs = getActivity().getPreferences(Context.MODE_PRIVATE);
        String json = mPrefs.getString(getString(R.string.key_secretariat), "");
        HashMap<String, String> secretariats = gson.fromJson(json, HashMap.class);
        return secretariats != null ? secretariats.get(key) : "Error";
    }
}
