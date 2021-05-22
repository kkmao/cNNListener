package tw.nccu.mail2.mao.cnnlistener

import android.app.ListActivity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity() {
    val adapter: CNNListRecycleAdapter by lazy {
//        CNNListAdapter(this)
        CNNListRecycleAdapter()
    }

    val swipeRefreshLayout by lazy {
        findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_refresh)

        val recyclerView: RecyclerView = findViewById(R.id.recycle_view)
        recyclerView.adapter = adapter
        //listAdapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
//            titles.clear()
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
//                for (subnews in news) {
////                    val textView = TextView(this@MainActivity)
////                    textView.text =subnews.title
////                    linearLayout.addView(textView)
//                    titles.add(subnews.title)
//                }
                adapter.data = news
                swipeRefreshLayout.isRefreshing = false
//                adapter.notifyDataSetChanged()
            }
        }).parseURL("https://www.youtube.com/feeds/videos.xml?channel_id=UCupvZG-5ko_eiXAupbDfxWw")
    }

}