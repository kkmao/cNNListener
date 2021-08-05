package tw.nccu.mail2.mao.cnnlistener

import android.graphics.Bitmap
import android.os.Bundle
import android.os.ParcelFileDescriptor
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import tw.nccu.mail2.mao.cnnlistener.databinding.ActivitySwipeRefreshBinding
import tw.nccu.mail2.mao.cnnlistener.databinding.FragmentNewsListBinding


/**
 * A simple [Fragment] subclass.
 * Use the [NewsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class NewsListFragment : Fragment() {

    interface OnNewsSelectedListener{
        fun onNewsSelected(title: String, thumbnail: Bitmap, description: String, videoId: String)
    }

    var listener : OnNewsSelectedListener? = null

    private lateinit var binding: FragmentNewsListBinding

    val adapter: CNNListRecycleAdapter by lazy {
//        CNNListAdapter(this)
        CNNListRecycleAdapter(requireContext())
    }

    val swipeRefreshLayout by lazy {
        //findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout)
        binding.swipeRefreshLayout
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news_list, container, false)
        swipeRefreshLayout.setOnRefreshListener {
            loadList()
        }
        binding.recycleView.adapter = adapter
        loadList()
        return binding.root

        if (parentFragment != null){

        }
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