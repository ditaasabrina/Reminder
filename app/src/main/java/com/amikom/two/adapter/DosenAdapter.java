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
import com.amikom.two.model.Dosen;
import java.util.List;

public class DosenAdapter extends RecyclerView.Adapter<DosenAdapter.ViewHolder> {
    private Context context;
    private List<Dosen> dosen;
    private AdapterView.OnItemClickListener listener;

    public DosenAdapter(Context context, List<Dosen> dosen,
                        AdapterView.OnItemClickListener listener) {
        this.context = context;
        this.dosen = dosen;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_dosen, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Dosen dosen = this.dosen.get(i);
        viewHolder.tvNamadosen.setText(dosen.getNamadosen());
        viewHolder.tvNik.setText(dosen.getNik());
        viewHolder.tvMatkuldiampu.setText(dosen.getMatkuldiampu());
        viewHolder.tvRuangan.setText(dosen.getRuangan());

        viewHolder.itemView.setOnClickListener(v -> listener.onItemClick(null, viewHolder.itemView, i, i));
    }

    @Override
    public int getItemCount() {
        return dosen.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamadosen;
        TextView tvNik;
        TextView tvMatkuldiampu;
        TextView tvRuangan;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamadosen = itemView.findViewById(R.id.nama_dosen);
            tvNik = itemView.findViewById(R.id.nik);
            tvMatkuldiampu = itemView.findViewById(R.id.matkul_diampu);
            tvRuangan = itemView.findViewById(R.id.ruangan);

        }
    }
}

