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

File reference:  
```  
1: # power
2: 1 ** 1
3: x ** y
4: 1 ** '1'
5: 
6: # assigns
7: 1 += 1
8: x %= y
9: 1 <<= '1'
10: 
11: # comparison
12: 1 < 1
13: x >= y
14: 1 in '1'
15: 
16: # comparison - NOT DANGEROUS
17: 1 is 1
18: x is y
19: 1 is '1'
20: 
21: # arithmetic operations
22: 1 + 1
23: x * y
24: 1 << '1'
  
```  
_In nestedFile.py:_  
**1 warning(s) found, 1 certain exceptions**  
**100% dangerous, classified to HIGH level of danger**  
At line 1: **certain** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  

File reference:  
```  
1: 'a' + 2
  
```  
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
_In sample2.py:_  
**13 warning(s) found, 2 certain exceptions**  
**48% dangerous, classified to LOW level of danger**  
At line 15: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `x must be iterable`.  
At line 18: **certain** _TypeError_ exception of _Type 1_. Dangerous operation `1 must be iterable`.  

File reference:  
```  
1: # functions
2: len([1, 2])
3: len(x)
4: len(1)
5: 
6: # non-integer index
7: [1, 2][1]
8: [1, 2][x]
9: [1, 2]['1']
10: 
11: # for in loop
12: for i in [1, 2]:
13:   print(i)
14: 
15: for i in x:
16:   print(i)
17: 
18: for i in 1:
19:   print(i)
20: 
21: # number of arguments
22: len([1, 2])
23: 
24: def fun4(**kwargs):
25:   print(kwargs)
26: 
27: len([1, 2], [3, 4])
  
```  
_In merge-mail.py:_  
**9 warning(s) found, 0 certain exceptions**  
**56% dangerous, classified to LOW level of danger**  
At line 11: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `names_file must be iterable`.  
At line 12: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  
At line 15: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  

File reference:  
```  
1: # open names.txt for reading
2: with open("names.txt",'r',encoding = 'utf-8') as names_file:
3: 
4:    # open body.txt for reading
5:    with open("body.txt",'r',encoding = 'utf-8') as body_file:
6: 
7:        # read entire content of the body
8:        body = body_file.read()
9: 
10:        # iterate over names
11:        for name in names_file:
12:            mail = "Hello "+name+body
13: 
14:            # write the mails to individual files
15:            with open(name.strip()+".txt",'w',encoding = 'utf-8') as mail_file:
16:                mail_file.write(mail)
  
```  
_In fibonacci.py:_  
**2 warning(s) found, 0 certain exceptions**  
**11% dangerous, classified to LOW level of danger**  
At line 14: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `>`.  
At line 16: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  

File reference:  
```  
1: class Fib:
2:     '''iterator that yields numbers in the Fibonacci sequence'''
3: 
4:     def __init__(self, max):
5:         self.max = max
6: 
7:     def __iter__(self):
8:         self.a = 0
9:         self.b = 1
10:         return self
11: 
12:     def __next__(self):
13:         fib = self.a
14:         if fib > self.max:
15:             raise StopIteration
16:         self.a, self.b = self.b, self.a + self.b
17:         return fib
  
```  
_In bubble-sort.py:_  
**8 warning(s) found, 0 certain exceptions**  
**100% dangerous, classified to LOW level of danger**  
At line 5: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `range(len(seq)-1) must be iterable`.  
At line 5: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `-`.  
At line 6: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `>`.  
At line 6: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  
At line 7: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  
At line 7: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  

File reference:  
```  
1: def bubble_sort(seq):
2:     changed = True
3:     while changed:
4:         changed = False
5:         for i in range(len(seq) - 1):
6:             if seq[i] > seq[i+1]:
7:                 seq[i], seq[i+1] = seq[i+1], seq[i]
8:                 changed = True
9:     return None
  
```  
_In client.py:_  
**22 warning(s) found, 0 certain exceptions**  
**84% dangerous, classified to LOW level of danger**  
At line 12: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  
At line 17: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  
At line 20: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `+`.  
At line 25: **possible** _TypeError_ exception of _Type 1_. Dangerous operation `==`.  

File reference:  
```  
1: import socket
2: 
3: def Main():
4:     host = "127.0.0.1"
5:     port = 5000
6: 
7:     mySocket = socket.socket()
8:     mySocket.bind((host,port))
9: 
10:     mySocket.listen(1)
11:     conn, addr = mySocket.accept()
12:     print ("Connection from: " + str(addr))
13:     while True:
14:             data = conn.recv(1024).decode()
15:             if not data:
16:                     break
17:             print ("from connected  user: " + str(data))
18: 
19:             data = str(data).upper()
20:             print ("sending: " + str(data))
21:             conn.send(data.encode())
22: 
23:     conn.close()
24: 
25: if __name__ == '__main__':
26:     Main()
  
```  
