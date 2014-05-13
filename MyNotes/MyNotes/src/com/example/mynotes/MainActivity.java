package com.example.mynotes;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class MainActivity extends ListActivity {

	private NoteOperations noteOperations;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);

		noteOperations = new NoteOperations(this);
		noteOperations.open();

		List values = noteOperations.getAllNotes();

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, values);
		
		setListAdapter(adapter);
	}

	public void addNote(View view) {

		ArrayAdapter adapter = (ArrayAdapter) getListAdapter();

		EditText text = (EditText) findViewById(R.id.editText1);
		Note note = noteOperations.addNote(text.getText().toString());

		adapter.add(note);

	}

	public void deleteFirstNote(View view) {

		ArrayAdapter adapter = (ArrayAdapter) getListAdapter();
		Note note = null;

		if (getListAdapter().getCount() > 0) {
			note = (Note) getListAdapter().getItem(0);
			noteOperations.deleteNote(note);
			adapter.remove(note);
		}

	}

	@Override
	protected void onResume() {
		noteOperations.open();
		super.onResume();
	}

	@Override
	protected void onPause() {
		noteOperations.close();
		super.onPause();
	}

}
