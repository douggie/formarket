<html>
<head>
<jsp:useBean id="login"  scope="request" class="net.commerce.zocalo.JspSupport.WelcomeScreen" />
<jsp:setProperty name="login" property="*" />
<title>Welcome to Zocalo Prediction Markets!</title>
<!--
Copyright 2007 Chris Hibbert.  All rights reserved.
Copyright 2006 CommerceNet Consortium, LLC.  All rights reserved.

This software is published under the terms of the MIT license, a copy
of which has been included with this distribution in the LICENSE file.
-->
</head>
<body background-image="images/welcome.jpg">
<% login.beginTransaction(); %>
<% login.processRequest(request, response); %>

<table border=0 cellspacing=0 cellpadding=50 width="90%" > <tbody>
  <tr> <td align="left">
        <img src="images/logo.zocalo.jpg" height=100 width=250 align="top" >
       </td>
       <td align="center">
       <img src="images/csa-logo.png" height=100 width=200 align="top">
       </td>
       <td align="right">
       <img src="images/Wall_Street.jpg" height=100 width=250 align="top">
       </td>
  </tr>
  </table>
  
  <p align="center">
  <table border=5 width="90%">
  <tr> <td align="center">
           <%= login.getWarning() %>
<% login.commitTransaction(); %>
       </td>
  </tr>
  <tr> <td align="center">
      <font size = 5>Please login to enter the market: </font> <br><p>
      <form method=GET action=Welcome.jsp>
      <table border=0 cellspacing=0 cellpadding=0 > <tbody>
      <tr> <td>
            <font size = 4 color = red><b>Username:</b> </font>
      </td></tr>
          <tr> <td>
          <input type=text name=userName ><br>
            </td></tr>
          <tr> <td>
            <font size = 4 color = red><b> Password: </b> </font>
          </td></tr>
          <tr> <td>
              <input type=password name=password ><br>
            <input type=submit name=action value="Sign In">
          </td>
          </tr>
      </tbody></table>
      </form>
  </td>
  </tr>
</tbody></table>

<p>
<table border=1 cellspacing=2 cellpadding=10 width="100%" > <tbody>
    <tr> <td align="center"><font size=4>If you're a new user, <a href="createAccount.jsp"> click here to create a new Account</a>.</font></td>
    </tr>
</tbody></table>

</body>
</html>
