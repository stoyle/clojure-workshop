# Installation instructions for Clojure workshop

## Java

Of course you need Java. By now, you should be using at least Java 8.

Get it [here](http://www.oracle.com/technetwork/java/javase/downloads/index.html).

## Git

Hopefully you already have git, if you don't it's about time ;)

Get it [here](http://git-scm.com/).

## Leiningen

This is the most used Clojure build tool. It will setup almost everything for you.

Get it [here](http://leiningen.org/).

## IntelliJ

If you don't have it already, community edition works fine.

Get it [here](https://www.jetbrains.com/idea/download/).

## Cursive (IntelliJ Clojure plugin)

Cursive is a very good plugin. Unfortunately not free, but you should get a free trial.

Installation instructions [here](https://cursive-ide.com/userguide/).


## Clone this project
```
git clone https://github.com/stoyle/clojure-workshop.git
```
## Make leiningen fetch necessary dependencies

Execute the following command in the downloaded git workspace:

```
lein do clean, deps, midje, uberjar
```

## Setup project in IntelliJ

In IntelliJ, select "File -> Open". Navigate to wherever you checked out the files,
and click "Ok".

### Setup the REPL

In IntelliJ, select "Run -> Edit configurations -> `+` -> Clojure REPL -> Local -> Ok"

Run the REPL (e.g. press the "play" button). You should be set up. You can try typing in
`(println "Hello world")` and press enter. It should print "Hello world" followed by
`=> nil`.

### A few useful keybindings

Cursive does ship some key bindings, they may be different on your system. The ones listed below,
are default for "Mac OS X 10.5+". In any case it is a good idea to get to familiar at least
with the following bindings.

In latest Cursive with IDEA 2019.x, key bindings are found under
"Settings -> Keymap -> Plugins -> Cursive".

* "Load file in REPL" - `Shift - Command - l` Lets you evaluate an entire file, and also switch namespace.
* "Send form before caret to REPL" - Not set, suggestion `Ctrl - Alt - b` Lets you evaluate a single expression, before the caret.
* "Send top form to REPL" - Not set, suggestion `Ctrl - Alt - f` Lets you evaluate the top level expression of the position of the caret.

If you are a bit more savvy, I would recommend setting up all of the Par Edit bindings (slurp/barf/join/kill/splice etc.). You
should however be fine without these in the workshop.

## Problems?

Don't hesitate to contact me at alf.kristian@kodemaker.no.

## License

Copyright © 2019 Alf Kristian Støyle

Distributed under the [Creative Commons Attribution 4.0 International Public License](http://creativecommons.org/licenses/by/4.0/).
