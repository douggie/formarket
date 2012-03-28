<html>
<head>
<jsp:useBean id="login"  scope="request" class="net.commerce.zocalo.JspSupport.WelcomeScreen" />
<jsp:setProperty name="login" property="*" />
<title>Please enter your user name and password</title>
<!--
Copyright 2007 Chris Hibbert.  All rights reserved.
Copyright 2006 CommerceNet Consortium, LLC.  All rights reserved.

This software is published under the terms of the MIT license, a copy
of which has been included with this distribution in the LICENSE file.
-->
</head>
<body bgcolor="CCFFCC">
<% login.beginTransaction(); %>
<% login.processRequest(request, response); %>

<table border=0 cellspacing=0 cellpadding=50 width="90%" > <tbody>
  <tr> <td align="center">
        <img src="images/logo.zocalo.jpg" height=81 width=250 align="top" >
       </td>
  </tr>
  <tr> <td align="center">
           <%= login.getWarning() %>
<% login.commitTransaction(); %>
       </td>
  </tr>
  <tr> <td align="center">
      Please login to access the zocalo service:<br><p>
      <form method=GET action=Welcome.jsp>
      <table border=0 cellspacing=0 cellpadding=0 > <tbody>
      <tr> <td>
            name:
      </td></tr>
          <tr> <td>
          <input type=text name=userName ><br>
            </td></tr>
          <tr> <td>
            password:
          </td></tr>
          <tr> <td>
              <input type=password name=password ><br>
            <input type=submit name=action value="Submit">
          </td>
          </tr>
      </tbody></table>
      </form>
  </td>
  </tr>
</tbody></table>

<p>
<table border=1 cellspacing=2 cellpadding=10 width="70%" > <tbody>
    <tr> <td align="center">No Account?  <a href="createAccount.jsp">Create a new Account</a>.</td>
    </tr>
</tbody></table>

</body>
</html>
