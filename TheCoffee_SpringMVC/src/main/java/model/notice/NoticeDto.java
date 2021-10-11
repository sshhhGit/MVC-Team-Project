package model.notice;

import java.sql.Timestamp;

public class NoticeDto {

	private int notice_no;
	private String notice_title;
	private String notice_content;
	private Timestamp notice_regdate;
	private int notice_readcount;
	
	public NoticeDto() {}

	public int getNotice_no() {
		return notice_no;
	}

	public void setNotice_no(int notice_no) {
		this.notice_no = notice_no;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public Timestamp getNotice_regdate() {
		return notice_regdate;
	}

	public void setNotice_regdate(Timestamp notice_regdate) {
		this.notice_regdate = notice_regdate;
	}

	public int getNotice_readcount() {
		return notice_readcount;
	}

	public void setNotice_readcount(int notice_readcount) {
		this.notice_readcount = notice_readcount;
	}

	 
}//class-end
