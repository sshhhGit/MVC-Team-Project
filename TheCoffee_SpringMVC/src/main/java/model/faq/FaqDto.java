package model.faq;

import java.sql.Timestamp;

public class FaqDto {
	
	private int faq_no;
	private String faq_writer;
	private String faq_title;
	private String faq_category;
	private String faq_content;	
	private Timestamp faq_regdate;
	private int faq_readcount;
	private String faq_ip;
	
	public FaqDto() {}

	public int getFaq_no() {
		return faq_no;
	}

	public void setFaq_no(int faq_no) {
		this.faq_no = faq_no;
	}

	public String getFaq_writer() {
		return faq_writer;
	}

	public void setFaq_writer(String faq_writer) {
		this.faq_writer = faq_writer;
	}

	public String getFaq_title() {
		return faq_title;
	}

	public void setFaq_title(String faq_title) {
		this.faq_title = faq_title;
	}

	public String getFaq_category() {
		return faq_category;
	}

	public void setFaq_category(String faq_category) {
		this.faq_category = faq_category;
	}

	public String getFaq_content() {
		return faq_content;
	}

	public void setFaq_content(String faq_content) {
		this.faq_content = faq_content;
	}

	public Timestamp getFaq_regdate() {
		return faq_regdate;
	}

	public void setFaq_regdate(Timestamp faq_regdate) {
		this.faq_regdate = faq_regdate;
	}

	public int getFaq_readcount() {
		return faq_readcount;
	}

	public void setFaq_readcount(int faq_readcount) {
		this.faq_readcount = faq_readcount;
	}

	public String getFaq_ip() {
		return faq_ip;
	}

	public void setFaq_ip(String faq_ip) {
		this.faq_ip = faq_ip;
	}

	
	
}
