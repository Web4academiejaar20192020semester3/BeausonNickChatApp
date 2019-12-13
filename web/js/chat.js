var statusXhr = new XMLHttpRequest();
function getStatus() {
    statusXhr.open("GET", "Controller?action=GetStatus");
    //Get request
    // haal op: Controller?action=GetStatus => Komt van controller en geeft status terug
    statusXhr.onreadystatechange = getData;  //No brackets after getData():  if brackets: function only excecuted once!
    //--> onreadystatechange: this gets executed every time the state changes
    statusXhr.send(); //Send gebruikt om parameter mee tegeven (key/value pair bv) -> in controller ophalen met request.getParam(key)
}
function getData() {
    //if httpstatus = 200
    var div = document.getElementById("status")
    var status = div.childNodes[0];
    var p;
    if (statusXhr.status == 200) {
        //xhr : verschillende states:
        //0=aangemaakt, 1 = connectie , 2 = na send, 3 = , 4= response binnen gekregen
        if (statusXhr.readyState == 4) {

            if (status == null) {
                p = document.createElement('p');
                status = document.createTextNode(statusXhr.responseText);
                p.appendChild(status);
                div.appendChild(p);
            } else {
                while (div.firstChild) {
                    div.removeChild(div.firstChild);
                }
                p = document.createElement('p');
                status = document.createTextNode(statusXhr.responseText);
                p.appendChild(status);
                div.appendChild(p);
            }
            setTimeout("getStatus()", 2000);
/*
            p = document.createElement('p');
            status = document.createTextNode(statusXhr.responseText);
            p.appendChild(status);
            div.appendChild(p);*/

        }
    }
}

var nameXhr = new XMLHttpRequest();
function getName(){

    nameXhr.open("GET", "Controller?action=GetName");
    nameXhr.onreadystatechange = getNameData;
    nameXhr.send();
}
function getNameData(){
    var div = document.getElementById("name");
    var p;
    var nameText = div.childNodes[0];

    if (nameXhr.status == 200) {
        if (nameXhr.readyState == 4) {
            if (nameText == null) {
                p = document.createElement('p');
                nameText = document.createTextNode("Welcome " + nameXhr.responseText + "!");
                p.appendChild(nameText);
                div.appendChild(p);
            } else {
                while (div.firstChild) {
                    div.removeChild(div.firstChild);
                }
                p = document.createElement('p');
                nameText = document.createTextNode("Welcome " + nameXhr.responseText + "!");
                p.appendChild(nameText);
                div.appendChild(p);
            }
            setTimeout("getName()", 2000);
        }
    }
}

