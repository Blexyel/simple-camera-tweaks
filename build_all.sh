#!/bin/bash
for dir in ./versions/*; do 
	echo "Processing: ${dir#./versions/}";
	gradle build "-Pmcversion=${dir#./versions/}"
done

# build latest
gradle build