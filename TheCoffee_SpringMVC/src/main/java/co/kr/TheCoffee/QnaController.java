package co.kr.TheCoffee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Provider.Service;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;

import util.PageTest;
import model.board.BoardDto;
import model.qna.QnaDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import model.member.MemberDto;

@Controller
@RequestMapping("/qna")
public class QnaController {
	
	@Autowired
	private SqlSession sqlSession;
	
	// 리스트
	// (중요)페이지 처리, 페이지 블록처리
	// 검색기능
	@RequestMapping("/list.do")
	public String list(Model model, @RequestParam(value="pageNum", required=false)String pageNum, 
			@RequestParam(value="search", required=false)String search) {
		
		if (pageNum == null || pageNum == "") {
			pageNum = "1";
		}
		
		System.out.println("search : " + search);
		
		// 1. search==null / 2. search=="" / 3. search=='가나다'
		// select * from qna where user_content like '%12%';
		if (search == null || search == "") {
			System.out.println("if구문 들어옴");
			int count = sqlSession.selectOne("qna.selectCount");

			PageTest pageTest = new PageTest(count, new Integer(pageNum)); // 객체 생성, 페이지 처리

//			게시글을 역순으로 출력하기, 게시글 limit 걸기
//			select * from qna
//		    order by num desc
//		    limit #{start},#{count}   (예) limit 3,10 : 4번째 행에서 10개의 행을 가져온다.(0행부터 시작)
			Map<String, Object> map = new HashMap<String, Object>();
			int startPosition = pageTest.getStartRow() - 1; // *중요*
			map.put("start", new Integer(startPosition));
			map.put("count", new Integer(pageTest.getPageSize())); // getPageSize만큼 -> 10개씩

			List<QnaDto> list = sqlSession.selectList("qna.selectByPageSize", map);

			// 에러처리 방지
			if (pageTest.getEndPage() > pageTest.getPageCnt()) {
				pageTest.setEndPage(pageTest.getPageCnt());
			}
			
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("pageTest", pageTest);
			model.addAttribute("count", count); // 총 글 갯수
			model.addAttribute("list", list); // jsp에서 사용할 데이터

		} else {
			System.out.println("else구문들어옴");
			
			String search2 = search.replace(" ", "");
			int count = sqlSession.selectOne("qna.selectCountBySearch", search2);
			PageTest pageTest = new PageTest(count, new Integer(pageNum)); // 객체 생성, 페이지 처리

			Map<String, Object> map = new HashMap<String, Object>();
			int startPosition = pageTest.getStartRow() - 1; // *중요*
			map.put("start", new Integer(startPosition));
			map.put("count", new Integer(pageTest.getPageSize())); // getPageSize만큼 -> 10개씩
			System.out.println("search2 : " + search2);
			map.put("search", search2);
//			search = "%" + search + "%";
//			map.put("search", search);
//			user_content like concat('%',#{search},'%')
			
			List<QnaDto> list = sqlSession.selectList("qna.selectByPageSizeAndSearch2", map);

			// 에러처리 방지
			if (pageTest.getEndPage() > pageTest.getPageCnt()) {
				pageTest.setEndPage(pageTest.getPageCnt());
			}
			
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("pageTest", pageTest);
			model.addAttribute("count", count); // 총 글 갯수
			model.addAttribute("list", list); // jsp에서 사용할 데이터
			model.addAttribute("search", search);

		} // (if-else)-end
		
		// ======================================
		
//		
//		if (pageNum == null || pageNum == "") {
//			pageNum = "1";
//		}
//		
//		int count = sqlSession.selectOne("qna.selectCount");
//		
//		PageTest pageTest = new PageTest(count, new Integer(pageNum)); // 객체 생성, 페이지 처리
//		
////		게시글을 역순으로 출력하기 and 게시글 limit 걸기
////		select * from qna
////	    order by num desc
////	    limit #{start},#{count}   (예) limit 3,10 : 4번째 행에서 10개의 행을 가져온다.(0행부터 시작)
//		Map<String, Object> map = new HashMap<String, Object>();
//		int startPosition = pageTest.getStartRow()-1; // *중요*
//		map.put("start", new Integer(startPosition));
//		map.put("count", new Integer(pageTest.getPageSize())); // getPageSize만큼 -> 10개씩
//		
//		List<QnaDto> list = sqlSession.selectList("qna.selectByPageSize", map);
//		
//		// 에러처리 방지
//		if (pageTest.getEndPage()>pageTest.getPageCnt()) {
//			pageTest.setEndPage(pageTest.getPageCnt());
//		}
//		
//		model.addAttribute("pageNum", pageNum);
//		model.addAttribute("pageTest", pageTest);
//		model.addAttribute("count", count); // 총 글 갯수
//		model.addAttribute("list", list); // jsp에서 사용할 데이터
		
//		=======================================================================
		
		
//		int count = sqlSession.selectOne("qna.selectCount");
		
//		List<QnaDto> list = sqlSession.selectList("qna.selectAll");
		
//		model.addAttribute("list", list);
//		model.addAttribute("count", count);
		
		return ".main.qna.list"; // 뷰 리턴, qna/list.jsp 리턴
	}
	
