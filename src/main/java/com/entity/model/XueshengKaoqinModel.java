package com.entity.model;

import com.entity.XueshengKaoqinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 学生考勤
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class XueshengKaoqinModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 学生
     */
    private Integer xueshengId;


    /**
     * 考勤唯一编号
     */
    private String xueshengKaoqinUuidNumber;


    /**
     * 考勤标题
     */
    private String xueshengKaoqinName;


    /**
     * 学生考勤类型
     */
    private Integer xueshengKaoqinTypes;


    /**
     * 考勤详情
     */
    private String xueshengKaoqinContent;


    /**
     * 考勤发起时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 考勤截止时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date jiezhiTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
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

    }
