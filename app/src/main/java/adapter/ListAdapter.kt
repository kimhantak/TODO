package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ew.todo_application.R
import database.Todo
import model.ItemData

class ListAdapter(
        private val context: Context
        ) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var list: ArrayList<ItemData> = arrayListOf()

    fun setList(list: List<Todo>) {
        val convert = list.map {
            ItemData(it.id!!, it.text ?: "")
        } as ArrayList<ItemData>
        this.list.addAll(convert)
    }

    fun clearList() {
        list.clear()
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) = holder.bind(list[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(LayoutInflater.from(context).inflate(R.layout.item, parent, false))
    }

    inner class ListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var id: TextView = view.findViewById(R.id.id)
        private var text: TextView = view.findViewById(R.id.text)
        fun bind(item: ItemData) {
            id.text = item.id.toString()
            text.text = item.text
        }
    }
}