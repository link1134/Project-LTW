<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<aside class="sidebar">
	<div class="logo">
		<h1>Admin Panel</h1>
	</div>

	<nav class="main-nav">
		<ul>
			

			<li class="nav-group-title">Quản lý Nội dung</li>

			<li><a
				href="${pageContext.request.contextPath}/admin/story-list"
				class="nav-item<c:if test='${activePage == "story-list"}'> active</c:if>">
					Danh Sách Truyện </a></li>

			<li><a href="${pageContext.request.contextPath}/admin/new-story"
				class="nav-item<c:if test='${activePage == "new-story"}'> active</c:if>">
					Đăng Truyện Mới </a></li>

			<li class="nav-group-title">Quản lý Dữ liệu</li>

			<li><a
				href="${pageContext.request.contextPath}/admin/genre-management"
				class="nav-item<c:if test='${activePage == "genre-management"}'> active</c:if>">
					Quản lý Thể loại </a></li>
		</ul>
	</nav>

	<div class="admin-logout">
		<a href="${pageContext.request.contextPath}/logout" class="logout-btn">Đăng xuất</a>
	</div>
</aside>
