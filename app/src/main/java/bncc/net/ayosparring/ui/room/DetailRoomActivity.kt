package bncc.net.ayosparring.ui.room

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.IntegerRes
import bncc.net.ayosparring.R
import bncc.net.ayosparring.databinding.ActivityDetailRoomBinding
import bncc.net.ayosparring.utils.StringHelper

class DetailRoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailRoomBinding
    private lateinit var username: String
    private lateinit var feature: String
    private lateinit var name: String
    private lateinit var title: String
    private lateinit var desc: String
    private lateinit var date: String
    private lateinit var hour: String
    private lateinit var alamat: String
    private lateinit var location: String
    private var joined: Int = 0
    private var total: Int = 0
    private lateinit var category: String
    private var price: Int = 0
    private lateinit var numberPhone: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //get data from extras
        feature = intent.getStringExtra("feature").toString()
        username = intent.getStringExtra("username").toString()
        name = intent.getStringExtra("name").toString()
        title = intent.getStringExtra("title").toString()
        desc = intent.getStringExtra("desc").toString()
        date = intent.getStringExtra("date").toString()
        hour = intent.getStringExtra("hour").toString()
        alamat = intent.getStringExtra("alamat").toString()
        location = intent.getStringExtra("location").toString()
        joined = intent.getIntExtra("joined", 0)
        total = intent.getIntExtra("total", 0)
        category = intent.getStringExtra("category").toString()
        price = intent.getIntExtra("price", 0)
        numberPhone = intent.getStringExtra("numberPhone").toString()

        //set data
        binding.textToolbar.text = feature
        binding.textNama.text = name
        binding.textUsername.text = "@$username"
        binding.textTitle.text = title
        binding.textDesc.text = desc
        binding.textDate.text = "$date, $hour WIB"
        binding.textPlace.text = location
        binding.textWa.text = numberPhone
        binding.textPrice.text = StringHelper.convertFormatPrice(price.toString())
        binding.textLokasi.text = alamat
        binding.textMax.text = "Maximum $total"
        binding.textPeserta.text = "($joined/$total)"

        binding.btnHubungi.setOnClickListener {
            val numberEdit = binding.textWa.text.subSequence(1, binding.textWa.text.length)
            val url = "https://api.whatsapp.com/send?phone=+62$numberEdit"
            val intent = Intent(Intent.ACTION_VIEW).setData(Uri.parse(url))
            startActivity(intent)
        }
    }
}