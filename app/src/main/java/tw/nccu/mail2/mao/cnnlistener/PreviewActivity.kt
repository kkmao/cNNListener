package tw.nccu.mail2.mao.cnnlistener

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import tw.nccu.mail2.mao.cnnlistener.databinding.ActivityPreviewBinding

class PreviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreviewBinding

    private var title : String? =null
    private var cover : Bitmap? =null
    private var description : String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        //val view = binding.root
        setContentView(binding.root)

        //setContentView(R.layout.activity_preview)

        val intent = getIntent()
        title = intent.getStringExtra("title")
        val byteArray = intent.getByteArrayExtra("cover")
        val size = byteArray?.size ?: 0
        cover = BitmapFactory.decodeByteArray(byteArray, 0, size)
//        cover = intent.getParcelableExtra<Bitmap>("cover")
        description = intent.getStringExtra("description")


        //val textView = findViewById<TextView>(R.id.textView)
        binding.textView.text = title
        //val imageView = findViewById<ImageView>(R.id.imageView)
        binding.imageView.setImageBitmap(cover)
        //val descriptionView = findViewById<TextView>(R.id.descriptionView)
        binding.descriptionView.text = description
    }

//    fun onPlayClick(view: View) {
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//        startActivity(intent)
//    }

}