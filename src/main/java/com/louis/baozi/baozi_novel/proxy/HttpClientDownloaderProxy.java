package com.louis.baozi.baozi_novel.proxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @date : 2021/11/1
 */
@Slf4j
@Component
public class HttpClientDownloaderProxy {

    @Autowired
    private IPPool ipPool;
    private TimedHttpClientDownloader timedHttpClientDownloader;

    public HttpClientDownloader getDownloadProxy() {
        if (timedHttpClientDownloader == null) {
            timedHttpClientDownloader = createTimedHttpClientDownloader(createHttpClientDownloader());
        }
        if (timedHttpClientDownloader.expired()) {
            timedHttpClientDownloader = createTimedHttpClientDownloader(createHttpClientDownloader());
        }
        return timedHttpClientDownloader.getClientDownloader();
    }

    private HttpClientDownloader createHttpClientDownloader() {
        HttpClientDownloader clientDownloader = new HttpClientDownloader();
        List<HostInfo> hostInfos = ipPool.getIp();
        Proxy[] proxies = hostInfos.stream().map(HostInfo::toProxy).toArray(Proxy[]::new);
        clientDownloader.setProxyProvider(SimpleProxyProvider.from(proxies));
        return clientDownloader;
    }

    private TimedHttpClientDownloader createTimedHttpClientDownloader(HttpClientDownloader clientDownloader) {
        Instant expiredTime = Instant.now().plus(3, ChronoUnit.MINUTES);
        log.info("createTimedHttpClientDownloader:now:{},expired:{},", Instant.now(), expiredTime);
        return new TimedHttpClientDownloader(expiredTime, clientDownloader);
    }

    @Data
    @AllArgsConstructor
    public static class TimedHttpClientDownloader {
        private Instant expiredTime;
        private HttpClientDownloader clientDownloader;

        public boolean expired() {
            return expiredTime.isBefore(Instant.now());
        }

        public static void main(String[] args) {
            TimedHttpClientDownloader timedDownloader = new TimedHttpClientDownloader(Instant.now().plus(3, ChronoUnit.MINUTES), null);
            System.out.println(timedDownloader.expired());
            sleep(1000);
            System.out.println(timedDownloader.expired());
        }
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
