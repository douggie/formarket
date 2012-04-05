<html>
<head>
<jsp:useBean id="account" scope="request" class="net.commerce.zocalo.JspSupport.AccountDisplay" />
<jsp:setProperty name="account" property="*" />
<title>Welcome to the Marketplace!</title>
<!--
Copyright 2007 Chris Hibbert.  All rights reserved.
Copyright 2005 CommerceNet Consortium, LLC.  All rights reserved.

This software is published under the terms of the MIT license, a copy
of which has been included with this distribution in the LICENSE file.
-->
    <link rel="stylesheet" type="text/css" href="trading.css">
    <script type="text/javascript" src="display.js"></script>
</head>
<body>
<p align=center>
<image src="images/Logo/iisclogo.jpg" height = 400 width=1100 align=center>
</p>
<hr noshade>

<p align=left>


<% account.beginTransaction(); %>
<% account.processRequest(request, response); %>

<%= account.navButtons("account.jsp") %>

<%= account.descriptionHtml() %>

<p> </p>

<% account.commitTransaction(); %>

<br>

</body>
</html>
