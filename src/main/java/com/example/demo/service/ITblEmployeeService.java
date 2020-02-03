package com.example.demo.service;

import com.example.demo.entity.TblEmployee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzh
 * @since 2020-01-14
 */
public interface ITblEmployeeService extends IService<TblEmployee> {
    Result addUser(TblEmployee tblEmployee);
    Result deleteUser(String userName);
    Result updateUser(TblEmployee tblEmployee);
}
