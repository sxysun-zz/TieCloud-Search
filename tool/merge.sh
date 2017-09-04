#!/bin/bash 

# merge the fetched .txt files in crawler folder
# to a single text file named merge_index_index.txt
# move the file to a new path for NLP future process

cd ..
path=cat tempFilePath
cd $path
touch merged.txt
for i in ./*.txt
do
cat *.txt >> merged.txt
done
cp merged.txt ../readyForNLP.txt
find . -type f -exec cp /dev/null {} \;