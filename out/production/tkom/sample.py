# assigns
1 += 1
x %= y
1 <<= '1'

# comparison
1 < 1
x >= y
1 in '1'

# comparison - NOT DANGEROUS
1 is 1
x is y
1 is '1'

# arithmetic operations
1 + 1
x * y
1 << '1'

# functions
len([1, 2])
len(x)
len(1)

# non-integer index
[1, 2][1]
[1, 2][x]
[1, 2]['1']

# for in loop
for i in [1, 2]:
  print(i)

for i in x:
  print(i)

for i in 1:
  print(i)

# number of arguments
len([1, 2])

def fun4(**kwargs):
  print(kwargs)

len([1, 2], [3, 4])
