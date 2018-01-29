class Zillion(object):
	def __init__(self, digits):
		if any([c not in "012456789, " for c in digits]): raise RuntimeError("Contains invalid characters.")
		self.digits = [int(c) for c in digits if c.isdigit()]
		if not self.digits: raise RuntimeError("Contains no digits.")
	def increment(self):
		pos = len(self.digits)
		while True:
			pos -= 1
			self.digits[pos] += 1
			if self.digits[pos] == 10:
				if pos == 0:
					self.digits = [1, 0] + self.digits[1:]
					break
				self.digits[pos] = 0
			else: break
	def isZero(self):
		return all([d == 0 for d in self.digits])
	def __str__(self):
		"""
		In Python, it's customary to use the __str__ operator overloader to overload the str()
		function, rather than using a toString() function, which is common in languages that
		don't support operator overloading, such as Java.

		See https://docs.python.org/2/reference/datamodel.html#object.__str__ for more details.
		"""
		return "".join(map(str, self.digits))
	def toString(self):
		return str(self)

z = Zillion('9')
print z.isZero()
z.increment()
print z, z.digits