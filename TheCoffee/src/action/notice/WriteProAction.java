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
		NoticeDTO dto = new NoticeDTO();//��ü����
		
		//Ŭ���̾�Ʈ���� ������ �����͸� dto�� ����
		
		dto.setNotice_title(request.getParameter("notice_title"));
		dto.setNotice_content(request.getParameter("notice_content"));
		
		NoticeDAO dao = NoticeDAO.getInstance();//dao��ü���
		dao.insertNotice(dto);//dao�޼��� ȣ��, dto�� �ѱ�
		
		return "/notice/writePro.jsp";
	}

}
