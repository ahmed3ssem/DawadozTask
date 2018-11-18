package com.example.a7medassem.dawadoztask.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import com.example.a7medassem.dawadoztask.Volley.getCityFiveTemp;
import com.example.a7medassem.dawadoztask.Model.cityTempsModel;
import com.example.a7medassem.dawadoztask.R;
import com.example.a7medassem.dawadoztask.Adapter.cityTempsAdapter;
import com.example.a7medassem.dawadoztask.RecycleView.DividerItemDecoration;
import java.util.ArrayList;
import java.util.List;

public class citytempratures extends AppCompatActivity {

    private static List<cityTempsModel> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private static cityTempsAdapter mAdapter;
    private TextView cityName;
    private String cityId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citytempratures);

        setCityName();

        initializeList();

        getDataFromServer();
    }

    private void setCityName()
    {
        cityName = findViewById(R.id.cityTempsName);
        cityName.setText(getIntent().getStringExtra("cityName"));

    }

    private void initializeList()
    {
        recyclerView = findViewById(R.id.cityTempsList);
        mAdapter = new cityTempsAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(mAdapter);
    }

    /* get data from class getCitiesTemp that get cities name and temps from server */
    private void getDataFromServer()
    {
        cityId = getIntent().getStringExtra("cityId");
        getCityFiveTemp temp= new getCityFiveTemp(this);
        temp.gettemps(cityId);
    }

    /* add data to list */
    public static void showData(String temp , String date)
    {
        cityTempsModel model = new cityTempsModel("City Temp: "+temp,"Temp Date: "+date);
        list.add(model);
        mAdapter.notifyDataSetChanged();

    }
}
