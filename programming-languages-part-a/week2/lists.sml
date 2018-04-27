
fun sum_list(xs: int list) =
    if null xs
    then 0
    else
    hd xs + sum_list(tl xs)

fun list_product(xs : int list) =
    if null xs
    then 1
    else
        hd xs * list_product(tl xs)

fun countdown (x: int) =
    if x = 0
    then []
    else x :: countdown(x - 1)

fun countup (from: int, to: int) =
    if from = to
    then to::[]
    else from :: countup(from + 1, to)

fun append(xs : int list, ys: int list) =
    if null xs
    then ys
    else (hd xs) :: append(tl xs, ys)


fun sum_pair_list(xs : (int * int) list) =
    if null xs
    then 0
    else #1 (hd xs) + #2 (hd xs) + sum_pair_list(tl xs)

fun firsts(xs: (int * int) list) =
    if null xs
    then []
    else #1 (hd xs) :: firsts(tl xs)

fun seconds(xs: (int * int) list) =
    if null xs
    then []
    else #2 (hd xs) :: seconds(tl xs)

(* sum_pair_list2([(1,2), (2, 3)])*)
fun sum_pair_list2(xs: (int * int) list) =
    sum_list(firsts xs) + sum_list(seconds xs)

fun factorial(n: int) =
    list_product(countdown(n))

fun max_in_list(xs : int list) =
    if null xs
    then 0
    else if null (tl xs)
    then hd xs
    else
        let val tl_max = max_in_list(tl xs)
        in
            if hd xs > tl_max
            then hd xs
            else
            tl_max
        end
