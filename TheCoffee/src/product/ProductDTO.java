package product;

import java.util.*;
public class ProductDTO {
	
	private int pro_no;//��ǰ ��ȣ
	private String name_ko;//��ǰ �ѱ۸�
	private String name_eng;//��ǰ ������
	private String subject;//��ǰ ���� ����
	private String content;//��ǰ �� ����
	private String image;//�̹��� ���
	private String hc_code;//���� �ڵ�(hot,cool)
	private String event_code;//�̺�Ʈ �ڵ�(NEW, BEST ��)
	
	public ProductDTO(){}//������

	public int getPro_no() {
		return pro_no;
	}

	public void setPro_no(int pro_no) {
		this.pro_no = pro_no;
	}

	public String getName_ko() {
		return name_ko;
	}

	public void setName_ko(String name_ko) {
		this.name_ko = name_ko;
	}

	public String getName_eng() {
		return name_eng;
	}

	public void setName_eng(String name_eng) {
		this.name_eng = name_eng;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getHc_code() {
		return hc_code;
	}

	public void setHc_code(String hc_code) {
		this.hc_code = hc_code;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}
	
	

}//class-end
