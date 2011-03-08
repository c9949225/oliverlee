<!-- redirect to this page with logooff parameter, if find the parameter clear the user infomation -->
<%
  if (request.getParameter("logoff") != null) {
    session.invalidate();
    response.sendRedirect("index.jsp");
    return;
  }
%>
<html>
<head>
<title>Protected Page for Examples</title>
</head>
<body bgcolor="white">

You are logged in as remote user <b><%= request.getRemoteUser() %></b> <!-- user1 -->
in session <b><%= session.getId() %></b><br><br>

<%
  if (request.getUserPrincipal() != null) {
%>
    Your user principal name is
    <b><%= request.getUserPrincipal().getName() %></b><br><br> <!-- user1 -->
<%
  } else {
%>
    No user principal could be identified.<br><br>
<%
  }
%>

<!-- Get Url Parameter, coz this page's form will be submitted -->
<%
  String role = request.getParameter("role");
  if (role == null)
    role = "";
  if (role.length() > 0) {
    if (request.isUserInRole(role)) {
%>
	<!-- check whether user has the role -->
      You have been granted role <b><%= role %></b><br><br>
<%
    } else {
%>
      You have <i>not</i> been granted role <b><%= role %></b><br><br>
<%
    }
  }
%>

To check whether your username has been granted a particular role,
enter it here:
<form method="GET" action='<%= response.encodeURL("index.jsp") %>'>
<input type="text" name="role" value="<%= role %>">
</form>
<br><br>

If you have configured this app for form-based authentication, you can log
off by clicking
<a href='<%= response.encodeURL("index.jsp?logoff=true") %>'>here</a>.
This should cause you to be returned to the logon page after the redirect
that is performed.

</body>
</html>
