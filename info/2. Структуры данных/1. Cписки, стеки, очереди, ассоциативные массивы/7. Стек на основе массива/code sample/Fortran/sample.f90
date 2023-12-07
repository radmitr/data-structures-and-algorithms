program sample
use stacks
implicit none
type(Array_Based_Stack)::stack
integer::element
logical::op_result

op_result = .false.

call stack%init()

call stack%push(1)
call stack%push(3)
call stack%push(5)

call stack%show()
write(*,'(A13,I2)') 'stack size = ',stack%get_size()

do
    element = stack%pop(op_result)
    if(.not. op_result) then
        exit
    end if
    write(*,*) element
end do

end program sample
