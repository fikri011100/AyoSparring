package bncc.net.ayosparring.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bncc.net.ayosparring.databinding.ItemCategoryBinding
import bncc.net.ayosparring.model.Category

class CategoryAdapter(val items: List<Category>, val context: Context) :
        RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            bind(items[position])
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(val view: ItemCategoryBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: Category) = with(view) {
            view.textCategory.text = item.nama
            view.imageCategory.setImageResource(item.image)
        }
    }
}