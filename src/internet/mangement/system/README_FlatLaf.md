# Using FlatLaf with Customer Management System

This document explains how to use FlatLaf to improve the look and feel of the Customer Management System.

## What is FlatLaf?

FlatLaf is a modern, cross-platform Look and Feel for Java Swing desktop applications. It provides a much more modern appearance than the default Swing look and feels.

## Adding FlatLaf to Your Project

### Step 1: Add FlatLaf Library

You need to add the FlatLaf library to your project. You can download it from:
- [FlatLaf GitHub Repository](https://github.com/JFormDesigner/FlatLaf)
- [Maven Central](https://search.maven.org/artifact/com.formdev/flatlaf)

#### Using Maven

If you're using Maven, add the following dependency to your pom.xml:

```xml
<dependency>
    <groupId>com.formdev</groupId>
    <artifactId>flatlaf</artifactId>
    <version>3.2.1</version>
</dependency>
```

#### Using Gradle

If you're using Gradle, add the following to your build.gradle:

```gradle
implementation 'com.formdev:flatlaf:3.2.1'
```

#### Manual Installation

If you're not using a build tool, download the JAR file and add it to your project's classpath.

### Step 2: Use the Provided Launcher

We've created a special launcher class that sets up FlatLaf before starting the application:

```java
src/internet/mangement/system/CustomerManagementLauncherWithFlatLaf.java
```

To use it, simply run this class instead of the regular CustomerManagementForm class.

### Step 3: Choose a Theme

FlatLaf comes with several built-in themes:

1. **FlatLightLaf** - A light theme similar to IntelliJ IDEA's light theme
2. **FlatDarkLaf** - A dark theme similar to IntelliJ IDEA's dark theme
3. **FlatIntelliJLaf** - IntelliJ IDEA's light theme
4. **FlatDarculaLaf** - IntelliJ IDEA's dark theme

You can change the theme in the `CustomerManagementLauncherWithFlatLaf.java` file by modifying the parameter passed to the `setupFlatLaf()` method:

```java
// Set up FlatLaf Look and Feel with light theme
setupFlatLaf("light");

// Or use dark theme
// setupFlatLaf("dark");

// Or use IntelliJ theme
// setupFlatLaf("intellij");

// Or use Darcula theme
// setupFlatLaf("darcula");
```

## Customizing FlatLaf

FlatLaf supports extensive customization through UI properties. You can customize colors, fonts, and other UI properties.

### Example: Customizing Colors

```java
// Must be called before setting the look and feel
FlatLightLaf.setup();

// Customize FlatLaf
UIManager.put("Button.arc", 10);
UIManager.put("Component.arc", 10);
UIManager.put("ProgressBar.arc", 10);
UIManager.put("TextComponent.arc", 10);

// Set the look and feel
UIManager.setLookAndFeel(new FlatLightLaf());
```

### Example: Customizing Fonts

```java
// Set global font
Font font = new Font("Arial", Font.PLAIN, 14);
UIManager.put("defaultFont", font);
```

## Troubleshooting

If you encounter issues with FlatLaf:

1. Make sure the FlatLaf JAR file is in your classpath
2. Check for exceptions in the console
3. Try using a different theme
4. Fall back to the default look and feel if necessary

## References

- [FlatLaf GitHub Repository](https://github.com/JFormDesigner/FlatLaf)
- [FlatLaf Documentation](https://www.formdev.com/flatlaf/)
