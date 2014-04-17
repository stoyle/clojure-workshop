# Installation instructions for Clojure workshop

## Git

Hopefully you already have git, if you don't it's about time ;)

Get it [here](http://git-scm.com/).

## Leiningen

This is the defacto Clojure build tool. It will setup almost everything for you.

Get it [here](http://leiningen.org/).

## Light Table

This is the editor we will use. Pretty cool with lots of innovative features. Written entirely in ClojureScript.

Get it [here](http://www.lighttable.com/).

## Clone this project

git clone https://gitlab.com/stoyle/clojure-workshop-flatmap.git

## Make leiningen fetch necessary dependencies

Execute the following command in the workspace:

```
lein do clean, deps, midje, install
```

## Open Light Table

Open project in Light Table`

```
File -> Open Folder -> Navigate to folder where you have checked out the project.
```

Start a repl at project. Open a Clojure namespace and evaluate:

```
"Test/clojure-workshop-flatmap/ex-1" -> Press "Cmd-shift-enter"/"Ctrl-shift-enter".
```

This may take a little time. You will see inline light grey markings in the file.

## License

Copyright © 2014 Alf Kristian Støyle

Distributed under the [Creative Commons Attribution 4.0 International Public License](http://creativecommons.org/licenses/by/4.0/).