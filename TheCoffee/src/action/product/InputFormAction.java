package action.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import product.*;//DTO, DAO
import command.CommandAction;
public class InputFormAction implements CommandAction{

	@Override
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable {
	
		
		return "/product/inputForm.jsp";
	}

}
