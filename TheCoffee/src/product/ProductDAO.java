package product;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.*;//DataSource

import java.util.*;

import javax.naming.*;//lookup

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
	
	//-------------------------------------------------------
	// 상품등록 , insert, 그림파일 업로드  
	//-------------------------------------------------------------
	public boolean insertProduct(ProductDTO dto,HttpServletRequest request){
		boolean re=false;
		//Connection con=null;
		//PreparedStatement pstmt=null;

		//JSP경우 :실제경로 얻기 , 그림 등록 하기 위해서 , ★★★★★★★★★그림 등록하기 우해서
		//<%= config.getServletContext().getRealPath("/")%> : 이것을 사용하세요 //아래와 결과 같으나 이게 더 정확
		//<%= application.getRealPath("/")%>

		//서블릿에서 실제 경로 얻기 
		//request.getRealPath("/");
		//request.getServletConext().getRealPath("/"); //이것을 사용하세요

		//그림을 웹으로 출력 할때 ★★★★★★★★★
		//<%= request.getContextPath()%>
		//프로젝트이름=컨텍스트이름이다 

		try{
			con=getConnection();
			 
			String sql="";
			sql="insert into product(name_ko,name_eng,subject,content,image,hc_code,event_code) values(?,?,?,?,?,?,?)";

			pstmt=con.prepareStatement(sql);//생성시 인자 들어간다

			//◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆◆ 중요
			//클라이언트 데이터 받을때 request로 하면 안된다
			//MultipartRequest mul 로 데이터를 받아야 한다

			//?값 채우기
			pstmt.setString(1, dto.getName_ko());
			pstmt.setString(2, dto.getName_eng());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getImage());
			pstmt.setString(6, dto.getHc_code());
			pstmt.setString(7, dto.getEvent_code());

			int count = pstmt.executeUpdate();//쿼리 수행, insert 1개 되면

			if(count==1){
				re=true;
			}

		}catch(Exception ex1){
			System.out.println("insertProduct()예외 :"+ex1);
		}finally{
			try{
				//if(rs!=null){rs.close();}
				if(pstmt!=null){pstmt.close();}
				if(con!=null){con.close();}
			}catch(Exception ex2){}
		}//finally-end

		return re;
	}//insertProduct()-end

}//class-end
