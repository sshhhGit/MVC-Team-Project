package action.qna;

import java.util.*;

import mysqlboard.*;//DRO,DAO

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import command.CommandAction;

//JSP의 로직부분을 여기에 코딩한다
public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int count = -1;
		List qnaList = null;
		
		QnaDAO dao = QnaDAO.getInstance();// dao객체얻기
		count = dao.getQnaCount();// 전체 글갯수 얻기

		if (count > 0) { // 글이 있으면
			qnaList = dao.getList();// dao메서드 호출하고 결과 받는다
		} else {// 글이 없을때
			qnaList = Collections.EMPTY_LIST;// 비어있다는 뜻
		}// else end

		// /JSP에서 사용할 값들을 setAttribute()
		request.setAttribute("count", new Integer(count));// 총글 갯수
		request.setAttribute("qnaList", qnaList);// ***데이터
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		} // if-end	
		
		int pageSize = 10;//한 페이지 당 글 갯수
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage-1)*pageSize+1;//한 페이지의 시작 글번호
		int endRow = currentPage*pageSize;//한페이지의 마지막 글번호
		
		
		int count = 0;//총 글갯수 넣을 변수
		int pageBlock = 10;//블럭당 페이지 수
		
		List qnaList = null;
		QnaDAO dao = QnaDAO.getInstance();//dao객체얻기
		count = dao.getQnaCount();//전체 글갯수 얻기
		
		
		if(count > 0){ //글이 있으면 
			qnaList = dao.getList(startRow, pageSize);//dao메서드 호출하고 결과 받는다
		}else{//글이 없을때
			qnaList = Collections.EMPTY_LIST;//비어있다는 뜻
		}//else end
		
		
		int pageCount = count/pageSize+(count%pageSize==0?0:1);//총 페이지 수
		//                 몫                       나머지
		
		int startPage = (currentPage/10)*10+1;//시작페이지
		int endPage = startPage+pageBlock-1;//end 페이지
		
		///JSP에서 사용할 값들을 setAttribute()
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("pageCount", pageCount);
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.setAttribute("count", new Integer(count));//총글 갯수 
		request.setAttribute("pageSize", new Integer(pageSize));//10
		request.setAttribute("number", new Integer(number));//출력할 글번호
		
		request.setAttribute("qnaList", qnaList);//***데이터
		*/
		
		return "/qna/list.jsp";//뷰를 리턴, 컨트롤러가 받아서 포워딩 해준다 
	}//requestPro()-end


	
}//class-end
