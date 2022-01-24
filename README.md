# Mars rovers navigation

This is a simple Mars rover navigation simulation. It is a demonstration of the Java programming language.

## Building

Run `./gradlew distZip` to create a distribution for the application.

## Usage

Run `.bin/app --input rover-instructions.txt` to read rover navigation instructions from a text file and print out the final rover positions to stdout.

Use the `--output <output-file>` option to write the output to a file. For example: `./bin/app ---input rover-instructions.txt --output rover-positions.txt`.

The matches input can also be piped to the app for example: `cat rover-instructions.txt | ./bin/app`.
