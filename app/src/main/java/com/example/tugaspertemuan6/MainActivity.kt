package com.example.tugaspertemuan6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import com.example.tugaspertemuan6.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), TimePicker.OnTimeChangedListener ,DatePicker.OnDateChangedListener {
    private var selectedTime: String = ""
    private var selectedDate: String = " "
    private lateinit var binding: ActivityMainBinding
    private lateinit var keterangan : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        keterangan = resources.getStringArray(R.array.keterangan2)

        with(binding){
            val adapterKeterangan = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_item, keterangan)
            adapterKeterangan.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerKet.adapter = adapterKeterangan

            spinnerKet.onItemSelectedListener=
                object : AdapterView.OnItemSelectedListener{
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        val keterangan2 = keterangan[p2]
                        Toast.makeText(this@MainActivity, keterangan2, Toast.LENGTH_SHORT).show()
                        if (keterangan2  == "Sakit") {
                            keteranganText.visibility = View.VISIBLE
                        } else {
                            keteranganText.visibility = View.GONE
                        }
                        Toast.makeText(this@MainActivity,keterangan2,Toast.LENGTH_SHORT).show()
                    }
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }
                }
            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth,
            ) { _, year, monthOfYear, dayOfMonth ->
                val monthNames = arrayOf(
                    "Januari", "Februari", "Maret", "April", "Mei", "Juni",
                    "Juli", "Agustus", "September", "Oktober", "November", "Desember"
                )
                selectedDate = "$dayOfMonth/${monthNames[monthOfYear]}/$year"
                Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
            }

            timePicker.setOnTimeChangedListener {view, hourOfDay, minute ->
                selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
            }
            btnKirim.setOnClickListener {
                Toast.makeText(this@MainActivity,"presensi berhasil dilakukan pada jam $selectedTime tanggal $selectedDate",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDateChanged(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        val selectedDate = "$p3/${p2+1}/$p1"
        Toast.makeText(this@MainActivity, selectedDate,Toast.LENGTH_SHORT).show()
    }

    override fun onTimeChanged(p0: TimePicker?, p1: Int, p2: Int) {
        val selectedTime = String.format("%02d:%02d", p1, p2)
        Toast.makeText(this@MainActivity, selectedTime,
            Toast.LENGTH_SHORT).show()
    }

}