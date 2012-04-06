<html>
<head>
<title>Available Markets</title>
<!--
Copyright 2007-2009 Chris Hibbert.  All rights reserved.
Copyright 2005, 2006 CommerceNet Consortium, LLC.  All rights reserved.

This software is published under the terms of the MIT license, a copy
of which has been included with this distribution in the LICENSE file.
-->
<jsp:useBean id="markets" scope="session" class="net.commerce.zocalo.JspSupport.MarketDisplay" />
<jsp:setProperty name="markets" property="*" />
    <link rel="stylesheet" type="text/css" href="trading.css">
    <script type="text/javascript" src="display.js"></script>
</head>
<body>
<p align=center>
<font size = 7><b> Available Markets</b></font>
</p>
<p align=left>
<font size=5>Below you'll find the markets which are presently active. Select a market.</font>

<% markets.beginTransaction(); %>
<% markets.processRequest(request, response); %>

<%= markets.navButtons() %>

<p>
<h2 align="center">You're logged in as: <%= markets.getUserName() %></h2>
<%= markets.getCashBalanceDisplay() %>
<p>

<% if ( markets.marketsExist()) { %>
    <%= markets.getMarketNamesTable() %>
<% } else { %>
    <p> No markets defined yet.
<% } %>
<% markets.commitTransaction(); %>

<p>
<br>
<p>

</body>
</html>
