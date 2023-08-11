<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<body>

  <c:forEach var="house" items="${myHouses}">

      <p>Price: ${house.price}</p>
      <p>Address: ${house.address}</p>

          <c:url var="deleteHouse" value="Delete house">
            <c:param name="houseId" value="${house.id}"/>   <--внутри ссылка параметр с id-->
          </c:url>
                  <input type="button" value="Delete house" onclick="window.location.href='${deleteHouse}'">
  </c:forEach>

</body>
</html>