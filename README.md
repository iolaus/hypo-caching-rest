hypo-caching-rest
=================

Rest backand for the hypoport caching app 

put user
curl -X POST -H "Content-Type: application/json" -d '{"imei":"5423254677657","coins":20}' http://localhost:8080/hypo-caching-rest/services/users
curl -X POST -H "Content-Type: application/json" -d '{"imei":"87654345","coins":33}' http://localhost:8080/hypo-caching-rest/services/users

get user
http://localhost:8080/hypo-caching-rest/services/users/5423254677657
http://localhost:8080/hypo-caching-rest/services/users/87654345

get all users
http://localhost:8080/hypo-caching-rest/services/users