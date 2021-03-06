package com.example.notesie;

public class Note {
	
	private int id;
	
	private String title;
	private String note;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public String toString() {
		return note;
	}
}
