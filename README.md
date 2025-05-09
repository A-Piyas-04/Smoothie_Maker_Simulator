# Smoothie Maker Simulator

A Java Swing application that lets users create and customize their own smoothies with various ingredients, flavors, and toppings.

## Project Overview

The goal of this project is to create an interactive simulation where users can design their own smoothies by selecting various fruits, flavors, and toppings.
It promotes health awareness by calculating a health score for each smoothie based on ingredient combinations, while offering a fun and customizable user experience.
The project follows object-oriented design principles and applies SOLID principles to maintain a modular, scalable architecture


## Key Features
- A wide range of ingredient categories—fruits, flavors, and toppings—for full customization.
- A mug selection system that allows users to choose their preferred container.
- Tracking smoothies' health-scores based on ingredient combinations.
- Getting aliases for each ingredients
- Automatic logging of all smoothie creations for easy history tracking.
- Fun ingredient aliases to make the user experience more engaging.
- A clean and responsive graphical user interface built using Java Swing.

## Structural Design
The project is structured using a "Modular Design" approach.
Each component of the system—such as the user interface, logic controllers, services, and models—is placed in a separate package.
For example, the UI package handles all user interactions, while the Services package includes logic like scoring and status logging.
The use of abstract classes and interfaces ensures flexibility and follows principles like Single Responsibility and Dependency Inversion.
This design makes it easy to extend the system, such as adding new ingredients or mug types without modifying existing code.

## SOLID Principles Analysis ---->

### Well-Implemented SOLID Principles :

1. **Single Responsibility Principle (SRP)**
   - `ScoreService`     : Handles only scoring logic
   - `MugFactory`       : Focused solely on mug creation
   - `StatusLogger`     : Dedicated to logging functionality
   - `GameStateManager` : Manages game state transitions
   - `MainFrame`        : Handles navigation and window management
   - `SmoothieLogger`   : Logs smoothie creation history
   - `LogViewer`        : Handles the display and loading of smoothie logs


2. **Open/Closed Principle (OCP)**
   - `Ingredient` hierarchy: Base abstract class with specific   
                             implementations (FruitIngredient, FlavorIngredient, ToppingIngredient)

   - New ingredient types can be added without modifying existing code


3. **Liskov Substitution Principle (LSP)**
   - `Ingredient` hierarchy : Subclasses (FruitIngredient, FlavorIngredient, ToppingIngredient)
                             can be substituted for their base class.

4. **Interface Segregation Principle (ISP)**
   - `Mug`        interface : Simple and focused with only essential methods
   - `Ingredient` Interface : Focused and small, providing only necessary methods for ingredients

5. **Dependency Inversion Principle (DIP)**
   - Components depend on abstractions (`Mug` interface, `Ingredient` abstract class)
   - `MugFactory` creates specific mug implementations through the `Mug` interface


### Areas for SOLID Improvement :

1. **Single Responsibility Principle**
   - `GamePanel`: Currently handles multiple responsibilities (UI, game state)
   - This could be refactored into separate components

2. **Open/Closed Principle**
   - `ScoreService`: Hard-coded scoring logic could be more extensible
   - `BlenderController`: Blending logic could be more modular

3. **Liskov Substitution Principle**
   - Current implementation generally follows LSP well
   - No significant violations observed

4. **Interface Segregation Principle**
   - Current implementation generally follows ISP well
   - No violations observed

5. **Dependency Inversion Principle**
   - Can be Considered creating interfaces for blending strategies and score calculation methods to allow for more flexible and interchangeable implementations.


## Project Structure

```
src/
├── UI Components
│   ├── MainFrame.java             # Main application window
│   ├── GameFrame.java             # Game container frame
│   ├── GamePanel.java             # Main game interface
│   ├── NavigationPanel.java       # Navigation controls
│   ├── BlendControlPanel.java     # Blending controls UI
│   ├── BlenderAnimationPanel.java # Blending animation
│   ├── IngredientPanel.java       # Ingredient selection UI
│   ├── MugSelectionPanel.java     # Mug selection interface
│   ├── StatusPanel.java           # Status display panel
│   └── SmoothieLogViewer.java     # History viewer
│
├── Controllers
│   ├── BlenderController.java   # Blending logic controller
│   └── GameStateManager.java    # Game state management
│
├── Services
│   ├── AliasService.java        # Ingredient alias handling
│   ├── ScoreService.java        # Score calculation
│   └── StatusLogger.java        # Status logging service
│
├── Models
│   ├── Ingredient.java          # Base ingredient class
│   ├── FlavorIngredient.java    # Flavor type ingredients
│   ├── FruitIngredient.java     # Fruit type ingredients
│   └── ToppingIngredient.java   # Topping type ingredients
│
└── Mug System
    ├── Mug.java                 # Mug interface
    ├── MugFactory.java          # Mug creation factory
    ├── GlassMug.java            # Glass mug implementation
    └── PlasticMug.java          # Plastic mug implementation
```

## Getting Started

1. Clone the repository
2. Open the project in your preferred Java IDE
3. Run `Main.java` to start the application

## Usage
1. Start the application and select 'Play Game' to go to the game window
   ![Main Menu and Game Window](Screenshots/2.1.png)
   ![Game Interface](Screenshots/2.2.png)

2. Select your preferred ingredients and mug type
3. Blend your ingredients to create a smoothie

   ![Ingredient Selection](Screenshots/3.png)
   ![Blending Process](Screenshots/4.png)

4. Get back to 'Main Menu' to track your smoothie
   ![Health Score](Screenshots/5.1.png)

5. View your smoothie creation history from the 'Main Menu'
   ![Creation History](Screenshots/5.2.png)
