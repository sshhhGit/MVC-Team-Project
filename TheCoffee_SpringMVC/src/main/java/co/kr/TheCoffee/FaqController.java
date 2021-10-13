package co.kr.TheCoffee;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.ibatis.session.SqlSession;

import util.PageTest;
import model.faq.FaqDto;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/faq")
public class FaqController {

	@Autowired
	private SqlSession sqlSession;
	
	//글쓰기
	@RequestMapping("/writeForm.do")
	public String writeForm(Model model, String faq_no, String pageNum) {
		
		if (faq_no==null) {
			faq_no = "0";		//글 번호
		}
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("faq_no",new Integer(faq_no));
		return ".main.faq.writeForm";
	}
		
	//DB글쓰기
	@RequestMapping(value = "writePro.do", method = RequestMethod.POST)
	public String writerPro(@ModelAttribute("faqDto") FaqDto faqDto, HttpServletRequest request) {
		
		int maxNum = 0; //최대 글번호에 넣을 변수
		if(sqlSession.selectOne("faq.numMax") != null) {
			
			maxNum = sqlSession.selectOne("faq.numMax");
		}
		
		if(maxNum != 0) { //답글 그룹으로 사용
			maxNum = maxNum+1;
		}else { //첫글일시
			maxNum = 1;
		}
		String ip = request.getRemoteAddr();
		faqDto.setFaq_ip(ip);
		
		sqlSession.insert("faq.insertFaq", faqDto);
		return "redirect:/faq/list.do";
	}
	
	//리스트, 검색
	@RequestMapping("/list.do")
	public String listFaq(Model model,
			@RequestParam(value="pageNum", required = false)String pageNum,
			@RequestParam(defaultValue="all")String type,
			@RequestParam(defaultValue="")String keyword,
			HttpServletRequest request) throws Exception {
		
		if(pageNum==null) {
			pageNum = "1";
		}
		
		int cnt = sqlSession.selectOne("faq.selectCount"); //총 글갯수 얻기
		int curPage = Integer.parseInt(pageNum);
		
		PageTest pp2 = new PageTest(cnt, curPage); //페이지 처리
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int startPos = pp2.getStartRow()-1; //***
		map.put("start", new Integer(startPos));
		map.put("cnt", new Integer(pp2.getPageSize())); //10개씩
		
		List<FaqDto> list = sqlSession.selectList("faq.selectListFaq", map);
				
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

		return ".main.faq.list"; //뷰리턴
	}
	
	//조회수 증가, 글내용 보기
	@RequestMapping("/content.do")
	public String contenPro(Model model, String faq_no, String pageNum) {
		
		int num = Integer.parseInt(faq_no);
			
		sqlSession.update("faq.readCount", num); //조회수 증가
			
		FaqDto fdto = sqlSession.selectOne("faq.selectOneFaq", num);
			
		model.addAttribute("faq_no", num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("fdto", fdto);

		return ".main.faq.content";
	}
		
	//글 수정 폼
	@RequestMapping("/updateForm.do")
	public ModelAndView updateForm(String faq_no, String pageNum) {
			
		int num = Integer.parseInt(faq_no);
			
		FaqDto fdto = sqlSession.selectOne("faq.selectOneFaq", num);
			
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNum", pageNum);
		mv.addObject("faq_no", faq_no);
		mv.addObject("fdto", fdto);
		mv.setViewName(".main.faq.updateForm");
			
		return mv;
	}
	
	//DB글 수정
	@RequestMapping(value = "updatePro.do", method = RequestMethod.POST)
	public ModelAndView updatePro(FaqDto faqDto, String pageNum) {
			
		sqlSession.update("faq.updateFaq", faqDto); //글 수정
			
		ModelAndView mv = new ModelAndView();
		mv.addObject("pageNum", pageNum);
		mv.setViewName("redirect:/faq/list.do");
			
		return mv;
	}
		
	@RequestMapping("delete.do")
	public String deletePro(Model model, String faq_no, String pageNum) {
		sqlSession.delete("faq.deleteFaq", new Integer(faq_no));
			
		model.addAttribute("pageNum", pageNum);
			
		return "redirect:/faq/list.do";
	}
}
