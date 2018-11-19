package com.example.a7medassem.dawadoztask.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.a7medassem.dawadoztask.Adapter.weatherAdapter;
import com.example.a7medassem.dawadoztask.Model.weatherModel;
import com.example.a7medassem.dawadoztask.R;
import com.example.a7medassem.dawadoztask.RecycleView.DividerItemDecoration;
import com.example.a7medassem.dawadoztask.RecycleView.RecyclerItemClickListener;
import com.example.a7medassem.dawadoztask.Volley.getCitiesTemp;
import com.example.a7medassem.dawadoztask.connectionChecker.internetChecker;
import com.example.a7medassem.dawadoztask.SQL.weatherDB;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    private static List<weatherModel> list = new ArrayList<>();
    private static RecyclerView recyclerView;
    private static weatherAdapter mAdapter;
    public static List<String> id = new ArrayList<>();
    private internetChecker checker = new internetChecker(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initializeList();

        checkConnection();

        getCityTemps();

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

    //Check if app installed before or not
    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {
            // first time
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();
        }
        return !ranBefore;
}

    /* check if there is connection or not to get data from sqlite */
    private void checkConnection()
    {
        if (checker.isInternetOn())
        {
            getDataFromServer();
        }
        else if(isFirstTime())
        {
            Toast.makeText(Home.this,"In your first Time , Please open your connection",Toast.LENGTH_LONG).show();
        }
        else  {
            weatherDB.creatTable(this);
            weatherDB.showCities();
        }
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
        list.clear();
    }

    /* get data from class getCitiesTemp that get cities name and temps from server */
    private void getDataFromServer()
    {
        getCitiesTemp temp= new getCitiesTemp(this);
        temp.gettemps();
    }

    /* add data to list */
    public static void showData(String name , String temp , String prsssure)
    {
        weatherModel model = new weatherModel("City Name: "+name,"City Temp: "+temp , "Pressure: "+prsssure  );
        list.add(model);
        mAdapter.notifyDataSetChanged();
    }

    private void getCityTemps()
    {
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // do whatever
                            weatherModel model = list.get(position);
                            String cityName = model.getName();
                            String cityId = id.get(position);
                            Intent intent = new Intent(Home.this,citytempratures.class);
                            intent.putExtra("cityName",cityName);
                            intent.putExtra("cityId",cityId);
                            startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                }));
    }
}
