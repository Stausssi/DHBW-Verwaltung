package dhbw.verwaltung.ui.impressum;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import dhbw.verwaltung.R;

public class ImpressumFragment extends Fragment {

    private ImpressumViewModel impressumViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        impressumViewModel =
                ViewModelProviders.of(this).get(ImpressumViewModel.class);
        View root = inflater.inflate(R.layout.fragment_impressum, container, false);
        final TextView textView = root.findViewById(R.id.text_impressum);
        textView.setMovementMethod(new ScrollingMovementMethod());
        return root;
    }
}
