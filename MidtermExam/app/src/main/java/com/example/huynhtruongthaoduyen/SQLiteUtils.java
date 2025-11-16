package com.example.huynhtruongthaoduyen;

import android.database.sqlite.SQLiteDatabase;

public class SQLiteUtils {
    public static void deleteTicket(DatabaseHelper dbHelper, int ticketId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("Tickets", "id = ?", new String[]{String.valueOf(ticketId)});
        db.close();
    }
}
