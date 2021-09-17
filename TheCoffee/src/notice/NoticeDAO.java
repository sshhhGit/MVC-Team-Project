package notice;

import java.sql.*;

import javax.sql.*;
import javax.naming.*;

import mysqlboard.BoardDTO;

import java.util.*;
import java.sql.Timestamp;

//DAO : DB처리
public class NoticeDAO {
	/*
	Statement 클래스
	- SQL 구문을 실행하는 역할
	- 스스로는 SQL 구문 이해 못함(구문해석 X) -> 전달역할
	- SQL 관리 O + 연결 정보 X

	PreparedStatement 클래스
	- Statement 클래스의 기능 향상
	- 인자와 관련된 작업이 특화(매개변수)
	- 코드 안정성 높음. 가독성 높음.
	- 코드량이 증가 -> 매개변수를 set해줘야하기 때문에..
	- 텍스트 SQL 호출
	*/
	
	/*
	ResultSet 
	- Statement 또는 PreparedStatement 객체로 
	Select문을 사용해 얻어온 레코드 값들을 테이블의 형태로 갖게 되는 객체
	즉, Select문을 통해 얻은 데이터 정보를 ResultSet객체에 저장해야 한다.
	데이터를 얻어올때 필수적으로 사용해야 하는 객체
	*/
	
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = "";
	
	//싱글톤 객체 사용(매모리 절약 효과)
	/*
	getInstance()는 이름에서처럼 인스턴스를 만드는 것
	new연산자를 이용해서 클래스를 새로운 메모리에 할당하는 것과 달리
	getInstance()는 최초에 할당된 하나의 메모리를 계속 쓰는 방식이다.
	*/
	
	private static NoticeDAO dao = new NoticeDAO();//객체생성
	
	private NoticeDAO(){}//생성자
	
	public static NoticeDAO getInstance(){
		return dao;
	}
	
