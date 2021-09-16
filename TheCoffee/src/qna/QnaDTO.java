package qna;

import java.sql.Timestamp;

public class QnaDTO {
	
	private int num; // 글번호
	private String user_id; // userID
	private String user_content; // user의 질문내용
	private Timestamp user_regdate; // user의 질문작성시간
	private String admin_id; // adminID
	private String admin_content; // admin의 답변내용
	private Timestamp admin_regdate; // admin의 답변작성시간
	
	// 디폴트 생성자
	public QnaDTO() {
		
	}

	// getter, setter
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_content() {
		return user_content;
	}

	public void setUser_content(String user_content) {
		this.user_content = user_content;
	}

	public Timestamp getUser_regdate() {
		return user_regdate;
	}

	public void setUser_regdate(Timestamp user_regdate) {
		this.user_regdate = user_regdate;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_content() {
		return admin_content;
	}

	public void setAdmin_content(String admin_content) {
		this.admin_content = admin_content;
	}

	public Timestamp getAdmin_regdate() {
		return admin_regdate;
	}

	public void setAdmin_regdate(Timestamp admin_regdate) {
		this.admin_regdate = admin_regdate;
	}


	
	
	
	
	
	
	
} // class-end
