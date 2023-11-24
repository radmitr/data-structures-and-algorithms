module queue

implicit none


type Array_Queue
    integer,allocatable::data_array(:)
    integer::tail, head, queue_size
    
    contains
        procedure,pass::init_array_queue
        procedure,pass::enqueue_array_queue
        procedure,pass::up_resize_array_queue
        procedure,pass::dequeue_array_queue
        procedure,pass::peek_array_queue
        procedure,pass::trim_to_size_array_queue
        procedure,pass::clear_array_queue
        procedure,pass::destroy_array_queue
        procedure,pass::show_array_queue
end type Array_Queue
        
contains

    subroutine init_array_queue(this)
        class(Array_Queue)::this
        if(.not. allocated(this%data_array)) then
            allocate(this%data_array(16))
            this%tail = 1
            this%head = 1
            this%queue_size = 0
        end if
    end subroutine init_array_queue
    
    subroutine up_resize_array_queue(this)
        class(Array_Queue)::this
        integer,allocatable::temp_array(:)
        integer::add_index, new_size, old_size
        old_size = size(this%data_array)
        new_size = old_size * 4/3 + 1
        allocate(temp_array(new_size))
        add_index = 1
        do
            if(this%head == this%tail) then
                exit
            end if
            temp_array(add_index) = this%data_array(this%head)
            add_index = add_index + 1
            this%head = this%head + 1
            if(this%head > size(this%data_array)) then
                this%head = 1
            end if
        end do
        this%data_array = temp_array
        this%head = 1
        this%tail = add_index
        this%queue_size = add_index - 1
        deallocate(temp_array)
    end subroutine up_resize_array_queue
         

    subroutine enqueue_array_queue(this, data_value)
        class(Array_Queue)::this
        integer, intent(in)::data_value
        integer:: current_size, next_index
        current_size = size(this%data_array)
        next_index = this%tail + 1
        if (next_index > current_size) then
            next_index = 1
        end if
        if (next_index == this%head) then
            call this%up_resize_array_queue()
        end if
        this%data_array(this%tail) = data_value
        this%tail = this%tail + 1
        if (this%tail > size(this%data_array)) then
            this%tail = 1
        end if
        this%queue_size = this%queue_size + 1
    end subroutine enqueue_array_queue
    
    subroutine dequeue_array_queue(this, element, op_result)
        class(Array_Queue)::this
        integer, intent(inout)::element
        logical, intent(inout)::op_result
        if(this%head == this%tail) then
            op_result = .false.
            return
        end if
        element = this%data_array(this%head)
        op_result = .true.
        this%head = this%head + 1
        if(this%head > size(this%data_array)) then
            this%head = 1
        end if
        this%queue_size = this%queue_size - 1
    end subroutine dequeue_array_queue
    
    subroutine peek_array_queue(this, element, op_result)
        class(Array_Queue)::this
        integer, intent(inout)::element
        logical, intent(inout)::op_result
        if(this%head == this%tail) then
            op_result = .false.
            return
        end if
        element = this%data_array(this%head)
        op_result = .true.
    end subroutine peek_array_queue
    
    subroutine trim_to_size_array_queue(this)
        class(Array_Queue)::this
        integer::add_index
        integer, allocatable::new_array(:)
        allocate(new_array(this%queue_size + 1))
        add_index = 1
        do
            if(this%head == this%tail) then
                exit
            end if
            new_array(add_index) = this%data_array(this%head)
            add_index = add_index + 1
            this%head = this%head + 1
            if(this%head > size(this%data_array)) then
                this%head = 1
            end if
        end do
        this%data_array = new_array
        this%head = 1
        this%tail = add_index
        this%queue_size = add_index - 1
        deallocate(new_array)
    end subroutine trim_to_size_array_queue
            
    subroutine clear_array_queue(this)
        class(Array_Queue)::this
        if(allocated(this%data_array)) then
            deallocate(this%data_array)
            allocate(this%data_array(16))
        end if
        this%head = 1
        this%tail = 1
        this%queue_size = 0
    end subroutine clear_array_queue
    
    subroutine destroy_array_queue(this)
        class(Array_Queue)::this
        if(allocated(this%data_array)) then
            deallocate(this%data_array)
        end if
    end subroutine destroy_array_queue
    
    subroutine show_array_queue(this)
        class (Array_Queue)::this
        integer::i, j
        i = this%tail - 1
        if (i < 1) then
            i = size(this%data_array)
        end if
        write(*,'(A1)',advance = 'NO') '['
        do j = 1, this%queue_size
            write(*,'(I5)',advance = 'NO') this%data_array(i)
            i = i - 1
            if (i < 1) then
                i = size(this%data_array)
            end if
        end do
        write(*,*)']'
    end subroutine show_array_queue
            
end module queue
