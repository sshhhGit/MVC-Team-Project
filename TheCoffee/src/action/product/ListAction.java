package action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import product.*;//DTO, DAO
import command.CommandAction;
public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int count = 0;//�� ��ǰ ���� ���� ����

		List productList = null;
		ProductDAO dao = ProductDAO.getInstance();//dao ��ü ���
		count = dao.getProductCount();//��ü �� ���� ���
		
		productList = dao.getGoodList();//daoȣ�� �� ����ޱ�
		
		//JSP���� ����� ������ setAttribute()�� �ֱ�
		request.setAttribute("count", new Integer(count));//�ѱ� ����
		
		request.setAttribute("productList", productList);//**** ������
		
		
		return "/product/list.jsp";//�並 ����, ��Ʈ�ѷ��� �޾Ƽ� ������ ���ش� 
	}//requestPro()-end

}
