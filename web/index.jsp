<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<body onload="getOnOffCount(); getName()"> <!--werkt niet in input/img tag!-->
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<main>
    <c:if test="${errors.size()>0 }">
        <div class="danger">
            <ul>
                <c:forEach var="error" items="${errors }">
                    <li>${error }</li>
                </c:forEach>
            </ul>
        </div>
    </c:if> <c:choose>
    <c:when test="${userEmail!=null}">
        <div id="name"></div>
        <form method="post" action="Controller?action=LogOut">
            <p id="onOffCount"></p>
            <p>
                <input type="submit" id="logoutbutton" value="Log Out">
            </p>

        </form>
        </div>

    </c:when>
    <c:otherwise>
        <form method="post" action="Controller?action=LogIn">
            <p>
                <label for="email">Your email </label>
                <input type="text" id="email" name="email" value="jan@ucll.be">
            </p>
            <p>
                <label for="password">Your password</label>
                <input type="password" id="password" name="password" value="t">
            </p>
            <p>
                <input type="submit" id="loginbutton" value="Log in">
            </p>
        </form>
    </c:otherwise>
</c:choose>

    <h3>Blog - Topics</h3>
    <ul>
        <li><a href="Controller?action=GiveComment&topic=8">Is CORS je vriend?</a></li>
        <li><a href="Controller?action=GiveComment&topic=1">Was het een interessante projectweek?</a></li>
        <li><a href="Controller?action=GiveComment&topic=2">Wat ben je van plan om te doen vandaag?</a></li>
        <li><a href="Controller?action=GiveComment&topic=3">Naar welke muziek ben je momenteel aan het luisteren?</a>
        </li>
        <li><a href="Controller?action=GiveComment&topic=4">Wat zijn mogelijke examenvragen voor het vak Web4?</a></li>
        <li><a href="Controller?action=GiveComment&topic=5">Komt er ooit Web5?</a></li>
        <li><a href="Controller?action=GiveComment&topic=6">Wat is je favoriete muziek?</a></li>
        <li><a href="Controller?action=GiveComment&topic=7">Waar ben je van plan om stage te doen?</a></li>
    </ul>
</main>
<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="js/chat.js"></script>
</body>
</html>