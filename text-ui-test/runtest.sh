#!/usr/bin/env bash

# Navigate to the root directory
cd ..

# Clean previous outputs
if [ -e "./text-ui-test/ACTUAL.TXT" ]
then
    rm ./text-ui-test/ACTUAL.TXT
fi

# Clean previous saved file
if [ -e "./data/dukegpt.txt" ]
then
    rm ./data/dukegpt.txt
fi

# Use Gradle to clean and build the project, terminates if error occurred
if ! ./gradlew clean build
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Run compiled gradle program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ./build/classes/java/main ui.DukeGPT < text-ui-test/input.txt > text-ui-test/ACTUAL.TXT

# Navigate back to the test folder to run comparisons
cd text-ui-test

# Convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# Compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
else
    echo "Test result: FAILED"
fi

# Compare the saved output file to the expected save file
diff ../data/dukegpt.txt EXPECTED_SAVE.TXT
if [ $? -eq 0 ]
then
    echo "Save file comparison: PASSED"
    exit 0
else
    echo "Save file comparison: FAILED"
    exit 1
fi
