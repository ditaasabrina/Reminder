package com.amikom.two.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.amikom.two.R;
import com.amikom.two.model.Jadwal;
import java.util.List;

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.ViewHolder> {
    private Context context;
    private List<Jadwal> jadwal;
    private AdapterView.OnItemClickListener listener;

    public JadwalAdapter(Context context, List<Jadwal> jadwal,
                         AdapterView.OnItemClickListener listener) {
        this.context = context;
        this.jadwal = jadwal;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_jadwal, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Jadwal jadwal = this.jadwal.get(i);
        viewHolder.tvHari.setText(jadwal.getHari());
        viewHolder.tvMakul.setText(jadwal.getMataKuliah());
        viewHolder.tvJam.setText(jadwal.getJam());
        viewHolder.tvRuangan.setText(jadwal.getRuangan());
        viewHolder.tvDosen.setText(jadwal.getDosen());

        viewHolder.itemView.setOnClickListener(v -> listener.onItemClick(null, viewHolder.itemView, i, i));
    }

    @Override
    public int getItemCount() {
        return jadwal.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHari;
        TextView tvMakul;
        TextView tvJam;
        TextView tvRuangan;
        TextView tvDosen;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHari = itemView.findViewById(R.id.jadwal_hari);
            tvMakul = itemView.findViewById(R.id.jadwal_makul);
            tvJam = itemView.findViewById(R.id.jadwal_jam);
            tvRuangan = itemView.findViewById(R.id.jadwal_ruangan);
            tvDosen = itemView.findViewById(R.id.jadwal_dosen);
        }
    }
}
