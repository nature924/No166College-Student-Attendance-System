package com.entity.vo;

import com.entity.XueshengKaoqinEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 学生考勤
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("xuesheng_kaoqin")
public class XueshengKaoqinVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 学生
     */

    @TableField(value = "xuesheng_id")
    private Integer xueshengId;


    /**
     * 考勤唯一编号
     */

    @TableField(value = "xuesheng_kaoqin_uuid_number")
    private String xueshengKaoqinUuidNumber;


    /**
     * 考勤标题
     */

    @TableField(value = "xuesheng_kaoqin_name")
    private String xueshengKaoqinName;


    /**
     * 学生考勤类型
     */

    @TableField(value = "xuesheng_kaoqin_types")
    private Integer xueshengKaoqinTypes;


    /**
     * 考勤详情
     */

    @TableField(value = "xuesheng_kaoqin_content")
    private String xueshengKaoqinContent;


    /**
     * 考勤发起时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 考勤截止时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "jiezhi_time")
    private Date jiezhiTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：学生
	 */
    public Integer getXueshengId() {
        return xueshengId;
    }


    /**
	 * 获取：学生
	 */

    public void setXueshengId(Integer xueshengId) {
        this.xueshengId = xueshengId;
    }
    /**
	 * 设置：考勤唯一编号
	 */
    public String getXueshengKaoqinUuidNumber() {
        return xueshengKaoqinUuidNumber;
    }


    /**
	 * 获取：考勤唯一编号
	 */

    public void setXueshengKaoqinUuidNumber(String xueshengKaoqinUuidNumber) {
        this.xueshengKaoqinUuidNumber = xueshengKaoqinUuidNumber;
    }
    /**
	 * 设置：考勤标题
	 */
    public String getXueshengKaoqinName() {
        return xueshengKaoqinName;
    }


    /**
	 * 获取：考勤标题
	 */

    public void setXueshengKaoqinName(String xueshengKaoqinName) {
        this.xueshengKaoqinName = xueshengKaoqinName;
    }
    /**
	 * 设置：学生考勤类型
	 */
    public Integer getXueshengKaoqinTypes() {
        return xueshengKaoqinTypes;
    }


    /**
	 * 获取：学生考勤类型
	 */

    public void setXueshengKaoqinTypes(Integer xueshengKaoqinTypes) {
        this.xueshengKaoqinTypes = xueshengKaoqinTypes;
    }
    /**
	 * 设置：考勤详情
	 */
    public String getXueshengKaoqinContent() {
        return xueshengKaoqinContent;
    }


    /**
	 * 获取：考勤详情
	 */

    public void setXueshengKaoqinContent(String xueshengKaoqinContent) {
        this.xueshengKaoqinContent = xueshengKaoqinContent;
    }
    /**
	 * 设置：考勤发起时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：考勤发起时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：考勤截止时间
	 */
    public Date getJiezhiTime() {
        return jiezhiTime;
    }


    /**
	 * 获取：考勤截止时间
	 */

    public void setJiezhiTime(Date jiezhiTime) {
        this.jiezhiTime = jiezhiTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
