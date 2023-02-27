package com.reggie_takeout.service.implement;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.reggie_takeout.mapper.employeeMapper;
import com.reggie_takeout.entity.Employee;
import com.reggie_takeout.service.EmployeeService;
import org.springframework.stereotype.Service;



@Service
public class EmployeeServiceImpl extends ServiceImpl<employeeMapper, Employee> implements EmployeeService {


}
