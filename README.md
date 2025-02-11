# casvisor-java-sdk

[![GitHub Actions](https://github.com/casvisor/casvisor-java-sdk/actions/workflows/maven-ci.yml/badge.svg)](https://github.com/casvisor/casvisor-java-sdk/actions/workflows/maven-ci.yml)
[![codebeat badge](https://codebeat.co/badges/e3e92eff-8b71-4903-9764-5126e855b3b6)](https://codebeat.co/projects/github-com-casvisor-casvisor-java-sdk-master)
[![codecov](https://codecov.io/gh/casvisor/casvisor-java-sdk/branch/master/graph/badge.svg?token=1C2FSTN4J8)](https://codecov.io/gh/casvisor/casvisor-java-sdk)
[![Maven Central](https://img.shields.io/maven-central/v/org.casbin/casvisor-java-sdk.svg)](https://mvnrepository.com/artifact/org.casbin/casvisor-java-sdk/latest)
[![Release](https://img.shields.io/github/release/casvisor/casvisor-java-sdk.svg)](https://github.com/casvisor/casvisor-java-sdk/releases/latest)
[![Discord](https://img.shields.io/discord/1022748306096537660?logo=discord&label=discord&color=5865F2)](https://discord.gg/5rPsrAzK7S)

Casvisor Java SDK is the official Java client for Casvisor, used to interact with Casvisor services.

## Step1. Init Config

Initialization requires 5 parameters, which are all string type:

| Name (in order)  | Must | Description                                           |
|------------------|------|-------------------------------------------------------|
| endpoint         | Yes  | Casvisor server URL, such as `http://localhost:16001` |
| clientId         | Yes  | Application.clientId                                  |
| clientSecret     | Yes  | Application.clientSecret                              |
| organizationName | Yes  | Organization name                                     |
| applicationName  | Yes  | Application name                                      |

### You can either initialize the SDK with global config

```java
CasvisorConfig config = new CasvisorConfig(endpoint, clientId, clientSecret, certificate, organizationName, applicationName);
```

## Step2. Get Service and use

Now provide service: ``RecordService``
You can create them like
```java
RecordService recordService = new RecordService(config);
```
## RecordService

``RecordService``support basic user operations, like:
- ``getRecord(String name)``, get one record by record name.
- ``getRecords()``, get all records.
- ``updateRecord(Record record)/addRecord(Record record)/deleteRecord(Record record)``, write record to database.

## Contribution

We welcome any form of contribution, including but not limited to:

1. Submit issues and suggestions
2. Submit Pull Request
3. Improve documentation

## License

This project is licensed under the [Apache 2.0 License](LICENSE).