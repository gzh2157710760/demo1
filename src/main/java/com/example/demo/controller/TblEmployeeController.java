package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.TblEmployee;
import com.example.demo.service.ITblEmployeeService;
import com.example.demo.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gzh
 * @since 2020-01-14
 */
@Api(tags = "人员管理接口")
@RestController
@RequestMapping("/dome/tbl-employee")
public class TblEmployeeController {
    @Autowired
    private ITblEmployeeService iTblEmployeeService;

    /**
     * 分页查询列表数据
     *
     * @param pageNo   页码
     * @param pageSize 数据条数
     * @param userName 人员姓名
     * @return
     */
    @ApiOperation("人员列表")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "数据条数"),
            @ApiImplicitParam(name = "userName", value = "人员姓名"),
    })
    public Result list(Long pageNo, Long pageSize, String userName) {
        QueryWrapper<TblEmployee> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.eq("user_name", userName);
        }
        IPage<TblEmployee> iPage = iTblEmployeeService.page(new Page<>(pageNo, pageSize), queryWrapper);
        return Result.page(iPage);
    }

    @ApiOperation("人员 姓名 + “——” +性别 数组")
    @GetMapping("/userList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "人员姓名"),
            @ApiImplicitParam(name = "age", value = "人员年龄")
    })
    public Result userList(String userName,String age) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(userName)){
            queryWrapper.like("user_name",userName);
        }
        if (StringUtils.isNotBlank(age)){
            queryWrapper.eq("age",age);
        }
        queryWrapper.select("user_name,gender");
        List<TblEmployee> list = iTblEmployeeService.list(queryWrapper);
        List<String> stringList = new ArrayList<>();
        for (TblEmployee tblEmployee: list) {
            String st = tblEmployee.getUserName()+"-"+tblEmployee.getGender();
            stringList.add(st);
        }
        return Result.ok(stringList);
    }

    /**
     * 添加人员
     *
     * @param tblEmployee 人员对象
     * @return
     */
    @ApiOperation("添加人员")
    @PostMapping("/addUser")
    public Result addUser(TblEmployee tblEmployee) {
        return iTblEmployeeService.addUser(tblEmployee);
    }

    /**
     * 根据id查询人员信息
     *
     * @param id 人员id
     * @return
     */
    @ApiOperation("根据id查询人员信息")
    @GetMapping("/userName")
    @ApiImplicitParam(name = "id", value = "人员id")
    public Result userName(int id) {
        try {
            TblEmployee tblEmployee = iTblEmployeeService.getOne(new QueryWrapper<TblEmployee>().eq("id", id));
            if (tblEmployee == null) {
                return Result.err("查无此人");
            } else {
                return Result.ok(tblEmployee);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err("获取失败");
        }
    }

    /**
     * 删除人员
     *
     * @param userNaem 人员姓名
     * @return
     */
    @ApiOperation("删除人员")
    @GetMapping("/deleteUser")
    @ApiImplicitParam(name = "userNaem", value = "人员姓名")
    public Result deleteUser(String userNaem) {
        return iTblEmployeeService.deleteUser(userNaem);
    }

    /**
     * 修改人员
     *
     * @param tblEmployee 人员对象
     * @return
     */
    @ApiOperation("修改人员")
    @PostMapping("/updateUser")
    private Result updateUser(TblEmployee tblEmployee) {
        return iTblEmployeeService.updateUser(tblEmployee);
    }
}
