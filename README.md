# wc.exe

功能介绍：

本程序仅支持c程序文件，即.c文件
wc.exe -c file.c 返回文件 file.c 的字符数
wc.exe -w file.c 返回文件 file.c 的单词数
wc.exe -l file.c 返回文件 file.c 的行数
wc.exe -a file.c 返回文件 file.c 的代码行数、空行数、注释行数
wc.exe -s directory *.c (支持通配符，*代表多个任意字符，？代表一个任意字符） 返回directory路径下所有符合条件的文件的代码行数、空行数、注释行数
wc.exe -x 显示图形界面，选取单个文件，显示文件的字符数、行数等全部统计信息