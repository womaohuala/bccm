<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul>
        	<c:choose>
        	 <c:when test="${objPage.pageNo <1}">
        	      <li><div class="btn_page1"><a href="#" onclick="return false;">首&nbsp;页</a></div></li>
                  <li><div class="btn_page1"><a href="#" onclick="return false;">上&nbsp;页</a></div></li>
        	 </c:when>
        	 <c:otherwise>
        	     <li><div class="btn_page1"><a href="${url}pageNo=1${params}">首&nbsp;页</a></div></li>
                  <li><div class="btn_page1"><a href="${url}pageNo=${objPage.pageNo-1}${params}">上&nbsp;页</a></div></li>
        	 </c:otherwise>
        	</c:choose>
        	
        	<c:choose>
        	 <c:when test="${objPage.pageNo eq objPage.totalPages}">
        	     <li><div class="btn_page1"><a href="#" onclick="return false;">下&nbsp;页</a></div></li>
                 <li><div class="btn_page1"><a href="#"  onclick="return false;">尾&nbsp;页</a></div></li>
        	 </c:when>
        	 <c:otherwise>
        	    <li><div class="btn_page1"><a href="${url}pageNo=${objPage.pageNo+1}${params}">下&nbsp;页</a></div></li>
                <li><div class="btn_page1"><a href="${url}pageNo=${objPage.totalPages}${params}">尾&nbsp;页</a></div></li>
        	 </c:otherwise>
        	</c:choose>
                <li><div class="btn_page2">第${objPage.pageNo}/${objPage.totalPages}页</div></li>
                <li><div class="btn_page2">
                                               到<select class="select_css2">
                     <c:forEach var="index" begin="1" end="${objPage.totalPages}">
                       <option value="${index}"  <c:if test="${index eq objPage.pageNo}">selected="selected"</c:if>>${index}</option>
                      </c:forEach>
                    </select>
                                               页</div></li>
            </ul>
