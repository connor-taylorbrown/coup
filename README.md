# coup

For rules and credits to the original game go here: https://boardgamegeek.com/boardgame/131357/coup.   
I am not affiliated with any of the copyright holders of the original card game.  

### Rule engine

At the start of each turn, the player can take any action provided they have sufficient coins. If the action requires a
card, another player may challenge - if it can be blocked, another player may block. These player responses also constitute
actions.  
A turn ends when the next player takes an independent action.  
Rules are stored in the ```resources/actions``` file, which consists of two sections:  
- ```<action>(:<gain>(,\(<coins>,<influence>\))?)?``` specifies coins gained and, if applicable, change in target's coins and influence.
- ```<action>:<card>(,<card>)*``` specifies, if applicable, the card required to make or block the action.
