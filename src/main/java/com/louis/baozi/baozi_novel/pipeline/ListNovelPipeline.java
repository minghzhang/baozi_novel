package com.louis.baozi.baozi_novel.pipeline;


import com.louis.baozi.baozi_novel.factory.SpiderFactory;
import com.louis.baozi.baozi_novel.pageprocessor.DetailNovelPageProcessor;
import com.louis.baozi.baozi_novel.repository.NovelRepository;
import com.louis.baozi.baozi_novel.repository.entity.NovelPO;
import com.louis.baozi.baozi_novel.util.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;
import java.util.Map;

/**
 * @date : 2021/10/29
 */
@Slf4j
@Component
public class ListNovelPipeline implements Pipeline {

    @Autowired
    private DetailNovelPageProcessor detailPageProcessor;

    @Autowired
    private DetailNovelPipeline detailPipeline;

    @Autowired
    private NovelRepository novelRepository;

    @Autowired
    private SpiderFactory spiderFactory;

    @Override
    public void process(ResultItems resultItems, Task task) {
        log.info("get page: " + resultItems.getRequest().getUrl());
        Map<String, Object> all = resultItems.getAll();

        for (Map.Entry<String, Object> entry : all.entrySet()) {
            log.info(entry.getKey() + " =: " + entry.getValue());
        }

        List<String> urlList = ListUtil.getListElements((String) all.get("url"));
        for (String url : urlList) {

            NovelPO novelPO = novelRepository.findNovelPOByUrl(url);
            if (novelPO != null) {
                continue;
            }

            spiderFactory.run(detailPageProcessor, detailPipeline, url);
        }

    }

}
