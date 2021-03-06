package com.example.mynotes;



import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NoteOperations {

	// Database fields
	private DataBaseWrapper dbHelper;
	private String[] NOTE_TABLE_COLUMNS = { DataBaseWrapper.NOTE_ID, DataBaseWrapper.NOTE };
	private SQLiteDatabase database;

	public NoteOperations(Context context) {
		dbHelper = new DataBaseWrapper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Note addNote(String note) {
		Note newNote = null;
		if (note.equals("")){
			return newNote;
		}else{
					
		ContentValues values = new ContentValues();

		values.put(DataBaseWrapper.NOTE, note);

		long noteID = database.insert(DataBaseWrapper.NOTES, null, values);

		// now that the note is created return it ...
		Cursor cursor = database.query(DataBaseWrapper.NOTES,
				NOTE_TABLE_COLUMNS, DataBaseWrapper.NOTE_ID + " = "
						+ noteID, null, null, null, null);

		cursor.moveToFirst();

		newNote = parseNote(cursor);
		cursor.close();
		return newNote;
		}
	}

	public void deleteNote(Note note) {
		long id = note.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(DataBaseWrapper.NOTES, DataBaseWrapper.NOTE_ID
				+ " = " + id, null);
	}

	public List getAllNotes() {
		List notes = new ArrayList();

		Cursor cursor = database.query(DataBaseWrapper.NOTES,
				NOTE_TABLE_COLUMNS, null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Note note = parseNote(cursor);
			notes.add(note);
			cursor.moveToNext();
		}

		cursor.close();
		return notes;
	}

	private Note parseNote(Cursor cursor) {
		Note note = new Note();
		note.setId((cursor.getInt(0)));
		note.setNote(cursor.getString(1));
		return note;
	}
}


