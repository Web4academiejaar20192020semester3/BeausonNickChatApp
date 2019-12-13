<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header role="banner">
    <img alt="Books" src="images/books.jpg">
    <h1><span>Chat App</span></h1>
    <nav>
        <ul>
            <c:choose>
                <c:when test="${topic != null}">
                    <li><a id="back" href="Controller?action=GoBack">Back</a></li>
                </c:when>
                <c:when test="${userEmail==null}">
                    <li id="actual"><a href="Controller?action=GetHome">Home</a></li>
                    <li><a href="Controller?action=UserForm">Register</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="Controller?action=GetHome">Home</a></li>
                    <li><a href="Controller?action=GetChatPage">Chat</a></li>
                    <li><a href="Controller?action=GetBlog">Blog</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
    <h2>
        ${param.title}
    </h2>

</header>