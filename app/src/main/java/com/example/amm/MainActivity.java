package com.example.amm;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.amm.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private NavController navController;
    private ActivityMainBinding binding;

    Button btnC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        loadLocale();
//        btnC = findViewById(R.id.btnChangeLanguage);
//
//        btnC.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                changLanguage();
//            }
//        });

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Change Language", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                changLanguage();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.locationFragment,R.id.contactUs,R.id.aboutUsFragment,R.id.donation,R.id.payment,R.id.eventFragment,R.id.adminLogin)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void changLanguage() {
        final AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.bottomsheetlayout, null);

        mBuilder.setView(dialogView);
        final AlertDialog alertDialog = mBuilder.create();

        // Apply enter animation (optional)
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        RadioGroup languageRadioGroup = dialogView.findViewById(R.id.languageRadioGroup);
        Button confirmButton = dialogView.findViewById(R.id.confirmButton);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedRadioButtonId = languageRadioGroup.getCheckedRadioButtonId();
                if (selectedRadioButtonId == R.id.englishRadioButton) {
                    setLocal("");
                } else if (selectedRadioButtonId == R.id.marathiRadioButton) {
                    setLocal("mr");
                } else if (selectedRadioButtonId == R.id.hindiRadioButton) {
                    setLocal("hi");
                }

                alertDialog.dismiss();
                recreate();
            }
        });

        alertDialog.show();
    }


    private void setLocal(String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext()
                .getResources().getDisplayMetrics());

        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("app_lang",language);
        editor.apply();
    }
    private  void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Settings",MODE_PRIVATE);
        String language = preferences.getString("app_lang", "");
        setLocal(language);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // Navigate to the notification_one destination using the NavController
                Toast.makeText(this, "Notifictoion", Toast.LENGTH_SHORT).show();
                return true;
            // Add other cases for other menu items if needed
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}