	// Q&A 내용
	@RequestMapping("/content.do")
	public String content(Model model, String num, String pageNum, HttpSession session) {

		System.out.println("들어온 세션 id : " + session.getAttribute("id"));

		QnaDto qnaDto = sqlSession.selectOne("qna.selectByNum", new Integer(num));

		model.addAttribute("qnaDto", qnaDto);
		model.addAttribute("pageNum", pageNum);

		return ".main.qna.content"; // 뷰 리턴, qna/content.jsp 리턴
	}
	
	// Q&A 작성(질문작성)
	@RequestMapping("/writeForm.do")
	public String writeForm() {
		return ".main.qna.writeForm"; // 뷰 리턴, qna/writeForm.jsp 리턴
	}
	
	// DB Q&A 작성(질문작성)
	@RequestMapping(value="/writePro.do", method=RequestMethod.POST)
	public String writePro(QnaDto qnaDto) {
		
//		System.out.println("qnaDto : " + qnaDto);
//		System.out.println("qnaDto.getNum() : " + qnaDto.getNum());
//		System.out.println("qnaDto.getUser_id() : " + qnaDto.getUser_id());
//		System.out.println("qnaDto.getUser_content() : " + qnaDto.getUser_content());
//		System.out.println("qnaDto.getUser_regdate() : " + qnaDto.getUser_regdate());
//		System.out.println("qnaDto.getAdmin_id() : " + qnaDto.getAdmin_id());
//		System.out.println("qnaDto.getUser_content() : " + qnaDto.getAdmin_content());
//		System.out.println("qnaDto.getAdmin_regdate() : " + qnaDto.getAdmin_regdate());
		
		sqlSession.insert("qna.insertQna_Q", qnaDto);
		
		return "redirect:/qna/list.do"; // 뷰 리턴, qna/writePro.jsp 리턴
	}
	
	// Q&A 작성(답변작성)
	@RequestMapping("/writeForm2.do")
	public String writeForm2(Model model, HttpServletRequest request) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		model.addAttribute("num", num);
		
