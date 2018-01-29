class Random:
	def __init__(self, seed):
		self.seed = seed

	def next(self, range=2147483648):
		number = (7 ** 5 * self.seed) % (2 ** 31 - 1)
		self.seed = number
		return number % range

	def choose(self, objects):
		return objects[self.next(len(objects))]

class Nonce:
	def __init__(self, seed):
		self._first = []
		self._follow = {}
		self._random = Random(seed)

	def add(self, word):
		if word[0] not in self._first:
			self._first.append(word[0])
		for i in range(len(word) - 1):
			if word[i] not in self._follow:
				self._follow[word[i]] = []
			if word[i + 1] not in self._follow[word[i]]:
				self._follow[word[i]].append(word[i + 1])

	def make(self, size):
		word = self._random.choose(self._first)
		while len(word) < size:
			next = self._follow.get(word[-1])
			if not next:
				word += self._random.choose(self._first)
			else:
				word += self._random.choose(next)
		return word

nw = Nonce(101)
nw.add("ada")
nw.add("algol")
nw.add("bliss")
nw.add("ceylon")
nw.add("clojure")
nw.add("curl")
nw.add("dart")
nw.add("eiffel")
nw.add("elephant")
nw.add("elisp")
nw.add("falcon")
nw.add("fortran")
nw.add("go")
nw.add("groovy")
nw.add("haskell")
nw.add("heron")
nw.add("intercal")
nw.add("java")
nw.add("javascript")
nw.add("latex")
nw.add("lisp")
nw.add("mathematica")
nw.add("nice")
nw.add("oak")
nw.add("occam")
nw.add("orson")
nw.add("pascal")
nw.add("postscript")
nw.add("prolog")
nw.add("python")
nw.add("ruby")
nw.add("scala")
nw.add("scheme")
nw.add("self")
nw.add("snobol")
nw.add("swift")
nw.add("tex")
nw.add("wolfram")

for i in range(100):
	print nw.make(6)