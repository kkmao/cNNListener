package tw.nccu.mail2.mao.cnnlistener

interface ParserListener {
    //Subscript design pattern
    fun start()
    fun finish(news : List<NewsData>)
}