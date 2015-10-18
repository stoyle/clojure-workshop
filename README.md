# Installation instructions for Clojure workshop

## Java

Of course you need Java. Java 6 or higher will suffice. 

Get it [here](http://www.oracle.com/technetwork/java/javase/downloads/index.html).

## Git

Hopefully you already have git, if you don't it's about time ;)

Get it [here](http://git-scm.com/).

## Leiningen

This is the defacto Clojure build tool. It will setup almost everything for you.

Get it [here](http://leiningen.org/).

## IntelliJ

If you don't have it already, cummunity edition works fine.

Get it [here](https://www.jetbrains.com/idea/download/).

## Cursive (IntelliJ Clojure plugin)

Cursive is a still in EAP, so it is currently free. However it is a pretty good plugin.

Installation instructions [here](https://cursiveclojure.com/userguide/).


## Clone this project
```
git clone https://github.com/stoyle/clojure-workshop.git
```
## Make leiningen fetch necessary dependencies

Execute the following command in the downloaded git workspace:

```
lein do clean, deps, midje, uberjar, install
```

## Setup project in IntelliJ

In IntelliJ, select "File -> New -> Project from existing sources". Navigate to wherever you checked
out the files, select the project.clj file and click "Ok".

Go through wizard, defaults should be ok.

### Setup the REPL

In IntelliJ, select "Run -> Edit configurations -> `+` -> Clojure REPL -> Local -> Ok"

Run the REPL (e.g. press the "play" button). You should be set up.

### A few useful keybindings

Cursive does not ship with any keybindings, because conflicts are hard to avoid. However using
Cursive without bindings is not very pleasant.

Key bindings are found under "Settings -> Keymap -> Clojure Keybindings". As a minimum I would
recommend setting up bindings for:

* "Load file in REPL" - Lets you evaluate an entire file, and also switch namespace.
* "Send form before caret to REPL" - Lets you evaluate a single expression, before the caret.
* "Send top form to REPL" - Lets you evaluate the top level expression of the position of the caret.

All of these commands are available through the "Tool -> REPL" sub-menu. If you are a bit more savvy,
I would recommend setting up all of the Par Edit bindings (slurp/barf/join/kill/splice etc.). You 
should however be fine without these in the workshop.

## Problems?

Don't hesitate to contact me at alf.kristian@kodemaker.no.

## License

Copyright © 2015 Alf Kristian Støyle

Distributed under the [Creative Commons Attribution 4.0 International Public License](http://creativecommons.org/licenses/by/4.0/).
