# Microservices-demo
![Architecture diagram](https://github.com/Nikkithakur/Microservices-demo/blob/master/ArchitectureDiagram.png)

# Key generation
	keytool -genkey -alias selfsigned -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore mykeystore.jks -validity 360

	other commands:
		Check a stand-alone certificate
			- [keytool -printcert -v -file mydomain.crt]
		Check which certificates are in a Java keystore
			- [keytool -list -v -keystore keystore.jks]



## Implementing One way SSL(Https)

	In pom.xml if filtering is enabled, then exclude .jks files and create another tag which says filtering is disabled where you can include .jks files
	we have keystore and truststore
	- If we are running our client on embedded web server then only keystore is required, which verifies the public certificate of a server at client side.
	- If application 1(client or db-service) wants to connect to application 2(server or eureka-server), then application 2's public certificate neets to be imported to application 1's trust store.
	commands and server certificate common name should be equivalent to application name(the way client calls server)
		Export:
			- [keytool -export -alias db -file db.crt -keystore db.jks]
			- [keytool -export -alias eureka -file eureka.crt -keystore eureka.jks]
  
		Import:
			- [keytool -import -alias eureka -file eureka.crt -keystore db.jks]
  
	While running client application we need to pass following command line arguments using maven or gradle
	or
	can be passed as VM arguments in eclipse or sts
	- [-Djavax.net.ssl.trustStore=src\\main\\resources\\db.jks]
	- [-Djavax.net.ssl.trustStorePassword=123456]


# For maven dependency errors

- check local repository and settings.xml is properly configured with maven in eclipse or not

- if something goes wrong with maven indexing or updating, delete whole project from eclipse, not from disk delete .classpath, 	  .settings, .project
- reimport whole project as maven project

# Building jar file
- Building into single fat jar using maven shade plugin and providing profile name to run specific configurations and build jar name.

- to include ojdbc jar into fat jar, we need to install ojdbc jar into maven local repo, by using below commands
	install:install-file -Dfile=path -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0 -Dpackaging=jar

# Docker commands

- [docker build -f Dockerfile-name -t image-name .]
- [docker images]
- [docker run -p app-port:exposed-port image-name]
- [ctrl + c], to stop app
- [docker ps], to view list of containers and status
- [docker container ls -a ], to view list of containers and status
- [docker container kill container-id1 container-id2], n containers can be killed.
- [docker container rm container-id1 container-id2],n containers can be removed only after killing them.



## db-service endpoints
- [POST]create Account https://localhost:65351/db/createAccount with payload
- [GET] accountdetails https://localhost:65351/db/{phonenumber}
- [GET] transaction list https://localhost:65351/db/{phonenumber}/transactions
- [PUT]makepayment https://localhost:65351/db/makePayment with payload

## eureka-service
- https://localhost:65350/eureka/

## accountdetails-service

- [POST]create Account https://localhost:65352/accountsService/getDetails/{phoneNumber}
- [GET] accountdetails https://localhost:65352/accountsService/addAccountDetails
- [GET] transaction list https://localhost:65352/accountsService/{phoneNumber}/transactions

## payments-service endpoints

- [PUT]makepayment https://localhost:65353/paymentServices/makePayment

## Swagger urls for above projects

- DB https://localhost:65351/swagger-ui.html
- ACCOUNTS https://localhost:65352/swagger-ui.html
- PAYMENTS https://localhost:65353/swagger-ui.html

## Swagger via api-gw or zuul

- ACCOUNTS https://localhost:65354/accounts/swagger-ui.html
- PAYMENTS https://localhost:65354/payments/swagger-ui.html
