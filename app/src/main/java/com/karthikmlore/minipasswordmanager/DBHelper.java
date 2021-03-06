/**
 * Mini Password Manager
 *
 * Copyright (c) 2014-2015 Karthik M'lore
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.karthikmlore.minipasswordmanager;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private SQLiteDatabase database;

    public DBHelper(Context context){
        super(context,"Araine",null,3);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE APP_SETTINGS (PATTERN VARCHAR(255) NOT NULL);");
            db.execSQL("CREATE TABLE RECORDS (ID INTEGER PRIMARY KEY, TITLE VARCHAR(60) NOT NULL, URL VARCHAR(255) NOT NULL, USERNAME VARCHAR(60) NOT NULL, PASSWORD VARCHAR(60) NOT NULL, NOTES TEXT NOT NULL);");
        } catch(SQLException e) {
            Toast.makeText(context, "Error Setting Up Database", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
    }

    public SQLiteDatabase openDB(boolean write) {
        try {
            if (write)
                database = getWritableDatabase();
            else
                database = getReadableDatabase();
        } catch (SQLiteException e) {
            Toast.makeText(context, "Error accessing database", Toast.LENGTH_SHORT).show();
        }
        return database;
    }

    public void closeDB() {
        close();
    }


}