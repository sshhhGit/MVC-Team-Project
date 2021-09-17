package action.notice;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.NoticeDAO;
import notice.NoticeDTO;
import command.CommandAction;

public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		request.setCharacterEncoding("UTF-8");
		NoticeDTO dto = new NoticeDTO();//객체생성
		
		//클라이언트에서 보내준 데이터를 dto에 저장
		
		dto.setNotice_title(request.getParameter("notice_title"));
		dto.setNotice_content(request.getParameter("notice_content"));
		
		NoticeDAO dao = NoticeDAO.getInstance();//dao객체얻기
		dao.insertNotice(dto);//dao메서드 호출, dto를 넘김
		
		return "/notice/writePro.jsp";
	}

}
