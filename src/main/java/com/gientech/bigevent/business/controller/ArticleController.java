package com.gientech.bigevent.business.controller;

import com.gientech.bigevent.business.pojo.Article;
import com.gientech.bigevent.business.pojo.Result;
import com.gientech.bigevent.business.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aimintang
 * @date 2024/2/22
 * @description
 */
@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public Result addArticle(@RequestBody Article article) {
        articleService.addArticle(article);
        return Result.success("添加文章成功");
    }

    @GetMapping(value = "/list")
    public Result listArticle() {
        return Result.success("所有文章数据");
    }
}
