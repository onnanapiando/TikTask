package com.mvp.buckler.timepressured;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskRepo {

    private DBHelper dbHelper;

    public TaskRepo(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Task task) {

        //Open connection to write data
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Task.KEY_name, task.name);
        values.put(Task.KEY_description,task.description);
        values.put(Task.KEY_duration, task.duration);
        values.put(Task.Key_type, task.type);

        // Inserting Row
        long task_Id = db.insert(Task.TABLE, null, values);
        db.close(); // Closing database connection
        return (int) task_Id;
    }

    public void delete(int task_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // It's a good practice to use parameter ?, instead of concatenate string
        db.delete(Task.TABLE, Task.KEY_ID + "= ?", new String[] { String.valueOf(task_Id) });
        db.close(); // Closing database connection
    }

    public void update(Task task) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Task.KEY_name, task.name);
        values.put(Task.KEY_description,task.description);
        values.put(Task.KEY_duration, task.name);
        values.put(Task.Key_type, task.type);

        // It's a good practice to use parameter ?, instead of concatenate string
        db.update(Task.TABLE, values, Task.KEY_ID + "= ?", new String[] { String.valueOf(task.task_id) });
        db.close(); // Closing database connection
    }

    public ArrayList<HashMap<String, String>> getTasktList() {
        //Open connection to read only
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Task.KEY_ID + "," +
                Task.KEY_name + "," +
                Task.KEY_description + "," +
                Task.Key_type + "," +
                Task.KEY_duration +
                " FROM " + Task.TABLE;

        //Student student = new Student();
        ArrayList<HashMap<String, String>> studentList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> student = new HashMap<String, String>();
                student.put("id", cursor.getString(cursor.getColumnIndex(Task.KEY_ID)));
                student.put("name", cursor.getString(cursor.getColumnIndex(Task.KEY_name)));
                studentList.add(student);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;
    }

    public Task getStudentById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Task.KEY_ID + "," +
                Task.KEY_name + "," +
                Task.KEY_description + "," +
                Task.Key_type + "," +
                Task.KEY_duration +
                " FROM " + Task.TABLE
                + " WHERE " +
                Task.KEY_ID + "=?";

        int iCount =0;
        Task task = new Task();
        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                task.task_id =cursor.getInt(cursor.getColumnIndex(Task.KEY_ID));
                task.name =cursor.getString(cursor.getColumnIndex(Task.KEY_name));
                task.description  =cursor.getString(cursor.getColumnIndex(Task.KEY_description));
                task.duration =cursor.getInt(cursor.getColumnIndex(Task.KEY_duration));
                task.type =cursor.getString(cursor.getColumnIndex(Task.Key_type));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return task;
    }
}
