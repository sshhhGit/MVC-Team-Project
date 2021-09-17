package action.notice;

import java.util.*;
import notice.*;//DAO, DTO
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import command.CommandAction;

//JSP�� ����
public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {

		String pageNo = request.getParameter("pageNo");
		
		if(pageNo == null){
			pageNo = "1";
		}//if-end
		
		int pageSize = 10;// ���������� �� ����
		int currentPage = Integer.parseInt(pageNo);
		
		int startRow = (currentPage-1)*pageSize+1;// �������� ���� ��ȣ
		int endRow = currentPage*pageSize;// �� ������ �� �۹�ȣ
		
		int count = 0;//�� �۰����� ���� ����
		int number = 0;//�۹�ȣ ó���� ���Ѻ���
		int pageBlock = 10;//���� ������ ��
		
		List noticeList = null;
		NoticeDAO dao = NoticeDAO.getInstance();//dao��ü ���
		count = dao.getNoticeCount();//��ü �� ���� ���
		
		if(count>0){//���� ������
			noticeList = dao.getList(startRow, pageSize);//dao�޼��� ȣ���ϰ� ����ޱ�
		}else{//���� ������
			noticeList = Collections.EMPTY_LIST;//��� �ִٴ� ��
		}//else-end
		
		number = count-(currentPage-1)*pageSize;//����� �۹�ȣ ����
		
		int pageCount = count/pageSize+(count%pageSize == 0?0:1);//�� ������ ��
		//                   ��                                             ������
		
		int startPage = (currentPage/10)*10+1; // ����������
		int endPage = startPage+pageBlock-1; // end ������
		
		//JSP���� ����� ������ setAttribute()
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("pageCount", pageCount);
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.setAttribute("count", new Integer(count)); //�� �� ����
		request.setAttribute("pageSize", new Integer(pageSize)); //10
		request.setAttribute("number", new Integer(number)); //����� �۹�ȣ

		request.setAttribute("noticeList", noticeList);
		
		return "/notice/list.jsp";//�� ����, ��Ʈ�ѷ��� �޾Ƽ� ������ ����
		
	}//requestProp-end

}//class-end
