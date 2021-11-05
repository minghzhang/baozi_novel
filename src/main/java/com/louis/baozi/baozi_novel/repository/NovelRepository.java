package com.louis.baozi.baozi_novel.repository;

import com.louis.baozi.baozi_novel.repository.entity.NovelPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @date : 2021/10/29
 */
public interface NovelRepository extends JpaRepository<NovelPO, Long> {

    NovelPO findNovelPOByUrl(String url);
}
