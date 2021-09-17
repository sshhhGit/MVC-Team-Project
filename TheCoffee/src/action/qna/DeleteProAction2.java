package action.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import command.CommandAction;
import mysqlboard.*; // DAO, DTO

public class DeleteProAction2 implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
//		String pageNum = request.getParameter("pageNum");
		int num = Integer.parseInt(request.getParameter("num"));
		String admin_id = request.getParameter("admin_id");
		String pw = request.getParameter("pw");
		
		System.out.println("deletePro액션2| num : " + num);
		System.out.println("deletePro액션2| admin_id : " + admin_id);
		System.out.println("deletePro액션2| pw : " + pw);
		
		
		QnaDAO dao = QnaDAO.getInstance(); // dao 객체 얻기
		int check = dao.deleteQna_A(num, admin_id, pw); // dao 메소드 호출, 정수값 받기
		// x==1 정상적으로 삭제, x==0 암호틀림 삭제실패
		
		// 뷰에서 사용할 속성 지정
//		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", check);
		
		
		
		return "/qna/deletePro2.jsp";
	} // requestPro()-end

} // class-end
