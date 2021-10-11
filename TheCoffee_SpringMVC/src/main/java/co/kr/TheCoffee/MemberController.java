package co.kr.TheCoffee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;
import model.member.MemberDto;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import co.kr.TheCoffee.service.MailSendService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/member")
public class MemberController {

	@Autowired
	private SqlSession sqlSession;
	
    @Autowired
    private MailSendService mss;
	
	@RequestMapping("/insertForm.do")
	public String insertForm( ) {
		//return "/member/insertForm";
		return ".main.member.insertForm";
	}
	
	//id중복 체크
	@RequestMapping(value = "idCheck.do", method = RequestMethod.POST)
	public String idCheck(String id, Model model) {
		int check = -1; //변수
		MemberDto mDto = sqlSession.selectOne("member.selectOne", id);
		if(mDto==null) {
			check = 1; //사용가능
		}
		
		model.addAttribute("check", check);
		
		return "/member/idCheck"; //idCheck.jsp 뷰 리턴
	}
	
	//회원가입
	@RequestMapping(value = "insertPro.do", method = RequestMethod.POST)
	public String memberInsert(@ModelAttribute("memberDto") MemberDto memberDto, HttpServletRequest request) {
		
		//String email = request.getParameter("email");
		//String email2 = request.getParameter("email2");
		//email = email+email2;
		//memberDto.setEmail(email);
		
		String tel = request.getParameter("tel");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		tel = tel+tel2+tel3;
		memberDto.setTel(tel);
		
		sqlSession.insert("member.insertMember", memberDto); //회원가입
		
        //임의의 authKey 생성 & 이메일 발송
        String authKey = mss.sendAuthMail(memberDto.getEmail());
        memberDto.setAuthKey(authKey);

        Map<String, String> map = new HashMap<String, String>();
        map.put("email", memberDto.getEmail());
        map.put("authKey", memberDto.getAuthKey());
        System.out.println(map);

        //DB에 authKey 업데이트
        sqlSession.update("member.updateAuthKey", map);
		
		
		//return "/member/loginForm"; //뷰 리턴, loginForm.jsp
		return ".main.member.loginForm"; //뷰 리턴, loginForm.jsp
		
	}
	@RequestMapping(value = "/insertConfirm.do", method = RequestMethod.GET)
	 public void signUpConfirm(@RequestParam Map<String, String> map, HttpServletResponse response) throws IOException{
	    //email, authKey 가 일치할경우 authStatus 업데이트
	    sqlSession.update("member.updateAuthStatus", map);
	    
	  //이메일 인증버튼을 누르면 alert창 띄우고 메인으로
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.println("<script>alert('이메일 인증이 완료되었습니다');location.href='/TheCoffee/';</script>");
	    out.flush();
//	    mv.addObject("display", "/view/member/signUp_confirm.jsp");
//	    mv.setViewName("/view/index");
//	    return mv;
	    //return ".main.layout";
	}
	
	
	//로그인 폼
	@RequestMapping("loginForm")
	public String loginForm() {
		//return "/member/loginForm";
		return ".main.member.loginForm";
	}
		
	//로그인
	@RequestMapping(value = "loginPro.do", method = RequestMethod.POST)
	public String loginPro(String id, String pw, Model model, HttpServletResponse response, HttpSession session) throws IOException {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		
		MemberDto mdto = sqlSession.selectOne("member.selectLogin", map);
		String authStatus = sqlSession.selectOne("member.authStatusCheck", map);
		String isDelete = sqlSession.selectOne("member.memberDeleteCheck", map);
		
		//아이디나 비빌번호가 없거나 틀린경우
		if(mdto==null) {
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('존재하지않는 회원이거나 비밀번호가 일치하지 않습니다');</script>");
		    out.flush();
		    return ".main.member.loginForm";
			/*
			 * model.addAttribute("msg", "로그인 실패"); return "/member/loginForm"; //뷰 리턴
			 * loginForm.jsp return ".main.member.loginForm";
			 */
		//이메일인증을 하지않은 경우
		}else if(authStatus.equals("N")) {
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('이메일 인증이 완료되지 않았습니다');</script>");
		    out.flush();
		    return ".main.member.loginForm";
		//이미 탈퇴한 회원일 경우
		}else if(isDelete.equals("Y")) {
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('탈퇴된 회원입니다');</script>");
		    out.flush();
		    return ".main.member.loginForm";
		}
		
		//정상적으로 로그인 된 경우
		String userName = mdto.getName();
		String userId = id;
		session.setAttribute("userName", userName);
		session.setAttribute("userId", userId);
		//System.out.println(userName);
		//System.out.println(userId);
		
		model.addAttribute("mdto", mdto);
		//return "/member/loginSuccess"; //뷰 리턴, loginSuccess.jsp
		return ".main.layout"; //뷰 리턴, loginSuccess.jsp
	}
		
