[Test Allure Report](https://ilkarion.github.io/Your-Diary-Auto-testing/) 

This repository contains automated UI tests for [Diary Cosmic](https://github.com/Ilkarion/diary_cosmic), a personal diary web application.

The main goal of this project is to practise and demonstrate UI test automation using **Java, Selenium WebDriver, TestNG, Maven and Allure**.

[Test Cases](https://github.com/Ilkarion/Your-Diary-Auto-testing/blob/main/test-cases.md) - [learned_new_stuff](https://github.com/Ilkarion/Your-Diary-Auto-testing/blob/main/i_learned.md) - [classes_code](https://github.com/Ilkarion/Your-Diary-Auto-testing/tree/main/src/main/java) - [tests_execution_code](https://github.com/Ilkarion/Your-Diary-Auto-testing/tree/main/src/test/java) 

## Example of **report** from Allure 

| Passed Report | Attached Image to Bug by Script |
|:---:|:---:|
| ![Passed Report](imgs/passed_report2.png) | ![Bug Report](imgs/bug_report.png) |
| **Passed Summary Report** | **Graphs** |
| ![Passed Summary](imgs/passed_report.png) | ![Graphs](imgs/graphs.png) |
| **4-Stream Testing** | **1-Stream Testing** |
| ![4-Stream Report](imgs/4-stream-report.png) | ![1-Stream Report](imgs/report.png) |
| **Test from CMD** | 
| ![Command Line Report](imgs/report-run-from-commandLine.png) | > Note: This project uses a free-tier database. Occasional test timeouts may occur during CI/CD runs due to resource limits, which realistically simulates real-world network bottlenecks.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Selenium WebDriver 4.52.0 | Browser automation |
| TestNG 7.12.0 | Test execution and assertions |
| Maven | Dependency management and build |
| Allure | Test reporting and screenshots |
| GitHub Actions | CI automation |
| Google Chrome | Browser under test |


## Project Structure
```
Your-Diary-Auto-testing/
│
├── .github/
│   └── workflows/
│       └── run-tests.yml
│
├── imgs/
│   └── Allure report screenshots
│
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── IntroPage.java
│   │       ├── SignPage.java
│   │       └── CreateEntryPage.java
│   │
│   └── test/
│       └── java/
│           ├── IntroPageTests.java
│           ├── SignPageTests.java
│           ├── CreateEntryTests.java
│           └── utils/
│               ├── BaseTest.java
│               └── ScreenshotListener.java
│
├── .gitignore
├── pom.xml
├── test-cases.md
├── testOrder.xml
└── README.md
```

## CI Workflow

- GitHub Actions prepares the Java environment.
- Maven installs project dependencies.
- Selenium tests are executed.
- Allure generates a test report.
- The report is published to GitHub Pages.

## Notes and Limitations

- Tests run against the deployed Diary Cosmic application.
- The test environment uses Chrome in headless mode.
- A dedicated test account is required.
- The first request may take longer when the backend is waking up after inactivity.
- Current coverage focuses on UI testing of the Intro, Sign and Create Diary Entry pages.
- API testing, diary editing and deletion are not included in this automation suite.


  Author: Ilkarion













