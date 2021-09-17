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
		
		NoticeDAO dao = NoticeDAO.getInstance();//dao��ü���
		NoticeDTO dto = dao.getNotice(notice_no);//dao�޼��� ȣ��, dto�� �޴´�
		
		//�信�� ����� ���� ����
		request.setAttribute("notice_no", new Integer(notice_no));
		request.setAttribute("pageNo", pageNo);
		
		request.setAttribute("dto", dto);
		
		return "/notice/content.jsp";//�� ����
	}

}
