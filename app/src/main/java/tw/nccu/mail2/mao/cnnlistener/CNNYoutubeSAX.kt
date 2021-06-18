package tw.nccu.mail2.mao.cnnlistener

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.net.URL
import javax.xml.parsers.SAXParserFactory

class CNNYoutubeSAX(val listener: ParserListener):DefaultHandler() {
    private val factory = SAXParserFactory.newInstance()
    private val parser = factory.newSAXParser()

    private var entryFound = false
    private var titleFound = false
    private var imageFound = false
    private var descriptionFound = false
    private var videoIdFound = false
    private var element = ""
    private var newsTitle = ""
    private var thumbNail : Bitmap? = null
    private var description = ""
    private var videoId: String = ""
    private var data = mutableListOf<NewsData>()

    override fun startElement(
        uri: String?,
        localName: String?,
        qName: String?,
        attributes: Attributes?
    ) {
        super.startElement(uri, localName, qName, attributes)

        if (localName == "entry"){
            entryFound = true
        }
        if (entryFound){
            if (localName == "title"){
                titleFound = true
            }else if (localName == "thumbnail"){
                imageFound = true
                val url = attributes?.getValue("url")
                val inputStream = URL(url).openStream()
                thumbNail = BitmapFactory.decodeStream(inputStream)
            }else if (localName == "description"){
                descriptionFound = true
            }else if (localName == "videoId"){
                videoIdFound = true
            }
        }
        element = ""
    }

    override fun endElement(uri: String?, localName: String?, qName: String?) {
        super.endElement(uri, localName, qName)
        if (entryFound){
            if (titleFound){
                titleFound = false
                newsTitle = element
            }else if (imageFound){
                imageFound =false
            }else if (descriptionFound){
                descriptionFound = false
                description = element
            }else if (videoIdFound){
                videoIdFound = false
                videoId = element
            }
        }
        if (localName == "entry"){
            entryFound = false
            data.add(NewsData(newsTitle, thumbNail, description, videoId))
        }
    }

    override fun characters(ch: CharArray?, start: Int, length: Int) {
        super.characters(ch, start, length)
        ch?.let {
            element += String(it, start, length)
        }
    }

    fun parseURL(url:String){
        listener.start()
        GlobalScope.launch {
            try {
                val inputStream = URL(url).openStream()
                parser.parse(inputStream, this@CNNYoutubeSAX)
                withContext(Dispatchers.Main){
                    listener.finish(data)
                }
            }catch (e : Throwable){
                e.printStackTrace()
            }
        }

    }


}