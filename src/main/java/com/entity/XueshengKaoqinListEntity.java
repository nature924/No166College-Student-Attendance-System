package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 学生考勤详情
 *
 * @author 
 * @email
 */
@TableName("xuesheng_kaoqin_list")
public class XueshengKaoqinListEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XueshengKaoqinListEntity() {

	}

	public XueshengKaoqinListEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 学生
     */
    @ColumnInfo(comment="学生",type="int(11)")
    @TableField(value = "xuesheng_id")

    private Integer xueshengId;


    /**
     * 考勤
     */
    @ColumnInfo(comment="考勤",type="int(11)")
    @TableField(value = "xuesheng_kaoqin_id")

    private Integer xueshengKaoqinId;


    /**
     * 打卡状态
     */
    @ColumnInfo(comment="打卡状态",type="int(11)")
    @TableField(value = "xuesheng_kaoqin_list_types")

    private Integer xueshengKaoqinListTypes;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="添加时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 打卡时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="打卡时间",type="timestamp")
    @TableField(value = "update_time",fill = FieldFill.UPDATE)

    private Date updateTime;


    /**
     * 创建时间  listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：学生
	 */
    public Integer getXueshengId() {
        return xueshengId;
    }
    /**
	 * 设置：学生
	 */

    public void setXueshengId(Integer xueshengId) {
        this.xueshengId = xueshengId;
    }
    /**
	 * 获取：考勤
	 */
    public Integer getXueshengKaoqinId() {
        return xueshengKaoqinId;
    }
    /**
	 * 设置：考勤
	 */

    public void setXueshengKaoqinId(Integer xueshengKaoqinId) {
        this.xueshengKaoqinId = xueshengKaoqinId;
    }
    /**
	 * 获取：打卡状态
	 */
    public Integer getXueshengKaoqinListTypes() {
        return xueshengKaoqinListTypes;
    }
    /**
	 * 设置：打卡状态
	 */

    public void setXueshengKaoqinListTypes(Integer xueshengKaoqinListTypes) {
        this.xueshengKaoqinListTypes = xueshengKaoqinListTypes;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：打卡时间
	 */
    public Date getUpdateTime() {
        return updateTime;
    }
    /**
	 * 设置：打卡时间
	 */

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "XueshengKaoqinList{" +
            ", id=" + id +
            ", xueshengId=" + xueshengId +
            ", xueshengKaoqinId=" + xueshengKaoqinId +
            ", xueshengKaoqinListTypes=" + xueshengKaoqinListTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", updateTime=" + DateUtil.convertString(updateTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
