program sample
use lists
implicit none
type(Array_Based_List)::list

logical::op_result
op_result = .false.

call list%init()

call list%add(1)
call list%add(3)
call list%add(5)

call list%show_list()

call list%add_by_index(-7, 1,op_result)

call list%show_list()


end program sample
