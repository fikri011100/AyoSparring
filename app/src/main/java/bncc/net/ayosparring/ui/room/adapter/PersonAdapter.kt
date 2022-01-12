package bncc.net.ayosparring.ui.room.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bncc.net.ayosparring.databinding.ItemPersonBinding
import bncc.net.ayosparring.model.Order

class PersonAdapter (val items: ArrayList<Order>, val context: Context) : RecyclerView.Adapter<PersonAdapter
.ViewHolder>(){
    inner class ViewHolder(val view: ItemPersonBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: Order) {
            view.textName.text = item.username
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.ViewHolder {
        val binding = ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonAdapter.ViewHolder, position: Int) {
        with(holder) {
            bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}