import os

def pairs(l):
	return [(x,y) for i,x in enumerate(l) for y in l[i+1:]]
	
def make_graph(lines):
	graph = {}
	
	def add_axis(x, y):
		if not x in graph.keys(): graph[x] = [y]
		elif not y in graph[x]: graph[x].append(y)
		
	for line in lines:
		for x,y in pairs(line):
			add_axis(x,y)
			add_axis(y,x)

	for adjs in graph.values():
		adjs.sort()
			
	return graph
		
def get_k3s(graph):
	k3s = set()	
		
	def add_cycle(a,b,c):
		cycle = [node, adj, third]
		cycle.sort()
		k3s.add((cycle[0], cycle[1], cycle[2]))
		
	for node in graph:
		for adj in graph[node]:
			for third in graph[adj]:
				if (third != node and third != adj and third in graph[node]):
					add_cycle(node, adj, third)
		
	return k3s

def filter_lines(k3s, lines):
	for a,b,c in k3s:
		if not any([a in l and b in l and c in l for l in lines]):
			yield (a,b,c)
	
def solve(lines):
	graph = make_graph(lines)
	k3s = get_k3s(graph)
	return ([t for t in filter_lines(k3s, lines)], graph)
	
#lines = [['A', 'B'],['A', 'D'],['A', 'C'],['B', 'C', 'D']]

lines = [['D', 'G', 'E'],['A','D','B'],['A','E','C'],['D', 'H', 'J', 'C'],['E','H','I','B'],['A','G','H','F'],['B','F','C'],['D','I','F'],['E','J','F']]
(solution, graph) = solve(lines)

print 'Grafo: '
for node, adjs in graph.iteritems():
	print ' ' + node + ': ' + str(adjs)
print 'Triangulos: ' + str(len(solution))
for t in solution:
	print ' '+ str(t)
print 'Triangulos: ' + str(len(solution))

os.system("PAUSE")