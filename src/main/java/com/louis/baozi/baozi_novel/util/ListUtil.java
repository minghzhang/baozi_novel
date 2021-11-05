package com.louis.baozi.baozi_novel.util;

import com.google.common.base.Splitter;

import java.util.List;

/**
 * @date : 2021/10/29
 */
public class ListUtil {

    public static List<String> getListElements(String content) {
        content = content.substring(content.indexOf("[") + 1, content.indexOf("]"));
        return Splitter.on(",").omitEmptyStrings().trimResults().splitToList(content);
    }

    public static void main(String[] args) {
        System.out.println(ListUtil.getListElements("[ 首页, 玄幻魔法, 仙侠修真, 都市言情, 历史军事, 网游竞技, 科幻灵异, 恐怖惊悚, 其他类型, 完本小说, 热门小说]"));
        System.out.println(ListUtil.getListElements("[https://www.qb5.la, https://www.qb5.la/fenlei/1_1/, https://www.qb5.la/fenlei/2_1/, https://www.qb5.la/fenlei/3_1/, https://www.qb5.la/fenlei/4_1/, https://www.qb5.la/fenlei/5_1/, https://www.qb5.la/fenlei/6_1/, https://www.qb5.la/fenlei/7_1/, https://www.qb5.la/fenlei/8_1/, https://www.qb5.la/quanben/, https://www.qb5.la/top/monthvisit/]"));
    }
}
