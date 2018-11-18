package com.example.a7medassem.dawadoztask.Volley;

import android.content.Context;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.a7medassem.dawadoztask.Activity.Home;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class getCitiesTemp {

    private Context context;
    private String api;
    private List<String> id = new ArrayList<>();


    public getCitiesTemp(Context context) {
        this.context = context;
        this.api= "http://api.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&APPID=78922e23114e02ebbce2d40bc5f5a8aa";
    }

    public void gettemps()
    {
        RequestQueue queue;
        StringRequest request = new StringRequest(Request.Method.GET, api, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (!response.equals(null))
                {
                    extractData(response);
                }
                else
                    {
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("error is ", "" + error);
            }
        }) {

        };
        queue = Volley.newRequestQueue((context));
        queue.add(request);
    }

    private void extractData(String response)
    {
        try {
            JSONObject mainobj = new JSONObject(response);
            JSONArray arrList = mainobj.getJSONArray("list");
            for(int i=0;i<5;i++)
            {
                JSONObject cities = arrList.getJSONObject(i);
                Home.id.add(cities.getString("id"));
                String name = cities.getString("name");
                JSONObject main = cities.getJSONObject("main");
                String temp = main.getString("temp");
                String pressure = main.getString("pressure");
                Home.showData(name,temp,pressure);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
