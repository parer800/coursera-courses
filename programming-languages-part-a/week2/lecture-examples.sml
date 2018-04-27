fun less_than(x: int, y: int) = if y < x then true else false;

(*

Syntax:
   if x < y then true else false

Type-checking:
   first x and y must have type int
   
Evaluation rules:
   filst evaluate x < y to a boolean expression, 

*)


fun pow(x: int, y: int) =
  if y = 0
  then 1
  else x * pow(x, y - 1)

	     
fun swap (pr: int * bool) =
  (#2 pr, #1 pr)

fun sum_two_pairs (pr1: int * int, pr2 : int * int) =
  (#1 pr1) + (#2 pr1) + (#1 pr2) + (#2 pr2)


fun div_mod (x : int, y : int) =
  (x div y, x mod y);

fun sort_pair(pr: int * int) =
  if (#1 pr) < (#2 pr)
  then pr
  else (#2 pr, #1 pr)

	   
