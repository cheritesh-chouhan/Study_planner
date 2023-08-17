package com.example.studyplanner;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the  factory method to
 * create an instance of this fragment.
 */
public class ViewFragment extends Fragment {

    private String view;

    View viewItem;

    MyDBHandler myDBHandler;
    MyRecyclerAdapter myRecyclerAdapter;
    RecyclerView recyclerView;


    public ViewFragment(String view) {
        // Required empty public constructor
        this.view = view;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view, container, false);
        viewItem = view;

        myDBHandler = new MyDBHandler(this.getContext());
        Cursor data = myDBHandler.get_all_events();

        ArrayList<Event> events = new ArrayList<>();

        try {
            while (data.moveToNext())
            {
                String event_type = data.getString(4);
                System.out.println(event_type);
                if(event_type.contains(this.view.substring(1,3)))
                {
                    Event event = new Event(data.getString(1),data.getString(4),data.getString(2),data.getString(3),data.getString(5),data.getString(0));

                    events.add(event);

                }
            }

        } finally {
            data.close();
        }

        recyclerView = viewItem.findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        myRecyclerAdapter = new MyRecyclerAdapter(this.getContext(),events);
        recyclerView.setAdapter(myRecyclerAdapter);

        return view;
    }


}