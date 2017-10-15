package cn.mldn.shopcar.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class City implements Serializable {
	private Long cid ;
	private Long pid ;
	private String title ;
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
