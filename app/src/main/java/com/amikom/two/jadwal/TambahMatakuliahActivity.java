package com.amikom.two.jadwal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amikom.two.R;
import com.amikom.two.model.Matakuliah;
import com.amikom.two.room.MatakuliahDatabase;
import com.amikom.two.room.MatakuliahRoom;

public class TambahMatakuliahActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtDosenpengajar;
    EditText edtNamamatkul;
    EditText edtJumlhsks;
    Button btnTambah;
    Button btnHapus;
    MatakuliahRoom matakuliahRoom ;
    int id;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_matakuliah);
        edtDosenpengajar = findViewById(R.id.list_tambah_dosen_pengajar);
        edtNamamatkul = findViewById(R.id.list_tambah_matakuliah);
        edtJumlhsks = findViewById(R.id.list_tambah_jmlsks);
        btnTambah = findViewById(R.id.matakuliah_tambah);
        btnTambah.setOnClickListener(this);
        btnHapus = findViewById(R.id.matakuliah_hapus);

        matakuliahRoom = MatakuliahDatabase.db(this).matakuliahRoom();
        id = getIntent().getIntExtra("id", 0);
        if (id != 0) {
            Matakuliah matakuliah = matakuliahRoom.select(id);
            edtDosenpengajar.setText(matakuliah.getDosenpengajar());
            edtNamamatkul.setText(matakuliah.getNamamatkul());
            edtJumlhsks.setText(matakuliah.getSks());
            btnTambah.setText("Update Matakuliah");
            btnHapus.setVisibility(View.VISIBLE);
            btnHapus.setOnClickListener(v -> {
                Matakuliah matakuliah1 = matakuliahRoom.select(id);
                matakuliahRoom.delete(matakuliah1);
                Intent result = new Intent();
                setResult(Activity.RESULT_OK, result);
                finish();
            });
        }
    }

    @Override
    public void onClick(View v) {
        Intent result = new Intent();
        Matakuliah matakuliah = new Matakuliah("","","");
        if (id != 0) {
            matakuliah = matakuliahRoom.select(id);
        }
        matakuliah.setDosenpengajar(edtDosenpengajar.getText().toString());
        matakuliah.setNamamatkul(edtNamamatkul.getText().toString());
        matakuliah.setSks(edtJumlhsks.getText().toString());
        if (id != 0) {
            matakuliahRoom.update(matakuliah);
        } else {
            matakuliahRoom.insert(matakuliah);
        }
        setResult(Activity.RESULT_OK, result);
        finish();
    }
}
