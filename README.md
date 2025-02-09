# BankLib

[![codecov](https://codecov.io/gh/rbaoueb/banklib/branch/main/graph/badge.svg)](https://codecov.io/gh/rbaoueb/banklib)
[![CodeFactor](https://www.codefactor.io/repository/github/rbaoueb/banklib/badge)](https://www.codefactor.io/repository/github/rbaoueb/banklib)

banklib is a JAVA library which allows us process operations on our bank account

## Installation
you can import the source project on your IDE or build the jar and push it to your maven repository :

```bash
git clone https://github.com/rbaoueb/banklib.git
cd banklib
mvn clean install
```

then add the generated dependency to your project pom.xml:
```xml
<dependencies>
    <dependency>
      <groupId>com.mrbaoueb</groupId>
	  <artifactId>banklib</artifactId>
      <version>1.0.0</version>
    </dependency>
</dependencies>
```

## Usage
you can use this library by using already exposed "in ports" through your services (in package com.bank.ridha.domain.port.in)


**Note: for data models, we can use different models with mapping layers between those models if we need to convert this library to a web lib (such as custom spring boot starter with in rest adapters and out persistence adapters ti separate domain models, dto models and entities models)**