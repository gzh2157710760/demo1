package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.Student;
import com.example.demo.service.IStudentService;
import com.example.demo.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 学生表 前端控制器
 * </p>
 *
 * @author gzh
 * @since 2020-01-15
 */
@Api(tags = "学生管理接口")
@RestController
@RequestMapping("/demo/student")
public class StudentController {
    @Resource
    private IStudentService iStudentService;

    @GetMapping("/studentList")
    @ApiOperation("学生列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "数据条数"),
            @ApiImplicitParam(name = "studentName", value = "学生姓名"),
    })
    public Result studentList(Long pageNo, Long pageSize, String studentName){
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(studentName)){
            queryWrapper.like("name",studentName);
        }
        IPage<Student> iPage = iStudentService.page(new Page<>(pageNo,pageSize),queryWrapper);
        return Result.page(iPage);
    }
}
