program main
use queue
implicit none

type(Array_Queue)::my_queue
logical::op_result = .false.
integer::n

call my_queue%init()

call my_queue%enqueue(3)
call my_queue%enqueue(5)

call my_queue%dequeue(n, op_result)
write(*,*) n
call my_queue%show()
call my_queue%enqueue(-1)
call my_queue%enqueue(9)

call my_queue%show()

call my_queue%dequeue(n, op_result)
write(*,*) n
call my_queue%show()

call my_queue%enqueue(7)
call my_queue%enqueue(1)
call my_queue%enqueue(11)
call my_queue%show()
call my_queue%peek(n, op_result)
write(*,*) n

call my_queue%show()

call my_queue%trim_to_size()

call my_queue%show()

call my_queue%clear()

call my_queue%show()

end program main
