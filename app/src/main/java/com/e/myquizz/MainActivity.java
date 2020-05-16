package com.e.myquizz;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import com.e.myquizz.fragments.Topics;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

public class MainActivity extends AppCompatActivity {

    FirebaseUser mFirebaseUser;
    FirebaseAuth mFirebaseAuth;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth=FirebaseAuth.getInstance();
        mFirebaseUser=mFirebaseAuth.getCurrentUser();

        if(mFirebaseUser==null){
            startActivity(new Intent(MainActivity.this,LoginActivity.class));
        }else {
            //
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new Topics()).commit();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.topics, R.id.friends, R.id.message,R.id.achievements,R.id.settings,R.id.logout)
                .setDrawerLayout(drawer)
                .build();


        //my code

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
