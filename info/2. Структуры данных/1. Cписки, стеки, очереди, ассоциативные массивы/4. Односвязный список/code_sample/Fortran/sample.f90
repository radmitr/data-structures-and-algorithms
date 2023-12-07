program sample
use linked_list
implicit none

type(List)::list_1

call list_1%add_first(4)
call list_1%add_first(7)
call list_1%add_first(1)
call list_1%add_first(-3)

call list_1%show_list()

call list_1%clear()

end program sample
