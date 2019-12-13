<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Register"/>
</jsp:include>
<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Register"/>
</jsp:include>
<main>

    <form method="POST" action="Controller?action=RegisterUser">

        <p>
            <label for="firstName">First name:
            </label>
            <input type="text" name="firstName" id="firstName" placeholder="firstName">
        </p>
        <p>
            <label for="lastName">Last name:
            </label>
            <input type="text" name="lastName" id="lastName" placeholder="lastName">
        </p>
        <p>
            <label for="userId">E-mail:
            </label>
            <input type="text" name="userId" id="userId" placeholder="email">
        </p>
        <p>
            <label for="age">Age:
            </label>
            <input type="number" name="age" id="age" placeholder="18">
        </p>
        <p>
            <label for="sex">Sex:
            </label>
            <select name="sex" id="sex">
                <option value="Male">Male</option>
                <option value="Female">Female</option>
            </select>
        </p>
        <p>
            <label for="password1">Password:
            </label>
            <input type="password" name="password1" id="password1">
        </p>
        <p>
            <label for="password2">Password:
            </label>
            <input type="password" name="password2" id="password2">
        </p>
        <p>
            <input type="submit" id="submit" value="Submit">
        </p>

    </form>


</main>
<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
</body>
</html>