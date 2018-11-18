package com.example.a7medassem.dawadoztask.Volley;

import android.content.Context;
import android.util.Log;
import com.example.a7medassem.dawadoztask.Activity.citytempratures;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class getCityFiveTemp {

    private Context context;

    public getCityFiveTemp(Context context) {
        this.context = context;
    }

    public void gettemps(String id)
    {
        String api = "http://api.openweathermap.org/data/2.5/forecast?id="+id+"&APPID=78922e23114e02ebbce2d40bc5f5a8aa";
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
            for(int i=0;i<40;i+=8)
            {
                JSONObject cities = arrList.getJSONObject(i);
                String date = cities.getString("dt_txt");
                JSONObject main = cities.getJSONObject("main");
                String temp = main.getString("temp");
                citytempratures.showData(temp,date);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
