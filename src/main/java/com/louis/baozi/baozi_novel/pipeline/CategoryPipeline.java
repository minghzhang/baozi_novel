package com.louis.baozi.baozi_novel.pipeline;

import com.louis.baozi.baozi_novel.repository.CategoryRepository;
import com.louis.baozi.baozi_novel.repository.entity.CategoryPO;
import com.louis.baozi.baozi_novel.util.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.List;

/**
 * @date : 2021/10/29
 */
@Component
@Slf4j
public class CategoryPipeline implements Pipeline {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public void process(ResultItems resultItems, Task task) {
        log.info("get page: " + resultItems.getRequest().getUrl());
        String titleString = resultItems.get("title");
        String urlString = resultItems.get("url");
        log.info("title: " + titleString);
        log.info("url: " + urlString);
        List<String> titleList = ListUtil.getListElements(titleString);
        List<String> urlList = ListUtil.getListElements(urlString);
        for (int i = 0; i < titleList.size(); i++) {
            String title = titleList.get(i);
            String url = urlList.get(i);

            CategoryPO categoryPO = CategoryPO.buildCategoryPO(i, title, url);
            categoryRepository.save(categoryPO);
        }
    }

}
