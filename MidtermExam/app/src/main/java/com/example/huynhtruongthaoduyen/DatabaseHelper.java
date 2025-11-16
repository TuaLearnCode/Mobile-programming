package com.example.huynhtruongthaoduyen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cinema.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TICKET = "Tickets";
    private static final String COL_ID = "id";
    private static final String COL_TEN = "tenPhim";
    private static final String COL_GIO = "gioChieu";
    private static final String COL_GHE = "ghe";
    private static final String COL_TRANGTHAI = "trangThai";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_TICKET + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TEN + " TEXT, " +
                COL_GIO + " TEXT, " +
                COL_GHE + " TEXT, " +
                COL_TRANGTHAI + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TICKET);
        onCreate(db);
    }

    public void insertTicket(String ten, String gio, String ghe, String trangThai) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TEN, ten);
        values.put(COL_GIO, gio);
        values.put(COL_GHE, ghe);
        values.put(COL_TRANGTHAI, trangThai);
        db.insert(TABLE_TICKET, null, values);
        db.close();
    }

    public ArrayList<Ticket> getAllTickets() {
        ArrayList<Ticket> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_TICKET, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID));
                String ten = cursor.getString(cursor.getColumnIndexOrThrow(COL_TEN));
                String gio = cursor.getString(cursor.getColumnIndexOrThrow(COL_GIO));
                String ghe = cursor.getString(cursor.getColumnIndexOrThrow(COL_GHE));
                String trangThai = cursor.getString(cursor.getColumnIndexOrThrow(COL_TRANGTHAI));
                list.add(new Ticket(id, ten, gio, ghe, trangThai));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return list;
    }
    // Cập nhật giờ chiếu của vé
    public void updateTicketTime(int id, String newTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_GIO, newTime); // COL_GIO = "gioChieu" trong DB
        db.update(TABLE_TICKET, values, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    // Cập nhật trạng thái vé
    public void updateTicketStatus(int id, String newStatus) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TRANGTHAI, newStatus); // COL_TRANGTHAI = "trangthai"
        db.update(TABLE_TICKET, values, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }


}
