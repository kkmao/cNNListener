package tw.nccu.mail2.mao.cnnlistener

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import tw.nccu.mail2.mao.cnnlistener.databinding.ActivityPreviewBinding
import tw.nccu.mail2.mao.cnnlistener.databinding.FragmentNewsListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [NewsPreviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsPreviewFragment : Fragment() {

    private lateinit var binding: FragmentNewsListBinding

    private var title: String? = null
    private var cover: Bitmap? = null
    private var description: String? = null
    private var videoId:String = ""

    private var videoCurrentPosition = 0
    private val PLAYBACK_TIME = "play_time"
    private var youTubePlayer : YouTubePlayer?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_preview)

        //intent part
        val intent = getIntent()
        title = intent.getStringExtra("title")
        val byteArray = intent.getByteArrayExtra("cover")
        val size = byteArray?.size ?: 0
        cover = BitmapFactory.decodeByteArray(byteArray, 0, size)
        description = intent.getStringExtra("description")
        videoId = intent.getStringExtra("videoId")!!


        //val textView = findViewById<TextView>(R.id.textView)
        binding.textView.text = title
        //val imageView = findViewById<ImageView>(R.id.imageView)
        binding.imageView.setImageBitmap(cover)
        //val descriptionView = findViewById<TextView>(R.id.descriptionView)
        binding.descriptionView.text = description

        if (savedInstanceState != null){
            videoCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME)
        }


        initializePlayer(videoId)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_preview, container, false)
        binding.
        return binding.root
    }

    fun previewNews(title:String, thumbnail:Bitmap, description: String, videoId:String){

    }

    private fun initializePlayer(videoId : String){
        binding.youtubePlayer.initialize(getString(R.string.api_key), object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                //get ytplayer
                youTubePlayer = p1

                if (videoCurrentPosition > 0){
                    p1!!.loadVideo(videoId, videoCurrentPosition)
                }else{
                    p1!!.loadVideo(videoId)
                }
                p1.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Log.i("error", "video load error")
            }

        })
    }

    override fun onSaveInstanceState(p0: Bundle) {
        super.onSaveInstanceState(p0)

        p0.putInt(PLAYBACK_TIME, youTubePlayer!!.currentTimeMillis)
    }


}