package com.example.a7medassem.dawadoztask.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.a7medassem.dawadoztask.Adapter.weatherAdapter;
import com.example.a7medassem.dawadoztask.Model.weatherModel;
import com.example.a7medassem.dawadoztask.R;
import com.example.a7medassem.dawadoztask.RecycleView.DividerItemDecoration;
import com.example.a7medassem.dawadoztask.Volley.getCitiesTemp;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private static List<weatherModel> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private static weatherAdapter mAdapter;
    public static List<String> id = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeList();

        getDataFromServer();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contactus:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto","Dev@dawadoz.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "DawadozTask");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /* initialize recyclerview */
    private void initializeList()
    {
        recyclerView = findViewById(R.id.homeList);
        mAdapter = new weatherAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(mAdapter);
    }

    /* get data from class getCitiesTemp that get cities name and temps from server */
    private void getDataFromServer()
    {
        getCitiesTemp temp= new getCitiesTemp(this);
        temp.gettemps();
    }

    /* add data to list */
    public static void showData(String name , String temp)
    {
        weatherModel model = new weatherModel("City Name: "+name,"City Temp: "+temp);
        list.add(model);
        mAdapter.notifyDataSetChanged();
    }
}
