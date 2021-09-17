package notice;

import java.sql.Timestamp;

public class NoticeDTO {

	private int notice_no;
	private String notice_title;
	private String notice_content;
	private Timestamp notice_date;
	private int notice_count;
	private String notice_pw;
	
	public NoticeDTO(){}

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

	public Timestamp getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(Timestamp notice_date) {
		this.notice_date = notice_date;
	}

	public int getNotice_count() {
		return notice_count;
	}

	public void setNotice_count(int notice_count) {
		this.notice_count = notice_count;
	}

	public String getNotice_pw() {
		return notice_pw;
	}

	public void setNotice_pw(String notice_pw) {
		this.notice_pw = notice_pw;
	}

	

	
}
