package com.louis.baozi.baozi_novel.proxy;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @date : 2021/11/1
 */
@Slf4j
@Component
public class IPPool {

    public List<HostInfo> getIp() {

        Request request = new Request.Builder()
                //.url("http://api.tianqiip.com/getip?secret=mjubo7vxsmd9ey63&type=json&num=1&time=3&port=1")
                // .url("http://api.tianqiip.com/getip?secret=mjubo7vxsmd9ey63&type=json&num=1&time=3&port=1")
                .url("http://api.tianqiip.com/getip?secret=mjubo7vxsmd9ey63&type=json&num=1&time=3&port=1")
                .build();

        OkHttpClient client = new OkHttpClient();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            IPResponse ipResponse = JSON.parseObject(string, IPResponse.class);
            log.info("ip:{}", ipResponse);
            if (CollectionUtils.isEmpty(ipResponse.getData())) {
                return null;
            }
            return ipResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
