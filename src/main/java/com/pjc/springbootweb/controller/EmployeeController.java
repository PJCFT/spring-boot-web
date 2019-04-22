package com.pjc.springbootweb.controller;/*
 *@author: PJC
 *@time: 2019/4/19
 *@description: null
 */

import com.pjc.springbootweb.dao.DepartmentDao;
import com.pjc.springbootweb.dao.EmployeeDao;
import com.pjc.springbootweb.entities.Department;
import com.pjc.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;

    @GetMapping(value = "emps")
    public String list(Model model){
        Collection<Employee> employees= employeeDao.getAll();

        model.addAttribute("emps",employees);
        return "emp/list";
    }
    @GetMapping(value = "emp")
    public String toAddPage(Model model){
        //获取员工部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
    @PostMapping(value = "emp")
    public String addEmp(Employee employee){

        employeeDao.save(employee);

        return "redirect:/emps";
    }
    @GetMapping(value = "emp/{id}")
    public String editPage(@PathVariable("id") Integer id,Model model){
        //根据id获取员工的信息
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        //获取所以部门信息
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
    @PutMapping(value = "emp")
    public String eidtEmp(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    @DeleteMapping(value = "emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
