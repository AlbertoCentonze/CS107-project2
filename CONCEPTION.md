# Conception
## Package and directories
To decide which package to put in every class the general criteria was followed:
1. Full priority to the indications given in the pdf
2. If no indication is given and the class is a specific implementation for the game SuperPacman it was inserted in the game-specific folder.
3. If no indication is given and the class is a generic functionality that could be useful to create other games (like Axis.java) it was added to the AreaGame package.
4. Hyperpacman contains a lot of additional features that are built upon Superpacman classes. Since it's a different game we decided to keep things clean using a separate directory that has the same internal structure of SuperPacman
## Axis enum
To make the code more readable we have created a new enum called Axis that can be used for Gate.java like actors that have only 2 orientations: a vertical one and a horizontal one. Since it's an extremely generic enum you can find it inside the AreaGame package and not in Superpacman.
## FREE_WITH removed
During the development, we have realized that in the enum with the cell types "FREE_WITH" was redundant and made things harder to read. That's why we've decided to rename it.
## Alternative constructor for DescreteCoordinates
To reduce the verbosity of the code we've added an alternative constructor that allows to instantiate a DiscreteCoordinates using only a vector as an argument.
## Weighted probability
Added to the random generator a method that according to a certain probability return true or false. Solution found [here](https://stackoverflow.com/questions/17359834/random-boolean-with-weight-or-bias)