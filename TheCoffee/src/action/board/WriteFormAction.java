package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;

public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		//���� ��� ó��
		int num=0, ref=1, re_step=0, re_level=0;
		
		try{
			if(request.getParameter("num") != null){//���
				num=Integer.parseInt(request.getParameter("num"));
				ref=Integer.parseInt(request.getParameter("ref"));
				re_step=Integer.parseInt(request.getParameter("re_step"));
				re_level=Integer.parseInt(request.getParameter("re_level"));
			}
		}catch(Exception ex){
			System.out.println("WriteFormAction ���� :"+ex);
		}
		
		//�信�� ����� �Ӽ� 
		request.setAttribute("num", num);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);
		
		return "/board/writeForm.jsp";//�丮��
	}//requestPro()-end

}//class-end
