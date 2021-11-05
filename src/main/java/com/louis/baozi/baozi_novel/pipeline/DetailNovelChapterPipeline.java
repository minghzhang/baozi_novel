package com.louis.baozi.baozi_novel.pipeline;

import com.louis.baozi.baozi_novel.repository.NovelChapterRepository;
import com.louis.baozi.baozi_novel.repository.entity.NovelChapterPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

/**
 * @date : 2021/11/1
 */
@Slf4j
@Component
public class DetailNovelChapterPipeline implements Pipeline {

    @Autowired
    private NovelChapterRepository novelChapterRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        log.info("get page: " + resultItems.getRequest().getUrl());
        String url = resultItems.get("url");

        NovelChapterPO novelChapterPO = novelChapterRepository.findNovelChapterPOByUrl(url);
        String content = resultItems.get("content");
        novelChapterPO.setContent(content);
        novelChapterRepository.save(novelChapterPO);
    }
}
