package com.example.mahe.moviesinfo;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 * Created by Mahe on 6/7/2017.
 */

public class CelebrityAdapter extends RecyclerView.Adapter<CelebrityAdapter.CelebrityViewHolder>{
 ArrayList<credit_list> list=new ArrayList<>();
    Context mContext;

    public CelebrityAdapter(Context context) {
        mContext=context;
    }

    @Override
    public CelebrityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.individual_celebrity_view,parent,false);
        view.setFocusable(true);
        return new CelebrityViewHolder(view);

    }

    @Override
    public void onBindViewHolder(CelebrityViewHolder holder, int position) {
         credit_list credit=list.get(position);
        String profile_path=credit.getImage();

        try {
            GetCredits.getProfilePicture(profile_path,holder.celebrity_image,mContext);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        holder.celebrity_name.setText(credit.getName());
        holder.celebrity_character.setText(credit.getCharacter());
    }

    @Override
    public int getItemCount() {
        if(list!=null)
        {
            return list.size();
        }else
        {
            return 0;
        }
    }

    public class CelebrityViewHolder extends RecyclerView.ViewHolder {
        ImageView celebrity_image;
        TextView celebrity_name;
        TextView celebrity_character;
        public CelebrityViewHolder(View itemView) {
            super(itemView);
            celebrity_image=(ImageView)itemView.findViewById(R.id.celebrity_view);
            celebrity_name=(TextView)itemView.findViewById(R.id.celebrity_name);
            celebrity_character=(TextView)itemView.findViewById(R.id.celebrity_character);
        }
    }
    public  void swap_data(ArrayList<credit_list> data)
    {
        list=data;
        notifyDataSetChanged();
    }
}
