package com.gientech.bigevent.business.service;

import com.gientech.bigevent.business.pojo.Article;
import com.gientech.bigevent.framework.serviceflow.output.PageBean;

/**
 * @author aimintang
 * @date 2024/2/22
 * @description
 */
public interface ArticleService {
    void addArticle(Article article);

    PageBean<Article> listArticle(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
