package action.notice;

import java.util.*;
import notice.*;//DAO, DTO
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;

//JSP의 로직
public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		String pageNo = request.getParameter("pageNo");
		
		if(pageNo == null){
			pageNo = "1";
		}//if-end
		
		int pageSize = 10;// 한페이지당 글 개수
		int currentPage = Integer.parseInt(pageNo);
		
		int startRow = (currentPage-1)*pageSize+1;// 한페이지 시작 번호
		int endRow = currentPage*pageSize;// 한 페이지 끝 글번호
		
		int count = 0;//총 글개수에 넣을 변수
		int number = 0;//글번호 처리를 위한변수
		int pageBlock = 10;//블럭당 페이지 수
		
		List noticeList = null;
		NoticeDAO dao = NoticeDAO.getInstance();//dao객체 얻기
		count = dao.getNoticeCount();//전체 글 개수 얻기
		
		if(count>0){//글이 있으면
			noticeList = dao.getList(startRow, pageSize);//dao메서드 호출하고 결과받기
		}else{//글이 없을때
			noticeList = Collections.EMPTY_LIST;//비어 있다는 뜻
		}//else-end
		
		number = count-(currentPage-1)*pageSize;//출력할 글번호 역순
		
		int pageCount = count/pageSize+(count%pageSize == 0?0:1);//총 페이지 수
		//                   몫                                             나머지
		
		int startPage = (currentPage/10)*10+1; // 시작페이지
		int endPage = startPage+pageBlock-1; // end 페이지
		
		//JSP에서 사용할 값들을 setAttribute()
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("pageCount", pageCount);
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.setAttribute("count", new Integer(count)); //총 글 개수
		request.setAttribute("pageSize", new Integer(pageSize)); //10
		request.setAttribute("number", new Integer(number)); //출력할 글번호

		request.setAttribute("noticeList", noticeList);
		
		return "/notice/list.jsp";//뷰 리턴, 컨트롤러가 받아서 포워딩 해줌
		
	}//requestProp-end

}//class-end
