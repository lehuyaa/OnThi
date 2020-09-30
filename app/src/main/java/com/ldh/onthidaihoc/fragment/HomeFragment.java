package com.ldh.onthidaihoc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ldh.onthidaihoc.R;
import com.ldh.onthidaihoc.activity.SubjectActivity;
import com.ldh.onthidaihoc.adapter.SubjectAdapter;
import com.ldh.onthidaihoc.listener.ClickListener;
import com.ldh.onthidaihoc.model.Subject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private CardView cvMath, cvPhysical, cvChemistry;
    private static HomeFragment ISTANCE;

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Subject> subjects;
    private SubjectAdapter adapter;


    public static HomeFragment getInstance() {
        if (ISTANCE == null) {
            ISTANCE = new HomeFragment();
        }
        return ISTANCE;
    }

    public HomeFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.rv_subject);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        reference = FirebaseDatabase.getInstance().getReference().child("Subject");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                subjects = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Subject subject = dataSnapshot1.getValue(Subject.class);
                    subjects.add(subject);
                }
                adapter = new SubjectAdapter(getContext(), subjects, new ClickListener() {
                    @Override
                    public void ClickItemListener(String subject_id,String subject_name) {
                        Intent intent = new Intent(getContext(), SubjectActivity.class);
                        intent.putExtra("subject_id",subject_id);
                        intent.putExtra("subject_name",subject_name);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        return view;

    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        cvMath.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), SubjectActivity.class);
//                startActivity(intent);
//                Animatoo.animateSlideLeft(getContext());
//            }
//        });
//    }
}
