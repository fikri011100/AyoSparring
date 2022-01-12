package bncc.net.ayosparring.ui.home.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bncc.net.ayosparring.R
import bncc.net.ayosparring.databinding.FragmentProfileBinding
import bncc.net.ayosparring.ui.auth.AuthActivity
import bncc.net.ayosparring.utils.SharedPref
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)
        firebaseAuth = FirebaseAuth.getInstance()

        binding.textNama.text = firebaseAuth.currentUser?.displayName
        Glide.with(requireActivity())
            .load(firebaseAuth.currentUser?.photoUrl)
            .into(binding.imageProfile)
        binding.btnLogout.setOnClickListener {
            val pref = SharedPref(requireContext())
            pref.clearSession()
            firebaseAuth.signOut()
            val intent = Intent(requireContext(), AuthActivity::class.java)
            requireContext().startActivity(intent)
            requireActivity().finishAffinity()
        }
    }
}