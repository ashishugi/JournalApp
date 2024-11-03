<h1>Response Entity</h1>


1. 2XX
   1. 200 
   2. 201 - created
   3. 204 - successful but no content to return.
2. 3XX - further step are needed to complete the request or redirection
3. 4XX - error on client part
   1. 400 - bad request
   2. 401 - unauthorized
   3. 404 - not found.
4. 5XX - error on server/spring boot application
   1. 500 - internal server error
   2. 502 - bad gateway
   3. 503 - service unavailable


<h2>Response Entity </h2>

1. Response Entity class is part of spring framework and commonly used in spring boot applications to customize the HTTP response.
2. 