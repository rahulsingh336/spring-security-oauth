# spring auth-service

1.) Postman request:- 

   a.) get auth-code
   http://localhost:9181/auth/oauth/authorize?response_type=code&client_id=fooClientIdPassword&redirect_uri=http://google.com
   
   b.) get access-toke
   http://localhost:9181/auth/oauth/token?client_id=fooClientIdPassword&client_secret=secret&grant_type=password&username=john&password=123
   
   

with basic authorization header for username will be clientId and password configured.

