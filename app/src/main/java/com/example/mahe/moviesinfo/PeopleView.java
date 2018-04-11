package com.example.mahe.moviesinfo;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class PeopleView extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    public PeopleView() {
        // Required empty public constructor
    }

CelebrityAdapter mAdapter;
String movie_id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       mAdapter=new CelebrityAdapter(this.getActivity());
        View view= inflater.inflate(R.layout.fragment_people_view, container, false);
         movie_id=getArguments().getString(MovieDetail.MOVIE_ID);
        AsyncTask<Void ,Void,ArrayList<credit_list>> task=new AsyncTask<Void, Void, ArrayList<credit_list>>() {
            @Override
            protected ArrayList<credit_list> doInBackground(Void... params) {
                ArrayList<credit_list> list=new ArrayList<>();
                GetCredits credits=new GetCredits();
                list=credits.getCreditDta(movie_id);
                return list;
            }
            @Override
            protected void onPostExecute(ArrayList<credit_list> list)
            {
                mAdapter.swap_data(list);
            }
        };
        task.execute();
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.celebrity_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getActivity(),3));
        recyclerView.setAdapter(mAdapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event

}
