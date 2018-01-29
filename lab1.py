def isInside(v, e):
	if type(e) is str:
		return v == e
	elif type(e) is tuple:
		if isInside(v, e[0]) or isInside(v, e[2]): return True
	return False

def solve(v, q):
	if isInside(v, q[0]): return solving(v, q)
	elif isInside(v, q[2]): return solving(v, q[::-1])
	else: return None

def solvingAdd(v, q):
	if isInside(v, q[0][0]):
		return solving(v, (q[0][0], '=', (q[2], '-', q[0][2])))
	elif isInside(v, q[0][2]):
		return solving(v, (q[0][2], '=', (q[2], '-', q[0][0])))

def solvingSubtract(v, q):
	if isInside(v, q[0][0]):
		return solving(v, (q[0][0], '=', (q[2], '+', q[0][2])))
	elif isInside(v, q[0][2]):
		return solving(v, (q[0][2], '=', (q[0][0], '-', q[2])))

def solvingMultiply(v, q):
	if isInside(v, q[0][0]):
		return solving(v, (q[0][0], '=', (q[2], '/', q[0][2])))
	elif isInside(v, q[0][2]):
		return solving(v, (q[0][2], '=', (q[2], '/', q[0][0])))

def solvingDivide(v, q):
	if isInside(v, q[0][0]):
		return solving(v, (q[0][0], '=', (q[2], '*', q[0][2])))
	elif isInside(v, q[0][2]):
		return solving(v, (q[0][2], '=', (q[0][0], '/', q[2])))

def solving(v, q):
	assert isInside(v, q[0])
	if v == q[0]:
		return q
	else:
		return {
			'+': solvingAdd,
			'-': solvingSubtract,
			'*': solvingMultiply,
			'/': solvingDivide
		}[q[0][1]](v, q)

#
#  TESTS. Test the equation solver for CSci 1913 Lab 1.
#
#    James B. Moen
#    12 Sep 16
#

#  Each PRINT is followed by a comment that shows what must be printed if your
#  program works correctly.

print(isInside('x', 'x'))                          #  True
print(isInside('x', 'y'))                          #  False
print(isInside('x', ('x', '+', 'y')))              #  True
print(isInside('x', ('a', '+', 'b')))              #  False
print(isInside('x', (('m', '*', 'x'), '+', 'b')))  #  True

print(solve('x', (('a', '+', 'x'), '=', 'c')))  #  ('x', '=', ('c', '-', 'a'))
print(solve('x', (('x', '+', 'b'), '=', 'c')))  #  ('x', '=', ('c', '-', 'b'))

print(solve('x', (('a', '-', 'x'), '=', 'c')))  #  ('x', '=', ('a', '-', 'c'))
print(solve('x', (('x', '-', 'b'), '=', 'c')))  #  ('x', '=', ('c', '+', 'b'))

print(solve('x', (('a', '*', 'x'), '=', 'c')))  #  ('x', '=', ('c', '/', 'a'))
print(solve('x', (('x', '*', 'b'), '=', 'c')))  #  ('x', '=', ('c', '/', 'b'))

print(solve('x', (('a', '/', 'x'), '=', 'c')))  #  ('x', '=', ('a', '/', 'c'))
print(solve('x', (('x', '/', 'b'), '=', 'c')))  #  ('x', '=', ('c', '*', 'b'))

print(solve('x', ('y', '=', (('m', '*', 'x'), '+', 'b')))) # ('x', '=', (('y', '-', 'b'), '/', 'm')

# custom case
print(solve('x', (('a', '+', 'b'), '=', ('w', '-', ('x', '*', ('y', '/', 'z'))))))

'''
Results:
True
False
True
False
True
('x', '=', ('c', '-', 'a'))
('x', '=', ('c', '-', 'b'))
('x', '=', ('a', '-', 'c'))
('x', '=', ('c', '+', 'b'))
('x', '=', ('c', '/', 'a'))
('x', '=', ('c', '/', 'b'))
('x', '=', ('a', '/', 'c'))
('x', '=', ('c', '*', 'b'))
('x', '=', (('y', '-', 'b'), '/', 'm'))
('x', '=', (('w', '-', ('a', '+', 'b')), '/', ('y', '/', 'z')))
'''