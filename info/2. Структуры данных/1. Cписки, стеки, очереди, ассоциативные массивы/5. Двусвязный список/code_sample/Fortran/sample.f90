program sample
use doubly_linked_list
implicit none

type(List)::list_1
logical::op_result = .false.
integer::n

call list_1%init()

call list_1%add_first(3)
call list_1%add_first(7)
call list_1%add_first(11)
call list_1%add_first(13)
call list_1%add_first(17)
call list_1%add_first(19)

call list_1%show_list()

n = list_1%get_by_index(1, op_result)

if(op_result) then
    write(*,*) n
else
    write(*,*) 'Invalid index'
end if

call list_1%delete_by_index(2,op_result)

if(op_result) then
    write(*,*) 'delete'
else
    write(*,*) 'Invalid index'
end if

call list_1%show_list()

call list_1%set_by_index(2, -5, op_result)
call list_1%show_list()

call list_1%insert_by_index(2, -10, op_result)

call list_1%show_list()

write(*,*) 'list size = ', list_1%get_length()

call list_1%clear()
call list_1%show_list()

write(*,*) 'list size = ', list_1%get_length()


end program sample
