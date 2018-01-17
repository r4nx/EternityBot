EternityBot
===========

EternityBot is an open source customisable bot for Telegram.

You can use it as a template for your own bot.

Structure
---------
#### `Telegram` class
This class contains the most basic methods for interacting with the Telegram API, such as `getUpdates`, `sendMessage`, `answerInlineQuery`.

#### `UpdateHandler` class
`UpdateHandler` class has only 1 method - `handle`, and this method has only 1 parameter - `updates`. `updates` variable can be obtained using the `Telegram.getUpdates` method.

#### `CommandHandler` class
Method `handle` in `CommandHandler` class distributes commands, passing them to the appropriate methods. `handle` method calls from `UpdateHandler`.

#### Executors
Executors in EternityBot are classes, containing `execute` method, which has 1 parameter of `TelegramCommand` type. This method should return string variable, containing the message, that will be sent to the user.
