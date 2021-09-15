package controller;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;//.properties
import java.util.*;//Map,HashMap
import command.CommandAction;
//import action.board.ListAction;

//Ŭ���̾�Ʈ ��û�� �޴´�
//��Ʈ�ѷ��� �������� ����� 
//��Ʈ�ѷ��� java����,servlet�������� �̷������ 

//��Ʈ�ѷ� �ۼ�
public class DispatcherController extends HttpServlet{

	  private Map map=new HashMap();
	  // key value
	  // uri  Action��ü
	  
	  //init() : �ʱ�ȭ �۾�
	  public void init(ServletConfig config) throws ServletException{
		  String path=config.getServletContext().getRealPath("/");
		  String ffile=path+config.getInitParameter("proFile");//web.xml
		  Properties pro=new Properties();//��ü����
		  
		  FileInputStream f=null;
		  
		  try{
			  f=new FileInputStream(ffile);//command.propertis
			  pro.load(f);/////******************
			  
		  }catch(Exception ex1){
			  System.out.println("���� �б� ���� "+ex1);
		  }finally{
			  try{
				  if(f != null) { f.close();}
			  }catch(Exception ex1){}
		  }//finally-end
		  
		  Iterator keys=pro.keySet().iterator();//******
		  
		  while(keys.hasNext()){//key�����ϴ� ���� �ݺ�
			  
			  String key=(String)keys.next();//  /board/list.do
			  String className=pro.getProperty(key);//key�ش��ϴ� ��, action.board.ListAction
		  
			  try{
				  //Class.forName(DRIVER)
				  Class commandClass=Class.forName(className);//Ŭ������ �������
				  Object commandObject=commandClass.newInstance();//Ŭ������ ��ü�� �������
				  
				  map.put(key, commandObject);//map����
				  
			  }catch(Exception ex3){
				  System.out.println("properties ���� ������ Ŭ���� ��ü�� ����� �� ���� �߻�:"+ex3);
			  }
		  }//while-end
		  
	  }//init()-end============
	  
	  //doGet()
	  public void doGet(HttpServletRequest request,HttpServletResponse response)
	   throws IOException,ServletException{
		reqPro(request,response);//�޼���
	  }
	  
	  //doPost()
	  public void doPost(HttpServletRequest request,HttpServletResponse response)
		throws IOException,ServletException{
		reqPro(request,response);//�޼���
	  }
	  
	  //����� ���� �޼���,Ŭ���̾�Ʈ ��û�� ó�� �ϴ� �� 
	  //reqPro()
	  private void reqPro(HttpServletRequest request,HttpServletResponse response)
	  throws IOException,ServletException{
		  
		  String view="";//jsp(��)���� ����   
		  
		  CommandAction commandAction=null;//�������̽��� ActionŬ����ó���ϱ� ���� ���� ���� 
		  
		  try{
			  String uri=request.getRequestURI();// uri=> /������Ʈ��/ch19/list.do
			  
			  if(uri.indexOf(request.getContextPath())==0){
				  uri=uri.substring(request.getContextPath().length());//=> /ch19/list.do
			  }//if-end
			  
			  commandAction=(CommandAction)map.get(uri);//==>/ch19/list.do Ű�� �ش��ϴ� ��(��ü)�� ��´�
		 	  view=commandAction.requestPro(request, response);//Action�� �޼��� ȣ���Ͽ�, view(jsp)�޴´�
		 	  
		  }catch(Throwable ex5){
			  System.out.println("reqPro() ���� �߻� :"+ex5);
		  }
		  
		  request.setAttribute("view", view);//JSP�� �������ؼ� ����� view�̴�
		  
		  RequestDispatcher rd=request.getRequestDispatcher("/template/template.jsp");
		  rd.forward(request, response);//JSP�� ������ 
	  }//reqPro()-end
}//class-end
