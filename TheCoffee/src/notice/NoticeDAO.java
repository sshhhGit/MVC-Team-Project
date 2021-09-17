package notice;

import java.sql.*;

import javax.sql.*;
import javax.naming.*;

import mysqlboard.BoardDTO;

import java.util.*;
import java.sql.Timestamp;

//DAO : DBó��
public class NoticeDAO {
	/*
	Statement Ŭ����
	- SQL ������ �����ϴ� ����
	- �����δ� SQL ���� ���� ����(�����ؼ� X) -> ���޿���
	- SQL ���� O + ���� ���� X

	PreparedStatement Ŭ����
	- Statement Ŭ������ ��� ���
	- ���ڿ� ���õ� �۾��� Ưȭ(�Ű�����)
	- �ڵ� ������ ����. ������ ����.
	- �ڵ差�� ���� -> �Ű������� set������ϱ� ������..
	- �ؽ�Ʈ SQL ȣ��
	*/
	
	/*
	ResultSet 
	- Statement �Ǵ� PreparedStatement ��ü�� 
	Select���� ����� ���� ���ڵ� ������ ���̺��� ���·� ���� �Ǵ� ��ü
	��, Select���� ���� ���� ������ ������ ResultSet��ü�� �����ؾ� �Ѵ�.
	�����͸� ���ö� �ʼ������� ����ؾ� �ϴ� ��ü
	*/
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = "";
	
	//�̱��� ��ü ���(�Ÿ� ���� ȿ��)
	/*
	getInstance()�� �̸�����ó�� �ν��Ͻ��� ����� ��
	new�����ڸ� �̿��ؼ� Ŭ������ ���ο� �޸𸮿� �Ҵ��ϴ� �Ͱ� �޸�
	getInstance()�� ���ʿ� �Ҵ�� �ϳ��� �޸𸮸� ��� ���� ����̴�.
	*/
	
	private static NoticeDAO dao = new NoticeDAO();//��ü����
	
	private NoticeDAO(){}//������
	
	public static NoticeDAO getInstance(){
		return dao;
	}
	
