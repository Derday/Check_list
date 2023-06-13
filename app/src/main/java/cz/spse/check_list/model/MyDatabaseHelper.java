package cz.spse.check_list.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Notes.db";
    private static final int DB_VERSION = 1;
    private final String CREATE_TABLE = "CREATE TABLE notes_table (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, description TEXT, finished INTEGER)";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS notes_table");
        onCreate(db);
    }

    public List<Note> mReadAllData(){
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase mdb = this.getReadableDatabase();

        Cursor cur = mdb.rawQuery("SELECT * FROM notes_table", null);
        while (cur.moveToNext()){
            Note note = new Note(cur.getInt(0), cur.getString(1), cur.getString(2), cur.getInt(4));
            notes.add(note);
        }
        return notes;
    }    public void changeState(Note note){
        int lastState = 1;
        SQLiteDatabase mdb = this.getReadableDatabase();
        Cursor cur = mdb.rawQuery("SELECT finished FROM notes_table WHERE id == "+note.getId()+";", null);
        while (cur.moveToNext()){
            if (cur.getInt(0) == 1){
                lastState = 0;
            } else {
                lastState = 1;
            }
        }
        mdb = this.getWritableDatabase();
        ContentValues mValues = new ContentValues();
        mValues.put("NAME", note.getName());
        mValues.put("DESCRIPTION", note.getDescription());
        mValues.put("FINISHED", lastState);
        mdb.update("notes_table", mValues, "_id = ?", new String[]{note.getId()+""});


    }
    public void update(Note note){
        SQLiteDatabase mdb = this.getWritableDatabase();
        ContentValues mValues = new ContentValues();
        mValues.put("NAME", note.getName());
        mValues.put("DESCRIPTION", note.getDescription());
        mValues.put("FINISHED", note.isFinished());

        mdb.update("notes_table", mValues, "_id = ?", new String[]{note.getId()+""});

    }

    public Note nextNote(){
        SQLiteDatabase mdb = this.getReadableDatabase();
        Cursor cur = mdb.rawQuery("SELECT * FROM notes_table WHERE finished == 0 LIMIT 1", null);
        Note note = new Note(cur.getInt(0), cur.getString(1), cur.getString(2), cur.getInt(4));
        return note;
    }

    public boolean mInsert(Note note){
        SQLiteDatabase mdb = this.getWritableDatabase();
        ContentValues mValues = new ContentValues();

        mValues.put("NAME", note.getName());
        mValues.put("DESCRIPTION", note.getDescription());
        mValues.put("FINISHED", note.isFinished());
        // id se generuje automaticky

        long result = mdb.insert("notes_table", null, mValues);
        if (result == -1) return false;
        else return true;
    }

    public void mDelete(Note note){
        SQLiteDatabase mdb = this.getWritableDatabase();
        mdb.delete("persons_table", "id = ?",
                new String[]{note.getId()+""});
    }

}
