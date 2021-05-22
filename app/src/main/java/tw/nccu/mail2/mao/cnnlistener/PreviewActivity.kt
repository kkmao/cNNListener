package tw.nccu.mail2.mao.cnnlistener

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class PreviewActivity : AppCompatActivity() {
    private var title : String? =null
    private var cover : Bitmap? =null
    private var description : String? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        val intent = getIntent()
        title = intent.getStringExtra("title")
        cover = intent.getParcelableExtra<Bitmap>("cover")
        description = intent.getStringExtra("description")

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = title
        val imageView = findViewById<ImageView>(R.id.imageView)
        imageView.setImageBitmap(cover)
        val descriptionView = findViewById<TextView>(R.id.descriptionView)
        descriptionView.text = description
    }

//    fun onPlayClick(view: View) {
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
//        startActivity(intent)
//    }

}