# IMS

An Inventory Management System, with the ability to add, create, read and delete; Users, Customers, Items, Products and Orders.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisite

You will need to download the following:

* [Java](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) - version 8+ is recommended
* [Maven](https://www.baeldung.com/install-maven-on-windows-linux-mac)
* [MySQL](https://www.mysql.com/) 

## Installation
To run the application backend you will need to run the following lines of code
```sh
git clone https://github.com/ayesharajaqa/ims
cd ims-server/server
mvn spring-boot:run
```
## Production
To change the configuration for this application, check the server/src/main/resources/application.properties.

The default configuration uses port 3306 and connects to a mysql database called ims.
The application will automatically generate the schema necessary for the application to work.

The default configuration uses the username and password for the database as "root" and "password"

## Built with
* [Eclipse](https://www.eclipse.org/downloads/)

## Versioning
We use SemVer for versioning. For the versions available, see the tags on this repository.

## Authors
* Ayesha Raja

## License
This project is licensed under the MIT License - see the LICENSE file for details
