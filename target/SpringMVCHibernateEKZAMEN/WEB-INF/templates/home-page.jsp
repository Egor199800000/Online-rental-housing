<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Егор
  Date: 10.06.2023
  Time: 1:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <form:form action="showHouses" modelAttribute="house">   <%--modelAttribute=""--%>

        Name ${authUser.name}
        <br>
        Surname ${authUser.surname}
        <br>
        Country ${authUser.country}
        <br>
        City ${authUser.city}
        <br>
        Email ${authUser.email}
        <br>
        Rating ${authUser.rating}
        <br>
        <br>
        <input type="submit" value="show houses">
    </form:form>
</head>
<body>

</body>
</html>
