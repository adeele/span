# arythmetic operations

print(1 + 1)

def fun1(x, y):
  print(x + y)

print(1 + '1')


# functions

print(len([1, 2]))

def fun2(x):
  print(len(x))

print(len(1))


# non-integer index

print([1, 2][1])

def fun3(x):
  print([1, 2][x])

print([1, 2]['1'])


# for in loop

for i in [1, 2, 3]:
  print(i)

def fun4(x):
  for i in x:
    print(i)

for i in 1:
  print i


# number of arguments

print(len([1, 2]))

def fun4(**kwargs):
  print(kwargs)

print(len([1, 2], [3, 4]))
