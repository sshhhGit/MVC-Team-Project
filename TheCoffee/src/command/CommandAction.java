package command;
import javax.servlet.http.*;

public interface CommandAction {

	//메서드 선언만 한다
	public String requestPro(HttpServletRequest request,
			HttpServletResponse response) throws Throwable;
}//interface-end
