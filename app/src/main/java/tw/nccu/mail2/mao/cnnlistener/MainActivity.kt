package tw.nccu.mail2.mao.cnnlistener

import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import tw.nccu.mail2.mao.cnnlistener.databinding.ActivitySwipeRefreshBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listFragment= supportFragmentManager.findFragmentById(R.id.listFragment)as NewsListFragment?
        listFragment?.listener = object : NewsListFragment.OnNewsSelectedListener{
            override fun onNewsSelected(
                title: String,
                thumbnail: Bitmap,
                description: String,
                videoId: String
            ) {
                val previewFragment =  supportFragmentManager.findFragmentById(R.id.previewFragment) as NewsPreviewFragment
                if(previewFragment != null){
                    previewFragment.previewNews(title, thumbnail, description, videoId)
                }
            }
        }

    }
//    private lateinit var binding: ActivitySwipeRefreshBinding
//
//    val adapter: CNNListRecycleAdapter by lazy {
////        CNNListAdapter(this)
//        CNNListRecycleAdapter(this)
//    }
//
//    val swipeRefreshLayout by lazy {
//        //findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
//        binding.swipeRefreshLayout
//    }
//
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_swipe_refresh)
//
////        binding = ActivitySwipeRefreshBinding.inflate(layoutInflater)
////        setContentView(binding.root)
//        //setContentView(R.layout.activity_swipe_refresh)
//
//        //val recyclerView: RecyclerView = findViewById(R.id.recycle_view)
//        binding.recycleView.adapter = adapter
//
//        swipeRefreshLayout.setOnRefreshListener {
//            loadList()
//        }
//        loadList()
//    }
//
//
//    private fun loadList() {
//        CNNYoutubeSAX(object : ParserListener {
//            override fun start() {
//                swipeRefreshLayout.isRefreshing = true
//            }
//
//            override fun finish(news: List<NewsData>) {
//                adapter.data = news
//                swipeRefreshLayout.isRefreshing = false
//            }
//        }).parseURL("https://www.youtube.com/feeds/videos.xml?channel_id=UCupvZG-5ko_eiXAupbDfxWw")
//    }

}