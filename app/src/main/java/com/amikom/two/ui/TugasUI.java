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
import com.amikom.two.adapter.TugasAdapter;
import com.amikom.two.jadwal.TambahTugasActivity;
import com.amikom.two.model.Tugas;
import com.amikom.two.room.TugasDatabase;
import com.amikom.two.room.TugasRoom;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TugasUI extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
        private TugasAdapter tugasAdapter;
        private List<Tugas> list = new ArrayList<>();
        private TugasRoom tugasRoom;


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            // jadwalDAO = new JadwalDAO();
            tugasRoom = TugasDatabase.db(Objects.requireNonNull(getContext())).tugasRoom();
            // list = jadwalDAO.selectAll();
            list = tugasRoom.selectAll();
            tugasAdapter = new TugasAdapter(getContext(), list, this);
        }

        @SuppressLint("WrongConstant")
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.ui_tugas, container, false);
            RecyclerView recyclerView = view.findViewById(R.id.recycler_tugas);
            recyclerView.setAdapter(tugasAdapter);
            LinearLayoutManager llm = new LinearLayoutManager(getContext());
            llm.setOrientation(LinearLayout.VERTICAL);
            recyclerView.setLayoutManager(llm);
            FloatingActionButton fab = view.findViewById(R.id.fab_tugas);
            fab.setOnClickListener(this);
            return view;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getContext(), TambahTugasActivity.class);
            startActivityForResult(intent, 30);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == Activity.RESULT_OK) {
                list.clear();
                list.addAll(tugasRoom.selectAll());
                tugasAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Tugas tugas = list.get(position);
            Toast.makeText(getContext(), tugas.getMatakuliah(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getContext(), TambahTugasActivity.class);
            intent.putExtra("id", tugas.getId());
            startActivityForResult(intent, 50);
        }
    }

