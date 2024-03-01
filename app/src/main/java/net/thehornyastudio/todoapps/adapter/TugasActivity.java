package net.thehornyastudio.todoapps.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.thehornyastudio.todoapps.R;
import net.thehornyastudio.todoapps.db.DbHelper;
import net.thehornyastudio.todoapps.model.Tugas;
import net.thehornyastudio.todoapps.ui.MainActivity;

import java.io.Serializable;
import java.util.ArrayList;

public class TugasActivity extends RecyclerView.Adapter<TugasActivity.TugasViewHolder> {
    private ArrayList<Tugas> listTugas = new ArrayList<>();
    private Activity activity;
    DbHelper dbHelper;

    public TugasActivity(Activity activity){
        this.activity = activity;
        dbHelper = new DbHelper(activity);
    }

    public ArrayList<Tugas> getListTugas(){
        return listTugas;
    }

    public void setListTugas(ArrayList<Tugas> listTugas){
        if (listTugas.size() > 0){
            this.listTugas.clear();
        }
        this.listTugas.addAll(listTugas);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TugasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tugas, parent, false);
        return new TugasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TugasViewHolder holder, int position) {
        holder.tvJudul.setText(listTugas.get(position).getJudul());
        holder.tvTugas.setText(listTugas.get(position).getTugas());

        holder.btnSave.setOnClickListener((View v) -> {
            Intent updateIntent = new Intent(activity, MainActivity.class);
            updateIntent.putExtra("user", (Serializable) listTugas.get(position));
            activity.startActivity(updateIntent);
        });

        holder.btnDelete.setOnClickListener((View v) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);

            builder.setTitle("Konfirmasi Hapus");
            builder.setMessage("Apakah anda yakin menghapus ?");

            builder.setPositiveButton("YA", (dialog, which) -> {
                dbHelper.deleteUser(listTugas.get(position).getId());
                Toast.makeText(activity, "Hapus Berhasil", Toast.LENGTH_SHORT).show();
                Intent delIntent = new Intent(activity, MainActivity.class);
                activity.startActivity(delIntent);
                activity.finish();
            });

            builder.setNegativeButton("TIDAK", (dialog, which) -> dialog.dismiss());

            AlertDialog alert = builder.create();
            alert.show();
        });
    }

    @Override
    public int getItemCount() {
        return listTugas.size();
    }

    public class TugasViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvTugas;
        Button btnDelete, btnSave;

        public TugasViewHolder(@NonNull View itemView) {
            super(itemView);
            tvJudul = itemView.findViewById(R.id.tv_item_judul);
            tvTugas = itemView.findViewById(R.id.tv_item_tugas);
            btnDelete = itemView.findViewById(R.id.btn_delete);
            btnSave = itemView.findViewById(R.id.btn_save);
        }
    }
}
