package com.louis.baozi.baozi_novel.pipeline;

import com.louis.baozi.baozi_novel.factory.SpiderFactory;
import com.louis.baozi.baozi_novel.pageprocessor.DetailNovelChapterPageProcessor;
import com.louis.baozi.baozi_novel.repository.CategoryRepository;
import com.louis.baozi.baozi_novel.repository.NovelChapterRepository;
import com.louis.baozi.baozi_novel.repository.NovelRepository;
import com.louis.baozi.baozi_novel.repository.entity.CategoryPO;
import com.louis.baozi.baozi_novel.repository.entity.NovelChapterPO;
import com.louis.baozi.baozi_novel.repository.entity.NovelPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.time.Instant;
import java.util.Map;

/**
 * @date : 2021/10/29
 */
@Slf4j
@Component
public class DetailNovelPipeline implements Pipeline {

    @Autowired
    private NovelRepository novelRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NovelChapterRepository novelChapterRepository;

    @Autowired
    private DetailNovelChapterPageProcessor detailNovelChapterPageProcessor;

    @Autowired
    private DetailNovelChapterPipeline detailNovelChapterPipeline;

    @Autowired
    private SpiderFactory spiderFactory;

    @Override
    public void process(ResultItems resultItems, Task task) {
        log.info("get page: " + resultItems.getRequest().getUrl());

        Map<String, Object> result = resultItems.getAll();
        NovelPO novelPO = novelRepository.findNovelPOByUrl((String) result.get("novelUrl"));
        if (novelPO == null) {
            novelPO = new NovelPO();
            novelPO.setAuthor((String) result.get("author"));
            novelPO.setTitle((String) result.get("title"));
            novelPO.setDescription((String) result.get("description"));
            novelPO.setPic((String) result.get("pic"));
            novelPO.setUrl((String) result.get("novelUrl"));
            novelPO.setHtml((String) result.get("html"));
            novelPO.setCreateTime(Instant.now());
            novelPO.setModifiedTime(Instant.now());
            String categoryTitle = (String) result.get("categoryTitle");
            CategoryPO categoryPO = categoryRepository.findByTitle(categoryTitle);
            if (categoryPO != null) {
                novelPO.setCategoryId(categoryPO.getId());
            }

            novelRepository.save(novelPO);
            log.info("save novelPO:{},{},{},{} ", novelPO.getId(), novelPO.getTitle(), novelPO.getAuthor(), novelPO.getDescription());
        } else {
            log.info("loading novelPO:{},{},{},{} ", novelPO.getId(), novelPO.getTitle(), novelPO.getAuthor(), novelPO.getDescription());
        }


       /* String novelUrl = novelPO.getUrl();
        String chapterUrlString = (String) result.get("chapterUrl");
        String chapterTitleString = (String) result.get("chapterTitle");
        log.info("novelUrl: {}", novelUrl);
        log.info("chapterUrl: {}", chapterUrlString);
        log.info("chapterTitle: {}", chapterTitleString);
        List<String> chapterUrlList = ListUtil.getListElements(chapterUrlString);
        List<String> chapterTitleList = ListUtil.getListElements(chapterTitleString);

        List<NovelChapterPO> chapterPOList = new ArrayList<>();
        for (int i = 0; i < chapterTitleList.size(); i++) {

            NovelChapterPO chapterPO = buildChapterPO(novelPO.getId(), chapterTitleList.get(i), novelUrl + chapterUrlList.get(i));
            chapterPOList.add(chapterPO);
            if (chapterPOList.size() == 100) {
                log.info("index:{},chapterPOList:{}", i, chapterPOList);
                novelChapterRepository.saveAll(chapterPOList);
                chapterPOList.clear();
            }

            //novelChapterRepository.saveAll(chapterPOList);
           *//* NovelChapterPO chapterPO = novelChapterRepository.findNovelChapterPOByUrl(chapterUrl);
            if (chapterPO == null) {
                chapterPO = buildChapterPO(novelPO.getId(), chapterTitleList.get(i), chapterUrl);
                log.info("novelChapterPO: " + chapterPO);
                novelChapterRepository.save(chapterPO);
            }*//*

         *//*  if (Strings.isNullOrEmpty(chapterPO.getContent())) {
                spiderFactory.run(detailNovelChapterPageProcessor, detailNovelChapterPipeline, chapterUrl);
            }*//*
        }*/
    }

    private NovelChapterPO buildChapterPO(Long novelId, String chapterTitle, String chapterUrl) {
        NovelChapterPO chapterPO = new NovelChapterPO();
        chapterPO.setUrl(chapterUrl);
        chapterPO.setTitle(chapterTitle);
        chapterPO.setNovelId(novelId);
        chapterPO.setCreateTime(Instant.now());
        chapterPO.setModifiedTime(Instant.now());
        return chapterPO;
    }
}
