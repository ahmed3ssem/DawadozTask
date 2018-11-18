package com.example.a7medassem.dawadoztask.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.a7medassem.dawadoztask.Model.weatherModel;
import com.example.a7medassem.dawadoztask.R;
import java.util.List;

public class weatherAdapter extends RecyclerView.Adapter<weatherAdapter.MyViewHolder> {

    private List<weatherModel> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, temp , pressure;

        public MyViewHolder(View view) {
            super(view);
            name =  view.findViewById(R.id.name);
            temp =  view.findViewById(R.id.temp);
            pressure = view.findViewById(R.id.pressure);
        }
    }


    public weatherAdapter(List<weatherModel> list) {
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.homelistitems, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        weatherModel model = list.get(position);
        holder.name.setText(model.getName());
        holder.temp.setText(model.getTemp());
        holder.pressure.setText(model.getPressure());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
