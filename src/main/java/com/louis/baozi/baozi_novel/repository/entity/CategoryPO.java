package com.louis.baozi.baozi_novel.repository.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Instant;

/**
 * @date : 2021/10/29
 */
@Entity(name = "category")
@Data
public class CategoryPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer sort;

    private String url;

    @Column(name = "parent_id")
    private String parentId;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "modified_time")
    private Instant modifiedTime;

    public static CategoryPO buildCategoryPO(int rank, String title, String url) {
        CategoryPO categoryPO = new CategoryPO();
        categoryPO.setTitle(title);
        categoryPO.setUrl(url);
        categoryPO.setSort(rank + 1);
        categoryPO.setParentId("-1");
        categoryPO.setCreateTime(Instant.now());
        categoryPO.setModifiedTime(Instant.now());
        return categoryPO;
    }
}
