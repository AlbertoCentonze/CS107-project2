# Conception
## Package and directories
To decide which package to put in every class the general criteria was followed:
1. Full priority to the indications given in the pdf
2. If no indication is given and the class is a specific implementation of the game SuperPacman it's inserted in the superpacman-specific folder.
3. If no indication is given and the class is a generic functionality that could be useful to create other games (like Axis.java), it's added to the AreaGame package.
4. Hyperpacman contains a lot of additional features that are built upon Superpacman classes. Since it's a different game we decided to keep things clean by using a separate directory that has the same internal structure as SuperPacman
## Axis enum
To make the code more readable we have created a new enum called Axis that can be used for Gate.java like actors that have only 2 orientations: a vertical one and a horizontal one. Since it's an extremely generic enum you can find it inside the AreaGame package and not in Superpacman.
## FREE_WITH removed
During the development, we realized that the cell types in the enum starting with "FREE_WITH" were redundant and made things harder to read, hence we've decided to rename them.
## Alternative constructor for DescreteCoordinates
To reduce the verbosity of the code we've added an alternative constructor that allows to instantiate a DiscreteCoordinates using only a vector as an argument.