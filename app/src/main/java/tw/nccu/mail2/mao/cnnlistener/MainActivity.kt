package tw.nccu.mail2.mao.cnnlistener

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import tw.nccu.mail2.mao.cnnlistener.databinding.ActivitySwipeRefreshBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySwipeRefreshBinding

    val adapter: CNNListRecycleAdapter by lazy {
//        CNNListAdapter(this)
        CNNListRecycleAdapter(this)
    }

    val swipeRefreshLayout by lazy {
        //findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        binding.swipeRefreshLayout
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_swipe_refresh)

//        binding = ActivitySwipeRefreshBinding.inflate(layoutInflater)
//        setContentView(binding.root)
        //setContentView(R.layout.activity_swipe_refresh)

        //val recyclerView: RecyclerView = findViewById(R.id.recycle_view)
        binding.recycleView.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            loadList()
        }
        loadList()
    }


    private fun loadList() {
        CNNYoutubeSAX(object : ParserListener {
            override fun start() {
                swipeRefreshLayout.isRefreshing = true
            }

            override fun finish(news: List<NewsData>) {
                adapter.data = news
                swipeRefreshLayout.isRefreshing = false
            }
        }).parseURL("https://www.youtube.com/feeds/videos.xml?channel_id=UCupvZG-5ko_eiXAupbDfxWw")
    }

}