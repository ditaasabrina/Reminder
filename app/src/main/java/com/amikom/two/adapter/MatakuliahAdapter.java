package com.amikom.two.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amikom.two.R;
import com.amikom.two.model.Matakuliah;

import java.util.List;

public class MatakuliahAdapter extends RecyclerView.Adapter<MatakuliahAdapter.ViewHolder>{
    private Context context;
    private List<Matakuliah> matakuliah;
    private AdapterView.OnItemClickListener listener;

    public MatakuliahAdapter(Context context, List<Matakuliah> matakuliah,
                             AdapterView.OnItemClickListener listener) {
        this.context = context;
        this.matakuliah = matakuliah;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_matakuliah, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Matakuliah matakuliah = this.matakuliah.get(i);
        viewHolder.tvNamamatkul.setText(matakuliah.getNamamatkul());
        viewHolder.tvDosenpengampu.setText(matakuliah.getDosenpengajar());
        viewHolder.tvJumlahsks.setText(matakuliah.getSks());

        viewHolder.itemView.setOnClickListener(v -> listener.onItemClick(null, viewHolder.itemView, i, i));
    }

    @Override
    public int getItemCount() { return matakuliah.size(); }

    class ViewHolder extends RecyclerView.ViewHolder {
         TextView tvNamamatkul;
         TextView tvDosenpengampu;
         TextView tvJumlahsks;


       ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamamatkul = itemView.findViewById(R.id.nama_matakul);
            tvDosenpengampu = itemView.findViewById(R.id.dosen_pengajar);
            tvJumlahsks = itemView.findViewById(R.id.jumlah_sks);

        }
    }
}
