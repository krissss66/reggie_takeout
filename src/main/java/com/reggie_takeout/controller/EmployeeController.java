package com.reggie_takeout.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.reggie_takeout.common.R;
import com.reggie_takeout.entity.Employee;
import com.reggie_takeout.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    // login
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        LambdaQueryWrapper<Employee>queryWrapper = new LambdaQueryWrapper<Employee>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        if (emp == null) {
            return R.error("User does not exist");
        } else if (!emp.getPassword().equals(password)) {
            return R.error("Password is incorrect");
        } else if(emp.getStatus() == 0){

            return R.error("Password is incorrect");

        }else {

            request.getSession().setAttribute("employee", emp.getId());
            return R.success(emp);
        }

    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");
        return R.success("logout successfully");
    }


    @PostMapping
    public R<String> save(HttpServletRequest request, @RequestBody Employee employee) {


        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
//
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//
//        Long empID = (Long) request.getSession().getAttribute("employee");
//        employee.setCreateUser(empID);
//        employee.setUpdateUser(empID);
        employeeService.save(employee);
        return R.success("save successfully");
    }


    @GetMapping("/page")
    public R<Page> page(int page, int pageSize,String name){
        Page pageInfo = new Page(page, pageSize);

        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<Employee>();
        queryWrapper.like(StringUtils.isNotBlank(name), Employee::getName, name);

        queryWrapper.orderByDesc(Employee::getCreateTime);

        employeeService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);

    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {

//        employee.setUpdateTime(LocalDateTime.now());
//        Long empID = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateUser(empID);
        employeeService.updateById(employee);
        return R.success("update successfully");
    }

    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){

        return R.success(employeeService.getById(id));
    }



}
