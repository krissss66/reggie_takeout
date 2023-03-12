package com.reggie_takeout.service.implement;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_takeout.common.CustomException;
import com.reggie_takeout.entity.Dish;
import com.reggie_takeout.entity.Setmeal;
import com.reggie_takeout.mapper.DishMapper;
import com.reggie_takeout.service.DishService;
import com.reggie_takeout.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {


}
