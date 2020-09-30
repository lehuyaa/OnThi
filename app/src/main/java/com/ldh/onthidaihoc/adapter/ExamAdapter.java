package com.ldh.onthidaihoc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.ldh.onthidaihoc.R;
import com.ldh.onthidaihoc.listener.ClickListener;
import com.ldh.onthidaihoc.model.Exam;

public class ExamAdapter extends FirebaseRecyclerAdapter<Exam, ExamAdapter.ExamViewHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */

    private ClickListener listener;

    public ExamAdapter(@NonNull FirebaseRecyclerOptions<Exam> options, ClickListener listener) {
        super(options);
        this.listener = listener;
    }

    @Override
    protected void onBindViewHolder(@NonNull ExamViewHolder holder, final int position, @NonNull final Exam exam) {
        holder.tvName.setText(exam.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Exam_id = exam.getId();
                String Exam_name = exam.getName();
                listener.ClickItemListener(Exam_id,Exam_name);
            }
        });

    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exam, parent, false);
        return new ExamViewHolder(view);
    }

    class ExamViewHolder extends RecyclerView.ViewHolder {


        TextView tvName;

        public ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

}
