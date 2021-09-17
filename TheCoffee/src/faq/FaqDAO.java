package faq;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class FaqDAO {
	Connection con = null;	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	//�̱��� ��ü ���
	private static FaqDAO dao = new FaqDAO(); //��ü����
	
	private FaqDAO(){}	//������
	
	public static FaqDAO getInstance(){
		return dao;
	}
	
	private Connection getConnection() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}
	
	//---------------------------
	//�۳��뺸��
	//---------------------------
	public FaqDTO getFaq(int num) throws Exception{
		FaqDTO dto = null;
		
		try {
			con = getConnection();
			
			//1. ��ȸ�� ����
			sql = "update faq set readcount = readcount + 1 where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
			//2. �۳��� ����
			sql = "select * from faq where num = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				//rs ������ dto�� �ִ´�.
				dto = new FaqDTO();
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setCategory(rs.getString("category"));
				
				//String im=rs.getString("content");
				//im=im.replace("\n", "<br>");
				//dto.setContent(im);
				dto.setContent(rs.getString("content"));
				
				dto.setRegdate(rs.getTimestamp("regdate"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setIp(rs.getString("ip"));
			}			
		} catch (Exception ex1) {
			System.out.println("getFaq() ���� :" + ex1);
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(con != null) {con.close();}
			} catch (Exception ex2) {}
		}//finally-end
		
		return dto;
		
	}//getFaq()-end
	
	//-------------------------------------------
	//����Ʈ
	//-------------------------------------------
	public List<FaqDTO> getList(String category, String searchText) throws Exception{
		List<FaqDTO> list = null;
		FaqDTO dto = null;
		
		try {
			con = getConnection();
			
			category = '%' + category + '%';
			searchText = '%' + searchText + '%';
			
			sql = "select @ROWNUM:=@ROWNUM+1 AS rno, f.* "
				+ " from ( SELECT @ROWNUM := 0) r, faq f "
				+ " where category like ? and (subject like ? or content like ?) order by num desc";			
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<FaqDTO>();										
			while(rs.next()) {					
				dto = new FaqDTO();
				
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setCategory(rs.getString("category"));
				dto.setContent(rs.getString("content"));
				
				dto.setRegdate(rs.getTimestamp("regdate"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setIp(rs.getString("ip"));
				
				list.add(dto);
			}//while-end
			
		} catch (Exception ex1) {
			System.out.println("getList() ���� :" + ex1);					
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(con != null) {con.close();}
				
			} catch (Exception ex2) {}
		}//finally-end
		
		return list;
	}//getList() - end
	
	//--------------------------------------------
	//�۰���
	//--------------------------------------------
	public int getFaqCount(String category, String searchText) throws Exception {
		int cnt = -1;
		
		try {			
			con = getConnection();
			sql = "select count(*) from faq"
					+ " where category like ? and (subject like ? or content like ?)";
			
			searchText = '%' + searchText + '%';
			category = '%' + category + '%';
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				cnt = rs.getInt(1);
			}			
		} catch (Exception ex1) {
			System.out.println("getFaqCount() ���� :" + ex1);
		} finally {
			try {
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
				
			} catch (Exception ex2) {}
		}//finally-end
		
		return cnt;
		
	}//getFaqCount()-end
	
	public void insertFaq(FaqDTO dto) throws Exception{
		try {
			con = getConnection();
			
			sql = "insert into faq(writer, subject, category, content," +
					"regdate, ip) " +
					"values(?,?,?,?,NOW(),?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,dto.getWriter());
			pstmt.setString(2,dto.getSubject());
			pstmt.setString(3,dto.getCategory());
			pstmt.setString(4,dto.getContent());
			pstmt.setString(5,dto.getIp());
			
			pstmt.executeUpdate();
			
		} catch (Exception ex1) {
			System.out.println("insertFaq() ���� : " + ex1);
		} finally {
			try {
				if(pstmt != null) {pstmt.close();}
				if(con != null) {con.close();}
			} catch (Exception ex2) {}
			
		}//finally-end
	}//insertFaq()-end
	
	public void updateFaq(FaqDTO dto) throws Exception {
		try {
			con = getConnection();	
			
			sql = "update faq set writer=?, subject=?, category=?, content=? where num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1,dto.getWriter());
			pstmt.setString(2,dto.getSubject());
			pstmt.setString(3,dto.getCategory());
			pstmt.setString(4,dto.getContent());			
			pstmt.setInt(5, dto.getNum());
			
			pstmt.executeUpdate();
			
		} catch (Exception ex1) {
			System.out.println("updateFaq() ���� : " + ex1);
		} finally {
			try {
				if(pstmt != null) {pstmt.close();}
				if(con != null) {con.close();}
			} catch (Exception ex2) {}
			
		}//finally-end
	}//updateFaq()-end
	
	public int deleteFaq(int num) throws Exception {
		int x = -1;
		
		try {
			con = getConnection();
			
			sql = "select num from faq where num = ?";
					
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				sql = "delete from faq where num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, num);
				
				pstmt.executeUpdate();
				x = 1; //����
			}			
		} catch (Exception ex1) {
			System.out.println("deleteFaq() ���� :" + ex1);
		} finally {
			try {
				if(rs != null) {rs.close();}
				if(pstmt != null) {pstmt.close();}
				if(con != null) {con.close();}
			} catch (Exception ex2) {}			
		}//finally-end
		
		return x; //����
	}//deleteFaq()-end
	
	
}
