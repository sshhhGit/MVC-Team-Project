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
	
	// �̱��� ��ü ���(�޸� ���� ȿ��)
	private static QnaDAO dao = new QnaDAO(); //��ü����
	
	// private ������
	private QnaDAO(){
		
	}
	
	public static QnaDAO getInstance() {
		return dao;
	} // getInstance()-end
	
	//Ŀ�ؼ� �����ϱ� 
	private Connection getConnection() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		
		return ds.getConnection();
	} // getConnection()-end
	
	
	
	// -------------------------
	// QnQ���� ����(Q)����
	// -------------------------
	public void insertQna_Q(QnaDTO dto) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("insert into qna(user_id,user_content,user_regdate) values(?,?,now())");

			// ?�� �� �� ä���
			pstmt.setString(1, dto.getUser_id()); 
			pstmt.setString(2, dto.getUser_content()); 

			pstmt.executeUpdate(); // ��������  executeUpdate() : insert, update, delete

		} catch (Exception e) {
			System.out.println("insertQna_Q() ���� �߻� : " + e);
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
	// QnQ���� �亯(A)����
	// -------------------------
	public void insertQna_A(QnaDTO dto) throws Exception {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			pstmt = con.prepareStatement("update qna set admin_id=?,admin_content=?,admin_regdate=now() where num=?");

			// ?�� �� �� ä���
			pstmt.setString(1, dto.getAdmin_id());
			pstmt.setString(2, dto.getAdmin_content());
			pstmt.setInt(3, dto.getNum());

			pstmt.executeUpdate(); // �������� executeUpdate() : insert, update,delete

		} catch (Exception e) {
			System.out.println("insertQna_A() ���� �߻� : " + e);
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
	// �� ����
	// ---------------------
	public int getQnaCount() throws Exception{
		int cnt = -1; //�ʱ�ȭ
		
		try{
			//ó��
			con = getConnection();//Ŀ�ؼ� ���
			pstmt = con.prepareStatement("select count(*) from qna"); //������ ���ڰ� ����
			rs = pstmt.executeQuery();//���� ���� 
			
			if(rs.next()){
				cnt = rs.getInt(1); // 1�� �ʵ� ��ȣ�̴�. �� ���� 
				System.out.println("cnt(��ü�� ��) : "+ cnt);
			}
			
		}catch(Exception ex1){
			System.out.println("getQnaCount() ���� : " + ex1);
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
		List<QnaDTO> list = null;// ����
		try {
			// ó��
			con = getConnection();
			sql = "select * from qna order by num desc";
			// ����,����
			// limit ���� ��ġ�� 0���� �� ��

			pstmt = con.prepareStatement(sql);// ������ ���� ����

			rs = pstmt.executeQuery();// ���� ����

			if (rs.next()) {
				list = new ArrayList<QnaDTO>();
				do {
					QnaDTO dto = new QnaDTO();// ��ü ����

					dto.setNum(rs.getInt("num"));
					dto.setUser_id(rs.getString("user_id"));
					dto.setUser_content(rs.getString("user_content"));
					dto.setUser_regdate(rs.getTimestamp("user_regdate"));
					dto.setAdmin_id(rs.getString("admin_id"));
					dto.setAdmin_content(rs.getString("admin_content"));
					dto.setAdmin_regdate(rs.getTimestamp("admin_regdate"));

					list.add(dto);// ///////******** ������ ����� ����
					// vec.add(dto); ������ ����� �ִ� (ä�ÿ��� ���)

				} while (rs.next()); // (do-while)-end
			} // if-end

		} catch (Exception ex1) {
			System.out.println("getList() ���� :" + ex1);
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
	// ����Ʈ
	// ---------------------
	public List getList(int startRow, int pageSize) throws Exception {
		// System.out.println("startRow : " + startRow);
		// System.out.println("pageSize : " + pageSize);
		List<QnaDTO> list = null;// ����
		try {
			// ó��
			con = getConnection();
			sql = "select * from qna order by num desc limit ?,?";
			// ����,����
			// limit ���� ��ġ�� 0���� �� ��

			pstmt = con.prepareStatement(sql);// ������ ���� ����
			pstmt.setInt(1, startRow - 1);// ?��ä���, 0���� ����
			pstmt.setInt(2, pageSize);// ?��ä���

			rs = pstmt.executeQuery();// ���� ����

			if (rs.next()) {
				list = new ArrayList<QnaDTO>();
				do {
					QnaDTO dto = new QnaDTO();// ��ü ����

					dto.setNum(rs.getInt("num"));
					dto.setUser_id(rs.getString("user_id"));
					dto.setUser_content(rs.getString("user_content"));
					dto.setUser_regdate(rs.getTimestamp("user_regdate"));
					dto.setAdmin_id(rs.getString("admin_id"));
					dto.setAdmin_content(rs.getString("admin_content"));
					dto.setAdmin_regdate(rs.getTimestamp("admin_regdate"));

					list.add(dto);// ///////******** ������ ����� ����
					// vec.add(dto); ������ ����� �ִ� (ä�ÿ��� ���)

				} while (rs.next());
			} // if-end

		} catch (Exception ex1) {
			System.out.println("getList() ���� :" + ex1);
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
	// �ش� �� ����(�ش� �� ��ü)
	// -----------------------
	public QnaDTO getQna(int num) throws Exception {
		QnaDTO dto = null;
		try {
			// ó��
			con = getConnection();

			// ��ȸ�� ����
//			sql = "update qna set readcount=readcount+1 where num=?";
//			pstmt = con.prepareStatement(sql);
//			pstmt.setInt(1, num);
//			pstmt.executeUpdate();// ���� ����

			// �� ���뺸��
			pstmt = con.prepareStatement("select * from qna where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();// ���� ����

			if (rs.next()) {
				// rs������ dto�ְ� dto�� Ŭ���̾�Ʈ�� ������
				dto = new QnaDTO();// ��ü����
				dto.setNum(rs.getInt("num"));
				dto.setUser_id(rs.getString("user_id"));
				dto.setUser_content(rs.getString("user_content"));
				dto.setUser_regdate(rs.getTimestamp("user_regdate"));
				dto.setAdmin_id(rs.getString("admin_id"));
				dto.setAdmin_content(rs.getString("admin_content"));
				dto.setAdmin_regdate(rs.getTimestamp("admin_regdate"));

			}// if-end
		} catch (Exception ex1) {
			System.out.println("getQna() ���� :" + ex1);
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
	// DB �ۼ���(�����)
	// -------------------
	public int updateQna_Q(QnaDTO dto) throws Exception {
		int x = -100;
		try {
			// ó��
			con = getConnection();
			pstmt = con.prepareStatement("select * from qna where num=?");
			pstmt.setInt(1, dto.getNum());// ?�� ä���
			rs = pstmt.executeQuery();// ���� ����

			if (rs.next()) {
					sql = "update qna set user_id=?,user_content=? where num=?";
					
					pstmt = con.prepareStatement(sql);// ������ ���� ����
					pstmt.setString(1, dto.getUser_id());
					pstmt.setString(2, dto.getUser_content());
					pstmt.setInt(3, dto.getNum());
					pstmt.executeUpdate();// ���� ����
					x = 1;
			}// if

		} catch (Exception ex1) {
			System.out.println("updateQna_Q() ���� :" + ex1);
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
	// DB �ۼ���(������)
	// -------------------
	public int updateQna_A(QnaDTO dto) throws Exception {
		int x = -100;
		try {
			// ó��
			con = getConnection();
			pstmt = con.prepareStatement("select * from qna where num=?");
			pstmt.setInt(1, dto.getNum());// ?�� ä���
			rs = pstmt.executeQuery();// ���� ����

			if (rs.next()) {
				sql = "update qna set admin_id=?,admin_content=? where num=?";

				pstmt = con.prepareStatement(sql);// ������ ���� ����
				pstmt.setString(1, dto.getAdmin_id());
				pstmt.setString(2, dto.getAdmin_content());
				pstmt.setInt(3, dto.getNum());
				pstmt.executeUpdate();// ���� ����
				x = 1;
			}// if

		} catch (Exception ex1) {
			System.out.println("updateQna_A() ���� :" + ex1);
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
	// ��������(����)
	// --------------------
	public int deleteQna_Q(int num, String user_id, String pw) throws Exception {
		String dbPw = "";
		int x = -100;

		try {
			// ó��
			con = getConnection();
			pstmt = con.prepareStatement("select pw from member where id=?");
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();// ���� ����
			if (rs.next()) {
				dbPw = rs.getString("pw");
				System.out.println("dbpw : " + dbPw);
				System.out.println("pw : " + pw);
				if (dbPw.equals(pw)) {// ��ȣ�� ��ġ�ϸ� �� ����
					pstmt = con.prepareStatement("delete from qna where num=?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();// ���� ����
					
					x = 1; // ���������� ����
				} else {// ��ȣ�� ��ġ���� ������
					x = 0; // ��ȣ Ʋ��
				}
			}
			
			System.out.println("x : " + x);

		} catch (Exception ex1) {
			System.out.println("deleteQna_A() ���� :" + ex1);
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
	// �亯����(������)
	// --------------------
	public int deleteQna_A(int num, String admin_id, String pw) throws Exception {
		int x = -100;
		String dbPw = "";

		try {
			// ó��
			con = getConnection();
			pstmt = con.prepareStatement("select pw from member where id=?");
			pstmt.setString(1, admin_id);
			rs = pstmt.executeQuery();// ���� ����
			
			if (rs.next()) {
				dbPw = rs.getString("pw");
				System.out.println("dbpw : " + dbPw);
				System.out.println("pw : " + pw);
				if (dbPw.equals(pw)) {// ��ȣ�� ��ġ�ϸ� �� ����
					pstmt = con.prepareStatement("update qna set admin_id=?,admin_content=?,admin_regdate=? where num=?");
					pstmt.setNull(1, java.sql.Types.VARCHAR);
					pstmt.setNull(2, java.sql.Types.VARCHAR);
					pstmt.setNull(3, java.sql.Types.TIMESTAMP);
					pstmt.setInt(4, num);
					pstmt.executeUpdate();// ���� ����

					x = 1; // ���������� ����
				} else {// ��ȣ�� ��ġ���� ������
					x = 0; // ��ȣ Ʋ��
				}
			}

			System.out.println("deleteQna_A()�� x : " + x);

		} catch (Exception ex1) {
			System.out.println("deleteQna_A() ���� :" + ex1);
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
	// �α��ε� id�� ������id ��
	// x==1 �������, x==0 �ٸ����
	// --------------------
	public int compareWriter(int num, String current_user_id) {
		int x = -100;
		String dbUser_id = "";
		
		try {
			// ó��
			con = getConnection();
			pstmt = con.prepareStatement("select user_id from qna where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();// ���� ����
			
			if (rs.next()) {
				dbUser_id = rs.getString(1);
				System.out.println("dbUser_id : " + dbUser_id);
				System.out.println("current_user_id : " + current_user_id);
				
				boolean b = dbUser_id.equals(current_user_id);
				System.out.println("boolean : " + b);
				
				if (dbUser_id.equals(current_user_id)) {
					x = 1; // �۾��� id == �α����� id 
				} else {
					x = 0; // �۾��� id != �α����� id 
				}
			}

			System.out.println("compareWriter()�� x : " + x);
			
		} catch (Exception e) {
			System.out.println("compareWriter() ���� �߻� : " + e);
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
	// �α��ε� �����ڿ� �亯��id ��
	// y==1 ����������, y==0 �ٸ�������
	// --------------------
	public int compareAdmin(int num, String current_admin_id) {
		int y = -100;
		String dbAdmin_id = "";

		try {
			// ó��
			con = getConnection();
			pstmt = con.prepareStatement("select admin_id from qna where num=?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();// ���� ����

			if (rs.next()) {
				dbAdmin_id = rs.getString(1);
				System.out.println("dbAdmin_id : " + dbAdmin_id);
				System.out.println("current_admin_id : " + current_admin_id);

				boolean b = dbAdmin_id.equals(current_admin_id);
				System.out.println("boolean : " + b);

				if (dbAdmin_id.equals(current_admin_id)) {
					y = 1; // �亯�� �ۼ��� ������ id == ���� ������ id
				} else {
					y = 0; /// �亯�� �ۼ��� ������ id != ���� ������ id
				}
			}

			System.out.println("compareAdmin()�� y : " + y);

		} catch (Exception e) {
			System.out.println("compareAdmin() ���� �߻� : " + e);
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
