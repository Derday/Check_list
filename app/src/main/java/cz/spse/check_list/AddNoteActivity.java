package cz.spse.check_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import cz.spse.check_list.model.MyDatabaseHelper;
import cz.spse.check_list.model.Note;

public class AddNoteActivity extends AppCompatActivity {

    private EditText mETName, mETDesc;
    private FloatingActionButton mSaveBtn, mCheckBtn;
    private MyDatabaseHelper mDBh;
    private int id, finished;
    private ColorStateList green, orange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        green = ColorStateList.valueOf(Color.rgb(85, 255, 0));
        orange = ColorStateList.valueOf(Color.rgb(255, 128, 0));

        mETName   = findViewById(R.id.nameEditText);
        mETDesc   = findViewById(R.id.descEditText);
        mSaveBtn  = findViewById(R.id.saveBtn);
        mCheckBtn = findViewById(R.id.checkBtn);
        mDBh      = new MyDatabaseHelper(this);

        Bundle bu = getIntent().getExtras();
        finished = 0;
        id = bu.getInt("ID");
        if (id != -1){
            Note note = mDBh.getNote(id);
            mETDesc.setText(note.getDescription());
            mETName.setText(note.getName());
            finished = note.isFinished();
        }

        if (finished == 0){
            mCheckBtn.setBackgroundTintList(orange);
        } else {
            mCheckBtn.setBackgroundTintList(green);
        }

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mETName.getText().toString().matches("")){
                    Toast.makeText(getApplicationContext(), "You didn't fill TODO", Toast.LENGTH_SHORT).show();
                } else if (mETDesc.getText().toString().matches("")) {
                    Toast.makeText(getApplicationContext(), "You didn't fill Description", Toast.LENGTH_SHORT).show();
                } else {
                    mDBh.mInsert(new Note(id, mETName.getText().toString(), mETDesc.getText().toString(), finished));
                    Intent ei = new Intent(AddNoteActivity.this, MainActivity.class);
                    startActivity(ei);
                }

            }
        });

        mCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (finished == 0){
                    finished = 1;
                    mCheckBtn.setBackgroundTintList(green);
                } else {
                    finished = 0;
                    mCheckBtn.setBackgroundTintList(orange);
                }
            }
        });
    }
}