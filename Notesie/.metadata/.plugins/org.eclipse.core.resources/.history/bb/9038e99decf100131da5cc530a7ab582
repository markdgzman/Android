package com.example.notesie;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {

	private NoteOperations noteOperations;
	private DataBaseWrapper dbHelper;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        
        noteOperations = new NoteOperations(this);
        noteOperations.open();
        
        //Note note = noteOperations.addNote("This is a note","Title");
        
        
        
        List values = noteOperations.getAllNotes();
        
        //Use simple cursor to show elements
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }
    
    @Override
    protected void onListItemClick (ListView l, View v, int position, long id){
    	super.onListItemClick(l, v, position, id);
    	Intent i = new Intent(this, NoteView.class);
    	i.putExtra(dbHelper.NOTE_ID, id);
    	startActivityForResult(i, 1);
    	
    }

    public void addNotesView(View view){
    	Intent intent = new Intent(this, NoteAdd.class);
    	
    	startActivity(intent);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    }
}
