package member;

import java.sql.*;

import javax.sql.*; 	//DataSource
import javax.naming.*;	//lookup

import java.util.*;

//DAO : 비지니스 로직(DB처리 기능) = MyBatis
public class MemberDAO {

	//싱글톤 객체 사용(객체를 하나만 사용) : 메모리 절약 효과
	private static MemberDAO instance = new MemberDAO(); //객체사용
	
	private MemberDAO(){}; //생성자, 외부에서 객체 생성 방지(private)
	
	public static MemberDAO getInstance(){//JSP에서 사용할때
		return instance;
	}
	
	
	//-------------------------
	//커넥션 풀 사용
	//-------------------------
	private Connection getConnection() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		return ds.getConnection();
	}
	
	//-------------------------
	//ID중복 체크
	//-------------------------
	public int confirmID(String id) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int check = -1;
		
		try {
			con = getConnection(); //커넥션 연결
			pstmt = con.prepareStatement("select id from member where id=?");
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				//id가 존재 할 때(사용중)
				check = 1;
			}else{
				//존재하지 않을 때(사용가능)
				check = -1;
			}
		} catch (Exception ex1) {
			System.out.println("confirmID() 예외 : " + ex1);
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
	//회원가입
	//-------------------------
	public void insertMember(MemberDTO dto) throws Exception{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = getConnection();
			pstmt = con.prepareStatement("insert into member values(?,?,?,?,?,?,?,?,NOW())");
			
			//'?'값 채우기
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
			System.out.println("insertMember() 예외 : " + ex1);
		} finally{
			try {
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception e2) {}
		}//finally-end
	}//insertMember-end
	//-------------------------
	//로그인(인증)
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
			
			rs = pstmt.executeQuery(); //쿼리수행
			
			if (rs.next()) {
				//id가 존재하면
				dbPw = rs.getString("pw");
				if(pw.equals(dbPw)){
					x = 1; //암호일치
				}else{
					x = 0; //암호불일치
				}
			}else{
				x = -1; //아이디가 존재하지 않거나 암호 불일치
			}
		} catch (Exception ex1) {
			System.out.println("loginCheck() 예외 : " + ex1);
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
	//내정보수정 폼으로 보낼 데이터
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
			System.out.println("getMember() 예외 : " + ex1);
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
	//DB정보 수정
	//-------------------------
	public int updateMember(MemberDTO dto, String id, String pw) throws Exception {
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
				if(pw.equals(dbPw)){  //입력한 암호와 db암호가 같으면 삭제
					pstmt2 = con.prepareStatement("update member set name=?,email=?,tel=?,zipcode=?,addr=?,addr2=? where id=?");
					//'?'값 채우기
					
					pstmt2.setString(1, dto.getName());
					pstmt2.setString(2, dto.getEmail());
					pstmt2.setString(3, dto.getTel());
					pstmt2.setString(4, dto.getZipcode());
					pstmt2.setString(5, dto.getAddr());
					pstmt2.setString(6, dto.getAddr2());
					pstmt2.setString(7, dto.getId());
					
					pstmt2.executeUpdate(); //쿼리 수행 insert, update, delete 일 때 수행
					x = 1;  //암호 일치
				}else{
					x = -1; //암호 불일치
				}
			}else{
				x = 0; 		//ID X
			}
			
		} catch (Exception ex1) {
			System.out.println("updateMember() 예외 : " + ex1);
		} finally{
			try {
				if (rs != null) {rs.close();}
				if (pstmt2 != null) {pstmt2.close();}
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception ex2) {}
		}//finally-end
		return x;
	}//updateMember()-end
	public int updatePassword(String newPW, String id, String oldPw) throws Exception {
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
				if(oldPw.equals(dbPw)){  //입력한 암호와 db암호가 같으면 삭제
					pstmt2 = con.prepareStatement("update member set pw=? where id=?");
					//'?'값 채우기
					
					pstmt2.setString(1,newPW);
					pstmt2.setString(2,id);
					
					pstmt2.executeUpdate(); //쿼리 수행 insert, update, delete 일 때 수행
					x = 1;  //암호변경돔
				}else{
					x = -1; //암호 불일치
				}
			}else{
				x = 0; 		//ID X
			}
			
		} catch (Exception ex1) {
			System.out.println("updatePassword() 예외 : " + ex1);
		} finally{
			try {
				if (rs != null) {rs.close();}
				if (pstmt2 != null) {pstmt2.close();}
				if (pstmt != null) {pstmt.close();}
				if (con != null) {con.close();}
			} catch (Exception ex2) {}
		}//finally-end
		return x;
	}//updatePassword()-end
	//-------------------------
	//회원탈퇴
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
				if(pw.equals(dbPw)){  //입력한 암호와 db암호가 같으면 삭제
					pstmt2 = con.prepareStatement("delete from member where id=?");
					pstmt2.setString(1, id);
					pstmt2.executeUpdate();
					x = 1;  //암호 일치
				}else{
					x = -1; //암호 불일치
				}
			}else{
				x = 0; 		//ID X
			}
			
		} catch (Exception ex1) {
			System.out.println("deleteMember() 예외 : " + ex1);
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
	// 이메일 인증하기
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
		System.out.println("emailDupleCheck() 예외 :  " + ex1);
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
	//모든 회원정보 가져오기
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
				dto.setAddr(rs.getString("addr"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setRegdate(rs.getString("regdate"));
                
                list.add(dto);
            }
        } catch (Exception ex1) {
            System.out.println("getMemberList() 예외 : " + ex1);
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
