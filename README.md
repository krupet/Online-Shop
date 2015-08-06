# Online Shop

Web application performing the functions of a primitive online store. You can login as admin (login: admin, password:
admin).

### Run

To run this application you need following steps:

```sh
$ git clone https://github.com/krupet/Online-Shop.git
$ cd Online-Shop/
$ mvn clean tomcat7:run
```

or

```sh
$ git clone clone https://github.com/krupet/Online-Shop.git
$ cd Online-Shop/
$ mvn clean install
```
and deploy war file into application server
### Requirements
In order to run this application you are need to be installed on youe system next components:
  - Java JDK 8
  - Maven 3
  - Git
  - Tomcat 7/8
 
### User roles

There is three basic user-roles: admin (login: admin, password: admin), manager (l:manager, p: manager) and user 
(l: user, p: user). Restrictions: user role - has ability to create orders and view his statistics, manager can create
 products and manage orders status (in addition to users abilities) and admin can create new users and change its roles. 

### Technologies
Spring (Core & Security), Hibernate, H2 in-memory database, JSF (mostly Primfaces), Tomcat and Maven.