package com.dev.user.entity;

import java.util.Date;

import com.dev.base.enums.Gender;
import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 用户详细信息</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年8月5日下午8:42:49</p>
 */
public class UserDetail extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 用户id */
	private Long userId;		
	
	/** 地址 */
	private String address;		
	
	/** 区域id */
	private Long areaId;		
	
	/** 生日 */
	private Date birth;		
	
	/** 国家 */
	private String country;		
	
	/** 性别 */
	private Gender gender;		
	
	/** 姓名 */
	private String name;		
	
	/** 昵称 */
	private String nickName;		
	
	/** 邮编 */
	private String zipCode;		
	
	/** 头像url */
	private String headUrl;		
	
	/** 毕业院校 */
	private String university;		
	

	public void setUserId(Long userId){
		this.userId = userId;
	}
	
	public Long getUserId(){
		return userId;
	}
	
	public void setAddress(String address){
		this.address = address;
	}
	
	public String getAddress(){
		return address;
	}
	
	public void setAreaId(Long areaId){
		this.areaId = areaId;
	}
	
	public Long getAreaId(){
		return areaId;
	}
	
	public void setBirth(Date birth){
		this.birth = birth;
	}
	
	public Date getBirth(){
		return birth;
	}
	
	public void setCountry(String country){
		this.country = country;
	}
	
	public String getCountry(){
		return country;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public void setNickName(String nickName){
		this.nickName = nickName;
	}
	
	public String getNickName(){
		return nickName;
	}
	
	public void setZipCode(String zipCode){
		this.zipCode = zipCode;
	}
	
	public String getZipCode(){
		return zipCode;
	}
	
	public void setHeadUrl(String headUrl){
		this.headUrl = headUrl;
	}
	
	public String getHeadUrl(){
		return headUrl;
	}
	
	public void setUniversity(String university){
		this.university = university;
	}
	
	public String getUniversity(){
		return university;
	}
	
}
