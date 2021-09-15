package mysqlboard;
import java.sql.*;
import javax.sql.*;//DataSource
import javax.naming.*;//lookup
import java.util.*;//List, ArrayList
import java.sql.Timestamp;

//DAO : DBó��(�����Ͻ� ����)
public class BoardDAO {
	Connection con=null;
	Statement stmt=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	String sql="";
	
	//�̱��� ��ü ���(�޸� ���� ȿ��)
	private static BoardDAO dao=new BoardDAO();//��ü����
	
	private BoardDAO(){}//������ , private�ϸ� �ܺο��� ���� ���� 
	
	public static BoardDAO getInstance(){
		return dao;
	}
	
	//Ŀ�ؼ� �����ϱ� 
	private Connection getCon() throws Exception{
		Context ct=new InitialContext();
		DataSource ds=(DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		
		return ds.getConnection();
	}
	
	//-------------------------
	//  ���۾���, ��� ���� 
	//-------------------------
	public void insertBoard(BoardDTO dto) throws Exception{
		int num=dto.getNum();
		int ref=dto.getRef();
		int re_step=dto.getRe_step();
		int re_level=dto.getRe_level();
		
		int number=0;//�� �׷� ó�� �ϱ� ���� ����
		try{
			//ó�� 
			con=getCon();//Ŀ�ؼ� ���
			
			//�ִ� �۹�ȣ ��� 
			pstmt=con.prepareStatement("select max(num) from board");
			rs=pstmt.executeQuery();//���� ����
			
			if(rs.next()){//���� ���� �ϸ� 
				number=rs.getInt(1)+1;//�ִ�۹�ȣ+1,  �� �׷쿡 ����Ϸ���
			}else{//���� ������
				number=1;//ó�� ��  ref=number;
			}//else end---
			
			if(num!=0){//����̸�
				//��� �������� ��ġ Ȯ�� 
				sql="update board set re_step=re_step+1 where ref=? and re_step>?";
				pstmt=con.prepareStatement(sql);//������ ���� ���� 
				
				pstmt.setInt(1, ref);//?�� ä��� 
				pstmt.setInt(2, re_step);
				
				pstmt.executeUpdate();//���� ���� 
				
				//ref=number;
				re_step=re_step+1;
				re_level=re_level+1;
				
				
			}else{//�����̸�, ù��° ���̸�
				ref=number;
				re_step=0;
				re_level=0;
			}//else -end
			
			//�۾���
			sql="insert into board(writer,subject,pw,regdate,"+
			"ref,re_step,re_level,content,ip) "+
			"values(?,?,?,NOW(),?,?,?,?,?)";
			
			pstmt=con.prepareStatement(sql);//������ ���� ���� 
			
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getPw());
			
			pstmt.setInt(4, ref);//ref***********
			pstmt.setInt(5, re_step);//**********
			pstmt.setInt(6, re_level);//*********
			
			pstmt.setString(7, dto.getContent());
			pstmt.setString(8, dto.getIp());
			
			pstmt.executeUpdate();
			
		}catch(Exception ex1){
			System.out.println("insertBoard() ���� :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(stmt!=null){stmt.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end
		
		
	}//insertBoard() end
	//---------------------
	//�� ����
	//---------------------
	public int getBoardCount() throws Exception{
		int cnt=-1;//�ʱ�ȭ
		
		try{
			//ó��
			con=getCon();//Ŀ�ؼ� ���
			pstmt=con.prepareStatement("select count(*) from board");//������ ���� ����
			
			rs=pstmt.executeQuery();//���� ���� 
			
			if(rs.next()){
				cnt=rs.getInt(1);//1�� �ʵ� ��ȣ, �� ���� 
				System.out.println("cnt:"+cnt);
			}
		}catch(Exception ex1){
			System.out.println("getBoardCount() ���� :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally end
		
		return cnt;
	}// getBoardCount()
	
	//---------------------
	//����Ʈ
	//---------------------
	public List getList(int startRow, int pageSize) throws Exception{
		//System.out.println("startRow:"+startRow);
		//System.out.println("pageSize:"+pageSize);
		List<BoardDTO> list=null;//����
        try{
        	//ó��
        	con=getCon();
        	sql="select * from board order by ref desc,re_step asc limit ?,?";
        	//                                                          ����,����
        	//limit ���� ��ġ��  0���� �� ��
        	
        	pstmt=con.prepareStatement(sql);//������ ���� ���� 
        	
        	pstmt.setInt(1,startRow-1);//?��ä��� ,0���� ����
        	pstmt.setInt(2,pageSize);//?��ä��� 
        	
        	rs=pstmt.executeQuery();//���� ���� 
        	
        	if(rs.next()){
        		list=new ArrayList<BoardDTO>();
        		do{
        			BoardDTO dto=new BoardDTO();//��ü ����
        			
        			dto.setNum(rs.getInt("num"));
        			dto.setWriter(rs.getString("writer"));
        			dto.setSubject(rs.getString("subject"));
        			dto.setPw(rs.getString("pw"));
        			dto.setRegdate(rs.getTimestamp("regdate"));//*****
        			//����� �ú���: getTimestamp
        			
        			dto.setReadcount(rs.getInt("readcount"));//��Ƚ�� 
        			dto.setRef(rs.getInt("ref"));//�� �׷�
        			dto.setRe_step(rs.getInt("re_step"));//�� ����
        			dto.setRe_level(rs.getInt("re_level"));//�� ����
        			
        			dto.setContent(rs.getString("content"));
        			dto.setIp(rs.getString("ip"));
        			
        			list.add(dto);/////////******** ������ ����� ����
        			//vec.add(dto); ������ ����� �ִ� (ä�ÿ��� ���)
        			
        		}while(rs.next());
        	}//if
        	
        }catch(Exception ex1){
        	System.out.println("getList() ���� :"+ex1);
        }finally{
        	try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
        }//finally-end
		
		return list;
	}//getList() - end
	
	//-----------------------
	//�۳��뺸��
	//-----------------------
	public BoardDTO getBoard(int num) throws Exception{
		BoardDTO dto=null;
		try{
			//ó��
			con=getCon();
			
			//��Ƚ�� ����
			sql="update board set readcount=readcount+1 where num=?";
			pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.executeUpdate();//���� ���� 
			//-----------------------------
			
			//�� ���뺸��
			pstmt=con.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();//���� ����
			
			if(rs.next()){
				//rs������ dto�ְ� dto�� Ŭ���̾�Ʈ�� ������ 
				dto=new BoardDTO();//��ü����
				dto.setNum(rs.getInt("num"));
				dto.setWriter(rs.getString("writer"));
				dto.setSubject(rs.getString("subject"));
				dto.setPw(rs.getString("pw"));
				dto.setRegdate(rs.getTimestamp("regdate"));
				
				dto.setReadcount(rs.getInt("readcount"));
				dto.setRef(rs.getInt("ref"));
				dto.setRe_step(rs.getInt("re_step"));
				dto.setRe_level(rs.getInt("re_level"));
				
				dto.setContent(rs.getString("content"));
				dto.setIp(rs.getString("ip"));
				
			}//if-end
		}catch(Exception ex1){
        	System.out.println("getBoard() ���� :"+ex1);
        }finally{
        	try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
        }//finally-end
		
		return dto;
	}//getBoard() end
	
	//-----------------------------
	// �ۼ��� Ŭ���̾�Ʈ�� ���� ������ 
	//-----------------------------
	public BoardDTO getUpdate(int num) throws Exception{
		BoardDTO dto=null;
		
		try{
		con=getCon();
		//�� ���뺸��
		pstmt=con.prepareStatement("select * from board where num=?");
		pstmt.setInt(1, num);
		rs=pstmt.executeQuery();//���� ����
		
		if(rs.next()){
			//rs������ dto�ְ� dto�� Ŭ���̾�Ʈ�� ������ 
			dto=new BoardDTO();//��ü����
			dto.setNum(rs.getInt("num"));
			dto.setWriter(rs.getString("writer"));
			dto.setSubject(rs.getString("subject"));
			dto.setPw(rs.getString("pw"));
			dto.setRegdate(rs.getTimestamp("regdate"));
			
			dto.setReadcount(rs.getInt("readcount"));
			dto.setRef(rs.getInt("ref"));
			dto.setRe_step(rs.getInt("re_step"));
			dto.setRe_level(rs.getInt("re_level"));
			
			dto.setContent(rs.getString("content"));
			dto.setIp(rs.getString("ip"));
			
		}//if-end
	}catch(Exception ex1){
    	System.out.println("getBoard() ���� :"+ex1);
    }finally{
    	try{
			if(rs!=null){rs.close();}
			if(pstmt!=null){pstmt.close();}
			if(con!=null){con.close();}
		}catch(Exception ex2){}
    }//finally-end
	
	return dto;
	}//getUpdate() end
	
	//-------------------
	//DB �ۼ���
	//-------------------
	public int updateBoard(BoardDTO dto) throws Exception{
		String dbPw="";
		int x=-1;
		try{
			//ó��
			con=getCon();
			pstmt=con.prepareStatement("select pw from board where num=?");
			pstmt.setInt(1, dto.getNum());//?�� ä��� 
			rs=pstmt.executeQuery();//���� ���� 
			
			if(rs.next()){
				dbPw=rs.getString("pw");
				String pw=dto.getPw();
				
				if(dbPw.equals(pw)){//��ȣ�� ��ġ�ϸ� �� ����
				  sql="update board set writer=?,subject=?,content=? where num=?";
				  pstmt=con.prepareStatement(sql);//������ ���� ���� 
				  
				  pstmt.setString(1, dto.getWriter());
				  pstmt.setString(2, dto.getSubject());
				  pstmt.setString(3, dto.getContent());
				  pstmt.setInt(4, dto.getNum());
				  
				  pstmt.executeUpdate();//���� ����
				  x=1;//�������� ����
				}else{
					//��ȣ�� Ʋ���� 
					x=0;
				}
			}//if
			
			
		}catch(Exception ex1){
	    	System.out.println("updateBoard() ���� :"+ex1);
	    }finally{
	    	try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
	    }//finally-end
		
		return x;
	}//updateBoard() end
	
	//--------------------
	// �ۻ���
	//--------------------
	public int deleteBoard(int num,String pw) throws Exception{
		String dbPw="";
		int x=-1;
		
		try{
			//ó��
			con=getCon();
			pstmt=con.prepareStatement("select pw from board where num=?");
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();//���� ���� 
			if(rs.next()){
				dbPw=rs.getString("pw");
				if(dbPw.equals(pw)){//��ȣ�� ��ġ�ϸ� �� ���� 
					pstmt=con.prepareStatement("delete from board where num=?");
					
					pstmt.setInt(1, num);
					pstmt.executeUpdate();//���� ����
					x=1;//���������� ���� 
					
				}else{//��ȣ�� ��ġ���� ������
					x=0;//���� ����
				}
			}
			
		}catch(Exception ex1){
			System.out.println("deleteBoard() ���� :"+ex1);
		}finally{
	    	try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
	    }//finally-end
		
		return x;
	}//deleteBoard() -end
	
}//class end
