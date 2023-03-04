package com.reggie_takeout.service.implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_takeout.entity.Category;
import com.reggie_takeout.mapper.CategoryMapper;
import com.reggie_takeout.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {



}
