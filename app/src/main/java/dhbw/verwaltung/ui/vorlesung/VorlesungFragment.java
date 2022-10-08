package dhbw.verwaltung.ui.vorlesung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import dhbw.verwaltung.R;

public class VorlesungFragment extends Fragment {

    private static final String raplaURL = "https://rapla.dhbw-stuttgart.de/rapla?key=txB1FOi5xd1wUJBWuX8lJhGDUgtMSFmnKLgAG_NVMhBUYcX7OIFJ2of49CgyjVbV";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_vorlesung, container, false);

        // Load Rapla in Web View
        WebView webView = root.findViewById(R.id.vorlesung_web_view);
        webView.loadUrl(raplaURL);

        // Open links and redirects inside the Web View
        webView.setWebViewClient(new WebViewClient());

        return root;
    }
}
