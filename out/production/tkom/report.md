_In sample.py:_  
**8 warning(s) found, 4 certain exceptions**  
**33% dangerous, classified to MEDIUM level of danger**  
At line 3: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `**`.  
At line 4: **certain** _TypeError_ exception of _Type 1_. Dangerous operation `**`.  
At line 8: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `%=`.  
At line 9: **certain** _TypeError_ exception of _Type 1_. Dangerous operation `<<=`.  
At line 13: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `>=`.  
At line 14: **certain** _TypeError_ exception of _Type 1_. Dangerous operation `in`.  
At line 23: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `*`.  
At line 24: **certain** _TypeError_ exception of _Type 1_. Dangerous operation `<<`.  

_In nestedFile.py:_  
**1 warning(s) found, 1 certain exceptions**  
**100% dangerous, classified to HIGH level of danger**  
At line 1: **certain** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  

_In test.py:_  
**11 warning(s) found, 3 certain exceptions**  
**42% dangerous, classified to LOW level of danger**  
At line 3: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  
At line 4: **certain** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  
At line 7: **possible** _TypeError_ exception of _Type 3_. Function `len()` may not take given number of arguments.  
At line 8: **possible** _TypeError_ exception of _Type 3_. Function `len()` may not take given number of arguments.  
At line 9: **possible** _TypeError_ exception of _Type 3_. Function `len()` may not take given number of arguments.  
At line 13: **possible** _TypeError_ exception of _Type 2_. Array index `[x]` must be an integer.  
At line 14: **certain** _TypeError_ exception of _Type 2_. Array index `['1']` must be an integer.  
At line 19: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `x must be iterable`.  
At line 21: **certain** _TypeError_ exception of _Type 1_. Dangerous operation `1 must be iterable`.  
At line 25: **possible** _TypeError_ exception of _Type 3_. Function `len()` may not take given number of arguments.  
At line 26: **possible** _TypeError_ exception of _Type 3_. Function `len()` may not take given number of arguments.  

_In sample2.py:_  
**13 warning(s) found, 2 certain exceptions**  
**48% dangerous, classified to LOW level of danger**  
At line 2: **possible** _TypeError_ exception of _Type 3_. Function `len()` may not take given number of arguments.  
At line 3: **possible** _TypeError_ exception of _Type 3_. Function `len()` may not take given number of arguments.  
At line 4: **possible** _TypeError_ exception of _Type 3_. Function `len()` may not take given number of arguments.  
At line 8: **possible** _TypeError_ exception of _Type 2_. Array index `[x]` must be an integer.  
At line 9: **certain** _TypeError_ exception of _Type 2_. Array index `['1']` must be an integer.  
At line 13: **possible** _TypeError_ exception of _Type 3_. Function `print()` may not take given number of arguments.  
At line 15: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `x must be iterable`.  
At line 16: **possible** _TypeError_ exception of _Type 3_. Function `print()` may not take given number of arguments.  
At line 18: **certain** _TypeError_ exception of _Type 1_. Dangerous operation `1 must be iterable`.  
At line 19: **possible** _TypeError_ exception of _Type 3_. Function `print()` may not take given number of arguments.  
At line 22: **possible** _TypeError_ exception of _Type 3_. Function `len()` may not take given number of arguments.  
At line 25: **possible** _TypeError_ exception of _Type 3_. Function `print()` may not take given number of arguments.  
At line 27: **possible** _TypeError_ exception of _Type 3_. Function `len()` may not take given number of arguments.  

