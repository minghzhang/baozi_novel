package com.louis.baozi.baozi_novel.factory;

import com.louis.baozi.baozi_novel.proxy.HttpClientDownloaderProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @date : 2021/11/1
 */
@Component
public class SpiderFactory {

    @Autowired
    private HttpClientDownloaderProxy httpClientDownloaderProxy;

    public void run(PageProcessor pageProcessor, Pipeline pipeline, String url) {
        Spider.create(pageProcessor)
                .addPipeline(pipeline)
                .addUrl(url)
                .setDownloader(httpClientDownloaderProxy.getDownloadProxy())
                .thread(3)
                .run();
    }
}
