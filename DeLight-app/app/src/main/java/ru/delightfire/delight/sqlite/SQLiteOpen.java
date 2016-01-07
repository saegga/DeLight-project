package ru.delightfire.delight.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sergei on 06.01.2016.
 */
public class SQLiteOpen extends SQLiteOpenHelper {

    public SQLiteOpen(Context context, String name, int version){
        super(context, name, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLiteFields.CREATE_TABLE_MEET);
        db.execSQL(SQLiteFields.CREATE_TABLE_CUSTOMERS);
        db.execSQL(SQLiteFields.CREATE_MEET_MEMBER);
        db.execSQL(SQLiteFields.CREATE_TABLE_EVENT_PLACES);
        db.execSQL(SQLiteFields.CREATE_TABLE_HAVE_REQUISITE);
        db.execSQL(SQLiteFields.CREATE_TABLE_LIST_PERFORMANCES);
        db.execSQL(SQLiteFields.CREATE_TABLE_PERF_MEMBER);
        db.execSQL(SQLiteFields.CREATE_TABLE_PERFORMANCE);
        db.execSQL(SQLiteFields.CREATE_TABLE_REQUISITE);
        db.execSQL(SQLiteFields.CREATE_TABLE_SHOW);
        db.execSQL(SQLiteFields.CREATE_TABLE_TRAINING);
        db.execSQL(SQLiteFields.CREATE_TABLE_USER);
        db.execSQL(SQLiteFields.CREATE_TABLE_TRAINING_MEMBER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        recreateDb(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        recreateDb(db);
    }

    public void recreateDb(SQLiteDatabase db){
        db.execSQL(SQLiteFields.DELETE_DATA_CUSTOMERS);
        db.execSQL(SQLiteFields.DELETE_DATA_EVENT_PLACES);
        db.execSQL(SQLiteFields.DELETE_DATA_HAVE_REQUISITE);
        db.execSQL(SQLiteFields.DELETE_DATA_LIST_PERFORMANCES);
        db.execSQL(SQLiteFields.DELETE_DATA_MEET);
        db.execSQL(SQLiteFields.DELETE_DATA_MEET_MEMBER);
        db.execSQL(SQLiteFields.DELETE_DATA_PERF_MEMBER);
        db.execSQL(SQLiteFields.DELETE_DATA_PERFORMANCE);
        db.execSQL(SQLiteFields.DELETE_DATA_REQISITE);
        db.execSQL(SQLiteFields.DELETE_DATA_SHOW);
        db.execSQL(SQLiteFields.DELETE_DATA_TRAINING_MEMBER);
        db.execSQL(SQLiteFields.DELETE_DATA_USERS);
        db.execSQL(SQLiteFields.DELETE_DATA_TRAININGS);

        onCreate(db);
    }
}
