package action.notice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import command.CommandAction;
import mysqlboard.*;//DAO,DTO

public class DeleteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		String pageNo = request.getParameter("pageNo");
		int notice_no = Integer.parseInt(request.getParameter("notice_no"));
		
		NoticeDAO dao = NoticeDAO.getInstance();//dao객체얻기
		dao.deleteNotice(notice_no);//삭제
		
		//뷰에서 사용할 속성 지정
		request.setAttribute("pageNo", pageNo);
		
		return "/notice/deletePro.jsp";
	}//requestPro()-end

}//class-end
