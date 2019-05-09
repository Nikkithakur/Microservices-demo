# Microservices-demo
![Architecture diagram](https://github.com/Nikkithakur/Microservices-demo/blob/master/ArchitectureDiagram.png)

# Key generation
keytool -genkey -alias selfsigned -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore mykeystore.jks -validity 360

# For maven deppendency errors

check local repository and settings.xml is properly configured with maven in eclipse or not

delete file from eclipse, not from disk
delete .classpath, .settings, .project
reimport whole project as maven project

## db-service endpoints
- [POST]create Account https://localhost:65351/rest/db/createAccount with payload
- [GET] accountdetails https://localhost:65351/rest/db/{phonenumber}
- [GET] transaction list https://localhost:65351/rest/db/{phonenumber}/transactions
- [GET]makepayment https://localhost:65351/rest/db/makePayment/{benefactor}/{beneficiary}/{transferAmount}

## eureka-service
- https://localhost:65350/eureka/

## accountdetails-service
