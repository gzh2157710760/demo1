package com.example.demo.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: jack
 * @Description: 前端table分页实体类
 * @Date Create in 17:17 2019-04-18
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {

    private long pageSize;

    private long pageNo;

    private long totalCount;

    private long totalPage;

    private Object data;

    public static PageResult ok(IPage p){
        return new PageResult(p.getSize(),p.getCurrent(),p.getTotal(),p.getPages(),p.getRecords());
    }

}
