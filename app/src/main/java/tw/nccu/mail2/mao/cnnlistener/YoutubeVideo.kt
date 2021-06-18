package tw.nccu.mail2.mao.cnnlistener

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.google.android.youtube.player.internal.t

class YoutubeVideo : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube_video)

        val layout = layoutInflater.inflate(R.layout.activity_youtube_video, null) as LinearLayout
        setContentView(layout)

        val playerView = YouTubePlayerView(this)
        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        playerView.initialize(getString(R.string.api_key), this)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
//        p1?.setPlayerStateChangeListener(playerStateChangeListener)
//        p1?.setPlaybackEventListener(playbackEventListener)

        if (!p2) {
            p1?.cueVideo("YOUTUBE_VIDEO_ID")
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        Log.i("test", "error")
    }

    private fun initializePlayer() {

    }


}