<%--
  Created by IntelliJ IDEA.
  User: 18572
  Date: 4/13/2020
  Time: 10:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Title</title>

</head>
<body>
<div class="demo">

           <div id="seat-map">

            <div class="front">屏幕1</div>                    

        </div>

        <div class="booking-details">

            <p>影片：<span>星际穿越3D</span></p>

            <p>时间：<span>11月14日 21:00</span></p>

            <p>座位：</p>

            <ul id="selected-seats"></ul>

            <p>票数：<span id="counter">0</span></p>

            <p>总计：<b>￥<span id="total">0</span></b></p>        

            <button class="checkout-button">确定购买</button>        

            <div id="legend"></div>

        </div>

</div>
<script type="text/javascript">
    $(document).ready(function() {

        var sc = $('#seat-map').seatCharts({
            map: [
                'aaaaaaaaaaaa',
                'aaaaaaaaaaaa',
                'bbbbbbbbbb__',
                'bbbbbbbbbb__',
                'bbbbbbbbbbbb',
                'cccccccccccc'
            ],
            seats: {
                a: {
                    price   : 99.99,
                    classes : 'front-seat' //your custom CSS class
                }

            },
            click: function () {
                if (this.status() == 'available') {
                    //do some stuff, i.e. add to the cart
                    return 'selected';
                } else if (this.status() == 'selected') {
                    //seat has been vacated
                    return 'available';
                } else if (this.status() == 'unavailable') {
                    //seat has been already booked
                    return 'unavailable';
                } else {
                    return this.style();
                }
            }
        });

        //Make all available 'c' seats unavailable
        sc.find('c.available').status('unavailable');

        /*
        Get seats with ids 2_6, 1_7 (more on ids later on),
        put them in a jQuery set and change some css
        */
        sc.get(['2_6', '1_7']).node().css({
            color: '#ffcfcf'
        });

        console.log('Seat 1_2 costs ' + sc.get('1_2').data().price + ' and is currently ' + sc.status('1_2'));

    });
</script>
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/jquery.seat-charts.css" />
<script
        src="https://code.jquery.com/jquery-3.4.1.js"
        integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
        crossorigin="anonymous"></script>
<script
        src="https://code.jquery.com/jquery-3.4.1.min.js"
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
<script type="text/javascript" src="${contextPath}/resources/css/jquery.seat-charts.min.js"></script>
</body>
</html>
