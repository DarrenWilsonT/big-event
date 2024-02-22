package com.gientech.bigevent.business.service.impl;

import com.gientech.bigevent.business.mapper.ArticleMapper;
import com.gientech.bigevent.business.pojo.Article;
import com.gientech.bigevent.business.service.ArticleService;
import com.gientech.bigevent.framework.utils.ThreadLocalUtil;
import org.springframework.stereotype.Service;

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
        article.setCreateTime(article.getCreateTime());
        article.setUpdateTime(article.getUpdateTime());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("userId");
        article.setCreateUser(userId);

        articleMapper.add(article);
    }
}
