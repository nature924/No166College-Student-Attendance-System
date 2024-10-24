package com.entity.vo;

import com.entity.XueshengqingjiaEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 学生请假
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("xueshengqingjia")
public class XueshengqingjiaVO implements Serializable {
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
     * 请假标题
     */

    @TableField(value = "xueshengqingjia_name")
    private String xueshengqingjiaName;


    /**
     * 请假缘由
     */

    @TableField(value = "xueshengqingjia_text")
    private String xueshengqingjiaText;


    /**
     * 请假类型
     */

    @TableField(value = "xueshengqingjia_types")
    private Integer xueshengqingjiaTypes;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 请假时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "xueshengqingjia_time")
    private Date xueshengqingjiaTime;


    /**
     * 请假天数
     */

    @TableField(value = "xueshengqingjia_number")
    private Integer xueshengqingjiaNumber;


    /**
     * 申请状态
     */

    @TableField(value = "xueshengqingjia_yesno_types")
    private Integer xueshengqingjiaYesnoTypes;


    /**
     * 处理意见
     */

    @TableField(value = "xueshengqingjia_yesno_text")
    private String xueshengqingjiaYesnoText;


    /**
     * 审核时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "xueshengqingjia_shenhe_time")
    private Date xueshengqingjiaShenheTime;


    /**
     * 创建时间 show3 listShow
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
	 * 设置：请假标题
	 */
    public String getXueshengqingjiaName() {
        return xueshengqingjiaName;
    }


    /**
	 * 获取：请假标题
	 */

    public void setXueshengqingjiaName(String xueshengqingjiaName) {
        this.xueshengqingjiaName = xueshengqingjiaName;
    }
    /**
	 * 设置：请假缘由
	 */
    public String getXueshengqingjiaText() {
        return xueshengqingjiaText;
    }


    /**
	 * 获取：请假缘由
	 */

    public void setXueshengqingjiaText(String xueshengqingjiaText) {
        this.xueshengqingjiaText = xueshengqingjiaText;
    }
    /**
	 * 设置：请假类型
	 */
    public Integer getXueshengqingjiaTypes() {
        return xueshengqingjiaTypes;
    }


    /**
	 * 获取：请假类型
	 */

    public void setXueshengqingjiaTypes(Integer xueshengqingjiaTypes) {
        this.xueshengqingjiaTypes = xueshengqingjiaTypes;
    }
    /**
	 * 设置：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：请假时间
	 */
    public Date getXueshengqingjiaTime() {
        return xueshengqingjiaTime;
    }


    /**
	 * 获取：请假时间
	 */

    public void setXueshengqingjiaTime(Date xueshengqingjiaTime) {
        this.xueshengqingjiaTime = xueshengqingjiaTime;
    }
    /**
	 * 设置：请假天数
	 */
    public Integer getXueshengqingjiaNumber() {
        return xueshengqingjiaNumber;
    }


    /**
	 * 获取：请假天数
	 */

    public void setXueshengqingjiaNumber(Integer xueshengqingjiaNumber) {
        this.xueshengqingjiaNumber = xueshengqingjiaNumber;
    }
    /**
	 * 设置：申请状态
	 */
    public Integer getXueshengqingjiaYesnoTypes() {
        return xueshengqingjiaYesnoTypes;
    }


    /**
	 * 获取：申请状态
	 */

    public void setXueshengqingjiaYesnoTypes(Integer xueshengqingjiaYesnoTypes) {
        this.xueshengqingjiaYesnoTypes = xueshengqingjiaYesnoTypes;
    }
    /**
	 * 设置：处理意见
	 */
    public String getXueshengqingjiaYesnoText() {
        return xueshengqingjiaYesnoText;
    }


    /**
	 * 获取：处理意见
	 */

    public void setXueshengqingjiaYesnoText(String xueshengqingjiaYesnoText) {
        this.xueshengqingjiaYesnoText = xueshengqingjiaYesnoText;
    }
    /**
	 * 设置：审核时间
	 */
    public Date getXueshengqingjiaShenheTime() {
        return xueshengqingjiaShenheTime;
    }


    /**
	 * 获取：审核时间
	 */

    public void setXueshengqingjiaShenheTime(Date xueshengqingjiaShenheTime) {
        this.xueshengqingjiaShenheTime = xueshengqingjiaShenheTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