var changeStatusXhr = new XMLHttpRequest();
function changeStatus() {
    var statustext = document.getElementById("selectedStatus").value;
    var status = "status=" + encodeURIComponent(statustext);
    changeStatusXhr.open("POST", "Controller?action=ChangeStatus");
    changeStatusXhr.onreadystatechange = getDataChangedStatus;  //No brackets after getData():  if brackets: function only excecuted once!
    changeStatusXhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    changeStatusXhr.send(status); //Meegeven key-value pair naar controller
    //toevoegen &status="..." aan url?
}
function getDataChangedStatus() {
    if (changeStatusXhr.status == 200) {
        //xhr : verschillende states:
        //0=aangemaakt, 1 = connectie , 2 = na send, 3 = , 4= response binnen gekregen
        if (changeStatusXhr.readyState == 4) {
            var div = document.getElementById("status"); //searches for div with id "status"
            var p = div.childNodes[0];  // maakt <p> </p> aan

            if (p == null) {
                p = document.createElement('p');

                var text = document.createTextNode(changeStatusXhr.responseText); //responseText data json
                p.appendChild(text);
                div.appendChild(p);
            }
            else {
                var text = document.createTextNode(changeStatusXhr.responseText);
                p.removeChild(p.childNodes[0]);//Delete text from <p></p>
                p.appendChild(text);  //Steek de tekst (status) in <p></p>
            }
        }
    }
}
var getFriendsXhr = new XMLHttpRequest();
function getFriends() {
    getFriendsXhr.open("GET", "Controller?action=GetFriends");
    getFriendsXhr.onreadystatechange = getFriendsData;
    getFriendsXhr.send();
}
function getFriendsData() {
    if (getFriendsXhr.status == 200) {
        if (getFriendsXhr.readyState == 4) {
            var serverResponse = JSON.parse(getFriendsXhr.responseText); //Get JSON text out of response server
            /*
            var myJSON = '{"name":"John", "age":31, "city":"New York"}';
            var myObj = JSON.parse(myJSON);
            document.getElementById("demo").innerHTML = myObj.name;
            Indien meerdere elementen in JSON '[{"name"="John","age":31},{"name"=Sofie","age"=23}]'
                => document.getElementById("demo").innerHTML = myObj[1].name; (Geeft Sofie)
             */
            var tableBody = document.getElementById("friend");
            var tableRow; // =  tableBody.childNodes[0];
            var tableData = tableBody.childNodes[0]; //zie uitleg ChildNodes -> childNodes[0] duid op de plek (waar niets is) tussen <body id="friend"> en </body>
            //Hiertussen de appendChild's plakken
            var tableData2;
            var tableData3;
            var JSONLength = serverResponse.length;
            var tableName;
            var tableStatus;
            var chatButtonText;
            var chatButton;
            var i;

            //On & offline count
            var h3 = document.getElementById("onoffline");
            var onlineText = h3.childNodes[0];
            var offlineText;
            var offline = 0;  //# vrienden offline
            var online = 0;   //# vrienden online, ...


            /*
            ChildNodes:
            <ul id="main">
                <li>
                    <p>A</p>
                    <p>B</p>
                    <p>C</p>
                </li>
                <li>
                    <p>D</p>
                    <p>E</p>
                </li>
                <li>
                    <p>F</p>
                </li>
             </ul>          ==> var ul = document.getElementById("main");
                            ==> ul.childNodes[i]; => ChildNodes. length -> telt hoeveel items in de ul zitten
                            ==> Telt ook lege whitespace: bv childNodes.length voor
                                        <ul id="main"></ul> = 0
                                        <ul id="main"> </ul> = 1
                                        <ul id="main"> <p>Text</p></ul> = 2
                                        <ul id="main"> <p>Text</p> </ul> = 3
                                         => Voor elke whitespace tussen tag + voor elke tagpair : +1
                            ==>To get D:
                                var D = ul.childNodes[3].childNodes[1].innerText;
                                document.getElementById("outputId").innerHTML = D;
                                id van 2e li is 3: whitespace - 1e li - whitespace - 2e li => index vanaf 0 dus 4e is 3
                                id van <p>D</p> is 1:  whitespace - <p> => 2e positie, index 1
                                innerText -> give value as string
                ==>Waarom tabledata = tableRow.childNodes[0]; ?
                <tbody id="friend"></tbody>  -> tableBody.childNodes.length = 0
                    => is undefined: want staat nog niets in...



             */

            //Friendslist weergeven
            if (tableData == null) {
                for (i = 0; i < JSONLength; i++) {
                    var userName = serverResponse[i].firstName;
                    var userMail = serverResponse[i].email;
                    tableName = document.createTextNode(userName);
                    tableStatus = document.createTextNode(serverResponse[i].status);
                    chatButtonText = document.createTextNode("Chat with " + userName);

                    chatButton = document.createElement("button");
                    chatButton.setAttribute("type", "button");
                    chatButton.setAttribute("id", userName);
                    chatButton.setAttribute("onclick", "enableChat(\"" + userName + "\""+ "," + "\"" + userMail + "\")");
                    chatButton.appendChild(chatButtonText);

                    tableData = document.createElement("td");
                    tableData2 = document.createElement("td");
                    tableData3 = document.createElement("td");

                    tableRow = document.createElement("tr");

                    tableData.appendChild(tableName);   //text in td
                    tableData2.appendChild(tableStatus);
                    tableData3.appendChild(chatButton);
                    tableRow.appendChild(tableData); //td in tr
                    tableRow.appendChild(tableData2);
                    tableRow.appendChild(tableData3);
                    tableBody.appendChild(tableRow);    //tr in tbody id="friend";
                }
            }
            else {
                while (tableBody.firstChild) {
                    tableBody.removeChild(tableBody.firstChild);
                } //Remove verwijdert bovenste, append voegt onderaan toe -> met while helemaal leegmake
                for (i = 0; i < JSONLength; i++) {
                    var userName = serverResponse[i].firstName;
                    var userMail = serverResponse[i].email;
                    tableName = document.createTextNode(userName);
                    tableStatus = document.createTextNode(serverResponse[i].status);
                    chatButtonText = document.createTextNode("Chat with " + userName);

                    chatButton = document.createElement("button");
                    chatButton.setAttribute("type", "button");
                    chatButton.setAttribute("id", userName);
                    chatButton.setAttribute("onclick", "enableChat(\"" + userName + "\""+ "," + "\"" +userMail + "\")");
                    chatButton.appendChild(chatButtonText);

                    tableData = document.createElement("td");
                    tableData2 = document.createElement("td");
                    tableData3 = document.createElement("td");

                    tableRow = document.createElement("tr");

                    tableData.appendChild(tableName);   //text in td
                    tableData2.appendChild(tableStatus);
                    tableData3.appendChild(chatButton);
                    tableRow.appendChild(tableData); //td in tr
                    tableRow.appendChild(tableData2);
                    tableRow.appendChild(tableData3);
                    tableBody.appendChild(tableRow);    //tr in tbody id="friend";
                }
            }

            //Online: x offline: y  weergeven
            if (onlineText == null) {
                //Nagaan hoeveel vrienden offline / online
                for (i = 0; i < JSONLength; i++) {
                    if (serverResponse[i].status == "offline") {
                        offline++;
                    } else {
                        online++;
                    }
                }
                onlineText = document.createTextNode("Online: " + online + " ");
                offlineText = document.createTextNode("Offline: " + offline);
                h3.appendChild(onlineText);
                h3.appendChild(offlineText);
            }
            else {
                while (h3.firstChild) {
                    h3.removeChild(h3.firstChild);
                }
                //Nagaan hoeveel vrienden offline / online
                for (i = 0; i < JSONLength; i++) {
                    if (serverResponse[i].status == "offline") {
                        offline++;
                    } else {
                        online++;
                    }
                }
                onlineText = document.createTextNode("Online: " + online + " ");
                offlineText = document.createTextNode("Offline: " + offline);
                h3.appendChild(onlineText);
                h3.appendChild(offlineText);
            }
            setTimeout("getFriends()", 2000);
        }
    }
}

