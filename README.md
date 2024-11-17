# Dievaluator

Dievaluator helps you decide if your favorite dice roll fairly.

## Use
Dievaluator.exe runs application and has a Java Swing GUI.

## Project contents

`Main` - Bootstraps application

### controller

`Controller` - This class passes data between the Model and View layers.

### model

`DiceStatisticsDTO` - Creates an instance of a Statistics Data Transfer Object.

`Trial` - Represents an ongoing trial to determine the fairness of a group of dice.

`TrialObserver` - Observer interface that informs classes that implements it that the {@code BlipLocationModel} has updated its map of blip objects.

### resources 
Folder of images used by the application.

### util

`Statistics` - Performs calculations to produce statistics based on assumed uniform distribution and on the supplied sample.

`TTable` - Contains static methods to evaluate probabilities for quantiles for t-distributions for an amount of different sample sizes.

`ZScores` - Contains static methods to evaluate standardized normal distribution z-scores and proportions.

### view

`ConsoleView` - A simple view to accept user input and present output in the terminal. (Deprecated)

`InfoPanel` - Responsible for rendering informative text to the user of the application.

`OptionPanel` - Responsible for rendering options for the user.

`SwingView` - Top-level container for the Java Swing-enabled view of the Dievaluator application.

