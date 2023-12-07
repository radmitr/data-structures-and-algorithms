program sample
use queue_implementation
implicit none

type(Queue)::my_queue
logical::op_result = .false.
integer::n

call my_queue%init()

call my_queue%enqueue(5)
call my_queue%enqueue(7)
call my_queue%enqueue(11)
call my_queue%enqueue(13)

call my_queue%show()

write(*,*) 'size = ', my_queue%get_length()

do
    n = my_queue%dequeue(op_result)
    if (.not. op_result) then
        exit
    end if
    write(*,*) n
end do

call my_queue%show()

write(*,*) 'size = ', my_queue%get_length()


end program sample