//////FANCY - toggle friendslist////
$(function () {
    $("#friendsListButton").on('click', function () {
        $("#friendsList").fadeToggle(2000, function () {   //Callback functie (gebeurt na de fade)
            var buttonText = $("#friendsListButton").text();
            var newButtonText = "Hide Friendslist";
            if (buttonText === "Hide Friendslist") {
                newButtonText = "Show Friendslist"
                $("#friendsListButton").html(newButtonText);
            } else {
                $("#friendsListButton").html(newButtonText);
            }
            /*
            if(newButtonText === "Hide Friendslist"){
                alert("Your friends are back!");
            }else{
                alert("FriendList is hidden!");
            }*/
        });


    });
})

/////////////ChatForm//////////
/*function getChatId() {
      window.onload = function () {
        var button =  document.getElementById("chatButton");
        button.onclick= function(){alert("getChatId")};
      };
  }
document.getElementById("chatButton").addEventListener("click", enableChat);
  -> niet gebruikt want werkte niet - vond id niet ookal kwam dit overeen
  -> id nodig om object op te roepen -> id is uniek per button? Hoe dan oproepen?
*/

function enableChat(userName, userEmail) {
    $(function () {
        var title = $("<h2/>").html("Chat met <b>" + userName + "</b>:");
        var input = $("<input />").attr({"type": "text", "id": "chatInput" + userName});
        var sendButton = $("<button />").attr({
            "type": "button",
            "id": "sendChatMessage" + userEmail,
            "onclick": "sendMessage(\"" + userName + "\""+ "," + "\"" +userEmail + "\")"
        }).text("Send");
        var closeButton = $("<button />").attr({
            "type": "button",
            "id": "removeChat" + userName,
            "onclick": "closeChat(\"" + userName + "\""+ "," + "\"" +userEmail + "\")"
        }).text("Close chat");
        var messagesDiv = $("<div />").attr({"id": "messagesDiv" + userName, "class": "messagesDiv"});
        var chatDiv = $("<div />").attr("id", "chatDiv" + userEmail).append(messagesDiv, input, sendButton, closeButton);
        var br = $("<br />");
        var outerDiv = $("<div />").attr("id", "outerDiv" + userName).append(title, chatDiv, br);
        $("#chat").append(outerDiv);
        getMessage(userName, userEmail);
    });
}

