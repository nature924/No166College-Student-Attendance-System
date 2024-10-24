package com.dao;

import com.entity.XueshengKaoqinListEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XueshengKaoqinListView;

/**
 * 学生考勤详情 Dao 接口
 *
 * @author 
 */
public interface XueshengKaoqinListDao extends BaseMapper<XueshengKaoqinListEntity> {

   List<XueshengKaoqinListView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
