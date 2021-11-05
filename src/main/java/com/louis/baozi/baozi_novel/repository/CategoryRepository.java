package com.louis.baozi.baozi_novel.repository;

import com.louis.baozi.baozi_novel.repository.entity.CategoryPO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @date : 2021/10/29
 */
public interface CategoryRepository extends JpaRepository<CategoryPO, Long> {

    CategoryPO findByTitle(String title);
}
