<html>
<head>
<jsp:useBean id="history" scope="request" class="net.commerce.zocalo.JspSupport.TradeHistory" />
<jsp:setProperty name="history" property="*" />
<title>Your Trading History</title>
<!--
Copyright 2007, 2009 Chris Hibbert.  All rights reserved.
Copyright 2006 CommerceNet Consortium, LLC.  All rights reserved.

This software is published under the terms of the MIT license, a copy
of which has been included with this distribution in the LICENSE file.
-->
    <link rel="stylesheet" type="text/css" href="trading.css">
    <script type="text/javascript" src="display.js"></script>
</head>
<body>
<p align=center>
<img src="images/Logo/createAccountLogo.gif">
</p>
<% history.beginTransaction(); %>
<% history.processRequest(request, response); %>

<%= history.navButtons() %>

<p align="center">
<font size=7 color=blue><b><i>Trading history for <%= history.getUserName() %></b></i></font>
</p>

<%= history.tradeTable() %>
<% history.commitTransaction(); %>
<hr>
<p align=center>
<font size=5><b><a href="pages/Credits.html">Credits</a><br> 	
<a href="pages/Help.html">Click here for help</a>
<hr>
</p>
</body>
</html>
