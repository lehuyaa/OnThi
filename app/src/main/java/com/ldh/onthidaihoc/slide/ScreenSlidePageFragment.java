package com.ldh.onthidaihoc.slide;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.ldh.onthidaihoc.R;
import com.ldh.onthidaihoc.model.Question;

import java.util.ArrayList;
import java.util.Objects;


public class ScreenSlidePageFragment extends Fragment {

    ArrayList<Question> questions;
    DatabaseReference databaseReference;

    TextView tvNum, tvQuestion;
    RadioGroup radioGroup;
    RadioButton rdA, rdB, rdC, rdD;
    ImageView img;


    public static final String ARG_PAGE = "page";
    private int mPageNumber;
    public ScreenSlidePageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(
                R.layout.fragment_screen_slide_page, container, false);


        tvNum = viewGroup.findViewById(R.id.tvNum);
        tvQuestion = viewGroup.findViewById(R.id.tvQuestion);
        rdA = viewGroup.findViewById(R.id.radA);
        rdB = viewGroup.findViewById(R.id.radB);
        rdC = viewGroup.findViewById(R.id.radC);
        rdD = viewGroup.findViewById(R.id.radD);
        radioGroup = viewGroup.findViewById(R.id.radGroup);
        img = viewGroup.findViewById(R.id.ivIcon);

        return viewGroup;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        questions = new ArrayList<>();
        databaseReference = FirebaseDatabase.getInstance().getReference();
        String Exam_id = (String)getActivity().getIntent().getSerializableExtra("Exam_id");

        Query query = databaseReference.child("Question").orderByChild("num_exam").equalTo(Exam_id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        Question question = dataSnapshot.getValue(Question.class);
                        questions.add(question);
                    }
                }
                tvNum.setText("Câu " + (mPageNumber + 1));
                tvQuestion.setText(questions.get(mPageNumber).getQuestion());
                rdA.setText(questions.get(mPageNumber).getAns_a());
                rdB.setText(questions.get(mPageNumber).getAns_b());
                rdC.setText(questions.get(mPageNumber).getAns_c());
                rdD.setText(questions.get(mPageNumber).getAns_d());
                Glide.with(Objects.requireNonNull(getContext())).load(questions.get(mPageNumber).getImage()).into(img);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }



    public static ScreenSlidePageFragment create(int pageNumber) {
        ScreenSlidePageFragment slidePageFragment = new ScreenSlidePageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        slidePageFragment.setArguments(args);
        return slidePageFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}