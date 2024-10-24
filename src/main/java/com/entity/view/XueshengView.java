package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.XueshengEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 学生
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("xuesheng")
public class XueshengView extends XueshengEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 性别的值
	*/
	@ColumnInfo(comment="性别的字典表值",type="varchar(200)")
	private String sexValue;
	/**
	* 账户状态的值
	*/
	@ColumnInfo(comment="账户状态的字典表值",type="varchar(200)")
	private String jinyongValue;

	//级联表 班级
					 
		/**
		* 班级 的 老师
		*/
		@ColumnInfo(comment="老师",type="int(200)")
		private Integer banjiLaoshiId;
		/**
		* 班级编号
		*/

		@ColumnInfo(comment="班级编号",type="varchar(200)")
		private String banjiUuidNumber;
		/**
		* 年级
		*/
		@ColumnInfo(comment="年级",type="int(11)")
		private Integer nianjiTypes;
			/**
			* 年级的值
			*/
			@ColumnInfo(comment="年级的字典表值",type="varchar(200)")
			private String nianjiValue;
		/**
		* 班级名称
		*/

		@ColumnInfo(comment="班级名称",type="varchar(200)")
		private String banjiName;
		/**
		* 班级位置
		*/

		@ColumnInfo(comment="班级位置",type="varchar(200)")
		private String banjiAddress;
		/**
		* 班级备注
		*/

		@ColumnInfo(comment="班级备注",type="longtext")
		private String banjiContent;



	public XueshengView() {

	}

	public XueshengView(XueshengEntity xueshengEntity) {
		try {
			BeanUtils.copyProperties(this, xueshengEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 性别的值
	*/
	public String getSexValue() {
		return sexValue;
	}
	/**
	* 设置： 性别的值
	*/
	public void setSexValue(String sexValue) {
		this.sexValue = sexValue;
	}
	//当前表的
	/**
	* 获取： 账户状态的值
	*/
	public String getJinyongValue() {
		return jinyongValue;
	}
	/**
	* 设置： 账户状态的值
	*/
	public void setJinyongValue(String jinyongValue) {
		this.jinyongValue = jinyongValue;
	}


	//级联表的get和set 班级
		/**
		* 获取：班级 的 老师
		*/
		public Integer getBanjiLaoshiId() {
			return banjiLaoshiId;
		}
		/**
		* 设置：班级 的 老师
		*/
		public void setBanjiLaoshiId(Integer banjiLaoshiId) {
			this.banjiLaoshiId = banjiLaoshiId;
		}

		/**
		* 获取： 班级编号
		*/
		public String getBanjiUuidNumber() {
			return banjiUuidNumber;
		}
		/**
		* 设置： 班级编号
		*/
		public void setBanjiUuidNumber(String banjiUuidNumber) {
			this.banjiUuidNumber = banjiUuidNumber;
		}
		/**
		* 获取： 年级
		*/
		public Integer getNianjiTypes() {
			return nianjiTypes;
		}
		/**
		* 设置： 年级
		*/
		public void setNianjiTypes(Integer nianjiTypes) {
			this.nianjiTypes = nianjiTypes;
		}


			/**
			* 获取： 年级的值
			*/
			public String getNianjiValue() {
				return nianjiValue;
			}
			/**
			* 设置： 年级的值
			*/
			public void setNianjiValue(String nianjiValue) {
				this.nianjiValue = nianjiValue;
			}

		/**
		* 获取： 班级名称
		*/
		public String getBanjiName() {
			return banjiName;
		}
		/**
		* 设置： 班级名称
		*/
		public void setBanjiName(String banjiName) {
			this.banjiName = banjiName;
		}

		/**
		* 获取： 班级位置
		*/
		public String getBanjiAddress() {
			return banjiAddress;
		}
		/**
		* 设置： 班级位置
		*/
		public void setBanjiAddress(String banjiAddress) {
			this.banjiAddress = banjiAddress;
		}

		/**
		* 获取： 班级备注
		*/
		public String getBanjiContent() {
			return banjiContent;
		}
		/**
		* 设置： 班级备注
		*/
		public void setBanjiContent(String banjiContent) {
			this.banjiContent = banjiContent;
		}


	@Override
	public String toString() {
		return "XueshengView{" +
			", sexValue=" + sexValue +
			", jinyongValue=" + jinyongValue +
			", banjiUuidNumber=" + banjiUuidNumber +
			", banjiName=" + banjiName +
			", banjiAddress=" + banjiAddress +
			", banjiContent=" + banjiContent +
			"} " + super.toString();
	}
}
