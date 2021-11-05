package com.louis.baozi.baozi_novel.repository;

import com.louis.baozi.baozi_novel.repository.entity.NovelChapterPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @date : 2021/10/29
 */
public interface NovelChapterRepository extends JpaRepository<NovelChapterPO, Long> {

    NovelChapterPO findNovelChapterPOByUrl(String url);
}
