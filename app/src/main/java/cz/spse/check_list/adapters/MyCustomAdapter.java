package cz.spse.check_list.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import cz.spse.check_list.R;
import cz.spse.check_list.model.Note;

public class MyCustomAdapter extends BaseAdapter {
    private Context context;
    private List<Note> notes;
    public MyCustomAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public int getCount() {return notes.size();}

    @Override
    public Object getItem(int i) {return notes.get(i);}

    @Override
    public long getItemId(int i) {return notes.get(i).getId();}

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {


        LayoutInflater layInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layInflater.inflate(R.layout.note_item, null);
        TextView tv_name = convertView.findViewById(R.id.name_item);
        TextView tv_desc = convertView.findViewById(R.id.desc_item);

        Note note = notes.get(i);

        tv_name.setText(note.getName());
        tv_desc.setText(note.getDescription()+"");



        return convertView;
    }
}
