# Appium Framework - Agent Guidelines

## Build/Test Commands
- **Build**: `mvn clean compile`
- **Test**: `mvn test`
- **Single test**: `mvn test -Dtest=ClassName#methodName`
- **Test with environment**: `mvn test -Denv=real` or `mvn test -Denv=emulator`
- **Clean build**: `mvn clean install`

## Code Style Guidelines
- **Language**: Java 17 with Maven
- **Testing Framework**: TestNG
- **Mobile Automation**: Appium + Selenium WebDriver
- **Package Structure**: 
  - `base/` - BaseTest, BasePage classes
  - `config/` - Configuration loaders (JSON files in resources/)
  - `utilities/` - Helper utilities
  - `pages/` - Page Object Model classes (if applicable)
- **Naming**: CamelCase for classes, camelCase for methods/variables
- **Dependencies**: Lombok for boilerplate reduction, Jackson for JSON handling
- **Configuration**: External configs in `src/test/java/resources/` (JSON format)
- **Error Handling**: Use try-catch blocks with specific exceptions, throw IOException for file operations
- **Imports**: Group imports (java.*, javax.*, org.*, com.*, then project packages)
- **Code Formatting**: Use consistent indentation (4 spaces), braces on same line
- **Logging**: Use System.err.println() for error messages (as seen in BaseTest)
- **Comments**: Add Javadoc for public methods and classes

## Project Specifics
- APK files stored in `src/test/java/app/`
- Appium server URL configured in JSON config files
- Tests extend BaseTest which handles driver initialization and cleanup
- Page classes extend BasePage which provides common mobile interactions