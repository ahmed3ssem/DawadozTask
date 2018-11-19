package com.example.a7medassem.dawadoztask.SQL;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.a7medassem.dawadoztask.Activity.Home;

import java.io.File;

public class weatherDB {

    private static SQLiteDatabase tasksSQLiteDB;
    private static Cursor dbCursor;

    public static void creatTable(Context context)
    {
        try {
            tasksSQLiteDB = context.openOrCreateDatabase("taskDB", Context.MODE_PRIVATE, null);

            tasksSQLiteDB.execSQL("CREATE TABLE IF NOT EXISTS city(name VARCHAR ,city_id INTEGER PRIMARY KEY )");

            tasksSQLiteDB.execSQL("CREATE TABLE IF NOT EXISTS temps(date VARCHAR ,temps VARCHAR ,pressure VARCHAR,city_id INTEGER,forcast_temp VARCHAR,temp_id INTEGER PRIMARY KEY AUTOINCREMENT)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertDataToCities(String Name ,String temp , String pressure , int id)
    {
        try {
            tasksSQLiteDB.execSQL("insert into city (name,city_id) values('"+Name+"','"+id+"')");

            tasksSQLiteDB.execSQL("insert into temps (temps,pressure,city_id) values('"+temp+"','"+pressure+"','"+id+"')");

        } catch (SQLException e) {
        }
    }

    public static void insertDataToTemps(int id , String date , String forcat_temp)
    {
        try {

            tasksSQLiteDB.execSQL("insert into temps (forcast_temp,date,city_id) values('"+forcat_temp+"','"+date+"','"+id+"')");
            Log.d("Messi","SUCCESS");
        } catch (SQLException e) {
            Log.d("Messi",String.valueOf(e));
        }
    }

    public static void showCities()
    {
        dbCursor = tasksSQLiteDB.rawQuery("SELECT DISTINCT name , temps ,pressure" + " FROM city,temps "+"WHERE city.city_id = temps.city_id",null);
        try {
            if (dbCursor != null && dbCursor.moveToFirst()) {
                do {
                    String Name = dbCursor.getString(0);
                    String Temp = dbCursor.getString(1);
                    String Preesure = dbCursor.getString(2);
                    Home.showData(Name,Temp,Preesure);
                } while (dbCursor.moveToNext());}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deletBD(Context context)
    {
        context.deleteDatabase("taskDB");
    }

    public static boolean doesDatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }
}
