program sample
use stack_mod
implicit none

type(Stack)::stack1
logical::operation_result
integer::temp

call stack1%push(3)
call stack1%push(5)
call stack1%push(-1)

call stack1%print_stack()

write(*,*) 'stack size = ', stack1%stack_size()

temp = stack1%pop(operation_result)
write(*,*) temp

write(*,*) 'stack size = ', stack1%stack_size()
call stack1%print_stack()

end program sample
