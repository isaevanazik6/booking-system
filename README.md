# booking-system

```bash
Add configuration in src/main/resources/application.properties
spring.application.name=booking-system

spring.datasource.url=${H2_DATASOURCE_URL} // database url, example jdbc:h2:mem:your_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.name=${H2_DATASOURCE_NAME} // database name
spring.datasource.username=${H2_DATASOURCE_USERNAME} // database username
spring.datasource.password=${H2_DATASOURCE_PASSWORD} // database password 
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.h2.console.settings.trace=false
```

# Default Endpoints
```bash
    Endpoints:
      your_host/swagger-ui/index.html - swagger url
      your_host/h2-console - for open h2 database http url
```


# Seats Endpoint

```bash
    CRUD seat
    'your_host/seats/{id}':
      Http-Get: Get seat by Id
      
    'your_host/seats/available'
      Http-Get: Get available seats
    
    'your_host/seats'
      Http-Post: Save seat
    
    'your_host/seats/{id}'
      Http-Put: Update seat 
      
    'your_host/seats/{id}'
      Http-Delete: Delete seat
```    

# Movie Endpoint

```bash
    CRUD movie
    'your_host/movies/{id}':
      Http-Get: Get movie by Id
      
    'your_host/movies'
      Http-Get: Get movie by pagination filter
    
    'your_host/movies'
      Http-Post: Save movie 
    
    'your_host/movies/{id}'
      Http-Put: Update movie 
      
    'your_host/movies/{id}'
      Http-Delete: Delete movie 
```    

# Cinema Endpoint

```bash
    CRUD cinema
    'your_host/cinemas/{id}':
      Http-Get: Get cinema by Id
      
    'your_host/cinemas'
      Http-Get: Get cinemas 
    
    'your_host/cinemas'
      Http-Post: Save cinema 
    
    'your_host/cinemas/{id}'
      Http-Put: Update cinema 
      
    'your_host/cinemas/{id}'
      Http-Delete: Delete cinema 
```   

# MovieSession Endpoint

```bash
    CRUD movie-session
    'your_host/movie-sessions':
      Http-Post: Save movie-session
      
    'your_host/movie-sessions/{id}'
      Http-Get: Get movie-session by Id
    
    'your_host/movie-sessions/available'
      Http-Get: Get movie-session by period
```   

# Order Endpoint

```bash
    CRUD order
    'your_host/orders/add':
      Http-Post: This endpoint for add order in Cinema
    
    'your_host/orders/confirm'
      Http-Post: Change status to 'CONFIRM' order
    
     'your_host/orders/rollback'
      Http-Post: Rollback order
```   