package com.louis.baozi.baozi_novel.controller;

import com.louis.baozi.baozi_novel.factory.SpiderFactory;
import com.louis.baozi.baozi_novel.pageprocessor.CategoryPageProcessor;
import com.louis.baozi.baozi_novel.pageprocessor.DetailNovelPageProcessor;
import com.louis.baozi.baozi_novel.pageprocessor.ListNovelPageProcessor;
import com.louis.baozi.baozi_novel.pipeline.CategoryPipeline;
import com.louis.baozi.baozi_novel.pipeline.DetailNovelPipeline;
import com.louis.baozi.baozi_novel.pipeline.ListNovelPipeline;
import com.louis.baozi.baozi_novel.repository.NovelRepository;
import com.louis.baozi.baozi_novel.repository.entity.NovelPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import us.codecraft.webmagic.Spider;

import java.time.Instant;

/**
 * @date : 2021/10/29
 */
@RestController
@RequestMapping("/novel")
public class NovelPatchController {

    @Autowired
    private NovelRepository novelRepository;

    @Autowired
    private DetailNovelPipeline detailPipeline;

    @Autowired
    private ListNovelPageProcessor listPageProcessor;

    @Autowired
    private ListNovelPipeline listPipeline;

    @Autowired
    private CategoryPageProcessor categoryPageProcessor;

    @Autowired
    private DetailNovelPageProcessor detailNovelPageProcessor;

    @Autowired
    private CategoryPipeline categoryPipeline;

    @Autowired
    private SpiderFactory spiderFactory;

    // https://www.qb5.la/fenlei/1_1/
    @GetMapping("/patch")
    public String patch(@RequestParam("url") String url) {

      /*  for (int i = 4; i < 602; i++) {
            String tmpUrl = url + i;
        }*/
        spiderFactory.run(listPageProcessor, listPipeline, url);

        return "OK";
    }

    @GetMapping("/patch-detail")
    public String patchDetail(@RequestParam("url") String url) {
        spiderFactory.run(detailNovelPageProcessor, detailPipeline, url);
        return "OK";
    }

    @GetMapping("/patch-category")
    public String patchCategory(@RequestParam("url") String url) {
        Spider.create(categoryPageProcessor).addUrl(url)
                .addPipeline(categoryPipeline).thread(1).run();
        return "OK";
    }

    @PostMapping("/add")
    public String addNovel(@RequestBody NovelPO novelPO) {
        novelPO.setCreateTime(Instant.now());
        novelPO.setModifiedTime(Instant.now());
        Object save = novelRepository.save(novelPO);
        return "OK";
    }
}
