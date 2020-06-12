<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="chap13.board.BoardDBBean"%>
<%@ page import="chap13.board.BoardDataBean"%>    
<%
	String subject = request.getParameter("subject");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String content = request.getParameter("content");
	String password = request.getParameter("passwd");
		
	BoardDataBean dataBean = new BoardDataBean();
	dataBean.setSubject(subject);
	dataBean.setWriter(name);
	dataBean.setEmail(email);
	dataBean.setContent(content);
	dataBean.setPasswd(password);
	
	System.out.println("subject = " + subject);
	System.out.println("name = " + name);
	System.out.println("email = " + email);
	System.out.println("content = " + content);
	System.out.println("password = " + password);
	
// 	dataBean.setSubject(sub);
	
// 	out.println("sub = " + sub);
// 	out.println(dataBean.toString());
	
	BoardDBBean bdb = BoardDBBean.getinstance();
	bdb.insertDetailArticle(subject, name, email, content, password);
	bdb.insertArticle(dataBean);
	

	out.println("writePro.jsp");
%>
