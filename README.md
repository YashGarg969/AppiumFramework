# 🧪 Appium Automation Framework (Java + TestNG)
# 📘 Overview
This project is a modular and extensible Appium automation framework built in Java using TestNG.
It provides a scalable setup for automating mobile app testing on both emulator and real Android devices.
The framework supports:
* Environment-based configuration
* Dynamic capability loading via reflection
* Test data management via DataProviders
* Automatic screen recording
* Allure report integration
* App lifecycle management (install/remove)
* Centralized utilities for reusability

# ⚙️ Key Features
🧩 1. Environment-Based Configuration
Load environment (REAL or EMULATOR) dynamically using:
<parameter name="env" value="REAL"/>
Automatically reads corresponding JSON config files:
desiredCapabilities.json
desiredCapabilitiesEmulator.json

🧠 2. Dynamic Desired Capabilities Loader
Capabilities are read from JSON files and mapped to UiAutomator2Options via Java Reflection.
This allows new capabilities to be added without modifying the Java code.
Code: DesiredCapabilitiesLoader.loadDesiredCapabilities(env)

⚡ 3. App Configuration Management
Configurations like appPackage, appActivity, and Appium Server URL are loaded from:
src/test/java/resources/appConfig.json

🧪 4. Base Test Lifecycle
BaseTest manages:
Driver initialization
Screen recording (start/stop)
App installation/removal
Allure video attachment
Graceful teardown

🎥 5. Screen Recording + Allure Integration
Every test execution is recorded and attached to the Allure report automatically

🧮 6. Data-Driven Testing
Provided dataproviders supplies multiple test datasets using TestNG’s @DataProvider.

🧱 7. Extensible Utilities
The Utils class centralizes helper functions such as:
Video recording
File management
Logging
Allure attachment helpers

📊 8. Allure Reporting
Report includes test execution video

# 🚀 How to Run Tests
1️⃣ Prerequisites
Java 17+
Maven
Appium Server (Desktop or CLI)
Android SDK installed
Device connected or Emulator running

2️⃣ Configure Appium
Update the appiumServerUrl in appConfig.json

3️⃣ Run Tests
Run directly via TestNG XML

4️⃣ Generate Allure Report
Open Terminal and navigate to target folder in terminal and then use the below commands:
* allure generate allure-results --clean -o allure-report
* allure open allure-report

🧩 The framework is evolving!
🔧 Keep checking this repository for future enhancements, integrations, and optimizations.
💬 Contributions and ideas are always welcome.

# 👨‍💻 Author
Yash Garg










