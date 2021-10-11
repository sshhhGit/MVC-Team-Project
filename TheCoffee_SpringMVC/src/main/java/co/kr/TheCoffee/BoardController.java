package co.kr.TheCoffee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;

import util.PageTest;
import model.board.BoardDto;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private SqlSession sqlSession;
	
	//글쓰기
	@RequestMapping("/writeForm.do")
	public String writeForm(Model model, String num, String ref, String re_step, String re_level, String pageNum) {
		
		if (num==null) {
			num = "0";		//글 번호
			ref = "1";		//글 그룹
			re_step = "0";	//글 순서
			re_level = "0";	//글 깊이
		}
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("num",new Integer(num));
		model.addAttribute("ref",new Integer(ref));
		model.addAttribute("re_step",new Integer(re_step));
		model.addAttribute("re_level",new Integer(re_level));
		
		//return "/board/writeForm";
		return ".main.board.writeForm";
	}
	
	//DB글쓰기
	@RequestMapping(value = "writePro.do", method = RequestMethod.POST)
	public String writerPro(@ModelAttribute("boardDto") BoardDto boardDto, HttpServletRequest request) {
		
		int maxNum = 0; //최대 글번호에 넣을 변수
		if(sqlSession.selectOne("board.numMax") != null) {
			
			maxNum = sqlSession.selectOne("board.numMax");
		}
		
		if(maxNum != 0) { //답글 그룹으로 사용
			maxNum = maxNum+1;
		}else { //첫글일시
			maxNum = 1;
		}
		String ip = request.getRemoteAddr();
		boardDto.setIp(ip);
		
		if(boardDto.getNum() != 0) { //답글이면
			//답글 끼워 넣기 위치 확보
			sqlSession.update("board.reStep", boardDto);
			
			boardDto.setRe_step(boardDto.getRe_step()+1); //글순서
			boardDto.setRe_level(boardDto.getRe_level()+1);  //글 레벨
			
		}else {
			boardDto.setRef(new Integer(maxNum)); //글 그룹
			boardDto.setRe_step(new Integer(0));
			boardDto.setRe_level(new Integer(0));
		}
		
		sqlSession.insert("board.insertBoard", boardDto);
		return "redirect:/board/list.do";
	}
	
	
	@RequestMapping("/list.do")
	public String listBoard(Model model, @RequestParam(value="pageNum", required = false)String pageNum, HttpServletRequest request) {
		
		if(pageNum==null) {
			pageNum = "1";
		}
		
		int cnt = sqlSession.selectOne("board.selectCount"); //총 글갯수 얻기
		int curPage = Integer.parseInt(pageNum);
		
		PageTest pp2 = new PageTest(cnt, curPage); //페이지 처리
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startPos = pp2.getStartRow()-1; //***
		map.put("start", new Integer(startPos));
		map.put("cnt", new Integer(pp2.getPageSize())); //10개씩
		
		List<BoardDto> list = sqlSession.selectList("board.selectListBoard", map);
				
		//에러처리
		if(pp2.getEndPage()>pp2.getPageCnt()) {
			pp2.setEndPage(pp2.getPageCnt());
		}
		
		
		int number = cnt-(curPage-1)*pp2.getPageSize();
		
		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("pp2", pp2);
		model.addAttribute("cnt", cnt); //총 글 갯수
		model.addAttribute("list", list); //jsp에서 사용 할 데이터
		
		//return "/board/list"; 
		return ".main.board.list"; 
	}
	
	//조회수 증가, 글내용 보기
	@RequestMapping("/content.do")
	public String contenPro(Model model, String num, String pageNum) {
		
		int num1 = Integer.parseInt(num);
		
		sqlSession.update("board.readCount", num1); //조회수 증가
		
		BoardDto bdto = sqlSession.selectOne("board.selectOneBoard", num1);
		
		model.addAttribute("num", num1);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("bdto", bdto);
		
		//return "/board/content";
		return ".main.board.content";
	}
	
	//글 수정 폼
	@RequestMapping("/updateForm.do")
	public ModelAndView updateForm(String num, String pageNum) {
		
		int num1 = Integer.parseInt(num);
		
		BoardDto bdto = sqlSession.selectOne("board.selectOneBoard", num1);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNum", pageNum);
		mv.addObject("num", num);
		mv.addObject("bdto", bdto);
		//mv.setViewName("/board/updateForm");
		mv.setViewName(".main.board.updateForm");
		
		return mv;
	}
	//DB글 수정
	@RequestMapping(value = "updatePro.do", method = RequestMethod.POST)
	public ModelAndView updatePro(BoardDto boardDto, String pageNum) {
		
		sqlSession.update("board.updateBoard", boardDto); //글 수정
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNum", pageNum);
		mv.setViewName("redirect:/board/list.do");
		
		return mv;
	}
	
	@RequestMapping("delete.do")
	public String deletePro(Model model, String num, String pageNum) {
		sqlSession.delete("board.deleteBoard", new Integer(num));
		
		model.addAttribute("pageNum", pageNum);
		
		return "redirect:/board/list.do";
	}
}
