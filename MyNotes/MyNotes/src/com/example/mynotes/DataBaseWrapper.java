package com.example.mynotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DataBaseWrapper extends SQLiteOpenHelper {

	public static final String NOTES = "Note";
	public static final String NOTE_ID = "_id";
	public static final String NOTE = "_note";

	private static final String DATABASE_NAME = "Note.db";
	private static final int DATABASE_VERSION = 1;

	// creation SQLite statement
	private static final String DATABASE_CREATE = "create table " + NOTES
			+ "(" + NOTE_ID + " integer primary key autoincrement, "
			+ NOTE + " text not null);";

	public DataBaseWrapper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL("DROP TABLE IF EXISTS " + NOTES);
		onCreate(db);
	}

}
