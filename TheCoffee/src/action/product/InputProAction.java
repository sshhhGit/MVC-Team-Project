package action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import java.util.*;

import product.*;
import command.CommandAction;
public class InputProAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
		
		request.setCharacterEncoding("UTF-8");
		
		//inputForm.jsp���� ������ �����͸� �޾Ƽ� mul�� ����		
		String real_path=request.getServletContext().getRealPath("/");//���� ��� ���
		String upload_path=real_path+"/imgs/";//��ǰ ��� �ϱ� ���� ���
		System.out.println(real_path);
		MultipartRequest mul=
				new MultipartRequest(request,upload_path,
						5*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
		
		ProductDTO dto = new ProductDTO();
		dto.setName_ko(mul.getParameter("name_ko"));
		dto.setName_eng(mul.getParameter("name_eng"));
		dto.setSubject(mul.getParameter("subject"));
		dto.setContent(mul.getParameter("content"));
		dto.setHc_code(mul.getParameter("hc_code"));
		dto.setEvent_code(mul.getParameter("event_code"));
		
		if(mul.getFilesystemName("image") != null){//�̹��� ������ ������
			//pstmt.setString(6, mul.getFilesystemName("image"));
			dto.setImage(mul.getFilesystemName("image"));
			
		}else{//�̹��� ������ ������
//			pstmt.setString(6, "ready.gif");//�⺻�̹��� �־��ֱ�
			dto.setImage("ready.gif");
		}

		ProductDAO dao = ProductDAO.getInstance();
		dao.insertProduct(dto, request);
		
		return "/product/inputPro.jsp";//�丮��
	}

}
