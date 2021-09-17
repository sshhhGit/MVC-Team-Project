package action.notice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import notice.NoticeDTO;
import command.CommandAction;
import mysqlboard.*;

public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String pageNo = request.getParameter("pageNo");
		
		NoticeDTO dto = new NoticeDTO();//객체생성
		
		//클라이언트가 보내준 데이터를 dto에 저장
		dto.setNotice_no(Integer.parseInt(request.getParameter("notice_no")));
		dto.setNotice_title(request.getParameter("notice_title"));
		dto.setNotice_content(request.getParameter("notice_content"));
		//dto.setNotice_pw(request.getParameter("notice_pw"));
		
		NoticeDAO dao = NoticeDAO.getInstance();//dao객체얻기
		dao.updateNotice(dto);
		
		request.setAttribute("pageNo", pageNo);
		
		return "/notice/updatePro.jsp";//뷰 리턴
	}//requestPro()-end

}//class-end
