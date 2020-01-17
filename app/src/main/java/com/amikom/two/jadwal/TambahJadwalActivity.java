package com.amikom.two.jadwal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.amikom.two.R;
import com.amikom.two.model.Jadwal;
import com.amikom.two.room.JadwalDatabase;
import com.amikom.two.room.JadwalRoom;

public class TambahJadwalActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtHari;
    EditText edtMakul;
    EditText edtJam;
    EditText edtRuangan;
    EditText edtDosen;
    Button btnTambah;
    Button btnHapus;
    JadwalRoom jadwalRoom;
    int id;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_jadwal);
        edtHari = findViewById(R.id.jadwal_tambah_hari);
        edtMakul = findViewById(R.id.jadwal_tambah_makul);
        edtJam = findViewById(R.id.jadwal_tambah_jam);
        edtRuangan = findViewById(R.id.jadwal_tambah_ruangan);
        edtDosen = findViewById(R.id.jadwal_tambah_dosen);
        btnTambah = findViewById(R.id.jadwal_tambah);
        btnTambah.setOnClickListener(this);
        btnHapus = findViewById(R.id.jadwal_hapus);

        jadwalRoom = JadwalDatabase.db(this).jadwalRoom();
        id = getIntent().getIntExtra("id", 0);
        if (id != 0) {
            Jadwal jadwal = jadwalRoom.select(id);
            edtHari.setText(jadwal.getHari());
            edtMakul.setText(jadwal.getMataKuliah());
            edtJam.setText(jadwal.getJam());
            edtRuangan.setText(jadwal.getRuangan());
            edtDosen.setText(jadwal.getDosen());
            btnTambah.setText("Update Jadwal");
            btnHapus.setVisibility(View.VISIBLE);
            btnHapus.setOnClickListener(v -> {
                Jadwal jadwal1 = jadwalRoom.select(id);
                jadwalRoom.delete(jadwal1);
                Intent result = new Intent();
                setResult(Activity.RESULT_OK, result);
                finish();
            });
        }
    }

    @Override
    public void onClick(View v) {
        Intent result = new Intent();
        Jadwal jadwal = new Jadwal("","","","","");
        if (id != 0) {
            jadwal = jadwalRoom.select(id);
        }
        jadwal.setHari(edtHari.getText().toString());
        jadwal.setMataKuliah(edtMakul.getText().toString());
        jadwal.setJam(edtJam.getText().toString());
        jadwal.setRuangan(edtRuangan.getText().toString());
        jadwal.setDosen(edtDosen.getText().toString());
        if (id != 0) {
            jadwalRoom.update(jadwal);
        } else {
            jadwalRoom.insert(jadwal);
        }
        setResult(Activity.RESULT_OK, result);
        finish();
    }
}
