package action.notice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import notice.NoticeDTO;
import mysqlboard.*;
import command.CommandAction;

public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int notice_no = Integer.parseInt(request.getParameter("notice_no"));
		String pageNo = request.getParameter("pageNo");
		
		NoticeDAO dao = NoticeDAO.getInstance();//객체얻기
		NoticeDTO dto = dao.getUpdate(notice_no);//dao호출, dto받는다.
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("notice_no", new Integer(notice_no));
		
		//request.setAttribute("key", value);
		//request.setAttribute("문자열", 객체);
		
		
		return "/notice/updateForm.jsp";//뷰 리턴
		
		
	}//requestPro()-end

}//class-end
