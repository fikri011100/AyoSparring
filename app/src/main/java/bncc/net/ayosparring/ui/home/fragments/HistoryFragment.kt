package bncc.net.ayosparring.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bncc.net.ayosparring.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_history.*

class HistoryFragment : Fragment() {

    private var angka = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_plus.setOnClickListener {
            angka++
            text_angka.text = angka.toString()
        }
        btn_minus.setOnClickListener {
            if (angka == 0) {
                val snack = Snackbar.make(it,"Angka kosong",Snackbar.LENGTH_LONG).show()
            } else {
                angka--
                text_angka.text = angka.toString()
            }
        }
    }
}