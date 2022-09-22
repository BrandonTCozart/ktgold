package com.example.ktapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context:Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "todoList.db"
        private const val TABLE_NAME = "table_todo"
        private const val TEXT = "text"
        private const val DATE = "date"


    }

    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = ("CREATE TABLE" + TABLE_NAME + " ( "
                + DATE + "TEXT," + TEXT + "TEXT,"
                + ")")

        db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertTODO(std: todoListClass): Long{
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(TEXT, std.text)
        contentValues.put(DATE, std.date)

        val sucess = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return sucess
    }

    fun getAllTODO(): ArrayList<todoListClass>{

        val stdList: ArrayList<todoListClass> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase

        val cursor: Cursor?

        try{
            cursor = db.rawQuery(selectQuery, null)

        }catch (e: Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var text:String
        var date:String


        if(cursor.moveToFirst()){
            do{
                text = cursor.getString(0)
                date = cursor.getString(1)

                val std = todoListClass(text = text,date = date )
                stdList.add(std)

            }while (cursor.moveToNext())
        }

        return stdList

    }

}