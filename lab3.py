class Sieve(object):
	def __init__(self, max):
		if max < 0:
			raise ValueError("max must be positive.")
		self.numbers = [False, False] + [True] * (max - 2)
	def findPrimes(self):
		for i, v in enumerate(self.numbers):
			if not v:
				continue
			for j in range(2 * i, len(self.numbers), i):
				self.numbers[j] = False
	def howMany(self):
		return reduce(lambda i, v: i + (1 if v else 0), self.numbers)
	def toList(self):
		return [i for i, v in enumerate(self.numbers) if v]

try:
	S = Sieve(-10)
except:
	print "Failed!"

S = Sieve(100)  
print S.howMany()     #  98  
print S.findPrimes()  
print S.howMany()     #  25  
print S.toList()      #  [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]

S = Sieve(1000)  
print S.howMany()     #  98  
print S.findPrimes()  
print S.howMany()     #  25  
print S.toList()      #  [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]