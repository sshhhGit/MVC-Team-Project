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
			pstmt = con.prepareStatement("insert into qna(admin_id,admin_content,admin_regdate) values(?,?,now())");

			// ?에 들어갈 값 채우기
			pstmt.setString(1, dto.getAdmin_id());
			pstmt.setString(2, dto.getAdmin_content());

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
	// DB 글수정
	// -------------------
	public int updateQna(QnaDTO dto) throws Exception {
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
			System.out.println("updateQna() 예외 :" + ex1);
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
	// 글삭제
	// --------------------
	public int deleteQna(int num, String user_id, String pw) throws Exception {
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
			System.out.println("deleteQna() 예외 :" + ex1);
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	

} // class-end
