package com.example.esteticacanina;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MenuActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    HomeFragment homeFragment = new HomeFragment();
    MessagesFragment messagesFragment = new MessagesFragment();
    CalenderFragment calenderFragment = new CalenderFragment();
    UserFragment userFragment = new UserFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.inicio:
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, homeFragment).commit();
                        return true;
                    case R.id.mensaje:
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, messagesFragment).commit();
                        return true;
                    case R.id.calendario:
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, calenderFragment).commit();
                        return true;
                    case R.id.perfil:
                        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor, userFragment).commit();
                        return true;
                }
                return false;
            }
        });
    }
}