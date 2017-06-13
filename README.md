# coup

For rules and credits to the original game go here: https://boardgamegeek.com/boardgame/131357/coup.   
I am not affiliated with any of the copyright holders of the original card game.  

### Rule engine

At the start of each turn, the player can take any action provided they have sufficient coins. If the action requires a
card, another player may challenge - if it can be blocked, another player may block. These player responses also constitute
actions.  
A turn ends when the next player takes an independent action.  
Rules are stored in the [resources/ruleset](https://raw.githubusercontent.com/connor-taylorbrown/coup/master/src/main/resources/ruleset) 
file, which consists of three sections:  
- ```<card>+``` names the cards in the game.
- ```<action>(:<gain>(,\(<coins>,<influence>\))?)?``` specifies coins gained and, if applicable, change in target's coins and influence.
- ```<action>:<card>(,<card>)*``` specifies, if applicable, the card required to make or block the action.  

All rules applied are drawn from this file at start. Custom [Action classes](https://connor-taylorbrown.github.io/coup/) can be
added to the parser to support expansion packs, but if not named in the file they will not be used.