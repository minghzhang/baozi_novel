package com.louis.baozi.baozi_novel.proxy;

import lombok.Data;
import us.codecraft.webmagic.proxy.Proxy;


/**
 * @date : 2021/11/1
 */
@Data
public class HostInfo {

    //"ip":"36.7.250.12","port":54919

    // {"code":1000,"data":[{"ip":"36.7.250.12","port":54919}]}
    private String ip;

    private Integer port;

    public Proxy toProxy() {
        return new Proxy(ip, port);
    }
}