		return ".main.qna.writeForm2"; // 뷰 리턴, qna/writeForm2.jsp 리턴
	}
	
	// DB Q&A 작성(답변작성)
	@RequestMapping("/writePro2.do")
	public String writePro2(QnaDto qnaDto) {
		
		sqlSession.update("qna.insertQna_A", qnaDto);
		
		return "redirect:/qna/list.do"; // 뷰 리턴, qna/writePro2.jsp 리턴
	}
	
	// Q&A 수정(질문수정)
	@RequestMapping("/updateForm.do")
	public String updateForm(Model model, String num) {
		
		QnaDto qnaDto = sqlSession.selectOne("qna.selectByNum", new Integer(num));
		
		model.addAttribute("qnaDto", qnaDto);
		
		return ".main.qna.updateForm"; // 뷰 리턴, qna/updateForm.jsp 리턴
	}
	
	// DB Q&A 수정(질문수정)
	@RequestMapping("/updatePro.do")
	public String updatePro(QnaDto qnaDto) {
		
//		System.out.println("qnaDto : " + qnaDto);
//		System.out.println("qnaDto.getNum() : " + qnaDto.getNum());
//		System.out.println("qnaDto.getUser_id() : " + qnaDto.getUser_id());
//		System.out.println("qnaDto.getUser_content() : " + qnaDto.getUser_content());
//		System.out.println("qnaDto.getUser_regdate() : " + qnaDto.getUser_regdate());
		
		sqlSession.update("qna.updateQna_Q", qnaDto);
		
		return "redirect:/qna/list.do"; // 뷰 리턴, qna/list.jsp 리턴
	}
	
	// Q&A 수정(답변수정)
	@RequestMapping("/updateForm2.do")
	public String updateForm2(Model model, String num) {
		
		QnaDto qnaDto = sqlSession.selectOne("qna.selectByNum", new Integer(num));
		model.addAttribute("qnaDto", qnaDto);
		
		return ".main.qna.updateForm2"; // 뷰 리턴, qna/updateForm2.jsp 리턴
	}

	// DB Q&A 수정(답변수정)
	@RequestMapping("/updatePro2.do")
	public String updatePro2(QnaDto qnaDto) {
		
//		System.out.println("qnaDto.getNum() : " + qnaDto.getNum());
//		System.out.println("qnaDto.getAdmin_id()" + qnaDto.getAdmin_id());
//		System.out.println("qnaDto.getAdmin_content()" + qnaDto.getAdmin_content());
//		System.out.println("qnaDto.getAdmin_regdate()" + qnaDto.getAdmin_regdate());
		
		sqlSession.update("qna.updateQna_A", qnaDto);
		
		return "redirect:/qna/list.do"; // 뷰 리턴, qna/list.jsp 리턴
	}
	
	// Q&A 삭제(질문삭제)
	@RequestMapping("/deleteForm.do")
	public String deleteForm(Model model, HttpServletRequest request) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		System.out.println("num : " + num);
		
		model.addAttribute("num", num);
		
		return ".main.qna.deleteForm"; // 뷰 리턴, qna/deleteForm.jsp 리턴
	}
	
	// DB Q&A 삭제(질문삭제)
	@RequestMapping("/deletePro.do")
	public String deletePro(HttpServletRequest request) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pw = request.getParameter("pw");
		System.out.println("pw : " + pw);
		
		QnaDto qnaDto = sqlSession.selectOne("qna.selectByNum", num);
		String dbid = qnaDto.getUser_id();
		System.out.println("dbid : " + dbid);
		
		
		MemberDto memberDto = sqlSession.selectOne("qna.selectMemberById", dbid);
		String dbpw = memberDto.getPw();
		System.out.println("dbpw : " + dbpw);
		
		
		if (pw.equals(dbpw)) { // pw가 일치하면
			sqlSession.delete("qna.deleteQna_Q", num);
			return "redirect:/qna/list.do"; // 뷰 리턴, qna/list.jsp 리턴
		} else { // pw가 틀리면
			return "redirect:/qna/content.do?num=" + num; // 해당 게시글내용보기로 이동, qna/content.jsp 리턴
		}
		
		
//		sqlSession.delete("qna.deleteQna_Q", num);
		
//		return "redirect:/qna/list.do"; // 뷰 리턴, qna/list.jsp 리턴
	}
	
	// Q&A 삭제(답변삭제)
	@RequestMapping("/deleteForm2.do")
	public String deleteForm2(Model model, HttpServletRequest request) {

		int num = Integer.parseInt(request.getParameter("num"));

		System.out.println("num : " + num);

		model.addAttribute("num", num);
		
		return ".main.qna.deleteForm2"; // 뷰 리턴, qna/deleteForm2.jsp 리턴
	}
	
	// DB Q&A 삭제(답변삭제)
	@RequestMapping("/deletePro2.do")
	public String deletePro2(HttpServletRequest request) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pw = request.getParameter("pw");
		System.out.println("pw : " + pw);
		
		QnaDto qnaDto = sqlSession.selectOne("qna.selectByNum", num);
		String dbid = qnaDto.getAdmin_id();
		System.out.println("dbid : " + dbid);
		
		
		MemberDto memberDto = sqlSession.selectOne("qna.selectMemberById", dbid);
		String dbpw = memberDto.getPw();
		System.out.println("dbpw : " + dbpw);
		
		
		if (pw.equals(dbpw)) { // pw가 일치하면
			sqlSession.delete("qna.deleteQna_A", num);
			return "redirect:/qna/list.do"; // 뷰 리턴, qna/list.jsp 리턴
		} else { // pw가 틀리면
			return "redirect:/qna/content.do?num=" + num; // 해당 게시글내용보기로 이동, qna/content.jsp 리턴
		}
		
		

//		sqlSession.delete("qna.deleteQna_A", num);
//		
//		return "redirect:/qna/list.do"; // 뷰 리턴, qna/list.jsp 리턴
	}
	
	
	
	
} // class-end
