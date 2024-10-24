package com.dao;

import com.entity.XueshengqingjiaEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.XueshengqingjiaView;

/**
 * 学生请假 Dao 接口
 *
 * @author 
 */
public interface XueshengqingjiaDao extends BaseMapper<XueshengqingjiaEntity> {

   List<XueshengqingjiaView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
