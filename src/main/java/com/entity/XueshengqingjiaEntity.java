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
 * 学生请假
 *
 * @author 
 * @email
 */
@TableName("xueshengqingjia")
public class XueshengqingjiaEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XueshengqingjiaEntity() {

	}

	public XueshengqingjiaEntity(T t) {
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
    @ColumnInfo(comment="学生",type="int(200)")
    @TableField(value = "xuesheng_id")

    private Integer xueshengId;


    /**
     * 请假标题
     */
    @ColumnInfo(comment="请假标题",type="varchar(200)")
    @TableField(value = "xueshengqingjia_name")

    private String xueshengqingjiaName;


    /**
     * 请假缘由
     */
    @ColumnInfo(comment="请假缘由",type="longtext")
    @TableField(value = "xueshengqingjia_text")

    private String xueshengqingjiaText;


    /**
     * 请假类型
     */
    @ColumnInfo(comment="请假类型",type="int(11)")
    @TableField(value = "xueshengqingjia_types")

    private Integer xueshengqingjiaTypes;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="申请时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 请假时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="请假时间",type="timestamp")
    @TableField(value = "xueshengqingjia_time")

    private Date xueshengqingjiaTime;


    /**
     * 请假天数
     */
    @ColumnInfo(comment="请假天数",type="int(200)")
    @TableField(value = "xueshengqingjia_number")

    private Integer xueshengqingjiaNumber;


    /**
     * 申请状态
     */
    @ColumnInfo(comment="申请状态",type="int(11)")
    @TableField(value = "xueshengqingjia_yesno_types")

    private Integer xueshengqingjiaYesnoTypes;


    /**
     * 处理意见
     */
    @ColumnInfo(comment="处理意见",type="longtext")
    @TableField(value = "xueshengqingjia_yesno_text")

    private String xueshengqingjiaYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="审核时间",type="timestamp")
    @TableField(value = "xueshengqingjia_shenhe_time")

    private Date xueshengqingjiaShenheTime;


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
        return "Xueshengqingjia{" +
            ", id=" + id +
            ", xueshengId=" + xueshengId +
            ", xueshengqingjiaName=" + xueshengqingjiaName +
            ", xueshengqingjiaText=" + xueshengqingjiaText +
            ", xueshengqingjiaTypes=" + xueshengqingjiaTypes +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", xueshengqingjiaTime=" + DateUtil.convertString(xueshengqingjiaTime,"yyyy-MM-dd") +
            ", xueshengqingjiaNumber=" + xueshengqingjiaNumber +
            ", xueshengqingjiaYesnoTypes=" + xueshengqingjiaYesnoTypes +
            ", xueshengqingjiaYesnoText=" + xueshengqingjiaYesnoText +
            ", xueshengqingjiaShenheTime=" + DateUtil.convertString(xueshengqingjiaShenheTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
