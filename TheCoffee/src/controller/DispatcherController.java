package controller;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;//.properties
import java.util.*;//Map,HashMap
import command.CommandAction;
//import action.board.ListAction;

//클라이언트 요청을 받는다
//컨트롤러는 서즐릿으로 만든다 
//컨트롤러는 java문법,servlet문법으로 이루어진다 

//컨트롤러 작성
public class DispatcherController extends HttpServlet{

	  private Map map=new HashMap();
	  // key value
	  // uri  Action객체
	  
	  //init() : 초기화 작업
	  public void init(ServletConfig config) throws ServletException{
		  String path=config.getServletContext().getRealPath("/");
		  String ffile=path+config.getInitParameter("proFile");//web.xml
		  Properties pro=new Properties();//객체생성
		  
		  FileInputStream f=null;
		  
		  try{
			  f=new FileInputStream(ffile);//command.propertis
			  pro.load(f);/////******************
			  
		  }catch(Exception ex1){
			  System.out.println("파일 읽기 에러 "+ex1);
		  }finally{
			  try{
				  if(f != null) { f.close();}
			  }catch(Exception ex1){}
		  }//finally-end
		  
		  Iterator keys=pro.keySet().iterator();//******
		  
		  while(keys.hasNext()){//key존재하는 동안 반복
			  
			  String key=(String)keys.next();//  /board/list.do
			  String className=pro.getProperty(key);//key해당하는 값, action.board.ListAction
		  
			  try{
				  //Class.forName(DRIVER)
				  Class commandClass=Class.forName(className);//클래스로 만들었다
				  Object commandObject=commandClass.newInstance();//클래스를 객체로 만들었다
				  
				  map.put(key, commandObject);//map저장
				  
			  }catch(Exception ex3){
				  System.out.println("properties 파일 내용을 클래스 객체로 만드는 중 예외 발생:"+ex3);
			  }
		  }//while-end
		  
	  }//init()-end============
	  
	  //doGet()
	  public void doGet(HttpServletRequest request,HttpServletResponse response)
	   throws IOException,ServletException{
		reqPro(request,response);//메서드
	  }
	  
	  //doPost()
	  public void doPost(HttpServletRequest request,HttpServletResponse response)
		throws IOException,ServletException{
		reqPro(request,response);//메서드
	  }
	  
	  //사용자 정의 메서드,클라이언트 요청을 처리 하는 곳 
	  //reqPro()
	  private void reqPro(HttpServletRequest request,HttpServletResponse response)
	  throws IOException,ServletException{
		  
		  String view="";//jsp(뷰)넣을 변수   
		  
		  CommandAction commandAction=null;//인터페이스로 Action클래스처리하기 위해 변수 선언 
		  
		  try{
			  String uri=request.getRequestURI();// uri=> /프로젝트명/ch19/list.do
			  
			  if(uri.indexOf(request.getContextPath())==0){
				  uri=uri.substring(request.getContextPath().length());//=> /ch19/list.do
			  }//if-end
			  
			  commandAction=(CommandAction)map.get(uri);//==>/ch19/list.do 키에 해당하는 값(객체)을 얻는다
		 	  view=commandAction.requestPro(request, response);//Action의 메서드 호출하여, view(jsp)받는다
		 	  
		  }catch(Throwable ex5){
			  System.out.println("reqPro() 예외 발생 :"+ex5);
		  }
		  
		  request.setAttribute("view", view);//JSP로 포워딩해서 사용할 view이다
		  
		  RequestDispatcher rd=request.getRequestDispatcher("/template/template.jsp");
		  rd.forward(request, response);//JSP로 포워딩 
	  }//reqPro()-end
}//class-end
