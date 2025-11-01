# 🐾 PetStoreAutomation

## 📘 Overview
**PetStoreAutomation** is a modular automation testing framework built for the **Swagger PetStore** API and UI applications.  
It supports **REST API**, **UI**, and **data-driven** testing, ensuring reliability and consistency across test runs.  
The framework integrates **Extent Reports** for detailed visual reporting and is **CI/CD ready** for Jenkins or GitHub Actions.

---

## 🧩 Table of Contents
- [Features](#-features)
- [Getting Started](#-getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
- [Running the Tests](#-running-the-tests)
- [Project Structure](#-project-structure)
- [Frameworks & Tools](#-frameworks--tools)
- [Extent Reports Integration](#-extent-reports-integration)
- [CI/CD Integration (Jenkins Example)](#-cicd-integration-jenkins-example)
- [Reporting](#-reporting)
- [Contributing](#-contributing)
- [License](#-license)

---

## 🚀 Features
✅ API automation using **REST Assured**  
✅ **Data-driven testing** with Excel and JSON  
✅ Reusable utilities for test data, logging, and assertions  
✅ **Extent Reports** integration for detailed HTML reports  
✅ Seamless CI/CD execution using **Jenkins** or **GitHub Actions**

---

## ⚙️ Getting Started

### 🧱 Prerequisites
- **Java 8+**
- **Maven 3.6+**
- **IDE**: IntelliJ IDEA / Eclipse
- (Optional) **TestNG Plugin** for test suite execution
- Access to the PetStore API or UI endpoints

### 💾 Installation
Clone the repository and build:
```bash 
git clone https://github.com/1hirachy/PetStoreAutomation.git
cd PetStoreAutomation
mvn clean install
```

### 🧪 Running the Tests
▶️ Via IDE

1. Open testng.xml
2. Right-click → Run ‘testng.xml’

💻 Via Command Line
```bash 
mvn test -DsuiteXmlFile=testng.xml
```

### Project Structure
```bash 
PetStoreAutomation/
├── src/
│   ├── main/
│   │   └── java/                # Utilities and helper libraries
│   ├── test/
│   │   └── java/                # Test classes
├── testData/                    # External Excel/JSON/CSV data
├── reports/                     # Extent & TestNG Reports
├── logs/                        # Log4j logs
├── pom.xml                      # Maven dependencies
├── testng.xml                   # TestNG suite definition
└── README.md
```

### 🧰 Frameworks & Tools

| Category                | Tools / Technologies                   |
| ----------------------- | -------------------------------------- |
| **API Testing**         |  REST Assured                   |
| **Unit Test Framework** | TestNG                                 |
| **Reporting**           | Extent Reports, TestNG Default Reports |
| **Build Management**    | Maven                                  |
| **CI/CD**               | Jenkins, GitHub Actions                |
| **Data Source**         | Excel, JSON, CSV                       |
| **Logging**             | Log4j                                  |
| **Language**            | Java                                   |


### 🌈 Extent Reports Integration
This framework uses ExtentReports for visually rich HTML reports with status, logs, screenshots, and environment info.

📁 Report Location

After each run, reports are generated in:
```bash
/reports/ExtentReport.html
```

⚙️ Example Initialization
```bash
ExtentSparkReporter spark = new ExtentSparkReporter("reports/ExtentReport.html");
extent = new ExtentReports();
extent.attachReporter(spark);
extent.setSystemInfo("Environment", "QA");
extent.setSystemInfo("Tester", "Md Chowdhury");
```

### 📊 Reporting

#### Reports are stored under /reports and include:

- ExtentReport.html — Rich HTML report with screenshots
- TestNG Summary — Pass/Fail/Skip breakdown
- Logs — Execution logs under /logs

### 🪪 License

Licensed under the MIT License — see the LICENSE
file for details.

