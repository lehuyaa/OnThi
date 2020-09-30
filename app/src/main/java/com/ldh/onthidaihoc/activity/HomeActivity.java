package com.ldh.onthidaihoc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import com.ldh.onthidaihoc.R;
import com.ldh.onthidaihoc.fragment.HomeFragment;
import com.ldh.onthidaihoc.fragment.NotificationFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private View headerView;
    private TextView tvName, tvEmail;
    private ImageView imgMenu;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = findViewById(R.id.draw_menu);


        imgMenu = findViewById(R.id.img_menu);
        imgMenu.setOnClickListener(this);

        navigationView = findViewById(R.id.nav_view);
        headerView = navigationView.getHeaderView(0);
        tvName = headerView.findViewById(R.id.tv_name);
        tvEmail = headerView.findViewById(R.id.tv_email);
        tvName.setText("The Doctor");
        tvEmail.setText("lehuyaa0103@gmail.com");

        firebaseAuth = FirebaseAuth.getInstance();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                    new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                        HomeFragment.getInstance()).commit();
                break;
            case R.id.nav_ommunityActivity:
                startActivity(new Intent(HomeActivity.this,CommunityActivity.class));
                break;

            case R.id.nav_notification:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                        NotificationFragment.getInstance()).commit();
                break;
            case R.id.nav_rank:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,
                        RankFragment.getInstance()).commit();
                break;
            case R.id.nav_log_out:
                logOut();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    private void CheckUserStatus(){
        FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user==null){
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finish();
            }
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        CheckUserStatus();
        super.onStart();
    }

    private void logOut() {
        firebaseAuth.signOut();
        CheckUserStatus();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_menu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
    }
}