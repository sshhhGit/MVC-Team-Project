package qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sun.xml.internal.ws.wsdl.writer.document.Types;



public class QnaDAO {
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	
	// 싱글톤 객체 사용(메모리 절약 효과)
	private static QnaDAO dao = new QnaDAO(); //객체생성
	
	// private 생성자
	private QnaDAO(){
		
	}
	
	public static QnaDAO getInstance() {
		return dao;
	} // getInstance()-end
	
	//커넥션 연결하기 
	private Connection getConnection() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		
		return ds.getConnection();
	} // getConnection()-end
	
	
	
	// -------------------------
	// QnQ에서 질문(Q)쓰기
	// -------------------------
	public void insertQna_Q(QnaDTO dto) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("insert into qna(user_id,user_content,user_regdate) values(?,?,now())");

			// ?에 들어갈 값 채우기
			pstmt.setString(1, dto.getUser_id()); 
			pstmt.setString(2, dto.getUser_content()); 

			pstmt.executeUpdate(); // 쿼리수행  executeUpdate() : insert, update, delete

		} catch (Exception e) {
			System.out.println("insertQna_Q() 예외 발생 : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {

			}
		}

	}// insertQna_Q() end
	
	
	// -------------------------
	// QnQ에서 답변(A)쓰기
	// -------------------------
	public void insertQna_A(QnaDTO dto) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("update qna set admin_id=?,admin_content=?,admin_regdate=now() where num=?");

			// ?에 들어갈 값 채우기
			pstmt.setString(1, dto.getAdmin_id());
			pstmt.setString(2, dto.getAdmin_content());
			pstmt.setInt(3, dto.getNum());

			pstmt.executeUpdate(); // 쿼리수행 executeUpdate() : insert, update,delete

		} catch (Exception e) {
			System.out.println("insertQna_A() 예외 발생 : " + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception e2) {

			}
		}

	}// insertQna_A() end
	
	
	
	// ---------------------
	// 글 갯수
	// ---------------------
	public int getQnaCount() throws Exception{
		int cnt = -1; //초기화
		
		try{
			//처리
			con = getConnection();//커넥션 얻기
			pstmt = con.prepareStatement("select count(*) from qna"); //생성시 인자가 들어간다
			rs = pstmt.executeQuery();//쿼리 수행 
			
			if(rs.next()){
				cnt = rs.getInt(1); // 1은 필드 번호이다. 글 갯수 
				System.out.println("cnt(전체글 수) : "+ cnt);
			}
			
		}catch(Exception ex1){
			System.out.println("getQnaCount() 예외 : " + ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally end
		
		return cnt;
	}// getQnaCount()
	
	
	
	
	
	
	
	
	public List getList() {
		List<QnaDTO> list = null;// 변수
		try {
			// 처리
			con = getConnection();
			sql = "select * from qna order by num desc";
			// 시작,갯수
			// limit 시작 위치는 0부터 할 것

			pstmt = con.prepareStatement(sql);// 생성시 인자 들어간다

			rs = pstmt.executeQuery();// 쿼리 수행

			if (rs.next()) {
				list = new ArrayList<QnaDTO>();
				do {
					QnaDTO dto = new QnaDTO();// 객체 생성

					dto.setNum(rs.getInt("num"));
					dto.setUser_id(rs.getString("user_id"));
					dto.setUser_content(rs.getString("user_content"));
					dto.setUser_regdate(rs.getTimestamp("user_regdate"));
					dto.setAdmin_id(rs.getString("admin_id"));
					dto.setAdmin_content(rs.getString("admin_content"));
					dto.setAdmin_regdate(rs.getTimestamp("admin_regdate"));

					list.add(dto);// ///////******** 스레드 기능이 없고
					// vec.add(dto); 스레드 기능이 있다 (채팅에서 사용)

				} while (rs.next()); // (do-while)-end
			} // if-end

		} catch (Exception ex1) {
			System.out.println("getList() 예외 :" + ex1);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex2) {
			}
		}// finally-end
		return list;
	}
	
	
	
	
	
	
	// ---------------------
	// 리스트
	// ---------------------
	public List getList(int startRow, int pageSize) throws Exception {
		// System.out.println("startRow : " + startRow);
		// System.out.println("pageSize : " + pageSize);
		List<QnaDTO> list = null;// 변수
		try {
			// 처리
			con = getConnection();
			sql = "select * from qna order by num desc limit ?,?";
			// 시작,갯수
			// limit 시작 위치는 0부터 할 것

			pstmt = con.prepareStatement(sql);// 생성시 인자 들어간다
			pstmt.setInt(1, startRow - 1);// ?값채우기, 0부터 시작
			pstmt.setInt(2, pageSize);// ?값채우기

			rs = pstmt.executeQuery();// 쿼리 수행

			if (rs.next()) {
				list = new ArrayList<QnaDTO>();
				do {
					QnaDTO dto = new QnaDTO();// 객체 생성

					dto.setNum(rs.getInt("num"));
					dto.setUser_id(rs.getString("user_id"));
					dto.setUser_content(rs.getString("user_content"));
					dto.setUser_regdate(rs.getTimestamp("user_regdate"));
					dto.setAdmin_id(rs.getString("admin_id"));
					dto.setAdmin_content(rs.getString("admin_content"));
					dto.setAdmin_regdate(rs.getTimestamp("admin_regdate"));

					list.add(dto);// ///////******** 스레드 기능이 없고
					// vec.add(dto); 스레드 기능이 있다 (채팅에서 사용)

				} while (rs.next());
			} // if-end

		} catch (Exception ex1) {
			System.out.println("getList() 예외 :" + ex1);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex2) {
			}
		}// finally-end

		return list;
	} // getList()-end
	
	
	
	// -----------------------
	// 해당 글 보기(해당 글 객체)
	// -----------------------
	public QnaDTO getQna(int num) throws Exception {
		QnaDTO dto = null;
		try {
			// 처리
			con = getConnection();

			// 조회수 증가
//			sql = "update qna set readcount=readcount+1 where num=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, num);
//			pstmt.executeUpdate();// 쿼리 수행

			// 글 내용보기
			pstmt = con.prepareStatement("select * from qna where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();// 쿼리 수행

			if (rs.next()) {
				// rs내용을 dto넣고 dto를 클라이언트로 보낸다
				dto = new QnaDTO();// 객체생성
				dto.setNum(rs.getInt("num"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_content(rs.getString("user_content"));
				dto.setUser_regdate(rs.getTimestamp("user_regdate"));
				dto.setAdmin_id(rs.getString("admin_id"));
				dto.setAdmin_content(rs.getString("admin_content"));
				dto.setAdmin_regdate(rs.getTimestamp("admin_regdate"));

			}// if-end
		} catch (Exception ex1) {
			System.out.println("getQna() 예외 :" + ex1);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex2) {
			}
		}// finally-end

		return dto;
	} // getQna()-end
	
	
	
	// -------------------
	// DB 글수정(사용자)
	// -------------------
	public int updateQna_Q(QnaDTO dto) throws Exception {
		int x = -100;
		try {
			// 처리
			con = getConnection();
			pstmt = con.prepareStatement("select * from qna where num=?");
			pstmt.setInt(1, dto.getNum());// ?값 채우기
			rs = pstmt.executeQuery();// 쿼리 수행

			if (rs.next()) {
					sql = "update qna set user_id=?,user_content=? where num=?";
					
					pstmt = con.prepareStatement(sql);// 생성시 인자 들어간다
					pstmt.setString(1, dto.getUser_id());
					pstmt.setString(2, dto.getUser_content());
					pstmt.setInt(3, dto.getNum());
					pstmt.executeUpdate();// 쿼리 수행
					x = 1;
			}// if

		} catch (Exception ex1) {
			System.out.println("updateQna_Q() 예외 :" + ex1);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex2) {
			}
		}// finally-end

		return x;
	} // updateQna()-end

	
	
	
	
	// -------------------
	// DB 글수정(관리자)
	// -------------------
	public int updateQna_A(QnaDTO dto) throws Exception {
		int x = -100;
		try {
			// 처리
			con = getConnection();
			pstmt = con.prepareStatement("select * from qna where num=?");
			pstmt.setInt(1, dto.getNum());// ?값 채우기
			rs = pstmt.executeQuery();// 쿼리 수행

			if (rs.next()) {
				sql = "update qna set admin_id=?,admin_content=? where num=?";

				pstmt = con.prepareStatement(sql);// 생성시 인자 들어간다
				pstmt.setString(1, dto.getAdmin_id());
				pstmt.setString(2, dto.getAdmin_content());
				pstmt.setInt(3, dto.getNum());
				pstmt.executeUpdate();// 쿼리 수행
				x = 1;
			}// if

		} catch (Exception ex1) {
			System.out.println("updateQna_A() 예외 :" + ex1);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex2) {
			}
		}// finally-end

		return x;
	} // updateQna()-end
	
	
	
	
	// --------------------
	// 질문삭제(유저)
	// --------------------
	public int deleteQna_Q(int num, String user_id, String pw) throws Exception {
		String dbPw = "";
		int x = -100;

		try {
			// 처리
			con = getConnection();
			pstmt = con.prepareStatement("select pw from member where id=?");
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();// 쿼리 수행
			if (rs.next()) {
				dbPw = rs.getString("pw");
				System.out.println("dbpw : " + dbPw);
				System.out.println("pw : " + pw);
				if (dbPw.equals(pw)) {// 암호가 일치하면 글 삭제
					pstmt = con.prepareStatement("delete from qna where num=?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();// 쿼리 수행
					
					x = 1; // 정상적으로 삭제
				} else {// 암호가 일치하지 않으면
					x = 0; // 암호 틀림
				}
			}
			
			System.out.println("x : " + x);

		} catch (Exception ex1) {
			System.out.println("deleteQna_A() 예외 :" + ex1);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex2) {
			}
		}// finally-end

		return x;
	}// deleteQna()-end
	
	
	
	// --------------------
	// 답변삭제(관리자)
	// --------------------
	public int deleteQna_A(int num, String admin_id, String pw) throws Exception {
		int x = -100;
		String dbPw = "";

		try {
			// 처리
			con = getConnection();
			pstmt = con.prepareStatement("select pw from member where id=?");
			pstmt.setString(1, admin_id);
			rs = pstmt.executeQuery();// 쿼리 수행
			
			if (rs.next()) {
				dbPw = rs.getString("pw");
				System.out.println("dbpw : " + dbPw);
				System.out.println("pw : " + pw);
				if (dbPw.equals(pw)) {// 암호가 일치하면 글 삭제
					pstmt = con.prepareStatement("update qna set admin_id=?,admin_content=?,admin_regdate=? where num=?");
					pstmt.setNull(1, java.sql.Types.VARCHAR);
					pstmt.setNull(2, java.sql.Types.VARCHAR);
					pstmt.setNull(3, java.sql.Types.TIMESTAMP);
					pstmt.setInt(4, num);
					pstmt.executeUpdate();// 쿼리 수행

					x = 1; // 정상적으로 삭제
				} else {// 암호가 일치하지 않으면
					x = 0; // 암호 틀림
				}
			}

			System.out.println("deleteQna_A()의 x : " + x);

		} catch (Exception ex1) {
			System.out.println("deleteQna_A() 예외 :" + ex1);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex2) {
			}
		}// finally-end

		return x;
	}// deleteQna()-end
	
	
	
	// --------------------
	// 로그인된 id와 질문자id 비교
	// x==1 같은사람, x==0 다른사람
	// --------------------
	public int compareWriter(int num, String current_user_id) {
		int x = -100;
		String dbUser_id = "";
		
		try {
			// 처리
			con = getConnection();
			pstmt = con.prepareStatement("select user_id from qna where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();// 쿼리 수행
			
			if (rs.next()) {
				dbUser_id = rs.getString(1);
				System.out.println("dbUser_id : " + dbUser_id);
				System.out.println("current_user_id : " + current_user_id);
				
				boolean b = dbUser_id.equals(current_user_id);
				System.out.println("boolean : " + b);
				
				if (dbUser_id.equals(current_user_id)) {
					x = 1; // 글쓴이 id == 로그인한 id 
				} else {
					x = 0; // 글쓴이 id != 로그인한 id 
				}
			}

			System.out.println("compareWriter()의 x : " + x);
			
		} catch (Exception e) {
			System.out.println("compareWriter() 예외 발생 : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex2) {
			}
		} // finally-end
		
		return x;
	} // compareWriter()-end
	
	
	
	
	// --------------------
	// 로그인된 관리자와 답변자id 비교
	// y==1 같은관리자, y==0 다른관리자
	// --------------------
	public int compareAdmin(int num, String current_admin_id) {
		int y = -100;
		String dbAdmin_id = "";

		try {
			// 처리
			con = getConnection();
			pstmt = con.prepareStatement("select admin_id from qna where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();// 쿼리 수행

			if (rs.next()) {
				dbAdmin_id = rs.getString(1);
				System.out.println("dbAdmin_id : " + dbAdmin_id);
				System.out.println("current_admin_id : " + current_admin_id);

				boolean b = dbAdmin_id.equals(current_admin_id);
				System.out.println("boolean : " + b);

				if (dbAdmin_id.equals(current_admin_id)) {
					y = 1; // 답변을 작성한 관리자 id == 현재 관리자 id
				} else {
					y = 0; /// 답변을 작성한 관리자 id != 현재 관리자 id
				}
			}

			System.out.println("compareAdmin()의 y : " + y);

		} catch (Exception e) {
			System.out.println("compareAdmin() 예외 발생 : " + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (Exception ex2) {
			}
		} // finally-end

		return y;
	} // compareWriter()-end
	
	

} // class-end
