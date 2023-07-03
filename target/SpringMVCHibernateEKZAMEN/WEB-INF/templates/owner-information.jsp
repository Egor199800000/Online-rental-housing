<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

<form:form action="homePage" modelAttribute="owner">

    <form:hidden path="id"/>

    Name ${owner.name}
    <br>
    Surname ${owner.surname}
    <br>
    Rented ${owner.rating}
    <br>
    <br>
    <input type="submit" value="Back home page">
</form:form>

</body>
</html>