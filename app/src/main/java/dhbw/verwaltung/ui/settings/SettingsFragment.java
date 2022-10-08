package dhbw.verwaltung.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import dhbw.verwaltung.MainActivity;
import dhbw.verwaltung.R;
import dhbw.verwaltung.util.OnScreenNotificationHandler;
import dhbw.verwaltung.util.SharedPrefUtil;

import java.util.Objects;

public class SettingsFragment extends PreferenceFragmentCompat {

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEdit, loginPrefEdit;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        sharedPref = SharedPrefUtil.getSharedPreferences();
        sharedPrefEdit = SharedPrefUtil.getSharedPrefEditor();
        loginPrefEdit = SharedPrefUtil.getLoginPrefEditor();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Hide the action bar item to prevent indefinite opening of the settings fragment
        ((MainActivity) Objects.requireNonNull(getActivity())).getCurrentMenu().findItem(R.id.action_settings).setVisible(false);

        // Get language setting and add a change listener
        ListPreference languagePref = (ListPreference) Objects.requireNonNull(getPreferenceManager().findPreference(getString(R.string.key_language)));
        languagePref.setValue(SharedPrefUtil.getString(getString(R.string.key_language), "de"));

        languagePref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                if (!newValue.toString().equals(sharedPref.getString(getString(R.string.key_language), "de"))) {
                    sharedPrefEdit.putString(getString(R.string.key_language), newValue.toString());
                    loginPrefEdit.putString(getString(R.string.key_language), newValue.toString());
                    loginPrefEdit.commit();

                    if (sharedPrefEdit.commit())
                        OnScreenNotificationHandler.displayToast(getContext(), getString(R.string.messages_changesSaved), Toast.LENGTH_SHORT);
                    else
                        OnScreenNotificationHandler.displayToast(getContext(), getString(R.string.messages_changesNotSaved), Toast.LENGTH_SHORT);

                    ((MainActivity) Objects.requireNonNull(getActivity())).refreshActivity();

                }
                return true;
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStop() {
        // Display action bar item again
        ((MainActivity) Objects.requireNonNull(getActivity())).getCurrentMenu().findItem(R.id.action_settings).setVisible(true);
        super.onStop();
    }
}
