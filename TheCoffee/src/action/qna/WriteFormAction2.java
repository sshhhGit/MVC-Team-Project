package action.qna;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class WriteFormAction2 implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		 
		int num = 0;
		
		try {
			if (request.getParameter("num") != null) { 
				num = Integer.parseInt(request.getParameter("num"));
			}
		} catch (Exception e) {
			System.out.println("WriteFormAction2 클래스에서 예외 발생 : " + e);
		}
		
		// 뷰에서 사용할 속성 설정
		request.setAttribute("num", num);
		
		return "/qna/writeForm2.jsp"; // 뷰 리턴
	}//requestPro()-end
	
	

}//class-end
