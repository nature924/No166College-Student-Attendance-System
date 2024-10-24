package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.XueshengKaoqinEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 学生考勤
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("xuesheng_kaoqin")
public class XueshengKaoqinView extends XueshengKaoqinEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 学生考勤类型的值
	*/
	@ColumnInfo(comment="学生考勤类型的字典表值",type="varchar(200)")
	private String xueshengKaoqinValue;




	public XueshengKaoqinView() {

	}

	public XueshengKaoqinView(XueshengKaoqinEntity xueshengKaoqinEntity) {
		try {
			BeanUtils.copyProperties(this, xueshengKaoqinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 学生考勤类型的值
	*/
	public String getXueshengKaoqinValue() {
		return xueshengKaoqinValue;
	}
	/**
	* 设置： 学生考勤类型的值
	*/
	public void setXueshengKaoqinValue(String xueshengKaoqinValue) {
		this.xueshengKaoqinValue = xueshengKaoqinValue;
	}




	@Override
	public String toString() {
		return "XueshengKaoqinView{" +
			", xueshengKaoqinValue=" + xueshengKaoqinValue +
			"} " + super.toString();
	}
}
