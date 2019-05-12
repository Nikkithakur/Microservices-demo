# Microservices-demo
![Architecture diagram](https://github.com/Nikkithakur/Microservices-demo/blob/master/ArchitectureDiagram.png)

# Key generation
keytool -genkey -alias selfsigned -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore mykeystore.jks -validity 360

# For maven deppendency errors

check local repository and settings.xml is properly configured with maven in eclipse or not

delete file from eclipse, not from disk
delete .classpath, .settings, .project
reimport whole project as maven project

Building into single fat jar using maven shade plugin and providing profile name to run specific configurations and build jar name.

to include ojdbc jar into fat jar, we need to install ojdbc jar into maven local repo, by using below commands
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
- [POST]create Account http://localhost:65351/db/createAccount with payload
- [GET] accountdetails http://localhost:65351/db/{phonenumber}
- [GET] transaction list http://localhost:65351/db/{phonenumber}/transactions
- [GET]makepayment http://localhost:65351/db/makePayment/{benefactor}/{beneficiary}/{transferAmount}

## eureka-service
- http://localhost:65350/eureka/

## accountdetails-service

- [POST]create Account http://localhost:65352/accountsService/getDetails/{phoneNumber}
- [GET] accountdetails http://localhost:65352/accountsService/addAccountDetails
- [GET] transaction list http://localhost:65352/accountsService/{phoneNumber}/transactions

## payments-service endpoints

- [GET]makepayment http://localhost:65353/paymentServices/makePayment/{benefactor}/{beneficiary}/{amount}

