# Dievaluator

Dievaluator helps you decide if your favorite dice roll fairly.

`Main` - Bootstraps application

### controller

`Controller` - This class passes data between the Model and View layers.

### model

`DiceStatisticsDTO` - Creates an instance of a Statistics Data Transfer Object.

`Trial` - Represents an ongoing trial to determine the fairness of a group of dice.

### util

`Statistics` - Performs calculations to produce statistics based on assumed uniform distribution and on the supplied sample.

`TTable` - Contains static methods to evaluate probabilities for quantiles for t-distributions for an amount of different sample sizes.

`ZScores` - Contains static methods to evaluate standardized normal distribution z-scores and proportions.

### view

`View` - A simple view to accept user input and present output in the terminal.
