package net.thehornyastudio.todoapps.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.thehornyastudio.todoapps.R;
import net.thehornyastudio.todoapps.db.DbHelper;

public class addTugas extends AppCompatActivity {
    DbHelper dbHelper;
    private EditText judul,tugas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tugas);

        getSupportActionBar().setTitle("Tambah Tugas");

        dbHelper = new DbHelper(this);

        judul = findViewById(R.id.tv_item_judul);
        tugas = findViewById(R.id.tv_item_tugas);

        Button btnSave = findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(judul.getText().toString().isEmpty()){
                    Toast.makeText(addTugas.this, "Judul tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else if(tugas.getText().toString().isEmpty()){
                    Toast.makeText(addTugas.this, "Nama tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else{
                    dbHelper.addUser(judul.getText().toString(), tugas.getText().toString());
                    judul.setText("");
                    tugas.setText("");
                    Toast.makeText(addTugas.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();

                    Intent saveIntent = new Intent(addTugas.this, MainActivity.class);
                    startActivity(saveIntent);
                }
            }
        });
        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle back functionality here
                handleBackButtonClick();
            }
        });
    }

    private void handleBackButtonClick() {
        finish();
    }

}
