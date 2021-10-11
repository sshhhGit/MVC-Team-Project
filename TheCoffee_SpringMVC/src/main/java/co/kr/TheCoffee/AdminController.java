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

import model.board.BoardDto;
import model.member.MemberDto;
import util.PageTest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

import co.kr.TheCoffee.service.MailSendService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private SqlSession sqlSession;

	@RequestMapping("/adminMenu.do")
	public String adminMenu() {
		//return "/member/insertForm";
		return ".main.admin.adminMenu";
	}
	
	@RequestMapping("/adminMemberList.do")
	public String adminMemberList(Model model, @RequestParam(value="pageNum", required = false)String pageNum, HttpServletRequest request) {
		
		if(pageNum==null) {
			pageNum = "1";
		}
		
		int cnt = sqlSession.selectOne("member.selectCount"); //총 글갯수 얻기
		int curPage = Integer.parseInt(pageNum);
		
		PageTest pp2 = new PageTest(cnt, curPage); //페이지 처리
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startPos = pp2.getStartRow()-1; //***
		map.put("start", new Integer(startPos));
		map.put("cnt", new Integer(pp2.getPageSize())); //10개씩
		
		List<BoardDto> mdto = sqlSession.selectList("member.selectAll", map);
				
		//에러처리
		if(pp2.getEndPage()>pp2.getPageCnt()) {
			pp2.setEndPage(pp2.getPageCnt());
		}
		
		
		int number = cnt-(curPage-1)*pp2.getPageSize();
		
		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pp2", pp2);
		model.addAttribute("cnt", cnt); //총 글 갯수
		model.addAttribute("mdto", mdto);
		
		return ".main.admin.adminMemberList";
	}
	
	@RequestMapping("/adminMemberSearch.do")
	public String adminMemberSearch(Model model, String search, @RequestParam(value="pageNum", required = false)String pageNum, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		if(pageNum==null) {
			pageNum = "1";
		}
		
		int cnt = sqlSession.selectOne("member.selectCount"); //총 글갯수 얻기
		int curPage = Integer.parseInt(pageNum);
		
		PageTest pp2 = new PageTest(cnt, curPage); //페이지 처리
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startPos = pp2.getStartRow()-1; //***
		map.put("start", new Integer(startPos));
		map.put("cnt", new Integer(pp2.getPageSize())); //10개씩
		
		List<BoardDto> mdto = sqlSession.selectList("member.selectOne", search);
		//System.out.println(mdto);
		if(mdto.isEmpty()) {
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    out.println("<script>alert('등록된 아이디가 존재하지않습니다');location.href='/TheCoffee/admin/adminMemberList.do';</script>");
		    out.flush();
		}
		
		//에러처리
		if(pp2.getEndPage()>pp2.getPageCnt()) {
			pp2.setEndPage(pp2.getPageCnt());
		}
		
		
		int number = cnt-(curPage-1)*pp2.getPageSize();
		
		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pp2", pp2);
		model.addAttribute("cnt", cnt); //총 글 갯수
		model.addAttribute("mdto", mdto);
		
		return ".main.admin.adminMemberList";
	}
	
	@RequestMapping(value = "/adminMemberListPro.do", method = RequestMethod.GET)
	public String adminDelete(Model model, String id, String isDelete, @RequestParam(value="pageNum", required = false)String pageNum, HttpServletRequest request) {
	    System.out.println("id" + id);
	    System.out.println("isDelete" + isDelete);
	    if (isDelete.equals("Y")) {
	    	sqlSession.update("member.memberDelete", id);
		}else if (isDelete.equals("N")) {
			sqlSession.update("member.memberRestore", id);
		}
		
		//return "/member/insertForm";
		
		if(pageNum==null) {
			pageNum = "1";
		}
		
		int cnt = sqlSession.selectOne("member.selectCount"); //총 글갯수 얻기
		int curPage = Integer.parseInt(pageNum);
		
		PageTest pp2 = new PageTest(cnt, curPage); //페이지 처리
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startPos = pp2.getStartRow()-1; //***
		map.put("start", new Integer(startPos));
		map.put("cnt", new Integer(pp2.getPageSize())); //10개씩
		
		List<BoardDto> mdto = sqlSession.selectList("member.selectAll", map);
				
		//에러처리
		if(pp2.getEndPage()>pp2.getPageCnt()) {
			pp2.setEndPage(pp2.getPageCnt());
		}
		
		
		int number = cnt-(curPage-1)*pp2.getPageSize();
		
		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pp2", pp2);
		model.addAttribute("cnt", cnt); //총 글 갯수
		model.addAttribute("mdto", mdto);
		
		return ".main.admin.adminMemberList";
	}
}