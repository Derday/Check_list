package cz.spse.check_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cz.spse.check_list.model.MyDatabaseHelper;
import cz.spse.check_list.model.Note;

public class AddNoteActivity extends AppCompatActivity {

    private EditText mETName, mETDesc;
    private FloatingActionButton mSaveBtn;
    private MyDatabaseHelper mDBh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        mETName = findViewById(R.id.nameEditText);
        mETDesc = findViewById(R.id.descEditText);
        mSaveBtn = findViewById(R.id.saveBtn);

        mDBh    = new MyDatabaseHelper(this);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDBh.mInsert(new Note(0, mETName.getText().toString(), mETDesc.getText().toString(), 0));

                Intent ei = new Intent(AddNoteActivity.this, MainActivity.class);
                startActivity(ei);
            }
        });
    }
}