package action.notice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import notice.NoticeDTO;
import command.CommandAction;
import mysqlboard.*;//DAO,DTO

public class ContentAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int notice_no = Integer.parseInt(request.getParameter("notice_no"));
		String pageNo = request.getParameter("pageNo");
		
		NoticeDAO dao = NoticeDAO.getInstance();//dao객체얻기
		NoticeDTO dto = dao.getNotice(notice_no);//dao메서드 호출, dto를 받는다
		
		//뷰에서 사용할 속정 지정
		request.setAttribute("notice_no", new Integer(notice_no));
		request.setAttribute("pageNo", pageNo);
		
		request.setAttribute("dto", dto);
		
		return "/notice/content.jsp";//뷰 리턴
	}

}
