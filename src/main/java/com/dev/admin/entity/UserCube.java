package com.dev.admin.entity;

import com.dev.base.mybatis.BaseMybatisEntity;

/**
 * 
		* <p>Title: 用户数据统计</p>
		* <p>Description: 描述（简要描述类的职责、实现方式、使用注意事项等）</p>
		* <p>CreateDate: 2015年10月10日下午5:21:34</p>
 */
public class UserCube extends BaseMybatisEntity{
	private static final long serialVersionUID = 1L;
	
	/** 总注册用户数 */
	private int totalRegistCount;		
	
	/** 日注册用户数 */
	private int dayRegistCount;		
	
	/** 日登录用户数 */
	private int dayLoginCount;		
	
	/** 日登陆老用户数 */
	private int dayOldLoginCount;		
	
	/** 总项目数 */
	private int totalProjCount;		
	
	/** 日新增项目数 */
	private int dayProjCount;		
	
	/** 即时在线用户数，数据库不保存*/
	private int onlineCount;
	
	
	public void setTotalRegistCount(int totalRegistCount){
		this.totalRegistCount = totalRegistCount;
	}
	
	public int getTotalRegistCount(){
		return totalRegistCount;
	}
	
	public void setDayRegistCount(int dayRegistCount){
		this.dayRegistCount = dayRegistCount;
	}
	
	public int getDayRegistCount(){
		return dayRegistCount;
	}
	
	public void setDayLoginCount(int dayLoginCount){
		this.dayLoginCount = dayLoginCount;
	}
	
	public int getDayLoginCount(){
		return dayLoginCount;
	}
	
	public void setDayOldLoginCount(int dayOldLoginCount){
		this.dayOldLoginCount = dayOldLoginCount;
	}
	
	public int getDayOldLoginCount(){
		return dayOldLoginCount;
	}
	
	public void setTotalProjCount(int totalProjCount){
		this.totalProjCount = totalProjCount;
	}
	
	public int getTotalProjCount(){
		return totalProjCount;
	}
	
	public void setDayProjCount(int dayProjCount){
		this.dayProjCount = dayProjCount;
	}
	
	public int getDayProjCount(){
		return dayProjCount;
	}

	public int getOnlineCount() {
		return onlineCount;
	}

	public void setOnlineCount(int onlineCount) {
		this.onlineCount = onlineCount;
	}
}
