package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;
import mysqlboard.*;//DAO,DTO

//DAO�޼��� ȣ�� 
public class WriteProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		request.setCharacterEncoding("utf-8");
		BoardDTO dto=new BoardDTO();//��ü����
		
		//Ŭ���̾�Ʈ���� ������ �����͸� �޾Ƽ� dto�� ����
		dto.setNum(Integer.parseInt(request.getParameter("num")));
		dto.setRef(Integer.parseInt(request.getParameter("ref")));
		dto.setRe_step(Integer.parseInt(request.getParameter("re_step")));
		dto.setRe_level(Integer.parseInt(request.getParameter("re_level")));
		
		dto.setWriter(request.getParameter("writer"));
		dto.setSubject(request.getParameter("subject"));
		dto.setContent(request.getParameter("content"));
		dto.setPw(request.getParameter("pw"));
		
		dto.setIp(request.getRemoteAddr());//IP
		
		BoardDAO dao=BoardDAO.getInstance();//dao���� ���
		dao.insertBoard(dto);//dao�޼��� ȣ�� , dto�� �ѱ��
		
		return "/board/writePro.jsp";//�丮��
	}//requestPro()-end

}//class-end
