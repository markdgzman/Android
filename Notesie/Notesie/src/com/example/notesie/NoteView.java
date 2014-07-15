package com.example.notesie;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoteView extends Activity {

	private NoteOperations noteOperations;
	private DataBaseWrapper dbHelper;
	
	private Cursor note;
	private Long mRowId;
	private TextView titleText;
	private TextView noteText;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		noteOperations = new NoteOperations(this);
        noteOperations.open();
		
		setContentView(R.layout.fragment_note_view);
		
		
		
        
        titleText = (TextView) findViewById(R.id.noteViewTitleText);
        noteText = (TextView) findViewById(R.id.noteViewNoteText);
        
		
		
        mRowId = (savedInstanceState == null) ? null :
            (Long) savedInstanceState.getSerializable(dbHelper.NOTE_ID);
        if (mRowId == null) {
            Bundle extras = getIntent().getExtras();
            mRowId = extras != null ? extras.getLong(dbHelper.NOTE_ID)
                                    : null;
        }
        
        populateFields();
        
        noteOperations.close();
        
	}

	
	public void populateFields() {
		
		titleText = (TextView) findViewById(R.id.noteViewTitleText);
		noteText = (TextView) findViewById(R.id.noteViewNoteText);
		
		if(mRowId != null){
			note = noteOperations.getNote(mRowId+1);
			
			startManagingCursor(note);
        	titleText.setText(note.getString(note.getColumnIndexOrThrow(dbHelper.NOTE)));
        	noteText.setText(note.getString(note.getColumnIndexOrThrow(dbHelper.TITLE)));
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.note_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void mainViewBack(View view){
    	Intent intent = new Intent(this, MainActivity.class);
    	
    	startActivity(intent);
    }
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_note_view,
					container, false);
			return rootView;
		}
	}

}
