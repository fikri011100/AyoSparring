package bncc.net.ayosparring.ui.home.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bncc.net.ayosparring.R
import bncc.net.ayosparring.databinding.FragmentRoomBinding
import bncc.net.ayosparring.ui.room.CreateRoomActivity
import bncc.net.ayosparring.ui.room.MyRoomActivity

class RoomFragment : Fragment(R.layout.fragment_room) {

    private lateinit var binding: FragmentRoomBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRoomBinding.bind(view)

        binding.cardMakeroom.setOnClickListener {
            val intent = Intent(requireContext(), CreateRoomActivity::class.java)
            requireContext().startActivity(intent)
        }
        binding.cardMyroom.setOnClickListener {
            val intent = Intent(requireContext(), MyRoomActivity::class.java)
            requireContext().startActivity(intent)
        }
    }
}