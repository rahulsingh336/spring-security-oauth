# spring auth-service

1.) Postman request:- 

   a.) get auth-code
   http://localhost:9181/auth/oauth/authorize?response_type=code&client_id=html5&redirect_uri=http://google.com
   
   b.) get access-toke
   http://localhost:9181/auth/oauth/token?client_id=html5&client_secret=password&grant_type=authorization_code&code=LTbmG0&redirect_uri=http://google.com
   
   

with basic authorization header for username and password configured.

