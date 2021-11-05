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
@Entity(name = "novel")
@Data
public class NovelPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_id")
    private Long categoryId;

    private String title;

    private String author;

    private String pic;

    private String description;

    private String tag;

    private String url;

    private String html;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "modified_time")
    private Instant modifiedTime;
}
