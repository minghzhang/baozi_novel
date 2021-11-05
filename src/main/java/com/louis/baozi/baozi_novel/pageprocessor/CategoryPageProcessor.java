package com.louis.baozi.baozi_novel.pageprocessor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @date : 2021/10/29
 */
@Component
public class CategoryPageProcessor implements PageProcessor {

    private Site site = Site.me().setDomain("127.0.0.1");

    @Override
    public void process(Page page) {
        page.putField("title", page.getHtml().xpath("//div[@class='nav_cont']/ul/li/a/text()").all().toString());
        page.putField("url", page.getHtml().xpath("//div[@class='nav_cont']/ul/li/a/@href").all().toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
