package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;
import mysqlboard.*;//DAO DTO

public class UpdateFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		int num=Integer.parseInt(request.getParameter("num"));
		String pageNum=request.getParameter("pageNum");
		
		BoardDAO dao=BoardDAO.getInstance();//dao��ü���
		BoardDTO dto=dao.getUpdate(num);//daoȣ��,dto�޴´�
		
		request.setAttribute("dto", dto);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", new Integer(num));
		//request.setAttribute(key, value);
		//request.setAttribute("���ڿ�", ��ü);
		
 		return "/board/updateForm.jsp";//�丮��
		
	}//requestPro()-end

}//class-end