function getMessage(userName, userEmail) {
    $.ajax({
        type: "GET",
        url: "Controller?action=GetMessages",
        data: "userEmail=" + userEmail,
        dataType: "json",
        success: function (json) {
            var div = $('#messagesDiv' + userName);
            div.html("");
            if (json.length == 0) {
                console.log("messages empty, trying again in 5 seconds");
            } else {
                //alert("messages not null");
                $(json).each(function (index, message) {
                    $('#messagesDiv' + userName).append($("<p />").text(message.person.firstName + ": " + message.message));

                })
            }
            div.scrollTop = div.scrollHeight;
            setTimeout(getMessage(userName,userEmail), 2000);
        }
        ,
        error: function () {
            //alert("Something went wrong, try restarting the chat!");
        }
    });

}

function sendMessage(userName, userEmail) {
        var message = $("#chatInput" + userName).val(); //ophalen data uit inputfield
    /* $.ajax({
         type: "post",
         url: "Controller?action=SendMessage",
         dataType:"json",
         ContentType:"application/jason; charset=utf-8",
         data: {
             userName: userName,
             message: message
         }
     });*/
    $.post("Controller?action=SendMessage", {userEmail: userEmail, message: message}, null, "json");
    $("#chatInput" + userName).val(""); //leegmaken inputfield
}

function closeChat(userName, userEmail) {
    console.log("Closing chat with " + userEmail);
    $("#outerDiv" + userName).remove();
}

//////Add friend /////
/*
<script>
function myFunction() {
	const friends = ["Sofie","Online","Jan","Away"];

    var tbody = document.getElementById("myDIV");

    for(i = 0 ; i<friends.length ; i++){

    var tr = document.createElement("tr");

 	var td = document.createElement("td");
    td.textContent = friends[i];

    var td2 = document.createElement("td");
    td2.textContent = friends[i+1];

   	i++;

    tr.appendChild(td);
    tr.appendChild(td2);
    tbody.appendChild(tr);
    }
}
</script>
    ==> Sofie   Online
        Jan     Away
 */

var newFriendXhr = new XMLHttpRequest();

function addFriend() {
    var friendMail = document.getElementById("addFriend").value;
    var newFriend = "newFriend=" + encodeURIComponent(friendMail); //Doorsturen email naar controller met key = newFriend;
    newFriendXhr.open("POST", "Controller?action=AddFriend");
    newFriendXhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    newFriendXhr.send(newFriend);
    //getFriends(); niet nodig want polling op friendlist
}

///////PUSH - Websocket////////

var webSocket;

function getBack() {
    document.getElementById("back").addEventListener("click", closeSocket);
}
/*var back = document.getElementById("back");
back.onclick= closeSocket;  werkte niet */

//Opgeroepen als op "Back" wordt geklikt
function closeSocket() {
    //alert("Closing socket");
    webSocket.close();
}

function openSocket() {
    webSocket = new WebSocket("ws://localhost:8080/comment");

    /*webSocket.onopen = function(event){
        writeResponse("Connection opened");
    };*/

    webSocket.onmessage = function (event) {
        writeResponse(event.data);
    };
    /*
        webSocket.onclose = function(event){
            alert("connection closed");
            //writeResponse("Connection closed");
        };*/
}

function send() { //Opgeroepen na invullen comment form
    var comment = document.getElementById("comment").value;
    var rating = document.getElementById("rating").value;
    var name = document.getElementById("name").value;
    var text = {comment: comment, rating: rating, user: name};
    var Json = JSON.stringify(text);
    webSocket.send(Json);
    //Omzetten naar JSON en send
}

function writeResponse(data) {
    //alert("writeResponse: " + data);
    var obj = JSON.parse(data);
    var comment = obj.comment;

    var rating = obj.rating;
    var name = obj.user;
    var commentSection = document.getElementById("commentSection");

    var name = document.createTextNode(name + " - ");
    var rating = document.createTextNode(rating);
    var comment = document.createTextNode(" -> " + comment);

    var commentP = document.createElement("p");
    var namePlusRatingP = document.createElement("p");
    var line = document.createElement("hr");

    namePlusRatingP.appendChild(name);
    namePlusRatingP.appendChild(rating);
    commentP.appendChild(comment);

    commentSection.appendChild(namePlusRatingP);
    commentSection.appendChild(commentP);
    commentSection.appendChild(line);
}

/////Give online & offline count on index.jsp//////
function getOnOffCount() {
    $.ajax({
        type: "GET",
        url: "Controller?action=GetOnOffCount",
        data: "",
        dataType: "json",
        success: function (json) {
            if (json.length != 0) {
                $("#onOffCount").text("Online: " + json[0] + " Offline: " + json[1])
            }
        },
        error: function () {
            //alert("fail");
        }
    });
    setTimeout(getOnOffCount, 2000);
}


