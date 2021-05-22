package tw.nccu.mail2.mao.cnnlistener

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class CNNListRecycleAdapter() : RecyclerView.Adapter<CNNListRecycleAdapter.ViewHolder>() {
    var data = listOf<NewsData>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
            .inflate(R.layout.cnn_list_item, parent, false)

        return ViewHolder(view, parent)
    }

    class ViewHolder(itemView: View, parent: ViewGroup) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.text_View)
        val image: ImageView = itemView.findViewById(R.id.image_View)
        val parent : ViewGroup = parent

        fun bind(item: NewsData) {
            title.text = item.title
            image.setImageBitmap(item.thumbNail)
            itemView.setOnClickListener{
                val intent = Intent(parent.context, PreviewActivity::class.java)
                intent.putExtra("title", item.title)
                intent.putExtra("cover", item.thumbNail)
                intent.putExtra("description", item.description)
                parent.context.startActivity(intent)
            }
        }

    }
}