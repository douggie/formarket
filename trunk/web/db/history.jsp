<html>
<head>
<jsp:useBean id="history" scope="request" class="net.commerce.zocalo.JspSupport.TradeHistory" />
<jsp:setProperty name="history" property="*" />
<title>Trading History</title>
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

<% history.beginTransaction(); %>
<% history.processRequest(request, response); %>

<%= history.navButtons() %>

<h2 align="center">Trading history for <%= history.getUserName() %></h2>

<%= history.tradeTable() %>
<% history.commitTransaction(); %>

</body>
</html>
