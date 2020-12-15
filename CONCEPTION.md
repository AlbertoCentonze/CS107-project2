# Conception
## Axis enum
To make the code more readable we have created a new enum called Axis that can be used for gate like actors that have only 2 orientation: a vertical one and an horizontal one. Since it's an extremely generic enum you can find it inside the areagame package and not in superpacman.
## FREE_WITH removed
During the developement we have realized that in the enum with the cell types "FREE_WITH" was redudant and made things harder to read. That's why we've decided to rename it.
Bonus timer on hud
## Alternative constructor for DescreteCoordinates
To reduce the verbosity of the code we've added an alternative constructor that allows to instance a DiscreteCoordinates using only a vector as an argument