package com.example.studyplanner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    Context context;
    ArrayList<Event> events;

    public MyRecyclerAdapter(Context context, ArrayList<Event> events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public MyRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.event_view,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.MyViewHolder holder, int position) {
        holder.Title.setText(events.get(position).getTitle());
        holder.Description.setText(events.get(position).getDes());
        holder.Event_type.setText(events.get(position).getType());
        String time = events.get(position).getDate() + " " + events.get(position).getTime();
        holder.dateTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Title,dateTime,Description,Event_type;
        MyDBHandler myDBHandler;
        FloatingActionButton del_btn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = itemView.findViewById(R.id.title_view);
            dateTime = itemView.findViewById(R.id.time_view);
            Description = itemView.findViewById(R.id.des_view);
            Event_type = itemView.findViewById(R.id.type_view);

            del_btn = itemView.findViewById(R.id.del_btn);

            del_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    int id = Integer.parseInt(events.get(pos).getId());
                    events.remove(pos);
                    myDBHandler = new MyDBHandler(MyRecyclerAdapter.this.context);


                    myDBHandler.remove_event(id);

                    notifyItemRemoved(pos);
                    notifyItemRangeChanged(pos,events.size());





                }
            });

        }
    }
}
