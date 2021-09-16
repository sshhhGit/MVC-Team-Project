package product;

import java.sql.*;

import javax.sql.*;//DataSource

import java.util.*;

import javax.naming.*;//lookup


import product.ProductDTO;

import java.io.*;//그림파일 삭세시 사용

//DAO : DB처리(비지니스 로직)
public class ProductDAO {
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = "";
	
	//싱글톤 객체 사용(메모리 절약 효과)
	private static ProductDAO dao = new ProductDAO();//객체 생성
	
	private ProductDAO(){}//생성자, private로  외부 접근 불가 
	
	public static ProductDAO getInstance(){
		return dao;
	}
	
	//커넥션 연결하기
	private Connection getConnection() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		
		return ds.getConnection();
	}//getCon()-end
	
	//-----------------------------------
	//상품 갯수 얻기
	//-----------------------------------
	public int getProductCount() throws Exception{
		int cnt = -1;//초기화
		
		try{
			//처리
			con = getConnection();//커넥션 얻기
			pstmt = con.prepareStatement("select count(*) from product");
			
			rs = pstmt.executeQuery();//쿼리 수행
			
			if(rs.next()){
				cnt = rs.getInt(1);//1은 필드 번호, 글 갯수
				//System.out.println("cnt:" + cnt);
			}
		}catch(Exception ex1){
			System.out.println("getProductCount()예외: "+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally end
		
		return cnt;
		
	}
	
	//-----------------------------------
	//상품 목록리스트
	//-----------------------------------
	public List<ProductDTO> getGoodList() throws Exception{
		//String sql="";
		//Connection con=null;
		//Statement stmt=null;
		//ResultSet rs=null;
		List<ProductDTO> list=new ArrayList<ProductDTO>();//객체생성

		try{
			con=getConnection();
			sql="select * from product";
			stmt=con.createStatement();//Statement 생성
			rs=stmt.executeQuery(sql);//쿼리 수행 

			while(rs.next()){
				ProductDTO dto=new ProductDTO();

				dto.setPro_no(rs.getInt("pro_no"));
				dto.setName_ko(rs.getString("name_ko"));
				dto.setName_eng(rs.getString("name_eng"));
				
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setImage(rs.getString("image"));

				dto.setHc_code(rs.getString("hc_code"));
				dto.setEvent_code(rs.getString("event_code"));
				
				list.add(dto);//*******
			}//while-end

		}catch(Exception ex1){
			System.out.println("getGoodList()예외 :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(stmt!=null){stmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return list;
	}//getGoodList()-end
	
	//-----------------------------------
	//상품 상세보기
	//-----------------------------------
	//@SuppressWarnings("null")
	public ProductDTO getDetail(int pro_no) throws Exception{
		
		ProductDTO dto=null;
		
		try{
			//처리
			con = getConnection();
			
			//상품 내용보기
			pstmt=con.prepareStatement("select * from product where pro_no=?");
			pstmt.setInt(1, pro_no);
			
			rs=pstmt.executeQuery();//쿼리 수행

			if(rs.next()){
				dto=new ProductDTO();
				
				//rs내용을 dto넣고 dto를 클라이언트로 보낸다 
				dto.setPro_no(rs.getInt("pro_no"));
				dto.setName_ko(rs.getString("name_ko"));
				dto.setName_eng(rs.getString("name_eng"));
				
				dto.setSubject(rs.getString("subject"));
				
				dto.setContent(rs.getString("content"));
				dto.setImage(rs.getString("image"));

				dto.setHc_code(rs.getString("hc_code"));
				dto.setEvent_code(rs.getString("event_code"));

			}//if-end
			
		}catch(Exception ex1){
			System.out.println("getDetail() 예외 :"+ex1);
		}finally{
			try{
				if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return dto;
	}//getDetail() end

}//class-end
