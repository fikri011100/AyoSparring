package bncc.net.ayosparring.ui.room

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import bncc.net.ayosparring.R
import bncc.net.ayosparring.databinding.ActivityCreateRoomBinding
import bncc.net.ayosparring.model.Room
import bncc.net.ayosparring.utils.StringHelper
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class CreateRoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateRoomBinding
    private lateinit var categorySelected: String
    private lateinit var date: String
    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = resources.getStringArray(R.array.category)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item, category
        )
        val firestore = FirebaseFirestore.getInstance()
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                categorySelected = category[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                val myFormat = "dd/MM/yyyy"
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                date = sdf.format(cal.getTime())
                binding.inputTextDate.text = StringHelper.formatDate(date)
            }

        binding.inputTextDate.setOnClickListener {
            DatePickerDialog(
                this@CreateRoomActivity,
                dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.btnCreate.setOnClickListener {
            if (!binding.inputTextCreateSuratTitle.text!!.equals("") &&
                !binding.inputTextCreateSuratKeterangan.text!!.equals("") &&
                !binding.inputTextDate.text!!.equals("") &&
                !binding.inputTextMax.text!!.equals("") &&
                !binding.inputTextPrice.text!!.equals("") &&
                !binding.inputTextStart.text!!.equals("") &&
                !binding.inputTextLapangan.text!!.equals("") &&
                !binding.inputTextLokasi.text!!.equals("") &&
                !binding.inputTextWa.text!!.equals("")
            ) {
                firestore.collection("room").add(
                    Room(
                        "fikri011100",
                        "Fikri Imadudin",
                        binding.inputTextCreateSuratTitle.text.toString(),
                        binding.inputTextCreateSuratKeterangan.text.toString(),
                        binding.inputTextDate.text.toString(),
                        binding.inputTextStart.text.toString(),
                        binding.inputTextLapangan.text.toString(),
                        binding.inputTextLokasi.text.toString(),
                        0,
                        binding.inputTextMax.text.toString().toInt(),
                        categorySelected,
                        binding.inputTextPrice.text.toString().toInt(),
                        binding.inputTextWa.text.toString(),
                    )
                ).addOnSuccessListener { result ->
                    onBackPressed()
                    Toast.makeText(applicationContext, "Berhasil Menambahkan Room", Toast
                        .LENGTH_SHORT).show()
                }
            }
        }
    }
}