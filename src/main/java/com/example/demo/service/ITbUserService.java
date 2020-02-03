package com.example.demo.service;

import com.example.demo.entity.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.vo.Result;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gzh
 * @since 2020-01-17
 */
public interface ITbUserService extends IService<TbUser> {
    /**
     * 添加用户
     * @param tbUser
     * @return
     */
    Result addUser(TbUser tbUser);

    /**
     * 删除用户
     * @param id
     * @return
     */
    Result deleteUser(int id);

    /**
     * 修改用户
     * @param userName
     * @param userPass
     * @param sign
     * @param tel
     * @return
     */
    Result modifyUser(String userName,String userPass, String sign, String tel,String icon);
}
