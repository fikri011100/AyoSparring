package bncc.net.ayosparring.ui.auth.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bncc.net.ayosparring.R
import bncc.net.ayosparring.databinding.FragmentStartedBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.Intent
import android.util.Log
import com.google.android.gms.common.api.ApiException
import android.widget.Toast

import bncc.net.ayosparring.MainActivity
import bncc.net.ayosparring.model.User
import bncc.net.ayosparring.utils.SharedPref

import com.google.firebase.auth.GoogleAuthProvider

import com.google.firebase.auth.AuthCredential
import com.google.firebase.firestore.FirebaseFirestore

class StartedFragment : Fragment(R.layout.fragment_started) {

    private lateinit var binding: FragmentStartedBinding
    private lateinit var signIn: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var pref: SharedPref

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentStartedBinding.bind(view)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client))
            .requestEmail()
            .build()
        pref = SharedPref(requireContext())
        if (pref.isLoggedIn()) {
            val intent = Intent(requireContext(), MainActivity::class.java)
            requireContext().startActivity(intent)
            requireActivity().finish()
        }
        signIn = GoogleSignIn.getClient(requireActivity(), gso)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnCreate.setOnClickListener {
            val signInIntent: Intent = signIn.getSignInIntent()
            startActivityForResult(signInIntent, 1234)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1234) {
            val task =  GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account?.idToken!!, null)
                firebaseAuth.signInWithCredential(credential)
                    .addOnSuccessListener(requireActivity()) {
                        pref.saveSession()
                        val firestore = FirebaseFirestore.getInstance()
                        firestore.collection("user").add(
                            User(
                                it.user?.email.toString(),
                                it.user?.displayName.toString(),
                                it.user?.photoUrl.toString()
                            )
                        )
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                        requireActivity().finish()
                    }
                    .addOnFailureListener(requireActivity()) { e ->
                        Toast.makeText(
                            requireContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } catch (e: Exception) {
                Log.d("error", e.message.toString())
            }
        }
    }
}