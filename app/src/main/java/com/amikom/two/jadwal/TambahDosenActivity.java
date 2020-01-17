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
import com.amikom.two.model.Dosen;
import com.amikom.two.room.DosenDatabase;
import com.amikom.two.room.DosenRoom;


public class TambahDosenActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edtNamadosen;
    EditText edtNik;
    EditText edtMatkuldiampu;
    EditText edtRuangandosen;
    Button btnTambah;
    Button btnHapus;
    DosenRoom dosenRoom;
    int id;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_dosen);

        edtNamadosen = findViewById(R.id.list_tambah_dosen);
        edtNik = findViewById(R.id.list_nik_dosen);
        edtMatkuldiampu = findViewById(R.id.list_tambah_matkul_dosen);
        edtRuangandosen = findViewById(R.id.list_tambah_ruangan_dosen);
        btnTambah = findViewById(R.id.dosen_tambah);
        btnTambah.setOnClickListener(this);
        btnHapus = findViewById(R.id.dosen_hapus);
        dosenRoom = DosenDatabase.db(this).dosenRoom();

        id = getIntent().getIntExtra("id", 0);
        if (id != 0) {
            Dosen dosen = dosenRoom.select(id);
            edtNamadosen.setText(dosen.getNamadosen());
            edtNik.setText(dosen.getNik());
            edtMatkuldiampu.setText(dosen.getMatkuldiampu());
            edtRuangandosen.setText(dosen.getRuangan());

            btnTambah.setText("Update Dosen");
            btnHapus.setVisibility(View.VISIBLE);
            btnHapus.setOnClickListener(v -> {
                Dosen dosen1 = dosenRoom.select(id);
                dosenRoom.delete(dosen1);
                Intent result = new Intent();
                setResult(Activity.RESULT_OK, result);
                finish();
            });
        }
    }

    @Override
    public void onClick(View v) {
        Intent result = new Intent();
        Dosen dosen = new Dosen("", "", "", "");
        if (id != 0) {
            dosen = dosenRoom.select(id);
        }
        dosen.setNamadosen(edtNamadosen.getText().toString());
        dosen.setNik(edtNik.getText().toString());
        dosen.setMatkuldiampu(edtMatkuldiampu.getText().toString());
        dosen.setRuangan(edtRuangandosen.getText().toString());
        if (id != 0) {
            dosenRoom.update(dosen);
        } else {
            dosenRoom.insert(dosen);
        }
        setResult(Activity.RESULT_OK, result);
        finish();
    }
}
