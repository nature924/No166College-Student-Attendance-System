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
 * 学生考勤
 *
 * @author 
 * @email
 */
@TableName("xuesheng_kaoqin")
public class XueshengKaoqinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XueshengKaoqinEntity() {

	}

	public XueshengKaoqinEntity(T t) {
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
     * 考勤唯一编号
     */
    @ColumnInfo(comment="考勤唯一编号",type="varchar(200)")
    @TableField(value = "xuesheng_kaoqin_uuid_number")

    private String xueshengKaoqinUuidNumber;


    /**
     * 考勤标题
     */
    @ColumnInfo(comment="考勤标题",type="varchar(200)")
    @TableField(value = "xuesheng_kaoqin_name")

    private String xueshengKaoqinName;


    /**
     * 学生考勤类型
     */
    @ColumnInfo(comment="学生考勤类型",type="int(11)")
    @TableField(value = "xuesheng_kaoqin_types")

    private Integer xueshengKaoqinTypes;


    /**
     * 考勤详情
     */
    @ColumnInfo(comment="考勤详情",type="longtext")
    @TableField(value = "xuesheng_kaoqin_content")

    private String xueshengKaoqinContent;


    /**
     * 考勤发起时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="考勤发起时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 考勤截止时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="考勤截止时间",type="timestamp")
    @TableField(value = "jiezhi_time")

    private Date jiezhiTime;


    /**
     * 创建时间
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
	 * 获取：考勤唯一编号
	 */
    public String getXueshengKaoqinUuidNumber() {
        return xueshengKaoqinUuidNumber;
    }
    /**
	 * 设置：考勤唯一编号
	 */

    public void setXueshengKaoqinUuidNumber(String xueshengKaoqinUuidNumber) {
        this.xueshengKaoqinUuidNumber = xueshengKaoqinUuidNumber;
    }
    /**
	 * 获取：考勤标题
	 */
    public String getXueshengKaoqinName() {
        return xueshengKaoqinName;
    }
    /**
	 * 设置：考勤标题
	 */

    public void setXueshengKaoqinName(String xueshengKaoqinName) {
        this.xueshengKaoqinName = xueshengKaoqinName;
    }
    /**
	 * 获取：学生考勤类型
	 */
    public Integer getXueshengKaoqinTypes() {
        return xueshengKaoqinTypes;
    }
    /**
	 * 设置：学生考勤类型
	 */

    public void setXueshengKaoqinTypes(Integer xueshengKaoqinTypes) {
        this.xueshengKaoqinTypes = xueshengKaoqinTypes;
    }
    /**
	 * 获取：考勤详情
	 */
    public String getXueshengKaoqinContent() {
        return xueshengKaoqinContent;
    }
    /**
	 * 设置：考勤详情
	 */

    public void setXueshengKaoqinContent(String xueshengKaoqinContent) {
        this.xueshengKaoqinContent = xueshengKaoqinContent;
    }
    /**
	 * 获取：考勤发起时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：考勤发起时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：考勤截止时间
	 */
    public Date getJiezhiTime() {
        return jiezhiTime;
    }
    /**
	 * 设置：考勤截止时间
	 */

    public void setJiezhiTime(Date jiezhiTime) {
        this.jiezhiTime = jiezhiTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "XueshengKaoqin{" +
            ", id=" + id +
            ", xueshengKaoqinUuidNumber=" + xueshengKaoqinUuidNumber +
            ", xueshengKaoqinName=" + xueshengKaoqinName +
            ", xueshengKaoqinTypes=" + xueshengKaoqinTypes +
            ", xueshengKaoqinContent=" + xueshengKaoqinContent +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", jiezhiTime=" + DateUtil.convertString(jiezhiTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
