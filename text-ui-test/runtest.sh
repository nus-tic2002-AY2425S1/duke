#!/usr/bin/env bash

# Create bin directory if it doesn't exist
if [ ! -d "../bin" ]; then
    mkdir ../bin
fi

# Delete output from the previous run
if [ -e "./ACTUAL.TXT" ]; then
    rm ACTUAL.TXT
fi

# Compile the code into the bin folder, terminate if an error occurs
if ! javac -cp ../src/main/java -Xlint:none -d ../bin $(find ../src/main/java -name "*.java"); then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Run the program, feed commands from input.txt file, and redirect the output to ACTUAL.TXT
java -classpath ../bin mochi.Mochi < input.txt > ACTUAL.TXT

# Convert EXPECTED.TXT to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# Compare the output to the expected output
if diff ACTUAL.TXT EXPECTED-UNIX.TXT > /dev/null; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
