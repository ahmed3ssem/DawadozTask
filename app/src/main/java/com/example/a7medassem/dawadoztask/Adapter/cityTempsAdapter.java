package com.example.a7medassem.dawadoztask.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.a7medassem.dawadoztask.Model.cityTempsModel;
import com.example.a7medassem.dawadoztask.R;

import java.util.List;

public class cityTempsAdapter extends RecyclerView.Adapter<cityTempsAdapter.MyViewHolder>{

    private List<cityTempsModel> tempList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView temp , date;

        public MyViewHolder(View view) {
            super(view);
            temp =  view.findViewById(R.id.cityTempsTemp);
            date = view.findViewById(R.id.cityTempsDate);
        }
    }


    public cityTempsAdapter(List<cityTempsModel> tempList) {
        this.tempList = tempList;
    }

    @Override
    public cityTempsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.citytempsitems, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(cityTempsAdapter.MyViewHolder holder, int position) {
        cityTempsModel model = tempList.get(position);
        holder.temp.setText(model.getTemp());
        holder.date.setText(model.getDate());
    }

    @Override
    public int getItemCount() {
        return tempList.size();
    }
}
