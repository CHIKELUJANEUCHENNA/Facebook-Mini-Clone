<%@ page import="com.example.myweek6project.MyDao.MyPostDao" %>
<%@ page import="com.example.myweek6project.MyDao.MyUserDao" %>
<%@ page import="com.example.myweek6project.MyModels.User" %>
<%@ page import="com.example.myweek6project.MyModels.Post" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        MyPostDao postDao = new MyPostDao();
        HttpSession session1 = request.getSession();
        session1.getAttribute("user");
        List<Post> myList = postDao.displayPost();
        session.setAttribute("list", myList);

    %>
    <title>FaceBook</title>
    <link rel="stylesheet" href="homePageStyle.css">
</head>
<body>
<h1>Welcome to facebook</h1>
${user.first_name}

<form action="${pageContext.request.contextPath}/post" method="post">

    <textarea name="postBody" placeholder="What's on your mind......."></textarea>
    <button type="submit" name="submit">Post</button>
</form>
<c:forEach var="post" items="${list}">
    <h1>${post.post_messages}</h1>
</c:forEach>
</body>
</html>
