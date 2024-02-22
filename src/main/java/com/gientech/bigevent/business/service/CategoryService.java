package com.gientech.bigevent.business.service;

import com.gientech.bigevent.business.pojo.Category;

import java.util.List;

/**
 * @author aimintang
 * @date 2024/2/22
 * @description
 */
public interface CategoryService {
    void addCategory(Category category);

    List<Category> listCategory();

    Category findById(Integer id);

    void update(Category category);

    void delete(Integer id);
}
