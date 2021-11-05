package com.louis.baozi.baozi_novel.pageprocessor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @date : 2021/11/1
 */
@Component
public class DetailNovelChapterPageProcessor implements PageProcessor {

    private Site site = Site.me().setDomain("127.0.0.1");

    @Override
    public void process(Page page) {
        page.putField("url", page.getUrl().toString());
        page.putField("content", page.getHtml().xpath("//*[@id='content']/text()").all().toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
