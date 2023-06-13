package cz.spse.check_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import cz.spse.check_list.adapters.MyCustomAdapter;
import cz.spse.check_list.model.MyDatabaseHelper;
import cz.spse.check_list.model.Note;

public class MainActivity extends AppCompatActivity {

    private TextView mTVname, mTVdesc;
    private FloatingActionButton mAddBtn;
    private ListView mListView;
    private MyDatabaseHelper mDBh;
    private List<Note> notes;
    private MyCustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTVname     = findViewById(R.id.viewName);
        mTVdesc     = findViewById(R.id.viewDesc);
        mAddBtn     = findViewById(R.id.addBtn);
        mListView   = findViewById(R.id.listView);

        mDBh    = new MyDatabaseHelper(this);
        notes   = mDBh.mReadAllData();
        mFillListView();

        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ei = new Intent(MainActivity.this, AddNoteActivity.class);

                startActivity(ei);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                notes = mDBh.mReadAllData();
                mDBh.changeState(notes.get(i));
                mFillListView();

            }
        });



    }

    private void mFillListView() {
        notes = mDBh.mReadAllData();
        mAdapter = new MyCustomAdapter(this, notes);

        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

    }


}