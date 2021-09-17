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
		
		//inputForm.jsp에서 보내준 데이터를 받아서 mul에 저장		
		String real_path=request.getServletContext().getRealPath("/");//실제 경로 얻기
		String upload_path=real_path+"/imgs/";//상품 등록 하기 위한 경로
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
		
		if(mul.getFilesystemName("image") != null){//이미지 파일이 있으면
			//pstmt.setString(6, mul.getFilesystemName("image"));
			dto.setImage(mul.getFilesystemName("image"));
			
		}else{//이미지 파일이 없으면
//			pstmt.setString(6, "ready.gif");//기본이미지 넣어주기
			dto.setImage("ready.gif");
		}

		ProductDAO dao = ProductDAO.getInstance();
		dao.insertProduct(dto, request);
		
		return "/product/inputPro.jsp";//뷰리턴
	}

}
