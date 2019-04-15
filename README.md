# Microservices-demo
![Architecture diagram](https://github.com/Nikkithakur/Microservices-demo/blob/master/ArchitectureDiagram.png)

## db-services endpoints
- [POST]create Account http://localhost:62351/rest/db/createAccount with payload
- [GET] accountdetails http://localhost:62351/rest/db/{phonenumber}
- [GET] transaction list http://localhost:62351/rest/db/{phonenumber}/transactions
- [GET]makepayment http://localhost:62351/rest/db/makePayment/{benefactor}/{beneficiary}/{transferAmount}
