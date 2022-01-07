package bncc.net.ayosparring.ui.home.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import bncc.net.ayosparring.R
import bncc.net.ayosparring.databinding.FragmentHomeBinding
import bncc.net.ayosparring.model.Room
import bncc.net.ayosparring.ui.home.adapter.RoomAdapter
import com.google.firebase.firestore.FirebaseFirestore

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        val firestore = FirebaseFirestore.getInstance()
        setData(firestore)
    }

    private fun getFireStore(
            category: String, firestore: FirebaseFirestore, data:
            ArrayList<Room>
    ) {
        firestore.collection("room").whereEqualTo("category", category).get()
                .addOnSuccessListener { result ->
                    for (document in result) {
                    data.add(
                        Room(
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
                val roomAdapter = RoomAdapter(data, requireContext(), category)
                binding.rvRoom.apply {
                    adapter = roomAdapter
                    layoutManager = LinearLayoutManager(requireContext())
                }
                }.addOnFailureListener { result ->
                    Log.d("error", result.toString())
                }
    }

    private fun setData(firestore: FirebaseFirestore) {
        var data: ArrayList<Room>?
        data = arrayListOf()

        getFireStore("Sepak Bola", firestore, data)

        binding.textFootball.setOnClickListener {
            data.clear()
            getFireStore("Sepak Bola", firestore, data)
            binding.textBasket.setBackgroundResource(R.drawable.bg_red_rounded)
            binding.textBasket.setTextColor(resources.getColor(R.color.black))
            binding.textFutsal.setBackgroundResource(R.drawable.bg_red_rounded)
            binding.textFutsal.setTextColor(resources.getColor(R.color.black))
            binding.textVolley.setBackgroundResource(R.drawable.bg_red_rounded)
            binding.textVolley.setTextColor(resources.getColor(R.color.black))

            binding.textFootball.setBackgroundResource(R.drawable.bg_red)
            binding.textFootball.setTextColor(resources.getColor(R.color.white))
        }

        binding.textFutsal.setOnClickListener {
            data.clear()
            getFireStore("Futsal", firestore, data)
            binding.textBasket.setBackgroundResource(R.drawable.bg_red_rounded)
            binding.textBasket.setTextColor(resources.getColor(R.color.black))
            binding.textFootball.setBackgroundResource(R.drawable.bg_red_rounded)
            binding.textFootball.setTextColor(resources.getColor(R.color.black))
            binding.textVolley.setBackgroundResource(R.drawable.bg_red_rounded)
            binding.textVolley.setTextColor(resources.getColor(R.color.black))

            binding.textFutsal.setBackgroundResource(R.drawable.bg_red)
            binding.textFutsal.setTextColor(resources.getColor(R.color.white))
        }

        binding.textVolley.setOnClickListener {
            data.clear()
            getFireStore("Volley", firestore, data)
            binding.textBasket.setBackgroundResource(R.drawable.bg_red_rounded)
            binding.textBasket.setTextColor(resources.getColor(R.color.black))
            binding.textFootball.setBackgroundResource(R.drawable.bg_red_rounded)
            binding.textFootball.setTextColor(resources.getColor(R.color.black))
            binding.textFutsal.setBackgroundResource(R.drawable.bg_red_rounded)
            binding.textFutsal.setTextColor(resources.getColor(R.color.black))

            binding.textVolley.setBackgroundResource(R.drawable.bg_red)
            binding.textVolley.setTextColor(resources.getColor(R.color.white))
        }

        binding.textBasket.setOnClickListener {
            data.clear()
            getFireStore("Basket", firestore, data)
            binding.textFutsal.setBackgroundResource(R.drawable.bg_red_rounded)
            binding.textFutsal.setTextColor(resources.getColor(R.color.black))
            binding.textFootball.setBackgroundResource(R.drawable.bg_red_rounded)
            binding.textFootball.setTextColor(resources.getColor(R.color.black))
            binding.textVolley.setBackgroundResource(R.drawable.bg_red_rounded)
            binding.textVolley.setTextColor(resources.getColor(R.color.black))

            binding.textBasket.setBackgroundResource(R.drawable.bg_red)
            binding.textBasket.setTextColor(resources.getColor(R.color.white))
        }

    }

}

//restore firestore data
//val dats = listOf(
//        Room(
//                "fikri011100",
//                "Fikri Imadudin",
//                "Ayo Sparring,",
//                "Lorem Ipsum Dolor sit amet\n,Lorem Ipsum Dolor sit amet\n Lorem Ipsum Dolor sit amet",
//                "Thursday,18 November 2021",
//                "16:00",
//                "Lapangan Sepak Bola  Tunjungsekar",
//                5,
//                10,
//                "Sepak Bola",
//                20000,
//                "0812309230",
//        ),
//        Room(
//                "abududa",
//                "Jordi",
//                "Sparring Kuy",
//                "Lorem Ipsum Dolor sit amet\n,Lorem Ipsum Dolor sit amet\n Lorem Ipsum Dolor sit amet",
//                "Thursday,18 November 2021",
//                "21:00",
//                "Lapangan Sepak Bola Tunjungsekar",
//                5,
//                10,
//                "Sepak Bola",
//                15000,
//                "0893203203",
//        ),
//        Room(
//                "abududa",
//                "Jordi",
//                "Sparring Kuy",
//                "Lorem Ipsum Dolor sit amet\n,Lorem Ipsum Dolor sit amet\n Lorem Ipsum Dolor sit amet",
//                "Thursday,18 November 2021",
//                "21:00",
//                "Lapangan Sepak Bola Tunjungsekar",
//                5,
//                10,
//                "Sepak Bola",
//                25000,
//                "08023923257",
//        ),
//        Room(
//                "kekeyi",
//                "Kekeyi Putri",
//                "Sparring Kuy",
//                "Lorem Ipsum Dolor sit amet\n,Lorem Ipsum Dolor sit amet\n Lorem Ipsum Dolor sit amet",
//                "Thursday,18 November 2021",
//                "21:00",
//                "Lapangan Sepak Bola Tunjungsekar",
//                5,
//                10,
//                "Volley",
//                10000,
//                "0830392321",
//        ),
//        Room(
//                "kekeyi",
//                "Kekeyi Putri",
//                "Sparring Kuy",
//                "Lorem Ipsum Dolor sit amet\n,Lorem Ipsum Dolor sit amet\n Lorem Ipsum Dolor sit amet",
//                "Thursday,18 November 2021",
//                "21:00",
//                "Lapangan Sepak Bola Tunjungsekar",
//                5,
//                10,
//                "Volley",
//                20000,
//                "0804398394",
//        ),
//        Room(
//                "kekeyi",
//                "Kekeyi Putri",
//                "Ayo main",
//                "Lorem Ipsum Dolor sit amet\n,Lorem Ipsum Dolor sit amet\n Lorem Ipsum Dolor sit amet",
//                "Thursday,18 November 2021",
//                "21:00",
//                "Lapangan Futsal Tunjungsekar",
//                5,
//                10,
//                "Futsal",
//                20000,
//                "0812309230",
//        ),
//        Room(
//                "jason01232",
//                "Jason Ibrahim",
//                "Sparring Kuy",
//                "Lorem Ipsum Dolor sit amet\n,Lorem Ipsum Dolor sit amet\n Lorem Ipsum Dolor sit amet",
//                "Thursday,18 November 2021",
//                "21:00",
//                "Lapangan Sepak Bola Tunjungsekar",
//                5,
//                10,
//                "Futsal",
//                20000,
//                "0812309230",
//        ),
//)
//
//for (doc in dats) {
//    firestore.collection("room").add(Room(
//            doc.username,
//            doc.name,
//            doc.title,
//            doc.desc,
//            doc.date,
//            doc.hour,
//            doc.location,
//            doc.joined,
//            doc.total,
//            doc.category,
//            doc.price,
//            doc.numberPhone,
//    )).addOnSuccessListener { result ->
//
//    }
//}