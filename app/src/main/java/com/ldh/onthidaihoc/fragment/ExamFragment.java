package com.ldh.onthidaihoc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.ldh.onthidaihoc.R;
import com.ldh.onthidaihoc.adapter.ExamAdapter;
import com.ldh.onthidaihoc.listener.ClickListener;
import com.ldh.onthidaihoc.model.Exam;
import com.ldh.onthidaihoc.slide.ScreenSlidePagerActivity;


public class ExamFragment extends Fragment {


    private static ExamFragment INSTANCE = null;

    private ExamFragment() {

    }

    public static ExamFragment getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ExamFragment();
        }
        return INSTANCE;
    }


    private RecyclerView rvExam;
    private ExamAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_exam, container, false);
        rvExam = view.findViewById(R.id.rv_exam);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        rvExam.setLayoutManager(linearLayoutManager);

        String subject_id = (String) getActivity().getIntent().getSerializableExtra("subject_id");

        FirebaseRecyclerOptions<Exam> options =
                new FirebaseRecyclerOptions.Builder<Exam>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Exam").orderByChild("numSubject").equalTo(subject_id), Exam.class)
                        .build();


        adapter = new ExamAdapter(options, new ClickListener() {
            @Override
            public void ClickItemListener(String Exam_id, String name) {
                Intent intent = new Intent(getContext(), ScreenSlidePagerActivity.class);
                intent.putExtra("Exam_id", Exam_id);
                startActivity(intent);
            }


        });
        rvExam.setAdapter(adapter);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}