	//커넥션 연결하기
	private Connection getCon() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		
		return ds.getConnection();
	}
	
	//--------------------------------------------------------------------
	//글쓰기
	//--------------------------------------------------------------------
	public void insertNotice(NoticeDTO dto) throws Exception{
		int no = dto.getNotice_no();
		
		int number = 0;//글 그룹 처리를 위한 변수
		
		try{
			//처리
			con = getCon(); //커넥션 얻기
			
			//최대 글번호 얻기
			pstmt = con.prepareStatement("select max(notice_no) from notice");
			/*
			executeQuery
			rs = pstmt.excuteQuery에서 executeQuery는 PreparedStatement를 통해 
			SQL을 실행한 값을 ResultSet에 전달하는 역할,
			그러므로 ResultSet객체를 생성한 변수 rs에 저장을 한번 해준 것이고
			커서 단위로 값을 출력하는 ResultSet의 특징을 사용해 원하는 SQL값을 rs변수를 통해 출력할 수 있다
			*/
			rs = pstmt.executeQuery();//쿼리수행
			
			if(rs.next()){//next() 입력된 값에 따라 원하는 반환값을 가지고 온다.
				number = rs.getInt(1)+1;//최대글번호 +1 글 그룹에서 사용
			}else{//글이 없을때
				number = 1;//처음 글
			}
			
			//글쓰기
			sql = "insert into notice(notice_title, notice_content, notice_date)values(?,?,NOW())";
			
			pstmt = con.prepareStatement(sql);//생성시 인자 들어감
			
		 
			pstmt.setString(1, dto.getNotice_title());
			pstmt.setString(2, dto.getNotice_content());
		 	
			pstmt.executeUpdate();
			
		}catch(Exception ex1){
			System.out.println("insertNotice() 예외 : "+ex1);
		}finally{
			try{
				if(rs != null){rs.close();}
				if(pstmt != null){pstmt.close();}
				if(con != null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
	}//insertNotice-end
	
	//--------------------------------------------------------------------
	//글개수
	//--------------------------------------------------------------------
	public int getNoticeCount() throws Exception{
		int cnt = -1;//초기화
		
		try{
			//처리
			con = getCon();//커넥션 얻기
			pstmt = con.prepareStatement("select count(*) from notice");//생성시 인자 들어감
			
			rs = pstmt.executeQuery();//쿼리 수행
			
			if(rs.next()){
				cnt = rs.getInt(1);//1은 필드 번호, 글 개수
			}
		}catch(Exception ex1){
			System.out.println("getNoticeCount() 예외 : "+ ex1);
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
	//리스트
	//--------------------------------------------------------------------

	public List getList(int startRow, int count) throws Exception{
		
		List<NoticeDTO> list = null; //변수
		try{
			con=getCon();
			sql="select * from notice order by notice_date desc limit ?,?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startRow-1);
			pstmt.setInt(2, count);
			
			rs=pstmt.executeQuery();
			//처리
			if(rs.next()){
				list = new ArrayList<NoticeDTO>();
				do{
					NoticeDTO dto = new NoticeDTO();//객체 생성
					
					dto.setNotice_no(rs.getInt("notice_no"));
					dto.setNotice_title(rs.getString("notice_title"));
					dto.setNotice_content(rs.getString("notice_content"));
					dto.setNotice_date(rs.getTimestamp("notice_date"));
					dto.setNotice_count(rs.getInt("notice_count"));
					
					list.add(dto);/////*****스레드 기능이 없고
					//vec.add(dto); 스레드 기능이 있다 (채팅에서 사용)
					
				}while(rs.next());
			}//if-end
			
		}catch(Exception ex1){
			System.out.println("getList() 예외 : "+ex1);
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
	//글내용보기
	//--------------------------------------------------------------------
	public NoticeDTO getNotice(int notice_no) throws Exception{
		NoticeDTO dto = null;
		try{
			//처리
			con = getCon();
			
			//조회수 증가
			sql = "update notice set notice_count = notice_count+1 where notice_no=-?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, notice_no);
			pstmt.executeUpdate();//쿼리
			//---------------------//조회수 증가 end
			
			//글 내용보기
			pstmt = con.prepareStatement("select * from notice where notice_no=?");
			pstmt.setInt(1, notice_no);
			rs = pstmt.executeQuery();//쿼리
			if(rs.next()){
				//rs내용 dto에 넣고 클라이언트 보냄
				dto = new NoticeDTO();//객체생성
				
				dto.setNotice_no(rs.getInt("notice_no"));
				dto.setNotice_title(rs.getString("notice_title"));
				dto.setNotice_content(rs.getString("notice_content"));
				dto.setNotice_date(rs.getTimestamp("notice_date"));
				dto.setNotice_count(rs.getInt("notice_count"));
				//dto.setNotice_pw(rs.getString("notice_pw"));
			}//if-end
			
		}catch(Exception ex1){
			System.out.println("getNotice() 예외 : "+ex1);
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
	// 글수정 클라이언트로 보낼 데이터 
	//--------------------------------------------------------------------
	public NoticeDTO getUpdate(int notice_no) throws Exception{
		NoticeDTO dto=null;
		
		try{
			con=getCon();
			//글 내용보기
			pstmt=con.prepareStatement("select * from notice where notice_no=?");
			pstmt.setInt(1, notice_no);
			rs=pstmt.executeQuery();//쿼리 수행
		
			if(rs.next()){
				//rs내용을 dto넣고 dto를 클라이언트로 보낸다 
				dto = new NoticeDTO();//객체생성
				
				dto.setNotice_no(rs.getInt("notice_no"));
				dto.setNotice_title(rs.getString("notice_title"));
				dto.setNotice_content(rs.getString("notice_content"));
				dto.setNotice_date(rs.getTimestamp("notice_date"));
				dto.setNotice_count(rs.getInt("notice_count"));
				//dto.setNotice_pw(rs.getString("notice_pw"));
			}//if-end
			
		}catch(Exception ex1){
			System.out.println("getUpdate() 예외 :"+ex1);
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
	//DB 글수정
	//--------------------------------------------------------------------
	public void updateNotice(NoticeDTO dto) throws Exception{
		String dbPw="";
		int x=-1;
		try{
			//처리
			con=getCon();
			sql="update notice set notice_title=?,notice_content=? where notice_no=?";
			pstmt=con.prepareStatement(sql);//생성시 인자 들어간다 

			pstmt.setString(1, dto.getNotice_title());
			pstmt.setString(2, dto.getNotice_content());
			pstmt.setInt(3, dto.getNotice_no());

			pstmt.executeUpdate();//쿼리 수행


		}catch(Exception ex1){
			System.out.println("updateNotice() 예외 :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

	}//updateBoard() end
	
	//--------------------
	// 글삭제
	//--------------------
	public void deleteNotice(int notice_no) throws Exception{
			
		try{
			//처리
			con=getCon();

			pstmt=con.prepareStatement("delete from notice where notice_no=?");

			pstmt.setInt(1, notice_no);
			pstmt.executeUpdate();//쿼리 수행

		}catch(Exception ex1){
			System.out.println("deleteNotice() 예외 :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

	}//deleteBoard() -end

}//class-end
