# Intern Case Study

## Pisti Game Implementation

### Game Rules

* Game is played with 4 players.
* Game is played with a deck of 52 cards.

#### The First Deal

* The dealer shuffles the deck and deals the top four cards face up to the center of the table.
* The dealer deals 4 cards to each player (beginning to dealer’s right and ending with the dealer).

#### The Play

* The player to dealer’s right begins, and turn order is counterclockwise. A turn consists of playing one card from your hand face up on top the discard pile.
* If the rank of the played (discarded) card matches the rank of the card on  the pile, the player collects the whole pile.
* Playing a jack also collects the whole pile, no matter what card is on top of it.
* If the played card is not equal to the top card of the pile, the played card is simply added to the top of the pile.
* If the pile consists of just one card and the next player collects it by matching a card (not a jack), the collecting player scores a 10 point bonus for a “pisti”.
* If the pile consists of just a single jack and you capture it with another jack, this counts as a double “pisti”, worth 20 points.

#### Further Deals

* When all the players have played their four cards, the dealer deals another batch of four cards to each player from the deck (but no more to the center of the table).
* On the last hand, if there are uncollected cards left on the discard pile, these cards are awarded to the last player who collected cards.
* Game ends when the there is no cards in the deck.

#### Scoring

| Type              | Score     |
|-------------------|-----------|
| Each Jack         | 1 point   |
| Each Ace          | 1 point   |
| Each Clubs        | 2 points  |
| Each Diamonds     | 3 points  |
| Each Pisti        | 10 points |
| Each Jack Pisti   | 20 points |

### Implementation Details

* A java application is developed that deals cards to the bots and make them play a pisti game.
* Game will be played by bots. There are two sample bots already implemented.
    * DummyBot - Just plays cards one by one.
    * RandomBot - Plays cards in hand randomly.
* New kinds of bots can be implemented easily and introduced to the system to play other bots.
* No graphical interface implemented, everything is console based.
* Application accepts following 6 parameters to play the game with bots:
    * Concurrent Game Count
    * Total Game Count
    * Class Name of Bot 1
    * Class Name of Bot 2
    * Class Name of Bot 3
    * Class Name of Bot 4
* Code base have two helper run scripts
    * *run-with-maven*: [Maven](https://maven.apache.org/) is used to compile and run the application.
    * *run-with-java*: [Java Compiler](http://www.oracle.com/technetwork/java/compile-136656.html) is used to compile and run the application.


## Expectations

### Case Study

* You are going to work within a team for 2 hours.
* [Clone](github-mac://openRepo/https://github.com/peakgames/intern-case-study) or [Download](https://github.com/peakgames/intern-case-study/archive/master.zip) the source code of the application into your local environment.
* Compile and run the application with your preferred development environment or existing run scripts (see above section).
* Review the existing code base of the application.
* Create and introduce a new bot into the application which conforms the [Bot](https://github.com/peakgames/intern-case-study/blob/master/src/main/java/net/peakgames/pisti/bot/Bot.java) interface. (Your bot should be one single class).
* You are not allowed to make any change on the existing code base other than your bot.
* Send your bot's class via a [pull request](https://help.github.com/articles/using-pull-requests/) to this project or send it to hakansaglam@peakgames.net as mail attachment.


### Case Assessment
* Your bot will play the game with other groups' bots.
* Explain your implementation of bot and your understanding of the application for 5-10 minutes.
