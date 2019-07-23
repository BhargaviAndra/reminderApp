package com.example.project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.RecycleViewHolder>
{dataBaseHelper db;
    Context context;
ArrayList<ObtainData>data;
RecyclerView recyclerView;
//private OnItemClickListener Listener;

    public recycleAdapter(Context context,ArrayList<ObtainData>data)//Adapter which converts data to be visible in recycler view and then pass to view holder
    {
      this.context=context;
      this.data=data;
    }
    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)//Creates View Holder
     {
         LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
         View view=layoutInflater.inflate(R.layout.view,viewGroup,false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder Holder, int i) //Binds Data with Views
    {
       Holder.task.setText(data.get(i).getTask());
       Holder.date.setText(data.get(i).getDate());
       Holder.time.setText(data.get(i).getTime());


    }

    @Override
    public int getItemCount()  //gets the position of view
    {
        return data.size();
    }



    public class RecycleViewHolder extends RecyclerView.ViewHolder//ViewHolder
 {private TextView task,date,time;
 private Button done;
     public RecycleViewHolder(@NonNull View itemView)
 {
         super(itemView);
         task=(TextView)itemView.findViewById(R.id.task);
         date=(TextView)itemView.findViewById(R.id.date);
         time=(TextView)itemView.findViewById(R.id.time);
         done=(Button)itemView.findViewById(R.id.done);
         done.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                    int position=getAdapterPosition();
                 ObtainData value=data.get(position);
                 db=new dataBaseHelper(context);
                 db.DeleteData(value.getTask());
                 data.remove(position);
                 notifyItemRemoved(position);
                 notifyItemRangeChanged(position, data.size());
                 notifyDataSetChanged();
             }
         });
     }
 }

}
