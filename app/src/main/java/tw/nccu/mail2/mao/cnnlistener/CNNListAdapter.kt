package tw.nccu.mail2.mao.cnnlistener

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class CNNListAdapter(val context: Context):BaseAdapter() {
    var news : List<NewsData> = listOf<NewsData>()

    set(value)  {
        field = value
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return news.size
    }

    override fun getItem(p0: Int): Any {
        return news.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.cnn_list_item, null)
        val textView = itemView.findViewById<TextView>(R.id.text_View)
        val imageView = itemView.findViewById<ImageView>(R.id.image_View)
        textView.text = news.get(p0).title
        imageView.setImageBitmap(news.get(p0).thumbNail)

        return itemView
    }
}