package com.gientech.bigevent.business.service.impl;

import com.gientech.bigevent.business.mapper.ArticleMapper;
import com.gientech.bigevent.business.pojo.Article;
import com.gientech.bigevent.business.service.ArticleService;
import com.gientech.bigevent.framework.serviceflow.output.PageBean;
import com.gientech.bigevent.framework.utils.ThreadLocalUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author aimintang
 * @date 2024/2/22
 * @description
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public void addArticle(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> listArticle(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageHelper.startPage(pageNum, pageSize);

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        List<Article> articleList = articleMapper.listArticle(categoryId, state, userId);

        return new PageBean<>(articleList);
    }
}
