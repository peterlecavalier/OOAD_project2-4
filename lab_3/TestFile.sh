#!/bin/bash
# Author : Elizabeth Shan
# Date: 2/9/21

#functions
count_lines(){
    echo "the number of lines in the file is" 
    grep -c ".*" sample_text.txt
}
count_words(){
    #-n does the same as echo 
    egrep -n '(Lorem|model|Ipsum|will)' sample_text.txt #instance of pipe used here?
}
add_text(){
    read -p "Enter text to append:" text
    echo $text >> sample_text.txt
}
copy_and_exit()(
    #make new folder
    mkdir solution
    #copy
    cp	sample_text.txt	solution
    echo "copied into new folder"
)

#main file
echo "File name:"
read file

if [ -f "$file" ]
    then
        echo "Displaying menu"
    else
        echo "file does not exist"
        exit    
fi 

while (true)
do 
    echo "menu options"
    echo "1 - count lines"
    echo "2 - count words"
    echo "3 - add text"
    echo "4 - copy and exit"
    read num
    case "$num" in
        "1") count_lines ;;
        "2") count_words ;;
        "3") add_text ;;
        "4") copy_and_exit ;;
    esac    
done