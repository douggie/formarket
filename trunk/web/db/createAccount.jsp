<html>
<head>
<title>Zocalo: Create New Account</title>
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

      <img src="images/logo.zocalo.jpg" height=81 width=250 align="right"><br>

<p>
<h2 align="center">Create a new Account</h2>
<p>

<form method=POST action="createAccount.jsp">
<table border=0 cellpadding="0" cellspacing=5 bgcolor="lightgreen" width=75% align="center">
    <tr>
        <td align=right colspan=2>Desired Account name:
    </td><td>
        <input type=text size="10" name=userName value='<%= createAccount.getUserNameForWeb() %>'><br><p>
        </td><td colspan="2"></td>
    </tr><tr>
        <td colspan="2"></td><td colspan="2" align=right> Account Password:
        </td><td colspan="1"><input type=password size="10" name="password">
        </td>
    </tr><tr>
        <td colspan="2"></td><td colspan="2" align=right> <small>(optional)</small> Retype<br>Account Password:
        </td>
        <td colspan="1"><input type=password size="10" name="password2">
        </td>
    </tr><tr>
        <td colspan="2" align=right>
            <p><br>Email Address<br> <small>(confirmation will be required)</small>:
        </td><td>
            <input type=text size="10" name="emailAddress" value="<%= createAccount.getEmailAddressForWeb() %>">
        </td><td colspan="2"></td>
    </tr><tr>
        <td colspan="1">&nbsp;</td> <td align=right><p><input class="smallFontButton" type=submit name=action value="Submit" align=center> </td>
        <td colspan="3"></td>
    </tr>
</table>
</form>

 <% if (createAccount.hasWarnings()) { %>
    <p align="center" class="userMessage"><%= createAccount.getWarnings() %>
 <% } %>

<br>

<form method=POST action='createAccount.jsp'>
    <label> <input type=button name='close' onclick="toggleVisibility('confirmDetails');"> enter confirmation code:</label>
    <div id='confirmDetails' style='display:none;background:lightgray'>
        <table border=1 cellpadding="0" cellspacing=3>
            <tr> <td>Registered name:
                    <input type=text name='userName' size="10" value='<%= createAccount.getUserNameForWeb() %>'>
            <tr> <td>Confirmation code from email:
                <input type="text" size="10" name="confirmation">
            <tr><td align="center" />
                <input type=submit class='smallFontButton'  name=action value='Submit'>
        </table>
    </div>
</form>

<% createAccount.commitTransaction(); %>

<p>

</body>
</html>
