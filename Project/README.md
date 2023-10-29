# Docker_Nginx_Spring-Gradle_MySQL
Docker-multi-compose-example

Nginx
Gradle-Spring-JDBCTemplate-CRUD
MySQL
[Open in Docker Dev Environments](https://open.docker.com/dashboard/dev-envs?url=https://github.com/ooMia/Docker_Nginx_Spring-Gradle_MySQL/tree/main/)

### getOrderDetails
GET [http://localhost:8080/app/orderdetails](http://localhost:8080/app/orderdetails)

### getOrderDetailsByOrderId
GET [http://localhost:8080/app/orderdetails?orderId=1](http://localhost:8080/app/orderdetails?orderId=1)

### createOrderDetails
POST http://localhost:8080/app/orderdetails
Content-Type: application/json

{
"order_id": 12,
"product_detail_id": 12,
"order_detail_unit_price": 12300,
"order_detail_quantity": 1,
"order_detail_discount": 1000,
"order_detail_price": 11300,
"order_detail_status": "arrived",
"delivery_id": 15223
}


### updateOrderDetails
PATCH http://localhost:8080/app/orderdetails/1
Content-Type: application/json

{
  "order_detail_status": "arrived"
}

### deleteOrderDetails
DELETE http://localhost:8080/app/orderdetails/1
