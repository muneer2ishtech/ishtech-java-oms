## User Sign-up

```sh
curl --location 'http://localhost:8080/api/v1/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "admin1@ishtech.fi",
    "password": "Test1234",
    "passwordConfirm": "Test1234",
    "firstName": "Admin1",
    "lastName": "Admin1",
    "acceptTermsConditions": true,
    "lang": "en"
}'

curl --location 'http://localhost:8080/api/v1/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "testuser1@ishtech.fi",
    "password": "Test1234",
    "passwordConfirm": "Test1234",
    "firstName": "Test1",
    "lastName": "User1",
    "acceptTermsConditions": true,
    "lang": "en"
}'

curl --location 'http://localhost:8080/api/v1/auth/signup' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "testuser2@ishtech.fi",
    "password": "Test1234",
    "passwordConfirm": "Test1234",
    "firstName": "Test2",
    "lastName": "User2",
    "acceptTermsConditions": true,
    "lang": "en"
}'

```

### Change role to admin
```sql
UPDATE t_user_role SET role_name = 'ADMIN' WHERE user_id = (SELECT id FROM t_user WHERE email = 'admin1@ishtech.fi');

```

##  User  sign-in

```sh
curl --location 'http://localhost:8080/api/v1/auth/signin' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "admin1@ishtech.fi",
    "password": "Test1234"
}'

curl --location 'http://localhost:8080/api/v1/auth/signin' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "testuser1@ishtech.fi",
    "password": "Test1234"
}'

curl --location 'http://localhost:8080/api/v1/auth/signin' \
--header 'Content-Type: application/json' \
--data-raw '{
    "email": "testuser2@ishtech.fi",
    "password": "Test1234"
}'

```

# Products

```sh
curl --location 'http://localhost:8080/api/v1/products' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer TODO_JWT' \
--data '{
    "name": "Work Table 30cmX50cm",
    "unitPrice": 100
}'

curl --location 'http://localhost:8080/api/v1/products' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer TODO_JWT' \
--data '{
    "name": "Chair with Wheels",
    "unitPrice": 50
}'

curl --location 'http://localhost:8080/api/v1/products' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer TODO_JWT' \
--data '{
    "name": "Coffee Table",
    "unitPrice": 75
}'

curl --location 'http://localhost:8080/api/v1/products' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer TODO_JWT' \
--data '{
    "name": "Sofa",
    "unitPrice": 150
}'

```

# Customer Discount

```
curl --location 'http://localhost:8080/api/v1/customer-discounts' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer TODO_JWT' \
--data '{
    "customerId": 2,
    "productId": 1,
    "discountType": "PRODUCT_PERCENT",
    "discountPercent": 0.05
}'

curl --location 'http://localhost:8080/api/v1/customer-discounts' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer TODO_JWT' \
--data '{
    "customerId": 3,
    "discountType": "SALES_ORDER_PERCENT",
    "discountPercent": 0.02
}'

curl --location 'http://localhost:8080/api/v1/customer-discounts' \
--header 'Content-Type: application/json' \
--header 'Authorization: Bearer TODO_JWT' \
--data '{
    "customerId": 2,
    "discountType": "SALES_ORDER_PERCENT",
    "discountPercent": 0.03
}'

```
