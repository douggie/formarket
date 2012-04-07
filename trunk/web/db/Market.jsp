<html>
<head>
<title>The IISC Prediction League</title>
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
<img src="images/Logo/MarketJsp.gif">
</p>
<p align=left>
<font size = 5><b> Below you'll find the league which is presently active. Just click on it 
					to get started.</b></font>
</p>
<p align=left>
<% markets.beginTransaction(); %>
<% markets.processRequest(request, response); %>

<%= markets.navButtons() %>

<p align=center>
<font size=5 color=red><b>You're logged in as: <%= markets.getUserName() %></b></font>
<%= markets.getCashBalanceDisplay() %>
<p>

<% if ( markets.marketsExist()) { %>
    <%= markets.getMarketNamesTable() %>
<% } else { %>
    <p> No markets defined yet.
<% } %>
<% markets.commitTransaction(); %>

<hr>
<p align=center>
<font size=5><b><a href="pages/Credits.html">Credits</a><br> 	
<a href="pages/Help.html">Click here for help</a>
<hr>
</p>

</body>
</html>
