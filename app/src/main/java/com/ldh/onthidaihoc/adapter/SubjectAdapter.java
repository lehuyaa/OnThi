package com.ldh.onthidaihoc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ldh.onthidaihoc.R;
import com.ldh.onthidaihoc.listener.ClickListener;
import com.ldh.onthidaihoc.model.Subject;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder>{



    private Context context;
    private List<Subject> subjects;
    private ClickListener listener;

    public SubjectAdapter(Context context, List<Subject> subjects, ClickListener listener) {
        this.context = context;
        this.subjects = subjects;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_subject,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Subject subject = subjects.get(position);
        holder.tvNameSubject.setText(subject.getName());
        Glide.with(context).load(subject.getImage()).into(holder.imgSubject);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String subject_id = subject.getId();
                String subject_name = subject.getName();
                listener.ClickItemListener(subject_id,subject_name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return subjects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNameSubject;
        ImageView imgSubject;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameSubject = itemView.findViewById(R.id.tv_subject);
            imgSubject = itemView.findViewById(R.id.img_subject);
        }
    }
}