	//Ŀ�ؼ� �����ϱ�
	private Connection getCon() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		
		return ds.getConnection();
	}
	
	//--------------------------------------------------------------------
	//�۾���
	//--------------------------------------------------------------------
	public void insertNotice(NoticeDTO dto) throws Exception{
		int no = dto.getNotice_no();
		
		int number = 0;//�� �׷� ó���� ���� ����
		
		try{
			//ó��
			con = getCon(); //Ŀ�ؼ� ���
			
			//�ִ� �۹�ȣ ���
			pstmt = con.prepareStatement("select max(notice_no) from notice");
			/*
			executeQuery
			rs = pstmt.excuteQuery���� executeQuery�� PreparedStatement�� ���� 
			SQL�� ������ ���� ResultSet�� �����ϴ� ����,
			�׷��Ƿ� ResultSet��ü�� ������ ���� rs�� ������ �ѹ� ���� ���̰�
			Ŀ�� ������ ���� ����ϴ� ResultSet�� Ư¡�� ����� ���ϴ� SQL���� rs������ ���� ����� �� �ִ�
			*/
			rs = pstmt.executeQuery();//��������
			
			if(rs.next()){//next() �Էµ� ���� ���� ���ϴ� ��ȯ���� ������ �´�.
				number = rs.getInt(1)+1;//�ִ�۹�ȣ +1 �� �׷쿡�� ���
			}else{//���� ������
				number = 1;//ó�� ��
			}
			
			//�۾���
			sql = "insert into notice(notice_title, notice_content, notice_date)values(?,?,NOW())";
			
			pstmt = con.prepareStatement(sql);//������ ���� ��
			
		 
			pstmt.setString(1, dto.getNotice_title());
			pstmt.setString(2, dto.getNotice_content());
		 	
			pstmt.executeUpdate();
			
		}catch(Exception ex1){
			System.out.println("insertNotice() ���� : "+ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
	}//insertNotice-end
	
	//--------------------------------------------------------------------
	//�۰���
	//--------------------------------------------------------------------
	public int getNoticeCount() throws Exception{
		int cnt = -1;//�ʱ�ȭ
		
		try{
			//ó��
			con = getCon();//Ŀ�ؼ� ���
			pstmt = con.prepareStatement("select count(*) from notice");//������ ���� ��
			
			rs = pstmt.executeQuery();//���� ����
			
			if(rs.next()){
				cnt = rs.getInt(1);//1�� �ʵ� ��ȣ, �� ����
			}
		}catch(Exception ex1){
			System.out.println("getNoticeCount() ���� : "+ ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//final-end
		return cnt;
	}//getNoticeCount()-end
	
	//--------------------------------------------------------------------
	//����Ʈ
	//--------------------------------------------------------------------

	public List getList(int startRow, int count) throws Exception{
		
		List<NoticeDTO> list = null; //����
		try{
			con=getCon();
			sql="select * from notice order by notice_date desc limit ?,?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, count);
			
			rs=pstmt.executeQuery();
			//ó��
			if(rs.next()){
				list = new ArrayList<NoticeDTO>();
				do{
					NoticeDTO dto = new NoticeDTO();//��ü ����
					
					dto.setNotice_no(rs.getInt("notice_no"));
					dto.setNotice_title(rs.getString("notice_title"));
					dto.setNotice_content(rs.getString("notice_content"));
					dto.setNotice_date(rs.getTimestamp("notice_date"));
					dto.setNotice_count(rs.getInt("notice_count"));
					
					list.add(dto);/////*****������ ����� ����
					//vec.add(dto); ������ ����� �ִ� (ä�ÿ��� ���)
					
				}while(rs.next());
			}//if-end
			
		}catch(Exception ex1){
			System.out.println("getList() ���� : "+ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//final-end
		return list;
	}//getList()-end
	
	//--------------------------------------------------------------------
	//�۳��뺸��
	//--------------------------------------------------------------------
	public NoticeDTO getNotice(int notice_no) throws Exception{
		NoticeDTO dto = null;
		try{
			//ó��
			con = getCon();
			
			//��ȸ�� ����
			sql = "update notice set notice_count = notice_count+1 where notice_no=-?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, notice_no);
			pstmt.executeUpdate();//����
			//---------------------//��ȸ�� ���� end
			
			//�� ���뺸��
			pstmt = con.prepareStatement("select * from notice where notice_no=?");
			pstmt.setInt(1, notice_no);
			rs = pstmt.executeQuery();//����
			if(rs.next()){
				//rs���� dto�� �ְ� Ŭ���̾�Ʈ ����
				dto = new NoticeDTO();//��ü����
				
				dto.setNotice_no(rs.getInt("notice_no"));
				dto.setNotice_title(rs.getString("notice_title"));
				dto.setNotice_content(rs.getString("notice_content"));
				dto.setNotice_date(rs.getTimestamp("notice_date"));
				dto.setNotice_count(rs.getInt("notice_count"));
				//dto.setNotice_pw(rs.getString("notice_pw"));
			}//if-end
			
		}catch(Exception ex1){
			System.out.println("getNotice() ���� : "+ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		return dto;
	}//getNotice()-end
	
	//--------------------------------------------------------------------
	// �ۼ��� Ŭ���̾�Ʈ�� ���� ������ 
	//--------------------------------------------------------------------
	public NoticeDTO getUpdate(int notice_no) throws Exception{
		NoticeDTO dto=null;
		
		try{
			con=getCon();
			//�� ���뺸��
			pstmt=con.prepareStatement("select * from notice where notice_no=?");
			pstmt.setInt(1, notice_no);
			rs=pstmt.executeQuery();//���� ����
		
			if(rs.next()){
				//rs������ dto�ְ� dto�� Ŭ���̾�Ʈ�� ������ 
				dto = new NoticeDTO();//��ü����
				
				dto.setNotice_no(rs.getInt("notice_no"));
				dto.setNotice_title(rs.getString("notice_title"));
				dto.setNotice_content(rs.getString("notice_content"));
				dto.setNotice_date(rs.getTimestamp("notice_date"));
				dto.setNotice_count(rs.getInt("notice_count"));
				//dto.setNotice_pw(rs.getString("notice_pw"));
			}//if-end
			
		}catch(Exception ex1){
			System.out.println("getUpdate() ���� :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
	
		return dto;
	}//getUpdate() end
	
	//--------------------------------------------------------------------
	//DB �ۼ���
	//--------------------------------------------------------------------
	public void updateNotice(NoticeDTO dto) throws Exception{
		String dbPw="";
		int x=-1;
		try{
			//ó��
			con=getCon();
			sql="update notice set notice_title=?,notice_content=? where notice_no=?";
			pstmt=con.prepareStatement(sql);//������ ���� ���� 

			pstmt.setString(1, dto.getNotice_title());
			pstmt.setString(2, dto.getNotice_content());
			pstmt.setInt(3, dto.getNotice_no());

			pstmt.executeUpdate();//���� ����


		}catch(Exception ex1){
			System.out.println("updateNotice() ���� :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

	}//updateBoard() end
	
	//--------------------
	// �ۻ���
	//--------------------
	public void deleteNotice(int notice_no) throws Exception{
			
		try{
			//ó��
			con=getCon();

			pstmt=con.prepareStatement("delete from notice where notice_no=?");

			pstmt.setInt(1, notice_no);
			pstmt.executeUpdate();//���� ����

		}catch(Exception ex1){
			System.out.println("deleteNotice() ���� :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

	}//deleteBoard() -end

}//class-end
