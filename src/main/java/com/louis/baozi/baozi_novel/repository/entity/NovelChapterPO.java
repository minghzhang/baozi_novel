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
@Entity(name = "novel_chapter")
@Data
public class NovelChapterPO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "novel_id")
    private Long novelId;

    private String title;

    private String content;

    private String url;

    @Column(name = "create_time")
    private Instant createTime;

    @Column(name = "modified_time")
    private Instant modifiedTime;
}
