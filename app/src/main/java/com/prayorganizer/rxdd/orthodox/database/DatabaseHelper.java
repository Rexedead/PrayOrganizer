package com.prayorganizer.rxdd.orthodox.database;


import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static String DB_PATH = "";
    private static final String DATABASE_NAME = "Orthodox.db3";
    private SQLiteDatabase mDatabase;
    private final Context mContext;



    public DatabaseHelper (Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        if (android.os.Build.VERSION.SDK_INT >= 4.2) {
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/"; //context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/"; //"/data/data/" + context.getPackageName() + "/databases/"
        }
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    public Cursor getCursor(String query){

        Cursor cursor = mDatabase.rawQuery(query, null);
        if (cursor == null) return null;
        System.out.println(Arrays.toString(cursor.getColumnNames()));
        return cursor;

    }
    public synchronized void close() {
        if (mDatabase != null) {
            mDatabase.close();
        }
        super.close();
    }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (newVersion > oldVersion) {
                copyDB();
            }
        }

//TODO при добавлении новых строк база не обновляется после перезаливки приложения, надо чистить через file explorer
    public void createDB() throws IOException {

        boolean existDB = checkDB();
        if (existDB) {
            return;
        }
        getWritableDatabase();
        copyDB();

    }


    private boolean checkDB() {
        SQLiteDatabase tempDB = null;
        try {
            String path = DB_PATH + DATABASE_NAME;
            tempDB = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLException s) {
            System.out.println("ErrorOpenDB");
        }
        if (tempDB != null) {
            tempDB.close();
        }
        return tempDB != null;
    }


    private void copyDB() {
        try {
            InputStream inputStream = mContext.getAssets().open(DATABASE_NAME);
            String outputFileName = DB_PATH + DATABASE_NAME;
            OutputStream outputStream = new FileOutputStream(outputFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDB() {
        String path = DB_PATH + DATABASE_NAME;
        mDatabase = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
    }

    public SQLiteDatabase getDB() {
        return mDatabase;
    }



}































