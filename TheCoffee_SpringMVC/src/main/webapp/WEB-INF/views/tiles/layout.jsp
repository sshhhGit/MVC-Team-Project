<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
<head>
<meta charset="UTF-8">
<title>layout.jsp</title>
<style type="text/css">
body {
	margin: 0px;
	background-color: #fefaf6;
}

h2{
	color: #913F09;
}
table {
	border-collapse: collapse;
	border-spacing: 0px;
	padding: 0px;
}

a:link {
  color : #913F09;
  text-decoration: none;
  font-weight: bold;
}
a:visited {
  color : #913F09;
  text-decoration: none;
}
a:hover {
  color : #913F09;
  text-decoration: none;
}
a:active {
  color : #913F09
  text-decoration: none;
}

</style>
</head>
<body>
  <table height="100%">
    <tr>
      <td height="130px">
        <tiles:insertAttribute name="top"/>
      </td>
    </tr>
    <tr align="center" >
      <td>
        <tiles:insertAttribute name="content"/>
      </td>
    </tr>
    <tr>
      <td height="180px">
        <tiles:insertAttribute name="footer"/>
      </td>
    </tr>
  </table>
</body>
</html>