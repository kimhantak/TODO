package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ew.todo_application.R
import model.ItemData

class ListAdapter(
            val context: Context
        ) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var list: ArrayList<ItemData> = arrayListOf(ItemData(1, "kimhantak"))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) = holder.bind(list[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ListViewHolder(view)
    }

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val id: TextView by lazy {
            view.findViewById(R.id.id)
        }
        val text: TextView by lazy {
            view.findViewById(R.id.text)
        }
        fun bind(item: ItemData) {
            id.text = item.id.toString()
            text.text = item.text
        }
    }
}