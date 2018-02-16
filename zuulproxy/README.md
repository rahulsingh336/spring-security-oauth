# Spring zuul proxy

1.) Post request for accessing token

http://localhost:9180/oauth/auth/oauth/token?client_id=fooClientIdPassword&client_secret=secret&grant_type=password&username=john&password=123

2.) Access secured resource with below header
Authorization:Bearer <REPLACE TOKEN>


