<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="top1" value="${list[0]}"/>
 <div class="hotnews_tit">
     <span class="fright mt10"><!--  <a href="#"><img src="images/more.jpg" width="36" height="11" alt="更多" /></a>--></span>
     <img src="images/news_title.jpg" width="115" height="24" />
 </div>
 <div class="hotnews_tu mt10 mb10">
      <div class="fleft mr10"><img src="${top1.titleimageurl}" width="124" height="93" /></div>
       <div class="txt_box">
            <h4><a href="news.shtml?id=${top1.id}" target="_blank">${top1.title }</a></h4>
            &nbsp;&nbsp;${top1.shortinfo}
       </div>
      <div class="clear"></div>
  </div>
  <div class="hotnews_list">
        <ul>
         <c:forEach var="news" items="${list}" begin="1">
            <li>·<a href="news.shtml?id=${news.id}" target="_blank">${news.title}</a></li>
          </c:forEach>
        </ul>
  </div> 