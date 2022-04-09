package com.example.third_assignment_template;

import androidx.appcompat.app.AppCompatActivity;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class DeleteNoteActivity extends AppCompatActivity {
    Spinner spSelectionForDelete;
    SpinnerAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        spSelectionForDelete = findViewById(R.id.spSelectionForDelete);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        ArrayList<String> notesList = new ArrayList<String>(sp.getStringSet("notes", new HashSet<String>()));
        ArrayAdapter listAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, notesList);
        spSelectionForDelete.setAdapter(this.listAdapter);


        }
    public void onDeleteNoteClick(View view) {
        EditText txtNote = findViewById(R.id.txtNote);

        //https://stackoverflow.com/questions/14034803/misbehavior-when-trying-to-store-a-string-set-using-sharedpreferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor spEd = sp.edit();

        Set<String> oldSet = sp.getStringSet("notes", new HashSet<String>());
        String selectedNote = spSelectionForDelete.getSelectedItem().toString();
        for(String item : oldSet) {
            if(item.equalsIgnoreCase(selectedNote)) {
                oldSet.remove(item);
                //Toast.makeText(this, "Found it! =>" + item, Toast.LENGTH_SHORT).show();
            }
            }
        }

    }
