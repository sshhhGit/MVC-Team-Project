package command;
import javax.servlet.http.*;

public interface CommandAction {

	//�޼��� ���� �Ѵ�
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable;
}//interface-end
