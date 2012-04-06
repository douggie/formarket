<html>
<head>
<title>IPL: Create New Account</title>
<!--
Copyright 2007-2009 Chris Hibbert.  All rights reserved.

This software is published under the terms of the MIT license, a copy
of which has been included with this distribution in the LICENSE file.
-->
<jsp:useBean id="createAccount" scope="session" class="net.commerce.zocalo.JspSupport.AccountCreationScreen" />
<jsp:setProperty name="createAccount" property="*" />
    <link rel="stylesheet" type="text/css" href="trading.css">
    <script type="text/javascript" src="display.js"></script>
</head>
<body>


<% createAccount.beginTransaction(); %>
<% createAccount.processRequest(request, response); %>
<p align="center">
<img src="images/Logo/createAccountLogo.gif">
</p>
<p>
<h2 align="center">Create a new Account</h2>
</p>
<p>

<form method=POST action="createAccount.jsp">
<table border=5 cellpadding="0" cellspacing=0  width=100% align="center">
    <tr>
        <td align=center><font size=5>Username: 	</font>:
    </td><td>
        <input type=text size="10" name=userName value='<%= createAccount.getUserNameForWeb() %>'><br>
        </td>
    </tr><tr>
        <td align=center> <font size=5>Account Password: </font>:</td>
        <td><input type=password size="10" name="password"></td>
    </tr><tr>
        <td align=center><font size=5>Retype Password: </font>
        </td>
        <td><input type=password size="10" name="password2"></td>
    </tr><tr>
        <td align=center> <font size=5>Email Address: </font><br> <small>(confirmation will be required) </small>:
        </td>
        <td>
            <input type=text size="10" name="emailAddress" value="<%= createAccount.getEmailAddressForWeb() %>">
        </td>
    </tr><tr>
        <td align="center" colspan=5><input type=submit name=action value="Create Account" align=center> </td>
    </tr>
</table>
</form>
</p>

<hr>
<font size = 4 color=blue><b>To activate your account, either click on the activation link sent to your email, or type in the confirmation code sent to your email.</b></font>
<hr>
 <% if (createAccount.hasWarnings()) { %>
    <p align="center" class="userMessage"><%= createAccount.getWarnings() %>
 <% } %>

<br>

<form method=POST action='createAccount.jsp'>
    <label> <input type=button name='close' value='Enter Registration Details' onclick="toggleVisibility('confirmDetails');"></label>
    <div id='confirmDetails'>
        <table border=0 cellpadding="0" cellspacing=3>
            <tr> <td align=right>Registered name: </td>
                 <td align=left>  <input type=text name='userName' size="10" value='<%= createAccount.getUserNameForWeb() %>'></td>
            <tr> <td align=right>Confirmation code from email: </td>
                <td align=left><input type="text" size="10" name="confirmation"></td>
            <tr><td align="center" />
                <input type=button name=action value='Register'>
        </table>
    </div>
</form>

<% createAccount.commitTransaction(); %>

<p>

</body>
</html>
