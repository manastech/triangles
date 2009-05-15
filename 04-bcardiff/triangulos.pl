
p(1).
p(2).
p(3).
p(4).
p(5).
p(6).
p(7).
p(8).
p(9).
p(10).

linea([1,2,3]).
linea([7,8,9]).
linea([1,7,10]).
linea([2,5,9]).
linea([10,9,3]).
linea([7,4,2]).
linea([1,4,6,9]).
linea([3,5,6,7]).
linea([2,6,8,10]).

alineados(A,B) :- linea(L), member(A,L), member(B,L).
alineados(A,B,C) :- linea(L), member(A,L), member(B,L), member(C,L).

puntos(A,B,C) :- p(A), p(B), A < B, p(C), B < C.

triangulo(A,B,C) :- puntos(A,B,C), alineados(A,B), alineados(B,C), alineados(C,A), not(alineados(A,B,C)).

triangulos(N) :- findall((A,B,C), triangulo(A,B,C), T), length(T,N).
