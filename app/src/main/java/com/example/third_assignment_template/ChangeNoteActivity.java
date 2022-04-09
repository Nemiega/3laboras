package com.example.third_assignment_template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;


public class ChangeNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_note);

        SharedPreferences sharedPref = this.getSharedPreferences(Constants.CHANGES_FILE_NAME, Context.MODE_PRIVATE);
        String savedValue = sharedPref.getString(Constants.CHANGES_KEY, "defaultValue");

        Toast.makeText(this, savedValue, Toast.LENGTH_LONG).show();
    }
}