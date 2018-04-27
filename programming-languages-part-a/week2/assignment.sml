(* date_type: (int * int * int) *)

fun is_greater(x : int, y : int) =
    x > y

(* helper functions *)
fun is_same_as(d1 : int * int * int, d2 : int * int * int) =
    (#1 d1 = #1 d2) andalso (#2 d1 = #2 d2) andalso (#3 d1 = #3 d2)

fun year_of(date : int * int * int) =
    #1 date

fun month_of(date : int * int * int) =
    #2 date

fun day_of(date : int * int * int) =
    #3 date

(* Assignments 1, 2,..., 12 *)

fun is_older(d1 : int * int * int, d2 : int * int * int) =
    (year_of(d1) < year_of(d2))
    orelse (year_of(d1) = year_of(d2) andalso month_of(d1) < month_of(d2))
    orelse (year_of(d1) = year_of(d2) andalso month_of(d1) = month_of(d2) andalso day_of(d1) < day_of(d2))

fun number_in_month(dates: (int * int * int) list, month: int) =
    let fun compare_dates_with_month(xs: (int * int * int) list, count: int) =
        if null xs
        then count
        else if month_of(hd xs) = month
        then iter(tl xs, count + 1)
        else iter (tl xs, count)
    in
        compare_dates_with_month(dates, 0)
    end

fun number_in_month2(dates: (int * int * int) list, month: int) =
    if null dates
    then 0
    else
        let
            val count = number_in_month(tl dates, month)
        in
            if month_of(hd dates) = month
            then count + 1
            else count
        end


(* 3 *)
fun number_in_months(dates: (int * int * int) list, months: int list) =
    if null months
    then 0
    else number_in_month(dates, hd months) + number_in_months(dates, tl months)

(* 4 *)
fun dates_in_month(dates: (int * int * int) list, month: int) =
    if null dates
    then []
    else
        let
            val dim = dates_in_month(tl dates, month)
        in
            if month_of(hd dates) = month
            then (hd dates) :: dim
            else dim
        end

(* 5 *)
fun dates_in_months(dates: (int * int * int) list, months: int list) =
    if null months
    then []
    else dates_in_month(dates, hd months) :: dates_in_months(dates, tl months)

(* 6 *)
fun get_nth(strings: string list, n : int) =
    if n = 1
    then hd strings
    else
        get_nth(tl strings, n - 1)

fun date_to_string(date : int * int * int) =
    let val month_names = ["January", "February", "March", "April","May", "June", "July", "August", "September", "October", "November", "December"]
    in
        get_nth(month_names, month_of(date)) ^ " " ^ Int.toString(day_of(date)) ^ ", "  ^ Int.toString(year_of(date))
    end;

fun number_before_reaching_sum(sum: int, numbers: int list) =
    let fun find_sum(n: int, nums: int list) =
        if n - hd nums - hd (tl(nums)) <= 0
        then hd nums
        else find_sum(n - hd nums, tl nums);
    in
        find_sum(sum, numbers)
    end;

fun number_before_reaching_sum(sum: int, numbers: int list) =
    if hd numbers >= sum
    then 0
    else 1 + number_before_reaching_sum(sum - hd numbers, tl numbers);

fun what_month(day_of_year: int) =
    let val days_per_month = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30 ,31]
    in
        (* The first month is January = 1, thus 1 + number_before_reaching_sum... *)
        1 + number_before_reaching_sum(day_of_year, days_per_month)
    end;

fun oldest(dates: (int * int * int) list) =
    if null dates
    then NONE
    else
        let val oldest_date = oldest(tl dates)
        in
            if is_older(hd dates, oldest_date)
            then SOME(hd dates)
            else SOME(oldest_date)
        end

fun oldest(dates: (int * int * int) list) =
    if null dates
    then NONE
    else
        let val oldest_date = oldest(tl dates)
        in
            if isSome oldest_date andalso is_older(valOf oldest_date, hd dates)
            then oldest_date
            else SOME(hd dates)
        end


fun what_month(day_of_year: int) =

    val days_per_month = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30 ,31]
    number_before_reaching_sum(day_of_year, days_per_month)

val test2 = number_in_month ([(2012,2,28),(2013,12,1), (2011,2,1)],2);
val test4 = dates_in_month ([(2012,2,28),(2013,12,1)],7)
val test4 = dates_in_month ([(2012,2,28),(2013,12,1), (2011,2,18)],2) = [(2012,2,28), (2011,2,18)]
val test5 = dates_in_months ([(2012,2,28),(2013,12,1),(2011,3,31),(2011,4,28)],[2,3,4]) = [(2012,2,28),(2011,3,31),(2011,4,28)]
val test6 = get_nth (["hi", "there", "how", "are", "you"], 2) = "there"
val test7 = date_to_string (2013, 6, 1)
val test8 = number_before_reaching_sum (10, [1,2,3,4,5])

val test8 = number_before_reaching_sum (10, [0, 0, 1,2,3,4,5]);

val test11 = oldest([(2012,2,28),(2011,3,31),(2011,4,28)]) = SOME (2011,3,31)

number_before_reaching_sum (1, [1,2,3,4,5]);
(*
let fun rotate_units(d1 : int * int * int, d2 : int * int * int) =
    ((#2 d1, #3 d1, #1 d1), (#2 d2, #3 d2, #1 d2))
in

(* If the same year, but not the same date, call  *)
if (#1 d1 = #1 d2) andalso not(is_same_as(d1, d2))
then is_older((#2 d1, #3 d1, #1 d1), (#2 d2, #3 d2, #1 d2))
else #1 d1 < #1 d2
*)

(*    else
        let fun day_of_year(date: int * int * int) =
            (#2 date) * 31 + (#3 date)
        in
            day_of_year(d1) < day_of_year(d2)
        end
        *)

is_older((2016, 3, 21), (2016, 3, 21));
is_older((2016, 3, 21), (2016, 3, 22));
is_older((2016, 3, 21), (2016, 3, 20));
