package com.gientech.bigevent.business.service.impl;

import com.gientech.bigevent.business.mapper.CategoryMapper;
import com.gientech.bigevent.business.pojo.Category;
import com.gientech.bigevent.business.service.CategoryService;
import com.gientech.bigevent.framework.utils.ThreadLocalUtil;
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
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public void addCategory(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");

        category.setCreateUser(id);

        categoryMapper.add(category);
    }

    @Override
    public List<Category> listCategory() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");

        return categoryMapper.listCategoryByUser(userId);
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.findById(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.update(category);
    }

    /**
     * @param category
     */
    @Override
    public void delete(Integer id) {
        categoryMapper.delete(id);
    }
}
