package cn.mldn.shopcar.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Province implements Serializable {
	private Long pid ;
	private String title ;
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
