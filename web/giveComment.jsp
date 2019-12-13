<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Comment"/>
</jsp:include>
<body onload="openSocket();getBack()">
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Comment"/>
</jsp:include>
<main>
    <h2>${topic}</h2>
    <form>
        <p><label for="comment">Comment:</label>
            <textarea rows="1" type="text" id="comment" name="comment"></textarea>
        </p>
        <p>
            <label for="rating">Rating:</label>
            <input  name="rating" id="rating" maxlength="2" size="2" min="1" max="10" type="number" />
        </p>
        <p>
            <label for="name">Name:</label>
            <input type="text" id="name" name="user" value="${userEmail}">
        </p>
        <p>
            <input type="button" value="Comment" onclick="send()">
        </p>
    </form>
    <div id="commentSection"></div>
</main>
<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<script src="js/chat.js"></script>
</body>
</html>
