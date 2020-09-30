package com.ldh.onthidaihoc.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ldh.onthidaihoc.Fragment.ExamFragment;
import com.ldh.onthidaihoc.Fragment.TrainingFragment;

public class FragmentAdapter extends FragmentStatePagerAdapter {
    public FragmentAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = TrainingFragment.getInstance();
                break;
            case 1:
                fragment = ExamFragment.getInstance();
                break;
        }
        return fragment;
    }

    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title = "Luyện Tập";
                break;
            case 1:
                title = "Làm Đề Thi Thử";
                break;
        }
        return title;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
