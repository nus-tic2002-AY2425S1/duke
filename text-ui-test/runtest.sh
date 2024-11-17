#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]; then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]; then
    rm ACTUAL.TXT
fi

if [ -e "./TaskLists.txt" ]; then
    rm TaskLists.txt
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/starkchatbot/**/*.java; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin starkchatbot.userui.Stark < input.txt > ACTUAL.TXT

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
if command -v dos2unix >/dev/null 2>&1; then
    dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT
else
    echo "dos2unix not found, converting using tr"
    tr -d '\r' < ACTUAL.TXT > ACTUAL.TXT.tmp && mv ACTUAL.TXT.tmp ACTUAL.TXT
    tr -d '\r' < EXPECTED.TXT > EXPECTED-UNIX.TXT.tmp && mv EXPECTED-UNIX.TXT.tmp EXPECTED-UNIX.TXT
fi

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
