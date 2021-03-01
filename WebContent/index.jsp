<%@page import="java.util.ArrayList"%>
<%@page import="service.ItemServiceImpl"%>
<%@page import="service.itemService"%>
<%@page import="model.Item"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
itemService itemService = new ItemServiceImpl();
 //Insert an Item
if (request.getParameter("itemCode") != null)
 {
	System.out.println(request.getParameter("itemName"));
 String stsMsg = itemService.insertItem(request.getParameter("itemCode"),
 request.getParameter("itemName"),
 request.getParameter("itemPrice"),
 request.getParameter("itemDesc"));
 
 session.setAttribute("statusMsg", stsMsg);
 }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Items Management</h1>
<form method="post" action="index.jsp">
 Item code: <input name="itemCode" type="text"><br> Item
 name: <input name="itemName" type="text"><br> Item price:
 <input name="itemPrice" type="text"><br> Item
 description: <input name="itemDesc" type="text"><br> <input
 name="btnSubmit" type="submit" value="Save">
</form>


<table>
<tr>
<td>Item Code</td>
<td>Item Name</td>
<td>Price</td>
<td>Description</td>
<td></td>
<td></td>
</tr>

<%
 ArrayList<Item> items = itemService.getItems(); 
 for(Item item : items) {
%>
<tr>
<td><%=item.getItemCode() %></td>
<td><%=item.getItemName() %></td>
<td><%=item.getItemPrice() %></td>
<td><%=item.getItemDesc() %></td>
<td><form action="index.jsp"><input name="dltId" value="<%item.getItemCode();%>"hidden><input type="submit" value="Delete"></form></td>
<td><form action="index.jsp"><input name="updateId" value="<%item.getItemCode();%>"hidden><input type="submit" value="Update"></form></td>
</tr>
<%} %>
</table>
<br>

</body>
</html>