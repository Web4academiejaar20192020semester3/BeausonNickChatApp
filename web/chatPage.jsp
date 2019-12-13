<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Chat"/>
</jsp:include>
<body onload="getStatus();getFriends();getName()">
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Chat page"/>
</jsp:include>
<main>

    <!--<p>Welcome ${user.getFirstName()}!</p>-->
    <div id="name"></div>
    <p>Current status:</p>
    <div id="status"></div>

    <input list="statusList" id="selectedStatus">
    <datalist id="statusList">
        <option value="online">
        <option value="offline">
        <option value="away">
    </datalist>
    <input type="button" value="Change status" onclick="changeStatus()">

    <input type="text" id="addFriend">
    <input type="button" value="Add friend" onclick="addFriend()">

    <button type="button" id="friendsListButton">Hide Friendslist</button>
    <div id="friendsList">
        <h2>Friends</h2>
        <h3 id="onoffline"></h3>
        <table>
            <thead>
            <tr>
                <th>Username</th>
                <th>Status</th>
                <th>Chat</th>
            </tr>
            </thead>
            <tbody id="friend"></tbody>
        </table>
    </div>

    <div id="chat">
    </div>

    <form method="post" action="Controller?action=LogOut">
        <p>
            <input type="submit" id="logoutbutton" value="Log Out">
        </p>
    </form>
</main>
<jsp:include page="footer.jsp">
    <jsp:param name="title" value="Home"/>
</jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.js"></script>
<script src="js/jquery-1.11.0.js"></script>
<script src="js/chat.js"></script>
</body>
</html>