package com.example.dbapp;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DeleteActivity extends AppCompatActivity {

    private Spinner spinnerDeleteCarId;

    private TextView textCarName, textCarAuthor;
    private Button buttonDelete;

    private DatabaseHelper dbHelper;
    private ArrayList<Integer> bookIds;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        spinnerDeleteCarId = findViewById(R.id.spinner_delete_car_id);
        textCarName = findViewById(R.id.carName);
        textCarAuthor = findViewById(R.id.carMark);
        buttonDelete = findViewById(R.id.button_delete);

        dbHelper = new DatabaseHelper(this);
        bookIds = new ArrayList<>();

        loadBookIds();

        spinnerDeleteCarId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedBookId = bookIds.get(position);
                loadBookDetails(selectedBookId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonDelete.setOnClickListener(v -> deleteBook());
    }

    private void loadBookIds() {
        Cursor cursor = dbHelper.getAllBooks();
        bookIds.clear();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
            bookIds.add(id);
        }
        cursor.close();

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bookIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDeleteCarId.setAdapter(adapter);
    }

    private void loadBookDetails(int bookId) {
        Cursor cursor = dbHelper.getAllBooks();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_ID));
            if (id == bookId) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_NAME));
                String author = cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COLUMN_AUTHOR));
                textCarName.setText(name);
                textCarAuthor.setText(author);
                break;
            }
        }
        cursor.close();
    }

    private void deleteBook() {
        int selectedBookId = bookIds.get(spinnerDeleteCarId.getSelectedItemPosition());

        int result = dbHelper.deleteBookById(selectedBookId);

        if (result > 0) {
            Toast.makeText(this, "Car deleted", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
