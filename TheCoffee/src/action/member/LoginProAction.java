package action.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.*;
import command.CommandAction;

public class LoginProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		//main.jsp에서 넘어온 데이터 받는다
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		MemberDAO dao = MemberDAO.getInstance(); //dao객체얻기
		int check = dao.loginCheck(id, pw);
		//check = 1 인증성공
		//      = 0 불일치
		//      = -1 없는ID
		
		request.setAttribute("check", check);
		request.setAttribute("id", id);
		
		// TODO Auto-generated method stub
		return "/member/loginPro.jsp"; //뷰 리턴
	}//requestPro()-end

}//class-end
