package co.kr.TheCoffee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.session.SqlSession;

import util.PageTest;
import model.product.ProductDto;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;



@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired //setter작업이 자동으로 된다 
	private SqlSession sqlSession;//변수 MyBatis 사용하려고 
	
	//상품 리스트
	@RequestMapping("/list.do")
	public String listPorduct(Model model) {
		
		int cnt = sqlSession.selectOne("product.selectCount");//총 상품 갯수 얻기
	
		List<ProductDto> list = sqlSession.selectList("product.selectListProduct");
		
		model.addAttribute("cnt", cnt);//총 상품 갯수
		model.addAttribute("list",list);//jsp에서 사용할 데이터
		
		return ".main.product.list";
	
	}
	
	//상품 정보 보기
	@RequestMapping("/productDetail.do")
	public String productDetail(Model model, String pro_no) {
		
		//int pro_no1 = Integer.parseInt(pro_no);
		
		ProductDto pdto = sqlSession.selectOne("product.selectOneProduct", new Integer(pro_no));
		
		model.addAttribute("pro_no", pro_no);
		model.addAttribute("pdto",pdto);
		
		return ".main.product.productDetail";
	}
	
	//상품 등록
	@RequestMapping("/inputProductForm.do")
	public String inputProductForm() {
		return ".main.product.inputProductForm";
	}
	
	//상품 DB등록
	@RequestMapping(value="inputProductPro.do", method=RequestMethod.POST)
	public String inputProductPro(MultipartHttpServletRequest mtfRequest, @ModelAttribute("productDto") ProductDto productDto, HttpServletRequest request) 
			throws IOException,NamingException{
		
		//
		String real_Path =  request.getSession().getServletContext().getRealPath("/");		//
		String upload_Path = real_Path+"/resources/imgs/product/";	//저장 경로 얻어오기
				
		MultipartFile uploadFile = mtfRequest.getFile("image_file");
		
		System.out.println("upload22_path:"+upload_Path);
				
		if (!uploadFile.isEmpty()) {
			String originalFileName = uploadFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(originalFileName);	//
			UUID uuid = UUID.randomUUID();	//
			String fileName=uuid+"."+ext;
			
			uploadFile.transferTo(new File(upload_Path + fileName));
			productDto.setImage(fileName);
			}
		
		sqlSession.insert("product.insertProduct",productDto);//
		return "redirect:list.do";
	}
	
	//상품 수정
	@RequestMapping("/updateProductForm.do")
	public ModelAndView updateProdcutForm(String pro_no) {
		
		ProductDto pdto = sqlSession.selectOne("product.selectOneProduct", new Integer(pro_no));
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("pro_no", pro_no);
		mv.addObject("pdto",pdto);
		mv.setViewName(".main.product.updateProductForm");
		
		return mv;
	}

	//상품 수정 DB등록
	@RequestMapping(value="updateProductPro.do", method=RequestMethod.POST)
	public ModelAndView updateProductPro(@ModelAttribute("productDto") ProductDto productDto, MultipartHttpServletRequest mtfRequest,String pro_no, HttpServletRequest request) 
			throws IOException,IllegalStateException{
		
		//
		String real_Path =  request.getSession().getServletContext().getRealPath("/");		//
		String upload_Path = real_Path+"/resources/imgs/product/";	//저장 경로 얻어오기
		
		System.out.println("upload_Path:"+upload_Path);
		
		MultipartFile uploadFile = mtfRequest.getFile("image_file");
		
		if (!uploadFile.isEmpty()) {
			String originalFileName = uploadFile.getOriginalFilename();
			String ext = FilenameUtils.getExtension(originalFileName);	//
			UUID uuid = UUID.randomUUID();	//
			String fileName=uuid+"."+ext;
			
			uploadFile.transferTo(new File(upload_Path + fileName));
			productDto.setImage(fileName);
			}
		
		sqlSession.update("product.updateProduct",productDto);//
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pro_no",pro_no);
		mv.setViewName("redirect:productDetail.do");
		
		return mv;
	}	
	
	//글 삭제
	@RequestMapping("deleteProduct.do")
	public String deletePro(String pro_no) {

		System.out.println(pro_no);
		sqlSession.delete("product.deleteProduct", new Integer(pro_no));

		return "redirect:/product/list.do";
	}
	
	
//	//상품 DB등록, 파일업로드
//	@RequestMapping(value = "inputProductPro.do", method = RequestMethod.POST)
//	public String inputProductPro(Model model,MultipartHttpServletRequest mtfRequest, @ModelAttribute("productDto") ProductDto productDto, HttpServletRequest request) throws IOException,NamingException { 
//		System.out.println("테스트");
//		
//		String realpath =  request.getSession().getServletContext().getRealPath("/");		//
//		String path = realpath+"/resources/imgs/product/";	//저장 경로 얻어오기
//		
//		MultipartFile file = mtfRequest.getFile("image");
//			
//		
//			MultipartFile mf = file;
//			
//			String originFileName = mf.getOriginalFilename();//원래 이름 가져오기
//			
//			System.out.println("originFileName : "+ originFileName);
//			
//			String safeFile = path +"1"+originFileName;
//			
//			model.addAttribute("path",path);
//			System.out.println("path:"+path);
//			if(!safeFile.isEmpty()) {
//            	
//	            mf.transferTo(new File(safeFile));
//	           
//	            }
//			productDto.setImage("1"+originFileName);
//			
//			System.out.println("safeFile:"+safeFile);
//			
//
//		
//		sqlSession.insert("prduct.insertProduct",productDto);
//		return ".main.product.udateProdcutForm";
//	}

}
