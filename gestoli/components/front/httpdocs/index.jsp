<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String redirectURL;
//    if (request.getUserPrincipal() == null)
//    	redirectURL = "Index.html";
//    else 
    	redirectURL = "Inici.html";
	response.sendRedirect(redirectURL);
%>