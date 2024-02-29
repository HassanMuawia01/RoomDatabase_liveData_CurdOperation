import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hassanmuawia_task.R
import com.example.hassanmuawia_task.room_mvvm.MyData

class Fragemnt1Adapter(
    private val dataList: MutableList<MyData>,
    private val onItemClick: (MyData) -> Unit,
) : RecyclerView.Adapter<Fragemnt1Adapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.textView)

        fun bind(data: MyData) {
            textView.text = data.text

            itemView.setOnClickListener {
                onItemClick.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setData(entries: List<MyData>) {
        dataList.clear()
        dataList.addAll(entries)
        notifyDataSetChanged()
    }
}
