package bncc.net.ayosparring.ui.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import bncc.net.ayosparring.R
import bncc.net.ayosparring.databinding.ActivityMyRoomBinding
import bncc.net.ayosparring.model.RoomId
import bncc.net.ayosparring.ui.home.adapter.RoomAdapter
import bncc.net.ayosparring.ui.room.adapter.MyRoomAdapter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MyRoomActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyRoomBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var  data: ArrayList<RoomId>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firestore = FirebaseFirestore.getInstance()
        firebaseAuth = FirebaseAuth.getInstance()
        val email = firebaseAuth.currentUser?.email

        data = arrayListOf()
        firestore.collection("room").whereEqualTo("username", email).get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    data.add(
                        RoomId(
                            document.id,
                            document.data["username"].toString(),
                            document.data["name"].toString(),
                            document.data["title"].toString(),
                            document.data["desc"].toString(),
                            document.data["date"].toString(),
                            document.data["hour"].toString(),
                            document.data["alamat"].toString(),
                            document.data["location"].toString(),
                            document.data["joined"].toString().toIntOrNull()!!,
                            document.data["total"].toString().toIntOrNull()!!,
                            document.data["category"].toString(),
                            document.data["price"].toString().toIntOrNull()!!,
                            document.data["numberPhone"].toString(),
                        )
                    )
                }
                val roomAdapter = MyRoomAdapter(data, applicationContext)
                binding.rvMyroom.apply {
                    adapter = roomAdapter
                    layoutManager = LinearLayoutManager(applicationContext)
                }
            }.addOnFailureListener { result ->
                Log.d("error", result.toString())
            }
        binding.imgBack.setOnClickListener {
            onBackPressed()
        }
    }
}