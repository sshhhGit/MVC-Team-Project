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
		
		int count = 0;//총 상품 갯수 넣을 변수

		List productList = null;
		ProductDAO dao = ProductDAO.getInstance();//dao 객체 얻기
		count = dao.getProductCount();//전체 글 갯수 얻기
		
		productList = dao.getGoodList();//dao호출 및 결과받기
		
		//JSP에서 사용할 값들을 setAttribute()로 넣기
		request.setAttribute("count", new Integer(count));//총글 갯수
		
		request.setAttribute("productList", productList);//**** 데이터
		
		
		return "/product/list.jsp";//뷰를 리턴, 컨트롤러가 받아서 포워딩 해준다 
	}//requestPro()-end

}
