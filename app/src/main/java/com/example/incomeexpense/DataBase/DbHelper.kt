package com.example.incomeexpense.DataBase

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.incomeexpense.Fragment.AmountFragment
import com.example.incomeexpense.ModelClass.TransModel

class DbHelper(
    context: Context?
) : SQLiteOpenHelper(context, "Hasti.db", null, 1) {

    var TABLE_NAME  = "trans"
    var ID = "id"
    var AMOUNT = "amount"
    var CATEGORY = "category"
    var NOTE = "note"
    var IS_EXPENSE = "isexpense"

    override fun onCreate(p0: SQLiteDatabase?) {
        var que = "CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY AUTOINCREMENT,$AMOUNT INTEGER," +
                "$CATEGORY TEXT,$NOTE TEXT,$IS_EXPENSE INTEGER)"
        p0?.execSQL(que)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    fun addTrans(transModel: TransModel){
        var db = writableDatabase
        var values = ContentValues().apply {
            transModel.apply {
                put(AMOUNT,amount)
                put(CATEGORY,category)
                put(NOTE,note)
                put(IS_EXPENSE,isExpense)
            }
        }
        db.insert(TABLE_NAME,null,values)
    }
    fun getTrans(): ArrayList<TransModel> {
        var transList = ArrayList<TransModel>()
        var db = readableDatabase
        var que = "SELECT * FROM $TABLE_NAME"
        var cursor = db.rawQuery(que,null)
        cursor.moveToFirst()
        for (i in 0..cursor.count-1){
            var id = cursor.getInt(0)
            var amount = cursor.getInt(1)
            var category = cursor.getString(2)
            var note = cursor.getString(3)
            var isExpense = cursor.getInt(4)
            var model = TransModel(id,amount,category,note,isExpense)
            transList.add(model)
            cursor.moveToNext()
        }
        return transList
    }
    fun updateTrans(transModel: TransModel){
        var db = writableDatabase
        var values = ContentValues().apply {
            transModel.apply {
                put(AMOUNT,amount)
                put(CATEGORY,category)
                put(NOTE,note)
                put(IS_EXPENSE,isExpense)
            }
        }
        db.update(TABLE_NAME,values,"id= ${transModel.id}",null)
    }
    fun deleteTrans(id : Int){
        var db = writableDatabase
        db.delete(TABLE_NAME,"id=$id",null)

    }
}