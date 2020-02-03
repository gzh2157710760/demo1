package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.entity.TbUser;
import com.example.demo.mapper.TbUserMapper;
import com.example.demo.service.ITbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author gzh
 * @since 2020-01-17
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserMapper, TbUser> implements ITbUserService {
    @Resource
    private TbUserMapper tbUserMapper;
    @Resource
    private ITbUserService iTbUserService;

    /**
     * 添加用户
     *
     * @param tbUser 用户对象
     * @return
     */
    @Override
    public Result addUser(TbUser tbUser) {
        int count = 0;
        try {
            count = tbUserMapper.insert(tbUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 去除QQ号重复的
        List<TbUser> userList = iTbUserService.list();
        ArrayList<Integer> idList = new ArrayList<>();
        // 获取重复数据的id
        for (int i = 0; i < userList.size(); i++) {
            for (int j = i + 1; j < userList.size(); j++) {
                if (userList.get(i).getQq().equals(userList.get(j).getQq())) {
                    // 将数据重复的id放入集合中
                    idList.add(userList.get(j).getId());
                }
            }
        }
        // 去除idList中重复的id
        idList = (ArrayList<Integer>) idList.stream().distinct().collect(Collectors.toList());
        if (idList.size() > 0) {
            // 根据id集合删除重复的数据
            tbUserMapper.deleteBatchIds(idList);
        }

        if (count == 1) {
            return Result.ok();
        } else {
            return Result.err("添加失败");
        }
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @Override
    public Result deleteUser(int id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("id", id);
        int count = tbUserMapper.delete(queryWrapper);
        if (count == 1) {
            return Result.ok("删除成功");
        } else {
            return Result.err("删除失败");
        }
    }

    /**
     * 修改用户
     *
     * @param userName
     * @param userPass
     * @param sign
     * @param tel
     * @return
     */
    @Override
    public Result modifyUser(String userName, String userPass, String sign, String tel, String icon) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if (StringUtils.isNotBlank(userName)){
            queryWrapper.eq("user_name",userName);
        }
        if (StringUtils.isNotBlank(userPass)){
            queryWrapper.eq("user_pass",userPass);
        }
        if (StringUtils.isNotBlank(sign)){
            queryWrapper.eq("sign",sign);
        }
        if (StringUtils.isNotBlank(tel)){
            queryWrapper.eq("tel",tel);
        }
        TbUser tbUser = new TbUser();
        tbUser.setIcon(icon);
        int count = tbUserMapper.update(tbUser,queryWrapper);
        if (count>0){
            return Result.ok("修改成功");
        }else {
            return Result.err("修改失败");
        }
    }


}
