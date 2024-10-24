package com.dao;

import com.entity.XueshengKaoqinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XueshengKaoqinView;

/**
 * 学生考勤 Dao 接口
 *
 * @author 
 */
public interface XueshengKaoqinDao extends BaseMapper<XueshengKaoqinEntity> {

   List<XueshengKaoqinView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
