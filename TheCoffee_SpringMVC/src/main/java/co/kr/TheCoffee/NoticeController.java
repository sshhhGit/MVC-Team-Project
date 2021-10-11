package co.kr.TheCoffee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;

import util.PageTest;
import model.notice.NoticeDto;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private SqlSession sqlSession;
	
	//글쓰기
	@RequestMapping("/writeForm.do")
	public String writeForm(Model model, String notice_no, String pageNum) {
			
		if (notice_no==null) {
			notice_no = "0";		//글 번호
		}
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("notice_no",new Integer(notice_no));

		return ".main.notice.writeForm";
	}
		
	//DB글쓰기
	@RequestMapping(value = "writePro.do", method = RequestMethod.POST)
	public String writerPro(@ModelAttribute("noticeDto") NoticeDto noticeDto, HttpServletRequest request) {
			
		int maxNum = 0; //최대 글번호에 넣을 변수
		if(sqlSession.selectOne("notice.numMax") != null) {
			
			maxNum = sqlSession.selectOne("notice.numMax");
		}
			
		if(maxNum != 0) { //답글 그룹으로 사용
			maxNum = maxNum+1;
		}else { //첫글일시
			maxNum = 1;
		}
		
		sqlSession.insert("notice.insertNotice", noticeDto);
		return "redirect:/notice/list.do";
	}
	
	//리스트
	@RequestMapping("/list.do")
	public String listNotice(Model model, @RequestParam(value="pageNum", required = false)String pageNum, HttpServletRequest request) {
		
		if(pageNum==null) {
			pageNum = "1";
		}
		
		int cnt = sqlSession.selectOne("notice.selectCount"); //총 글갯수 얻기
		int curPage = Integer.parseInt(pageNum);
		
		PageTest pp2 = new PageTest(cnt, curPage); //페이지 처리
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startPos = pp2.getStartRow()-1; //***
		map.put("start", new Integer(startPos));
		map.put("cnt", new Integer(pp2.getPageSize())); //10개씩
		
		List<NoticeDto> list = sqlSession.selectList("notice.selectListNotice", map);
				
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

		return ".main.notice.list"; //뷰리턴
	}
	
	//조회수 증가, 글내용 보기
	@RequestMapping("/content.do")
	public String contenPro(Model model, String notice_no, String pageNum) {
		
		int num = Integer.parseInt(notice_no);
			
		sqlSession.update("notice.readCount", num); //조회수 증가
			
		NoticeDto ndto = sqlSession.selectOne("notice.selectOneNotice", num);
			
		model.addAttribute("notice_no", num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("ndto", ndto);

		return ".main.notice.content";
	}
		
	//글 수정 폼
	@RequestMapping("/updateForm.do")
	public ModelAndView updateForm(String notice_no, String pageNum) {
			
		int num = Integer.parseInt(notice_no);
		//System.out.println("num:"+num);
		
		NoticeDto ndto = sqlSession.selectOne("notice.selectOneNotice", num);
			
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNum", pageNum);
		mv.addObject("notice_no", notice_no);
		mv.addObject("ndto", ndto);
		mv.setViewName(".main.notice.updateForm");
			
		return mv;
	}
	
	//DB글 수정
	@RequestMapping(value = "updatePro.do", method = RequestMethod.POST)
	public ModelAndView updatePro(NoticeDto noticeDto, String pageNum) {
			
		sqlSession.update("notice.updateNotice", noticeDto); //글 수정
			
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNum", pageNum);
		mv.setViewName("redirect:/notice/list.do");
			
		return mv;
	}
		
	@RequestMapping("delete.do")
	public String deletePro(Model model, String notice_no, String pageNum) {
		sqlSession.delete("notice.deleteNotice", new Integer(notice_no));
			
		model.addAttribute("pageNum", pageNum);
			
		return "redirect:/notice/list.do";
	}
}
