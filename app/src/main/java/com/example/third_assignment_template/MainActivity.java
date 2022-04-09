package com.example.third_assignment_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    private ListView lvNotes;
    private ArrayAdapter listAdapter;
    private ArrayList<String> notesList;

    Spinner spSelectionForDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.lvNotes = findViewById(R.id.lvNotes);
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        this.notesList = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));
        this.listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, notesList);
        this.lvNotes.setAdapter(this.listAdapter);
        this.lvNotes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
                Toast.makeText(getApplicationContext(), "long clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //Your adapter loses reference to your list.
        //https://stackoverflow.com/questions/15422120/notifydatasetchange-not-working-from-custom-adapter
        notesList.clear();
        this.notesList.addAll((sp.getStringSet("notes", new HashSet<String>())));
        listAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_note_activity:
                Intent addActivityIntent = new Intent(getApplicationContext(), com.example.third_assignment_template.AddNoteActivity.class);
                startActivity(addActivityIntent);
                return true;
            case R.id.delete_note_activity:
                SharedPreferences removePref = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editorDel = removePref.edit();
                editorDel.remove(Constants.CHANGES_KEY);
                editorDel.apply();
                Intent deleteActivityIntent = new Intent(getApplicationContext(), com.example.third_assignment_template.DeleteNoteActivity.class);
                startActivity(deleteActivityIntent);
            case R.id.change_note_activity:
                EditText txtName = findViewById(R.id.txtName);

                SharedPreferences sharedPref = this.getSharedPreferences(Constants.CHANGES_FILE_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString(Constants.CHANGES_KEY, txtName.getText().toString());
                editor.apply();

                Intent changeActivityIntent = new Intent(getApplicationContext(), com.example.third_assignment_template.ChangeNoteActivity.class);
                startActivity(changeActivityIntent);
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
