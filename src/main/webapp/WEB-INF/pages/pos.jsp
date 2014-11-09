<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <script src="js/moment-with-locales.js"></script>
</head>
<body>
<h3>*******************购物清单*******************</h3  >
<span id="date"></span><br>
<script>
    var date = moment(new Date()).format("YYYY-MM-DD HH:mm:ss");
    document.getElementById("date").innerHTML = date;
</script>
<c:forEach items="${categoryCartItems}" var="categoryList">
    ${categoryList.category.name}<br>
    <c:forEach items="${categoryList.cartItemList}" var="cartItem">
        ${cartItem.barcode}&nbsp;&nbsp;&nbsp;&nbsp;
        ${cartItem.itemName}&nbsp;&nbsp;&nbsp;&nbsp;
        ${cartItem.price}/${cartItem.itemUnit}&nbsp;&nbsp;&nbsp;&nbsp;
        ${cartItem.num}&nbsp;&nbsp;&nbsp;&nbsp;
        ${cartItem.sumPrice}&nbsp;&nbsp;&nbsp;&nbsp;
        ${cartItem.promotionPrice}<br>
    </c:forEach><br>
</c:forEach>
优惠前：${sumPrice}<br>
优惠后：${sumPrice-promotionPrice}<br>
优惠差价：${promotionPrice}<br>
</body>
</html>
