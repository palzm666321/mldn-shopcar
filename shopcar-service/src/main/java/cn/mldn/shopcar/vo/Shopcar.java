package cn.mldn.shopcar.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Shopcar implements Serializable { 
	private Long scid ;
	private String mid ;
	private Long gid ;
	private Integer amount ;
	public Long getScid() {
		return scid;
	}
	public void setScid(Long scid) {
		this.scid = scid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
