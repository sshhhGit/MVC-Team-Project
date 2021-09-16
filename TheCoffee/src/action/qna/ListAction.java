package action.qna;

import java.util.*;

import mysqlboard.*;//DRO,DAO

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import qna.QnaDAO;
import command.CommandAction;

//JSP�� �����κ��� ���⿡ �ڵ��Ѵ�
public class ListAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		int count = -1;
		List qnaList = null;
		
		QnaDAO dao = QnaDAO.getInstance();// dao��ü���
		count = dao.getQnaCount();// ��ü �۰��� ���

		if (count > 0) { // ���� ������
			qnaList = dao.getList();// dao�޼��� ȣ���ϰ� ��� �޴´�
		} else {// ���� ������
			qnaList = Collections.EMPTY_LIST;// ����ִٴ� ��
		}// else end

		// /JSP���� ����� ������ setAttribute()
		request.setAttribute("count", new Integer(count));// �ѱ� ����
		request.setAttribute("qnaList", qnaList);// ***������
		
		
		
		
		
		
		
		
		
		
		
		
		/*
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
		} // if-end	
		
		int pageSize = 10;//�� ������ �� �� ����
		int currentPage = Integer.parseInt(pageNum);
		
		int startRow = (currentPage-1)*pageSize+1;//�� �������� ���� �۹�ȣ
		int endRow = currentPage*pageSize;//���������� ������ �۹�ȣ
		
		
		int count = 0;//�� �۰��� ���� ����
		int pageBlock = 10;//���� ������ ��
		
		List qnaList = null;
		QnaDAO dao = QnaDAO.getInstance();//dao��ü���
		count = dao.getQnaCount();//��ü �۰��� ���
		
		
		if(count > 0){ //���� ������ 
			qnaList = dao.getList(startRow, pageSize);//dao�޼��� ȣ���ϰ� ��� �޴´�
		}else{//���� ������
			qnaList = Collections.EMPTY_LIST;//����ִٴ� ��
		}//else end
		
		
		int pageCount = count/pageSize+(count%pageSize==0?0:1);//�� ������ ��
		//                 ��                       ������
		
		int startPage = (currentPage/10)*10+1;//����������
		int endPage = startPage+pageBlock-1;//end ������
		
		///JSP���� ����� ������ setAttribute()
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("currentPage", currentPage);
		
		request.setAttribute("startRow", startRow);
		request.setAttribute("endRow", endRow);
		
		request.setAttribute("pageBlock", pageBlock);
		request.setAttribute("pageCount", pageCount);
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		
		request.setAttribute("count", new Integer(count));//�ѱ� ���� 
		request.setAttribute("pageSize", new Integer(pageSize));//10
		request.setAttribute("number", new Integer(number));//����� �۹�ȣ
		
		request.setAttribute("qnaList", qnaList);//***������
		*/
		
		return "/qna/list.jsp";//�並 ����, ��Ʈ�ѷ��� �޾Ƽ� ������ ���ش� 
	}//requestPro()-end


	
}//class-end
