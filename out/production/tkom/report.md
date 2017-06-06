_In test.py:_  
**8 warning(s) found, 3 certain exceptions**  
**38% dangerous, classified to MEDIUM level of danger**  
At line 3: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  
At line 4: **certain** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  
At line 9: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `x must be iterable`.  
At line 11: **certain** _TypeError_ exception of _Type 1_. Dangerous operation `1 must be iterable`.  

File reference:  
```  
1: # arithmetic operations
2: 1 + 1
3: x + y
4: 1 + '1'
5: 
6: # for in loop
7: for i in [1, 2, 3]:
8:   pass
9: for i in x:
10:   pass
11: for i in 1:
12:   pass
13: 
14: # non-integer index
15: [1, 2][1]
16: [1, 2][x]
17: [1, 2]['1']
18: 
19: # number of arguments
20: len([1, 2])
21: len([1, 2], [3, 4])
  
```  
