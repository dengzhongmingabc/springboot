package com.peachamy.springboot.entry;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
//配置文件类，只有一条数据，ID 为1
@Entity
@Table(name = "t_sys_config")
public class SysConfig implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3617126330666256082L;
	public static final long ID = 1;//只有一条数据，ID 为1

	private Long id;
	private Boolean isProduct=true;//是否是生产环境
	private Integer consigorCheckedPoint=300;//审校通过驿道币
	private Integer truckCheckedPoint=300;//审校通过驿道币
	
	private Double tax;//团购普通税率
	private Double mailCharge;//邮费
	private Double addValTax;//团购普通税率
	
	private String chinaLifeMobile;//中国人寿审核员电话
	
	private Double farePercent;//运费首次支付比例
	
	private Double getMoneyTax = 30.0;//提现费率 万分之几，现暂定万分之30
	
	private Double consignorPayPercent;//货主支付信誉支付比例;
	
	private Boolean isRecommend=true;//是否推荐
	
	private Boolean isLoginActive=true;//是否登录赠送
	
	@Column(name="is_login_active")
	public Boolean getIsLoginActive() {
		return isLoginActive;
	}
	public void setIsLoginActive(Boolean isLoginActive) {
		this.isLoginActive = isLoginActive;
	}
	
	@Column(name="is_recommend")
	public Boolean getIsRecommend() {
		return isRecommend;
	}
	public void setIsRecommend(Boolean isRecommend) {
		this.isRecommend = isRecommend;
	}
	@Column(name="consignor_pay_percent")
	public Double getConsignorPayPercent() {
		return consignorPayPercent;
	}
	public void setConsignorPayPercent(Double consignorPayPercent) {
		this.consignorPayPercent = consignorPayPercent;
	}
	@Column(name="getMoney_tax")
	public Double getGetMoneyTax() {
		return getMoneyTax;
	}
	public void setGetMoneyTax(Double getMoneyTax) {
		this.getMoneyTax = getMoneyTax;
	}
	@Column(name="fare_percent")
	public Double getFarePercent() {
		return farePercent;
	}
	public void setFarePercent(Double farePercent) {
		this.farePercent = farePercent;
	}
	@Id
	@GeneratedValue
	@Column(name="id")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="china_life_mobile")
	public String getChinaLifeMobile() {
		return chinaLifeMobile;
	}
	public void setChinaLifeMobile(String chinaLifeMobile) {
		this.chinaLifeMobile = chinaLifeMobile;  
	}
	
	@Column(name="add_val_tax")
	public Double getAddValTax() {
		return addValTax;
	}
	public void setAddValTax(Double addValTax) {
		this.addValTax = addValTax;
	}
	
	@Column(name="is_product")
	public Boolean getIsProduct() {
		return isProduct;
	}
	public void setIsProduct(Boolean isProduct) {
		this.isProduct = isProduct;
	}
	
	@Column()
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	@Column()
	public Double getMailCharge() {
		return mailCharge;
	}
	public void setMailCharge(Double mailCharge) {
		this.mailCharge = mailCharge;
	}
	@Column(name="consignor_check_point")
	public Integer getConsigorCheckedPoint() {
		return consigorCheckedPoint;
	}
	public void setConsigorCheckedPoint(Integer consigorCheckedPoint) {
		this.consigorCheckedPoint = consigorCheckedPoint;
	}
	@Column(name="truck_check_point")
	public Integer getTruckCheckedPoint() {
		return truckCheckedPoint;
	}
	public void setTruckCheckedPoint(Integer truckCheckedPoint) {
		this.truckCheckedPoint = truckCheckedPoint;
	}
}