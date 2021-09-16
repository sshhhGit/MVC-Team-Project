package action.faq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import faq.FaqDAO;
import faq.FaqDTO;

public class ListAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		List<FaqDTO> faqList = null;
		
		String category = request.getParameter("category");
		String searchText = request.getParameter("searchText");
		
		if(category == null){
			category = "";
		}
		
		if(searchText == null){
			searchText = "";
		}

		int count = 0; //총글갯수
		FaqDAO dao = FaqDAO.getInstance();
		count = dao.getFaqCount(category, searchText);
		count = 2;				
		if(count > 0){//글이 있으면
			faqList = new ArrayList<FaqDTO>();
			faqList = dao.getList(category, searchText);			
		} else {//글이 없으면
			faqList = Collections.EMPTY_LIST;
		}
		
		request.setAttribute("count", count);
		request.setAttribute("category", category);
		request.setAttribute("searchText", searchText);
		request.setAttribute("faqList", faqList);		
		
		return "/faq/list.jsp";
		
	}//requestPro()-end

}//class-end
