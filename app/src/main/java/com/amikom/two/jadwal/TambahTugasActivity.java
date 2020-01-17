package com.amikom.two.jadwal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amikom.two.AppReceiver;
import com.amikom.two.R;
import com.amikom.two.model.Tugas;
import com.amikom.two.room.TugasDatabase;
import com.amikom.two.room.TugasRoom;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@SuppressLint("Registered")
public class TambahTugasActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtMatakuliah;
    EditText edtJenistugas;
    EditText edtKeterangan;
    EditText edtJamPengumpulan;
    TextView tvTanggalPengumpulan;
    Button btnTambah;
    Button btnHapus;
    Button btnKalender;
    TugasRoom tugasRoom;
    int id;
    private int dayOfMonth = 0;
    private int monthOfYear;
    private int year;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    private PendingIntent pendingIntent;
    private static final int ALARM_REQUEST_CODE = 134;
    private Calendar newDate;
    private Date date;

    @SuppressLint({"SetTextI18n", "NewApi"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_tugas);
        dateFormatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss" ,Locale.US);
        findView();
        addToDataBase();
        createCalender();
    }

    private void findView() {
        edtMatakuliah = findViewById(R.id.list_matakuliah);
        edtJenistugas = findViewById(R.id.list_jenistugas);
        edtKeterangan = findViewById(R.id.list_keterangan);
        edtJamPengumpulan = findViewById(R.id.list_jamkumpul);
        tvTanggalPengumpulan = findViewById(R.id.list_tglkumpul);
        btnTambah = findViewById(R.id.tugas_tambah);
        btnTambah.setOnClickListener(this);
        btnHapus = findViewById(R.id.tugas_hapus);
        btnKalender = findViewById(R.id.bt_datepicker);
    }

    @SuppressLint("SetTextI18n")
    private void addToDataBase() {
        tugasRoom = TugasDatabase.db(getApplicationContext()).tugasRoom();

        id = getIntent().getIntExtra("id", 0);
        if (id != 0) {
            Tugas tugas = tugasRoom.select(id);
            edtMatakuliah.setText(tugas.getMatakuliah());
            edtJenistugas.setText(tugas.getJenistugas());
            edtKeterangan.setText(tugas.getKeterangan());
            tvTanggalPengumpulan.setText(tugas.getTanggalpengumpulan());
            edtJamPengumpulan.setText(tugas.getJampengumpulan());
            btnTambah.setText("Update Tugas");
            btnHapus.setVisibility(View.VISIBLE);
            btnHapus.setOnClickListener(v -> {
                Tugas tugas1 = tugasRoom.select(id);
                tugasRoom.delete(tugas1);
                Intent result = new Intent();
                setResult(Activity.RESULT_OK, result);
                finish();
            });
        }
    }

    @Override
    public void onClick(View v) {
        Intent result = new Intent();
        Tugas tugas = new Tugas("", "", "", "", "");
        if (id != 0) {
            tugas = tugasRoom.select(id);
        }
        tugas.setMatakuliah(edtMatakuliah.getText().toString());
        tugas.setJenistugas(edtJenistugas.getText().toString());
        tugas.setKeterangan(edtKeterangan.getText().toString());
        tugas.setTanggalpengumpulan(tvTanggalPengumpulan.getText().toString());
        tugas.setJampengumpulan(edtJamPengumpulan.getText().toString());
        if (id != 0) {
            tugasRoom.update(tugas);
        } else {
            setTimeAlarm();
            tugasRoom.insert(tugas);
        }
        setResult(Activity.RESULT_OK, result);
        finish();
    }

    @SuppressLint("NewApi")
    private void setTimeAlarm() {

        Toast.makeText(TambahTugasActivity.this, "Tugas Baru Berhasil di Tambahkan", Toast.LENGTH_SHORT).show();

    }

    private void createCalender() {
        btnKalender.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View view) {
                showDateDialog();
            }
        });
    }

    private void showDateDialog() {

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @SuppressLint("NewApi")
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                try {
                    date = new SimpleDateFormat("EEE MMM dd yyyy", Locale.ENGLISH)
                            .parse(dateFormatter.format(newDate.getTime()));
                } catch (ParseException e) {
                    Toast.makeText(TambahTugasActivity.this, "Gagal " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                if (date == null) {
                    Toast.makeText(TambahTugasActivity.this, "Tugas gagal di tambahkan", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent alarmIntent = new Intent(TambahTugasActivity.this, AppReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(TambahTugasActivity.this, ALARM_REQUEST_CODE, alarmIntent, 0);

                Calendar cal = Calendar.getInstance();
//                long interval_seconds = date.getTime() - 129600000;

                long interval_seconds = 15000;

                Toast.makeText(TambahTugasActivity.this, String.valueOf(interval_seconds), Toast.LENGTH_SHORT).show();

                cal.add(Calendar.MILLISECOND, (int) interval_seconds);
                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

                //set alarm manager dengan memasukkan waktu yang telah dikonversi menjadi milliseconds
                manager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);


                tvTanggalPengumpulan.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

}