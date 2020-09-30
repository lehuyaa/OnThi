package com.ldh.onthidaihoc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.tabs.TabLayout;
import com.ldh.onthidaihoc.R;
import com.ldh.onthidaihoc.adapter.FragmentAdapter;

public class SubjectActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgBack;
    private TextView tvSubject;
    private ViewPager pager;
    private TabLayout tabLayout;
    private FragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        tvSubject = findViewById(R.id.tv_subject);
        imgBack = findViewById(R.id.img_back);
        imgBack.setOnClickListener(this);
        String subject_name = (String) getIntent().getSerializableExtra("subject_name");
        tvSubject.setText(subject_name);
        addViewPaper();

    }

    private void addViewPaper() {
        pager = findViewById(R.id.view_pager);
        tabLayout = findViewById(R.id.tab_layout);
        adapter = new FragmentAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        Animatoo.animateSlideRight(SubjectActivity.this);
    }
}