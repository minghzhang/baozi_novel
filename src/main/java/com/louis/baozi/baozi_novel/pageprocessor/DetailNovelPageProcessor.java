package com.louis.baozi.baozi_novel.pageprocessor;

import org.springframework.stereotype.Component;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @date : 2021/10/29
 */
@Component
public class DetailNovelPageProcessor implements PageProcessor {
    private Site site = Site.me().setDomain("127.0.0.1");

    @Override
    public void process(Page page) {
        page.putField("pic", page.getHtml().xpath("//*[@id='picbox']/div/img/@src").toString());
        page.putField("author", page.getHtml().xpath("//*[@id='info']/h1/small/a/text()").toString());
        page.putField("title", page.getHtml().xpath("//*[@id='info']/h1/text()").toString());
        page.putField("description", page.getHtml().xpath("//*[@id='intro']/text()").toString());
        page.putField("categoryTitle", page.getHtml().xpath("//*[@id='bookdetail']/div[1]/a[2]/text()").toString());
        page.putField("novelUrl", page.getUrl().toString());

        page.putField("chapterUrl", page.getHtml().xpath("//div[@class='zjbox']/dl/dd/a/@href").all().toString());
        page.putField("chapterTitle", page.getHtml().xpath("//div[@class='zjbox']/dl/dd/a/text()").all().toString());
        page.putField("novelUrl", page.getUrl().toString());
        page.putField("html", page.getHtml().toString());
    }

    @Override
    public Site getSite() {
        return site;
    }
}
