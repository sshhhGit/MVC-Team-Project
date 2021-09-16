package action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import product.*;
import command.CommandAction;
public class ProductDetailAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int pro_no=Integer.parseInt(request.getParameter("pro_no"));
		
		//System.out.println("ProductDetailAction pro_no:"+request.getParameter("pro_no"));
		ProductDAO dao = ProductDAO.getInstance();//dao객체 얻기
		ProductDTO dto = dao.getDetail(pro_no);//dao메서드 호촐, dto를 받는다.
		
		//뷰에서 사용할 속성 지정
		request.setAttribute("dto", dto);
		
		return "/product/productDetail.jsp";
	}

}
