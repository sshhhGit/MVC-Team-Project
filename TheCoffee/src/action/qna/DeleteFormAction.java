package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.CommandAction;

public class DeleteFormAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
//		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		String user_id = request.getParameter("user_id");
		
		System.out.println("deleteForm액션| num : " + num);
		System.out.println("deleteForm액션| user_id : " + user_id);
		
		// 뷰에서 사용할 속성 지정
//		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", new Integer(num));
		request.setAttribute("user_id", user_id);
		
		
		return "/qna/deleteForm.jsp"; // 뷰 리턴
	} // requestPro()-end

} // class-end
