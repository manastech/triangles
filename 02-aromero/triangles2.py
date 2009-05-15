import os

lines = [['D', 'G', 'E'],['A','D','B'],['A','E','C'],['D', 'H', 'J', 'C'],['E','H','I','B'],['A','G','H','F'],['B','F','C'],['D','I','F'],['E','J','F']]
triangles = set()

def add_triangle(a,b,c):
	cycle = [a,b,c]
	cycle.sort()
	triangles.add((cycle[0],cycle[1],cycle[2]))

# cruze de dos lineas
def cross(a,b):
	for x in a:
		if x in b: 
			return x
	return None

# buscar los 3 puntos que forman cruzes entre lineas
l = len(lines)
for i in range(l):
	for j in (range(i+1,l)):
		a = cross(lines[i], lines[j])
		if a is not None:
			for k in range(j+1,l):
				b = cross(lines[j],lines[k])
				if b is not None and a is not b:
					c = cross(lines[k],lines[i])
					if c is not None and a is not c and b is not c:
						add_triangle(a,b,c)
						
for t in triangles:
	print ' '+ str(t)
print 'Triangulos: ' + str(len(triangles))

os.system("PAUSE")