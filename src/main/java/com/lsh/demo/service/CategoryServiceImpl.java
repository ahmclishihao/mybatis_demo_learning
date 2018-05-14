package com.lsh.demo.service;

import com.lsh.demo.mapper.CategoryMapper;
import com.lsh.demo.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-14
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> findAll() {
        return categoryMapper.getCategoryAll();
    }
}
