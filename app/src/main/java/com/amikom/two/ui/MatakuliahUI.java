package com.amikom.two.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amikom.two.R;
import com.amikom.two.adapter.MatakuliahAdapter;
import com.amikom.two.jadwal.TambahMatakuliahActivity;
import com.amikom.two.model.Matakuliah;
import com.amikom.two.room.MatakuliahDatabase;
import com.amikom.two.room.MatakuliahRoom;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MatakuliahUI extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    private MatakuliahAdapter matakuliahAdapter;
    private List<Matakuliah> list = new ArrayList<>();
    // private JadwalDAO jadwalDAO;
    private MatakuliahRoom matakuliahRoom;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // jadwalDAO = new JadwalDAO();
        matakuliahRoom = MatakuliahDatabase.db(Objects.requireNonNull(getContext())).matakuliahRoom();
        // list = jadwalDAO.selectAll();
        list = matakuliahRoom.selectAll();
        matakuliahAdapter = new MatakuliahAdapter(getContext(), list, this);
    }

    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ui_matakuliah, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_matakuliah);
        recyclerView.setAdapter(matakuliahAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(llm);
        FloatingActionButton fab = view.findViewById(R.id.fab_matakuliah);
        fab.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), TambahMatakuliahActivity.class);
        startActivityForResult(intent, 30);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            list.clear();
            list.addAll(matakuliahRoom.selectAll());
            matakuliahAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Matakuliah matakuliah = list.get(position);
        Toast.makeText(getContext(), matakuliah.getNamamatkul(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getContext(), TambahMatakuliahActivity.class);
        intent.putExtra("id", matakuliah.getId());
        startActivityForResult(intent, 50);
    }
}
