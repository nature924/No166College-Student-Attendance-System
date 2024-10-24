package com.entity.model;

import com.entity.XueshengqingjiaEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 学生请假
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class XueshengqingjiaModel implements Serializable {
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
     * 请假标题
     */
    private String xueshengqingjiaName;


    /**
     * 请假缘由
     */
    private String xueshengqingjiaText;


    /**
     * 请假类型
     */
    private Integer xueshengqingjiaTypes;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 请假时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date xueshengqingjiaTime;


    /**
     * 请假天数
     */
    private Integer xueshengqingjiaNumber;


    /**
     * 申请状态
     */
    private Integer xueshengqingjiaYesnoTypes;


    /**
     * 处理意见
     */
    private String xueshengqingjiaYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date xueshengqingjiaShenheTime;


    /**
     * 创建时间 show3 listShow
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
	 * 获取：请假标题
	 */
    public String getXueshengqingjiaName() {
        return xueshengqingjiaName;
    }


    /**
	 * 设置：请假标题
	 */
    public void setXueshengqingjiaName(String xueshengqingjiaName) {
        this.xueshengqingjiaName = xueshengqingjiaName;
    }
    /**
	 * 获取：请假缘由
	 */
    public String getXueshengqingjiaText() {
        return xueshengqingjiaText;
    }


    /**
	 * 设置：请假缘由
	 */
    public void setXueshengqingjiaText(String xueshengqingjiaText) {
        this.xueshengqingjiaText = xueshengqingjiaText;
    }
    /**
	 * 获取：请假类型
	 */
    public Integer getXueshengqingjiaTypes() {
        return xueshengqingjiaTypes;
    }


    /**
	 * 设置：请假类型
	 */
    public void setXueshengqingjiaTypes(Integer xueshengqingjiaTypes) {
        this.xueshengqingjiaTypes = xueshengqingjiaTypes;
    }
    /**
	 * 获取：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：申请时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：请假时间
	 */
    public Date getXueshengqingjiaTime() {
        return xueshengqingjiaTime;
    }


    /**
	 * 设置：请假时间
	 */
    public void setXueshengqingjiaTime(Date xueshengqingjiaTime) {
        this.xueshengqingjiaTime = xueshengqingjiaTime;
    }
    /**
	 * 获取：请假天数
	 */
    public Integer getXueshengqingjiaNumber() {
        return xueshengqingjiaNumber;
    }


    /**
	 * 设置：请假天数
	 */
    public void setXueshengqingjiaNumber(Integer xueshengqingjiaNumber) {
        this.xueshengqingjiaNumber = xueshengqingjiaNumber;
    }
    /**
	 * 获取：申请状态
	 */
    public Integer getXueshengqingjiaYesnoTypes() {
        return xueshengqingjiaYesnoTypes;
    }


    /**
	 * 设置：申请状态
	 */
    public void setXueshengqingjiaYesnoTypes(Integer xueshengqingjiaYesnoTypes) {
        this.xueshengqingjiaYesnoTypes = xueshengqingjiaYesnoTypes;
    }
    /**
	 * 获取：处理意见
	 */
    public String getXueshengqingjiaYesnoText() {
        return xueshengqingjiaYesnoText;
    }


    /**
	 * 设置：处理意见
	 */
    public void setXueshengqingjiaYesnoText(String xueshengqingjiaYesnoText) {
        this.xueshengqingjiaYesnoText = xueshengqingjiaYesnoText;
    }
    /**
	 * 获取：审核时间
	 */
    public Date getXueshengqingjiaShenheTime() {
        return xueshengqingjiaShenheTime;
    }


    /**
	 * 设置：审核时间
	 */
    public void setXueshengqingjiaShenheTime(Date xueshengqingjiaShenheTime) {
        this.xueshengqingjiaShenheTime = xueshengqingjiaShenheTime;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
