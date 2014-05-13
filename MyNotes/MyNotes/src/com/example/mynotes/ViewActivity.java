package com.example.mynotes;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

public class ViewActivity extends ListActivity {
	
	private NoteOperations noteOperations;
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_view);

		noteOperations = new NoteOperations(this);
		noteOperations.open();

		List values = noteOperations.getAllNotes();

		// Use the SimpleCursorAdapter to show the
		// elements in a ListView
		ArrayAdapter adapter = new ArrayAdapter(this,
				android.R.layout.simple_list_item_1, values);
		
		setListAdapter(adapter);
	}

	public void backToAddNotesView(View view){
		Intent intent = new Intent(this, MainActivity.class);
		
		startActivity(intent);
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

