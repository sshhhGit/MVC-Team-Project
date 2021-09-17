package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import mysqlboard.*;//DAO,DTO

public class UpdateProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		String pageNum=request.getParameter("pageNum");
		
		BoardDTO dto=new BoardDTO();//��ü����
		
		//Ŭ���̾�Ʈ�� ������ �����͸� dto�� ����
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setWriter(request.getParameter("writer"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setPw(request.getParameter("pw"));
		
		BoardDAO dao=BoardDAO.getInstance();//dao��ü���
		int x=dao.updateBoard(dto);
		//x=1 ���������� ����
		//x=0 ��ȣƲ��
		
		request.setAttribute("check", x);
		request.setAttribute("pageNum", pageNum);
		
		return "/board/updatePro.jsp";//�丮��
	}//requestPro()-end

}//class-end
