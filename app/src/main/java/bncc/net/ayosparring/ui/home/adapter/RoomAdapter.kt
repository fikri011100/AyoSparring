package bncc.net.ayosparring.ui.home.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bncc.net.ayosparring.databinding.ItemRoomBinding
import bncc.net.ayosparring.model.Room
import bncc.net.ayosparring.ui.room.DetailRoomActivity

class RoomAdapter(val items: ArrayList<Room>, val context: Context, val category: String) :
        RecyclerView.Adapter<RoomAdapter.ViewHolder>() {

    inner class ViewHolder(val view: ItemRoomBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: Room) {
            view.textNama.text = item.name
            view.textUsername.text = "@" + item.username
            view.textTitle.text = item.title
            view.textDate.text = item.date + " | " + item.hour
            view.textPlace.text = item.location

            view.consRoom.setOnClickListener {
                val intent = Intent(context, DetailRoomActivity::class.java)
                intent.putExtra("feature", "Join Room")
                intent.putExtra("name", item.name)
                intent.putExtra("username", item.username)
                intent.putExtra("title", item.title)
                intent.putExtra("desc", item.desc)
                intent.putExtra("date", item.date)
                intent.putExtra("hour", item.hour)
                intent.putExtra("alamat", item.alamat)
                intent.putExtra("location", item.location)
                intent.putExtra("joined", item.joined)
                intent.putExtra("total", item.total)
                intent.putExtra("category", item.category)
                intent.putExtra("price", item.price)
                intent.putExtra("numberPhone", item.numberPhone)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomAdapter.ViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomAdapter.ViewHolder, position: Int) {
        with(holder) {
            bind(items[position])
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}