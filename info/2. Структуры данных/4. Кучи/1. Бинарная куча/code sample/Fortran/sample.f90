program sample
use heap

type(BinaryHeap)::heap_1
character(len = 20)::data_value
logical::op_result

call heap_1%init()

call heap_1%add(6, "Orange")
call heap_1%add(7, "Apple")
call heap_1%add(3, "Plum")
call heap_1%add(4, "Lemon")
call heap_1%add(5, "Pear")
call heap_1%add(9, "Cherry")
!call heap_1%add(12, "Banana")

call heap_1%show_heap()
write(*,*)

!call heap_1%extract_and_insert(data_value,2,'Grapefruit',op_result)

!if(op_result) then
!    write(*,*) data_value
!end if

!call heap_1%delete_by_key(6)

call heap_1%change_key(6,2)

write(*,*)
call heap_1%show_heap()

call heap_1%destroy()

end program sample
