package net.thehornyastudio.todoapps.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.thehornyastudio.todoapps.R;
import net.thehornyastudio.todoapps.adapter.TugasActivity;
import net.thehornyastudio.todoapps.db.DbHelper;
import net.thehornyastudio.todoapps.model.Tugas;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private TugasActivity adapter;
    private ArrayList<Tugas> tugasArrayList;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rview);
        adapter = new TugasActivity(this);

        dbHelper = new DbHelper(this);
        tugasArrayList = dbHelper.getAllUser();
        adapter.setListTugas(tugasArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Button btnTambah = findViewById(R.id.btn_tambah_data);
        btnTambah.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_tambah_data) {
            Intent entryIntent = new Intent(MainActivity.this, addTugas.class);
            startActivity(entryIntent);
        }
    }
}