package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.TblEmployee;
import com.example.demo.mapper.TblEmployeeMapper;
import com.example.demo.service.ITblEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gzh
 * @since 2020-01-14
 */
@Service
public class TblEmployeeServiceImpl extends ServiceImpl<TblEmployeeMapper, TblEmployee> implements ITblEmployeeService {

    @Resource
    private TblEmployeeMapper tblEmployeeMapper;

    /**
     * 添加人员
     *
     * @param tblEmployee
     * @return
     */
    @Override
    public Result addUser(TblEmployee tblEmployee) {
        int count = tblEmployeeMapper.insert(tblEmployee);
        if (count == 1) {
            return Result.ok();
        } else {
            return Result.err("操作失败");
        }
    }

    /**
     * 删除人员
     *
     * @param userName
     * @return
     */
    @Override
    public Result deleteUser(String userName) {
        QueryWrapper<TblEmployee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        int count = tblEmployeeMapper.delete(queryWrapper);
        if (count > 0) {
            return Result.ok();
        } else {
            return Result.err("删除失败");
        }
    }

    /**
     * 修改人员
     *
     * @param tblEmployee
     * @return
     */
    @Override
    public Result updateUser(TblEmployee tblEmployee) {
        try {
            int count = 0;
            count = tblEmployeeMapper.updateById(tblEmployee);
            if (count == 1) {
                return Result.ok();
            } else {
                return Result.err("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err("修改失败");
        }
    }

}
