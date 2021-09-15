package member;

import java.sql.*;

import javax.sql.*; 	//DataSource
import javax.naming.*;	//lookup

import java.util.*;

//DAO : �����Ͻ� ����(DBó�� ���) = MyBatis
public class MemberDAO {

	//�̱��� ��ü ���(��ü�� �ϳ��� ���) : �޸� ���� ȿ��
	private static MemberDAO instance = new MemberDAO(); //��ü���
	
	private MemberDAO(){}; //������, �ܺο��� ��ü ���� ����(private)
	
	public static MemberDAO getInstance(){//JSP���� ����Ҷ�
		return instance;
	}
	
	
	//-------------------------
	//Ŀ�ؼ� Ǯ ���
	//-------------------------
	private Connection getConnection() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}
	
	//-------------------------
	//ID�ߺ� üũ
	//-------------------------
	public int confirmID(String id) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int check = -1;
		
		try {
			con = getConnection(); //Ŀ�ؼ� ����
			pstmt = con.prepareStatement("select id from member where id=?");
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				//id�� ���� �� ��(�����)
				check = 1;
			}else{
				//�������� ���� ��(��밡��)
				check = -1;
			}
		} catch (Exception ex1) {
			System.out.println("confirmID() ���� : " + ex1);
		} finally{
			try {
				if (rs != null) {rs.close();}
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception e2) {}
		}//finally-end
		return check;
	}//confirmID-end
	//-------------------------
	//ȸ������
	//-------------------------
	public void insertMember(MemberDTO dto) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("insert into member values(?,?,?,?,?,?,?,?,NOW())");
			
			//'?'�� ä���
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getEmail());
			pstmt.setString(5, dto.getTel());
			pstmt.setString(6, dto.getZipcode());
			pstmt.setString(7, dto.getAddr());
			pstmt.setString(8, dto.getAddr2());
			
			pstmt.executeUpdate(); //insert, update,
			
		} catch (Exception ex1) {
			System.out.println("insertMember() ���� : " + ex1);
		} finally{
			try {
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception e2) {}
		}//finally-end
	}//insertMember-end
	//-------------------------
	//�α���(����)
	//-------------------------
	public int loginCheck(String id, String pw) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String dbPw = "";
		int x = -100;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("select * from member where id=?");
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery(); //��������
			
			if (rs.next()) {
				//id�� �����ϸ�
				dbPw = rs.getString("pw");
				if(pw.equals(dbPw)){
					x = 1; //��ȣ��ġ
				}else{
					x = 0; //��ȣ����ġ
				}
			}else{
				x = -1; //���̵� �������� �ʰų� ��ȣ ����ġ
			}
		} catch (Exception ex1) {
			System.out.println("loginCheck() ���� : " + ex1);
		} finally{
			try {
				if (rs != null) {rs.close();}
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception e2) {}
		}//finally-end
		return x;
	}//loginCheck-end
	//-------------------------
	//���������� ������ ���� ������
	//-------------------------
	public MemberDTO getMember(String id) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDTO dto = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("select * from member where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				dto = new MemberDTO();
				
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddr(rs.getString("Addr"));
				dto.setAddr2(rs.getString("Addr2"));
			}//while-end
		} catch (Exception ex1) {
			System.out.println("getMember() ���� : " + ex1);
		} finally{
			try {
				if (rs != null) {rs.close();}
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception e2) {}
		}//finally-end
		return dto;
	}//getMember()-end
	
	//-------------------------
	//DB���� ����
	//-------------------------
	public void updateMember(MemberDTO dto) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			String sql = "update member set pw=?,name=?,email=?,tel=?,zipcode=?,addr=?,addr2=? where id=?";
			
			pstmt = con.prepareStatement(sql);
			//'?'�� ä���
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getTel());
			pstmt.setString(5, dto.getZipcode());
			pstmt.setString(6, dto.getAddr());
			pstmt.setString(7, dto.getAddr2());
			pstmt.setString(8, dto.getId());
			
			pstmt.executeUpdate(); //���� ���� insert, update, delete �� �� ����
			
		} catch (Exception ex1) {
			System.out.println("updateMember() ���� : " + ex1);
		} finally{
			try {
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception e2) {}
		}//finally-end
	}//updateMember()-end
	//-------------------------
	//ȸ��Ż��
	//-------------------------
	public int deleteMember(String id, String pw) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		
		String dbPw = "";
		int x = -100;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("select pw from member where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				dbPw = rs.getString("pw");
				if(pw.equals(dbPw)){  //�Է��� ��ȣ�� db��ȣ�� ������ ����
					pstmt2 = con.prepareStatement("delete from member where id=?");
					pstmt2.setString(1, id);
					pstmt2.executeUpdate();
					x = 1;  //��ȣ ��ġ
				}else{
					x = -1; //��ȣ ����ġ
				}
			}else{
				x = 0; 		//ID X
			}
			
		} catch (Exception ex1) {
			System.out.println("deleteMember() ���� : " + ex1);
		} finally{
			try {
				if (rs != null) {rs.close();}
				if (pstmt2 != null) {pstmt2.close();}
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception e2) {}
		}//finally-end
		return x;
	}//deleteMember()-end
	//--------------------
	// �̸��� �����ϱ�
	//---------------------------
	public String emailDupleCheck(String email) {
	String result = "unuseable";

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {
		con = getConnection();
		pstmt = con.prepareStatement("select email from member where email=?");
		pstmt.setString(1, email);
		rs = pstmt.executeQuery();

		if (!rs.next()) {
			result = "useable";
			System.out.println("this email possible to use.");
		} else {
			result = "unuseable";
			System.out.println("this email is already in use.");
		}
	} catch (Exception ex1) {
		System.out.println("emailDupleCheck() ���� :  " + ex1);
	} finally {
		try {
			if (rs != null){con.close();}
			if (pstmt != null){pstmt.close();}
			if (con != null){con.close();}
		} catch (Exception ex2) {}
	}//finally()-end
	return result;
  }//emailDupleCheck()-end
	//------------------------
	//��� ȸ������ ��������
	//-------------------------
    public ArrayList<MemberDTO> getMemberList(){
    	
        ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberDTO dto = null;
        
        try {
            
        	con = getConnection();
            pstmt = con.prepareStatement("select * from member");
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                dto = new MemberDTO();
                
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				
				dto.setEmail(rs.getString("email"));
				dto.setTel(rs.getString("tel"));
				
				dto.setZipcode(rs.getString("zipcode"));
				dto.setAddr(rs.getString("Addr"));
				dto.setAddr2(rs.getString("Addr2"));
                
                list.add(dto);
            }
        } catch (Exception ex1) {
            System.out.println("getMemberList() ���� : " + ex1);
        } finally {
            try{
            	if ( rs != null ){ rs.close();}
                if ( pstmt != null ){ pstmt.close();}
                if ( con != null ){ con.close();}
            }catch(Exception ex2){}
        }//finally()-end
        return list;
    }//getMemberList()-end
}//class-end
