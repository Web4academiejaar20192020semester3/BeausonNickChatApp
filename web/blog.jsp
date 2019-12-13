<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Blog - topics"/>
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Blog - topics"/>
</jsp:include>
<main>
    <h1>Blog - Topics</h1>

    <ul>
        <li><a href="Controller?action=GiveComment&topic=1">Was het een interessante projectweek?</a></li>
        <li><a href="Controller?action=GiveComment&topic=2">Wat ben je van plan om te doen vandaag?</a></li>
        <li><a href="Controller?action=GiveComment&topic=3">Naar welke muziek ben je momenteel aan het luisteren?</a></li>
        <li><a href="Controller?action=GiveComment&topic=4">Wat zijn mogelijke examenvragen voor het vak Web4?</a></li>
        <li><a href="Controller?action=GiveComment&topic=5">Komt er ooit Web5?</a></li>
        <li><a href="Controller?action=GiveComment&topic=6">Wat is je favoriete muziek?</a></li>
        <li><a href="Controller?action=GiveComment&topic=7">Waar ben je van plan om stage te doen?</a></li>
    </ul>
</main>
<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<script src="js/chat.js"></script>
</body>
</html>
