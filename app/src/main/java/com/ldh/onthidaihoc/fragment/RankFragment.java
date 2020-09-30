package com.ldh.onthidaihoc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ldh.onthidaihoc.R;
import com.ldh.onthidaihoc.adapter.RankUserAdapter;
import com.ldh.onthidaihoc.collections.ScoreComparator;
import com.ldh.onthidaihoc.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankFragment extends Fragment {
    private static RankFragment ISTANCE;

    public static RankFragment getInstance() {
        if (ISTANCE == null) {
            ISTANCE = new RankFragment();
        }
        return ISTANCE;
    }

    public RankFragment() {

    }

    private RecyclerView rvRankUser;
    private RankUserAdapter adapter;
    private List<User> userList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_rank, container, false);
        rvRankUser = view.findViewById(R.id.rv_rank);
        userList = new ArrayList<>();
        userList.add(new User("a","b","c","d","e","100","g"));
        userList.add(new User("a","b","c","d","e","90","g"));
        userList.add(new User("a","b","c","d","e","110","g"));
        userList.add(new User("a","b","c","d","e","80","g"));
        userList.add(new User("a","b","c","d","e","60","g"));
        userList.add(new User("a","b","c","d","e","70","g"));
        userList.add(new User("a","b","c","d","e","80","g"));
        Collections.sort(userList,new ScoreComparator());
        adapter = new RankUserAdapter(getContext(),userList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);

        DividerItemDecoration dividerHorizontal =
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);

        rvRankUser.addItemDecoration(dividerHorizontal);
        rvRankUser.setLayoutManager(linearLayoutManager);
        rvRankUser.setAdapter(adapter);
        return view;
    }
}
