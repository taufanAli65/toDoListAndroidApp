package net.thehornyastudio.todoapps.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import net.thehornyastudio.todoapps.R;
import net.thehornyastudio.todoapps.adapter.TugasActivity;
import net.thehornyastudio.todoapps.db.DbHelper;
import net.thehornyastudio.todoapps.model.Tugas;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {
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

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListDataActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}