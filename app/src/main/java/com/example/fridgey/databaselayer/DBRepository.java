package com.example.fridgey.databaselayer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import com.example.fridgey.models.Cocktail;

import java.util.ArrayList;

public class DBRepository {
    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBRepository(Context c) {
        context = c;
    }

    public DBRepository open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(Cocktail object) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.NAME, object.name);
        contentValue.put(DatabaseHelper.DESC, object.instructions);
        contentValue.put(DatabaseHelper.IMG, object.imgurl);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }

    public ArrayList<Cocktail> fetch() {
        ArrayList<Cocktail> retVal = dbHelper.fetchFavorites();
        return retVal;
    }




    public void delete(String _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }

}
