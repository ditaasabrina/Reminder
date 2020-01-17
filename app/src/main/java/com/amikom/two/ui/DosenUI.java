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
import com.amikom.two.adapter.DosenAdapter;
import com.amikom.two.jadwal.TambahDosenActivity;
import com.amikom.two.model.Dosen;
import com.amikom.two.room.DosenDatabase;
import com.amikom.two.room.DosenRoom;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DosenUI extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{
    private DosenAdapter dosenAdapter;
    private List<Dosen> list = new ArrayList<>();
    private DosenRoom dosenRoom;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // DosenDAO = new DosenDAO();
        dosenRoom = DosenDatabase.db(Objects.requireNonNull(getContext())).dosenRoom();
        // list = jadwalDAO.selectAll();
        list = dosenRoom.selectAll();
        dosenAdapter = new DosenAdapter(getContext(), list, this);
    }
    @SuppressLint("WrongConstant")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ui_dosen, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_dosen);
        recyclerView.setAdapter(dosenAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(llm);
        FloatingActionButton fab = view.findViewById(R.id.fab_dosen);
        fab.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), TambahDosenActivity.class);
        startActivityForResult(intent, 30);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            list.clear();
            list.addAll(dosenRoom.selectAll());
            dosenAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Dosen dosen = list.get(position);
        Toast.makeText(getContext(), dosen.getNamadosen(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(getContext(), TambahDosenActivity.class);
        intent.putExtra("id", dosen.getId());
        startActivityForResult(intent, 50);
    }
}
