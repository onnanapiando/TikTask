package com.mvp.buckler.timepressured;

/**
 * Created by xdionie on 10/10/2015.
 */
public class Task {

    // Labels table name
    public static final String TABLE = "Task";

    // Labels Table Columns names
    public static final String KEY_ID = "id";
    public static final String KEY_name = "name";
    public static final String KEY_description = "description";
    public static final String KEY_duration = "timeduration";
    public static final String Key_type="type";

    // property help us to keep data
    public int task_id;
    public String name;
    public String description;
    public int duration;
    public String type;
}
