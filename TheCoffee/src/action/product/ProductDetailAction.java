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
		ProductDAO dao = ProductDAO.getInstance();//dao��ü ���
		ProductDTO dto = dao.getDetail(pro_no);//dao�޼��� ȣ��, dto�� �޴´�.
		
		//�信�� ����� �Ӽ� ����
		request.setAttribute("dto", dto);
		
		return "/product/productDetail.jsp";
	}

}