	//로그아웃
	@RequestMapping("/logOut.do")
	public String logOut(HttpSession session) {
		//return "/member/logOut";
		session.invalidate();
		//return ".main.member.logOut";
		return ".main.layout";
	}
	//마이메뉴
	@RequestMapping("/myMenu.do")
	public String myMenu() {
		
		return ".main.member.myMenu";
	}
	
	//회원 정보 수정 폼
	@RequestMapping(value="editForm.do", method = RequestMethod.POST)
	public String editForm(String id, String pw, Model model) {
		MemberDto mdto = sqlSession.selectOne("member.selectOne", id);
		
//		String emailTemp = mdto.getEmail();
//		int idx = emailTemp.indexOf("@");
//		String email = emailTemp.substring(0, idx);
//		String email2 = emailTemp.substring(idx+1);
		
		String telTemp = mdto.getTel();
		String tel = telTemp.substring(0,3);
		String tel2 = telTemp.substring(3,7);
		String tel3 = telTemp.substring(7);
		
//		model.addAttribute("email", email);
//		model.addAttribute("email2", email2);
		model.addAttribute("tel", tel);
		model.addAttribute("tel2", tel2);
		model.addAttribute("tel3", tel3);
		model.addAttribute("mdto", mdto);
		//return "/member/editForm";
		
		
		return ".main.member.editForm";
	}
	
	//DB회원 정보 수정
	@RequestMapping(value = "/editPro.do", method = RequestMethod.POST)
	public String editPro(@ModelAttribute("memberDto") MemberDto memberDto,String id, String pw, HttpServletRequest request, HttpServletResponse response) throws Exception{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		
		MemberDto mdto = sqlSession.selectOne("member.selectOne", map);
		
		String userPw = request.getParameter("pw");
		
		if (!(mdto.getPw()).equals(userPw)) {
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('비밀번호가 일치하지 않습니다');</script>");
		    out.flush();
		    return ".main.member.myMenu";
		}
//		String email = request.getParameter("email");
//		String email2 = request.getParameter("email2");
//		email=email+email2;
//		memberDto.setEmail(email);
		
		String tel = request.getParameter("tel");
		String tel2 = request.getParameter("tel2");
		String tel3 = request.getParameter("tel3");
		tel=tel+tel2+tel3;
		memberDto.setTel(tel);
		
		sqlSession.update("member.memberUpdate", memberDto);
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.println("<script>alert('변경되었습니다');</script>");
	    out.flush();
		//return "main";
		return ".main.layout";
	}
	
	//비밀번호 변경 폼
	@RequestMapping("/pwEditForm.do")
	public String pwEditForm() {
		return ".main.member.pwEditForm";
	}
	
	//DB비밀번호 변경
	@RequestMapping(value = "/pwEditPro.do", method = RequestMethod.POST)
	public String pwEditPro(String id, String oldPw, String newPw, HttpServletResponse response) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		
		MemberDto mdto = sqlSession.selectOne("member.selectOne", map);
		
		//String userPw = oldPw;
		
		if (!(mdto.getPw()).equals(oldPw)) {
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('비밀번호가 일치하지 않습니다');</script>");
		    out.flush();
		    return ".main.member.myMenu";
		}
		
		map.put("pw", newPw);
		sqlSession.update("member.pwUpdate", map);
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    out.println("<script>alert('변경되었습니다');</script>");
	    out.flush();
		return ".main.layout";
	}
	//회원탈퇴 폼
	@RequestMapping("/deleteForm.do")
	public String deleteForm() {
		return ".main.member.deleteForm";
	}
	
	//회원탈퇴
	@RequestMapping(value = "/deleteMember.do", method = RequestMethod.POST)
	public String deletePro(String id, String pw, HttpServletResponse response, HttpSession session) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		
		MemberDto mdto = sqlSession.selectOne("member.selectOne", map);
		
		if (!(mdto.getPw()).equals(pw)) {
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('비밀번호가 일치하지 않습니다');</script>");
		    out.flush();
		    return ".main.member.myMenu";
		}
		
		sqlSession.update("member.memberDelete", id);

		session.invalidate();
		//return "main";
		return ".main.layout";
	}
	
}
	
	

