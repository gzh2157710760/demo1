package com.example.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.entity.TbUser;
import com.example.demo.mapper.TbUserMapper;
import com.example.demo.service.ITbUserService;
import com.example.demo.vo.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author gzh
 * @since 2020-01-17
 */
@Api(tags = "用户管理接口")
@RestController
@RequestMapping("/demo/tb-user")
public class TbUserController {
    @Resource
    private ITbUserService iTbUserService;
    @Resource
    private TbUserMapper tbUserMapper;

    @ApiOperation("人员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "条数")
    })
    @GetMapping("/userList")
    public Result userList(Long pageNo, Long pageSize) {
        IPage<TbUser> iPage = iTbUserService.page(new Page<>(pageNo, pageSize));
        return Result.page(iPage);
    }

    @ApiOperation("添加用户")
    @PostMapping("/addUser")
    public Result addUser(TbUser tbUser) {
        return iTbUserService.addUser(tbUser);
    }

    @ApiOperation("删除用户")
    @PostMapping("/deleteUser")
    @ApiImplicitParam(name = "id", value = "用户id")
    public Result deleteUser(int id) {
        return iTbUserService.deleteUser(id);
    }

    @ApiOperation("修改用户")
    @PostMapping("updateUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "如果用户姓名为"),
            @ApiImplicitParam(name = "userPass", value = "如果用户密码为"),
            @ApiImplicitParam(name = "sign", value = "如果用户签名为"),
            @ApiImplicitParam(name = "tel", value = "如果用户电话为"),
            @ApiImplicitParam(name = "icon", value = "则修改用户头像为")
    })
    public Result updateUser(String userName, String userPass, String sign, String tel, String icon) {
        return iTbUserService.modifyUser(userName, userPass, sign, tel, icon);
    }
}
