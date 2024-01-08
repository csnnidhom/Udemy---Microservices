# CURRENCY-EXCHANGE: http://localhost:8383/currency-exchange/from/USD/to/INR

# CURRENCY-COVERSION (FEIGN) : http://localhost:8181/currency-conversion-feign/from/USD/to/INR/quantity/10

# CURRENCY-COVERSION (REST-TEMPLATE) : http://localhost:8181/currency-conversion-rest-template/from/USD/to/INR/quantity/10

# EUREKA : http://localhost:8761/

# API-GATEWAY : Memanggil microservices menggunakan Eureka dengan melalui API Gateway
    - http://localhost:8484/currency-exchange/from/USD/to/INR
    - http://localhost:8484/currency-conversion-feign/from/USD/to/INR/quantity/10
    - http://localhost:8484/currency-conversion-rest-template/from/USD/to/INR/quantity/10

