package tw.nccu.mail2.mao.cnnlistener

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tw.nccu.mail2.mao.cnnlistener.databinding.CnnListItemBinding
import java.io.ByteArrayOutputStream


class CNNListRecycleAdapter(mContext : Context) : RecyclerView.Adapter<CNNListRecycleAdapter.ViewHolder>() {
    var data = listOf<NewsData>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    val mContext = mContext

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = CnnListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

//        val layoutInflater = LayoutInflater.from(parent.context)
//        val view = layoutInflater
//            .inflate(R.layout.cnn_list_item, parent, false)

        return ViewHolder(itemBinding, parent, mContext)
    }

    class ViewHolder(itemBinding: CnnListItemBinding, parent: ViewGroup, mContext: Context) : RecyclerView.ViewHolder(itemBinding.root){
        val title: TextView = itemBinding.textView
//            itemView.findViewById(R.id.text_View)
        val image: ImageView = itemBinding.imageView
//            itemView.findViewById(R.id.image_View)
        var item : NewsData = NewsData("", null, "")


        init {
            itemBinding.root.setOnClickListener {

//                if(itemBinding.root.parentFra)

                val intent = Intent(mContext, PreviewActivity::class.java)
                intent.putExtra("title", item.title)
                var byteArrayOutputStream  = ByteArrayOutputStream()
                item.thumbNail?.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
                intent.putExtra("cover", byteArrayOutputStream.toByteArray())
                intent.putExtra("description", item.description)
                intent.putExtra("videoId", item.videoId)
                mContext.startActivity(intent)
            }
        }

        fun bind(item: NewsData) {
            title.text = item.title
            image.setImageBitmap(item.thumbNail)
            this.item = item
        }

    }

}