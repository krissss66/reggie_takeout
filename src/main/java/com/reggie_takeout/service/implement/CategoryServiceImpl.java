package com.reggie_takeout.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_takeout.common.CustomException;
import com.reggie_takeout.entity.Category;
import com.reggie_takeout.entity.Dish;
import com.reggie_takeout.entity.Setmeal;
import com.reggie_takeout.mapper.CategoryMapper;
import com.reggie_takeout.service.CategoryService;
import com.reggie_takeout.service.DishService;
import com.reggie_takeout.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    @Override
    public void remove(Long id){
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dish::getCategoryId, id);
        int count = (int) dishService.count(wrapper);
        if(count>0){
            throw new CustomException("category has dishes, cannot delete");
        }
        LambdaQueryWrapper<Setmeal> Setmealwrapper = new LambdaQueryWrapper<>();
        Setmealwrapper.eq(Setmeal::getCategoryId, id);
        int SetrmealCount = (int) setmealService.count(Setmealwrapper);
        if(SetrmealCount>0){
            throw new CustomException("category has setmeals, cannot delete");
        }
        super.removeById(id);
    }


}
