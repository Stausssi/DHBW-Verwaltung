package dhbw.verwaltung;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import dhbw.verwaltung.ui.login.LoginActivity;
import dhbw.verwaltung.util.SharedPrefUtil;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private String userName, userMatNr, userMail;

    private TextView navUserName, navUserMail;

    private Menu navigationMenu;
    private Menu currentMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (SharedPrefUtil.getSharedPreferences() == null) {
            SharedPrefUtil.setSharedPreferences(getPreferences(MODE_PRIVATE));

            if(extras.containsKey(getString(R.string.key_language)) && extras.containsKey(getString(R.string.key_mail))) {
                SharedPreferences.Editor edit = SharedPrefUtil.getSharedPrefEditor();
                edit.putString(getString(R.string.key_language), extras.get(getString(R.string.key_language)).toString());
                edit.putString(getString(R.string.key_mail), extras.get(getString(R.string.key_mail)).toString());

                edit.commit();
            }

            refreshActivity();
        }

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        setNavigationMenu(navigationView.getMenu());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_profile, R.id.nav_home, R.id.nav_vorlesung, R.id.nav_impressum)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navUserName = navigationView.getHeaderView(0).findViewById(R.id.nav_nameView);
        navUserMail = navigationView.getHeaderView(0).findViewById(R.id.nav_mailView);

        refreshNavHeader();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        setCurrentMenu(menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // Pretend this is a call from the sidebar menu
                // Use the invisible menu item
                getNavigationMenu().performIdentifierAction(R.id.nav_settings, 0);
                return true;
            case R.id.action_signout:
                SharedPrefUtil.setSharedPreferences(null);
                Intent i = new Intent();
                i.setClass(this, LoginActivity.class);
                i.putExtra(getString(R.string.key_logout), true);
                startActivity(i);
                finish();
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(updateBaseContextLocale(base));
    }

    public void refreshActivity() {
        // Restart Activity to make the changes take effect
        finish();
        overridePendingTransition(0, 0);
        startActivity(getIntent().addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
        overridePendingTransition(0, 0);
    }

    public Context updateBaseContextLocale(Context context) {
        String lang = SharedPrefUtil.getString(context.getResources().getString(R.string.key_language), "de");

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        Configuration config = new Configuration(context.getResources().getConfiguration());
        config.setLocale(locale);

        return context.createConfigurationContext(config);
    }

    public void refreshNavHeader() {
        setUserName(SharedPrefUtil.getString(getString(R.string.key_name), "Max Mustermann"));
        setUserMatNr(String.valueOf(SharedPrefUtil.getInt(getString(R.string.key_matNr), 12345678)));
        setUserMail(SharedPrefUtil.getString(getString(R.string.key_mail), "example@lehre.dhbw-stuttgart.de"));

        refreshUserName();
        refreshUserMail();
    }

    public void refreshUserName() {
        navUserName.setText(getString(R.string.nav_header_userName, userName, userMatNr));
    }

    public void refreshUserMail() {
        navUserMail.setText(getString(R.string.nav_header_userMail, userMail));
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMatNr() {
        return userMatNr;
    }

    public void setUserMatNr(String userMatNr) {
        this.userMatNr = userMatNr;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public Menu getNavigationMenu() {
        return navigationMenu;
    }

    public void setNavigationMenu(Menu navigationMenu) {
        this.navigationMenu = navigationMenu;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }
}
