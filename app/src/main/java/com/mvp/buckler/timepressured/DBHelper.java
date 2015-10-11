package com.mvp.buckler.timepressured;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by xdionie on 10/10/2015.
 */
public class DBHelper extends SQLiteOpenHelper {
    //version number to upgrade database version
    //each time if you Add, Edit table, you need to change the
    //version number.
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "crud.db";

    public DBHelper(Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //All necessary tables you like to create will create here

        String CREATE_TABLE_STUDENT = "CREATE TABLE " + Task.TABLE  + "("
                + Task.KEY_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + Task.KEY_name + " TEXT, "
                + Task.KEY_description+ " TEXT, "
                + Task.KEY_duration + " INTEGER , "
                + Task.Key_type + "TEXT )";

        db.execSQL(CREATE_TABLE_STUDENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed, all data will be gone!!!
        db.execSQL("DROP TABLE IF EXISTS " + Task.TABLE);

        // Create tables again
        onCreate(db);

    }

}
