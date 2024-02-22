package com.gientech.bigevent.business.mapper;

import com.gientech.bigevent.business.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author aimintang
 * @date 2024/2/22
 * @description
 */
@Mapper
public interface ArticleMapper {
    @Insert("insert into article(title, content, cover_img, create_user, create_time, update_time)" +
            " values(#{title}, #{content}, #{coverImg}, #{createUser}, #{createTime}, #{updateTime})")
    void add(Article article);
}
