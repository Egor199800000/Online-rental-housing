<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!doctype html>
<html>

<body>
<br>
<br>

Range by price houses
<form action="priceRange">

  <input type="text" name="from" placeholder="from"/>
  <p></p>
  <input type="text" name="to" placeholder="to"/>

  <input type="submit"/>

</form>


<br>
<br>
<br>

  <form:form action="profileUser"  modelAttribute="authUser">
    <c:url var="profileButton" value="profile">
      <c:param name="authUser" value="${authUser.id}"/>   <--внутри ссылка параметр с id-->
    </c:url>
    <p><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
      <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z"/>
    </svg>${authUser.name}</p>
    <input type="button" value="Profile" onclick="window.location.href='${profileButton}'">
  </form:form>
  <br>

  <div style="margin-left: auto" class="container button">
    <input type="button" value="Register"
           onclick="window.location.href='addNewUser'"/>
     <br>
    <input type="button" value="Sign in"
           onclick="window.location.href='signIn'"/>
  <br>
    <form:form action="addNewHouse"  modelAttribute="authUser">
      <c:url var="addNewHouse" value="addNewHouse">
        <c:param name="authUserId" value="${authUser.id}"/>   <--внутри ссылка параметр с id-->
      </c:url>
      <input type="button" value="To add an advert" onclick="window.location.href='${addNewHouse}'">
    </form:form>
  <div>
  <br>
  <br>

    <p style="display:inline">
        <input type="button" value="sort by price asc"
               onclick="window.location.href='priceAsc'"/>

                   <input type="button" value="sort by price desc"
                          onclick="window.location.href='priceDesc'"/>
    </p>

  <br>
  <br>
  <br>

<div>

  <c:forEach var="house" items="${allHouses}">

    <c:url var="infoHouseButton" value="showDetails">
      <c:param name="houseId" value="${house.id}"/>   <--внутри ссылка параметр с id-->
    </c:url>


      <p>Price: ${house.price}</p>
      <p>Address: ${house.address}</p>
      <p>
        <input type="button" value="Show" onclick="window.location.href='${infoHouseButton}'">
      </p>


  </c:forEach>



</div>




</body>
</html>



<%--<a href="signIn">SignIn</a>--%>