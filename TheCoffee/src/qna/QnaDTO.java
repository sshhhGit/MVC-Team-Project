package qna;

import java.sql.Timestamp;

public class QnaDTO {
	
	private int num; // �۹�ȣ
	private String user_id; // userID
	private String user_content; // user�� ��������
	private Timestamp user_regdate; // user�� �����ۼ��ð�
	private String admin_id; // adminID
	private String admin_content; // admin�� �亯����
	private Timestamp admin_regdate; // admin�� �亯�ۼ��ð�
	
	// ����Ʈ ������
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
