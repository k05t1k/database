package com.example.dbapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {


    private EditText editTextName, editTextMark;
    private Button addButton;
    private DatabaseHelper dbHelper;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        editTextName = findViewById(R.id.editTextName);

        editTextMark = findViewById(R.id.editTextMark);

        addButton = findViewById(R.id.add);

        dbHelper = new DatabaseHelper(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBookToDatabase();
            }
        });
    }

    private void addBookToDatabase() {
        String bookName = editTextName.getText().toString().trim();
        String bookAuthor = editTextMark.getText().toString().trim();

        if (bookName.isEmpty() || bookAuthor.isEmpty()) {
            Toast.makeText(this, "Empty values", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            long result = dbHelper.addBook(bookName, bookAuthor);
            if (result > 0) {
                Toast.makeText(this, "Car added", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(AddActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error DB: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
