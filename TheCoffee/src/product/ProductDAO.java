package product;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.sql.*;//DataSource

import java.util.*;

import javax.naming.*;//lookup

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import product.ProductDTO;

import java.io.*;//�׸����� �輼�� ���

//DAO : DBó��(�����Ͻ� ����)
public class ProductDAO {
	Connection con = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String sql = "";
	
	//�̱��� ��ü ���(�޸� ���� ȿ��)
	private static ProductDAO dao = new ProductDAO();//��ü ����
	
	private ProductDAO(){}//������, private��  �ܺ� ���� �Ұ� 
	
	public static ProductDAO getInstance(){
		return dao;
	}
	
	//Ŀ�ؼ� �����ϱ�
	private Connection getConnection() throws Exception{
		Context ct = new InitialContext();
		DataSource ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		
		return ds.getConnection();
	}//getCon()-end
	
	//-----------------------------------
	//��ǰ ���� ���
	//-----------------------------------
	public int getProductCount() throws Exception{
		int cnt = -1;//�ʱ�ȭ
		
		try{
			//ó��
			con = getConnection();//Ŀ�ؼ� ���
			pstmt = con.prepareStatement("select count(*) from product");
			
			rs = pstmt.executeQuery();//���� ����
			
			if(rs.next()){
				cnt = rs.getInt(1);//1�� �ʵ� ��ȣ, �� ����
				//System.out.println("cnt:" + cnt);
			}
		}catch(Exception ex1){
			System.out.println("getProductCount()����: "+ex1);
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
	//��ǰ ��ϸ���Ʈ
	//-----------------------------------
	public List<ProductDTO> getGoodList() throws Exception{
		//String sql="";
		//Connection con=null;
		//Statement stmt=null;
		//ResultSet rs=null;
		List<ProductDTO> list=new ArrayList<ProductDTO>();//��ü����

		try{
			con=getConnection();
			sql="select * from product";
			stmt=con.createStatement();//Statement ����
			rs=stmt.executeQuery(sql);//���� ���� 

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
			System.out.println("getGoodList()���� :"+ex1);
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
	//��ǰ �󼼺���
	//-----------------------------------
	//@SuppressWarnings("null")
	public ProductDTO getDetail(int pro_no) throws Exception{
		
		ProductDTO dto=null;
		
		try{
			//ó��
			con = getConnection();
			
			//��ǰ ���뺸��
			pstmt=con.prepareStatement("select * from product where pro_no=?");
			pstmt.setInt(1, pro_no);
			
			rs=pstmt.executeQuery();//���� ����

			if(rs.next()){
				dto=new ProductDTO();
				
				//rs������ dto�ְ� dto�� Ŭ���̾�Ʈ�� ������ 
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
			System.out.println("getDetail() ���� :"+ex1);
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
	// ��ǰ��� , insert, �׸����� ���ε�  
	//-------------------------------------------------------------
	public boolean insertProduct(ProductDTO dto,HttpServletRequest request){
		boolean re=false;
		//Connection con=null;
		//PreparedStatement pstmt=null;

		//JSP��� :������� ��� , �׸� ��� �ϱ� ���ؼ� , �ڡڡڡڡڡڡڡڡڱ׸� ����ϱ� ���ؼ�
		//<%= config.getServletContext().getRealPath("/")%> : �̰��� ����ϼ��� //�Ʒ��� ��� ������ �̰� �� ��Ȯ
		//<%= application.getRealPath("/")%>

		//�������� ���� ��� ��� 
		//request.getRealPath("/");
		//request.getServletConext().getRealPath("/"); //�̰��� ����ϼ���

		//�׸��� ������ ��� �Ҷ� �ڡڡڡڡڡڡڡڡ�
		//<%= request.getContextPath()%>
		//������Ʈ�̸�=���ؽ�Ʈ�̸��̴� 

		try{
			con=getConnection();
			 
			String sql="";
			sql="insert into product(name_ko,name_eng,subject,content,image,hc_code,event_code) values(?,?,?,?,?,?,?)";

			pstmt=con.prepareStatement(sql);//������ ���� ����

			//�ߡߡߡߡߡߡߡߡߡߡߡߡߡߡߡ� �߿�
			//Ŭ���̾�Ʈ ������ ������ request�� �ϸ� �ȵȴ�
			//MultipartRequest mul �� �����͸� �޾ƾ� �Ѵ�

			//?�� ä���
			pstmt.setString(1, dto.getName_ko());
			pstmt.setString(2, dto.getName_eng());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getImage());
			pstmt.setString(6, dto.getHc_code());
			pstmt.setString(7, dto.getEvent_code());

			int count = pstmt.executeUpdate();//���� ����, insert 1�� �Ǹ�

			if(count==1){
				re=true;
			}

		}catch(Exception ex1){
			System.out.println("insertProduct()���� :"+ex1);
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
