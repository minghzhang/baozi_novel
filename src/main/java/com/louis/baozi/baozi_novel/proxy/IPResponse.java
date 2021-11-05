package com.louis.baozi.baozi_novel.proxy;

import lombok.Data;

import java.util.List;

/**
 * @date : 2021/11/1
 */
@Data
public class IPResponse {

    //{"code":1000,"data":[{"ip":"36.7.250.12","port":54919}]}

    private Integer code;

    private String msg;

    private List<HostInfo> data;
}
