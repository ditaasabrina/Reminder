package com.amikom.two.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.amikom.two.R;
import com.amikom.two.adapter.JadwalAdapter;
import com.amikom.two.jadwal.TambahJadwalActivity;
import com.amikom.two.model.Jadwal;
import com.amikom.two.room.JadwalDatabase;
import com.amikom.two.room.JadwalRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JadwalUI extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private JadwalAdapter jadwalAdapter;
    private List<Jadwal> list = new ArrayList<>();
    // private JadwalDAO jadwalDAO;
    private JadwalRoom jadwalRoom;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // jadwalDAO = new JadwalDAO();
        jadwalRoom = JadwalDatabase.db(Objects.requireNonNull(getContext())).jadwalRoom();
        // list = jadwalDAO.selectAll();
        list = jadwalRoom.selectAll();
        jadwalAdapter = new JadwalAdapter(getContext(), list, this);
    }


    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ui_jadwal, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_jadwal);
        recyclerView.setAdapter(jadwalAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(llm);
        FloatingActionButton fab = view.findViewById(R.id.fab_jadwal);
        fab.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), TambahJadwalActivity.class);
        startActivityForResult(intent, 30);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            list.clear();
            list.addAll(jadwalRoom.selectAll());
            jadwalAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Jadwal jadwal = list.get(position);
        Toast.makeText(getContext(), jadwal.getHari(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getContext(), TambahJadwalActivity.class);
        intent.putExtra("id", jadwal.getId());
        startActivityForResult(intent, 50);
    }
}
