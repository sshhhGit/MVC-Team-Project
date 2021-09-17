package action.board;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;

public class WriteFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		//원글 답글 처리
		int num=0, ref=1, re_step=0, re_level=0;
		
		try{
			if(request.getParameter("num") != null){//답글
				num=Integer.parseInt(request.getParameter("num"));
				ref=Integer.parseInt(request.getParameter("ref"));
				re_step=Integer.parseInt(request.getParameter("re_step"));
				re_level=Integer.parseInt(request.getParameter("re_level"));
			}
		}catch(Exception ex){
			System.out.println("WriteFormAction 예외 :"+ex);
		}
		
		//뷰에서 사용할 속성 
		request.setAttribute("num", num);
		request.setAttribute("ref", ref);
		request.setAttribute("re_step", re_step);
		request.setAttribute("re_level", re_level);
		
		return "/board/writeForm.jsp";//뷰리턴
	}//requestPro()-end

}//class-end
