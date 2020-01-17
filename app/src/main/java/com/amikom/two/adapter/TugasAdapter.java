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
import com.amikom.two.model.Tugas;

import java.util.List;

    public class TugasAdapter extends RecyclerView.Adapter<TugasAdapter.ViewHolder> {
        private Context context;
        private List<Tugas> tugas;
        private AdapterView.OnItemClickListener listener;

        public TugasAdapter(Context context, List<Tugas> tugas,
                            AdapterView.OnItemClickListener listener) {
            this.context = context;
            this.tugas = tugas;
            this.listener = listener;
        }

        @NonNull
        @Override
        public TugasAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View itemView = LayoutInflater.from(context)
                    .inflate(R.layout.item_tugas, viewGroup, false);
            return new TugasAdapter.ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
            final Tugas tugas = this.tugas.get(i);
            viewHolder.tvMatakuliah.setText(tugas.getMatakuliah());
            viewHolder.tvJenistugas.setText(tugas.getJenistugas());
            viewHolder.tvKeterangan.setText(tugas.getKeterangan());
            viewHolder.tvTanggalpengumpulan.setText(tugas.getTanggalpengumpulan());
            viewHolder.tvJampengumpulan.setText(tugas.getJampengumpulan());

            viewHolder.itemView.setOnClickListener(v -> listener.onItemClick(null, viewHolder.itemView, i, i));
        }

        @Override
        public int getItemCount() {
            return tugas.size();
        }

         class ViewHolder extends RecyclerView.ViewHolder {
             TextView tvMatakuliah;
             TextView tvJenistugas;
             TextView tvKeterangan;
             TextView tvTanggalpengumpulan;
             TextView tvJampengumpulan;

             ViewHolder(@NonNull View itemView) {
                super(itemView);
                tvMatakuliah = itemView.findViewById(R.id.matakuliah);
                tvJenistugas = itemView.findViewById(R.id.jenis_tugas);
                tvKeterangan = itemView.findViewById(R.id.keterangan);
                tvTanggalpengumpulan= itemView.findViewById(R.id.tanggal_kumpul);
                tvJampengumpulan = itemView.findViewById(R.id.jam_kumpul);
            }
        }
    }



