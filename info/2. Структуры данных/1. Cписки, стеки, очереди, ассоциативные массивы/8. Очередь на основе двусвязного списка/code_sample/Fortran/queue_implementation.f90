module queue_implementation

implicit none

type Node
    integer::data_value
    class(Node),pointer::next=>null()
    class(Node),pointer::prev=>null()
end type Node

type Queue
    class(Node),pointer::head => null()
    class(Node),pointer::tail => null()
    integer::length = 0    
    contains
        procedure,pass::init
        procedure,pass::enqueue
        procedure,pass::dequeue
        procedure,pass::get_length
        procedure,pass::peek
        procedure,pass::show
        procedure,pass::clear
        procedure,pass::destroy
        
        
end type Queue
        
contains

    subroutine init(this)
        class(Queue)::this
        allocate(this%head)
        allocate(this%tail)
        this%head%next => this%tail
        this%tail%prev => this%head
    end subroutine init 

    subroutine enqueue(this, data_value)
        class(Queue)::this
        integer, intent(in)::data_value
        class (Node), pointer::new_node
        allocate(new_node)
        new_node%data_value = data_value
        new_node%next => this%head%next
        new_node%prev => this%head
        new_node%next%prev => new_node
        this%head%next => new_node
        this%length = this%length + 1
    end subroutine enqueue
    
    function dequeue(this, op_result)
        class(Queue)::this
        logical, intent(inout) :: op_result
        class (Node), pointer::delete_node
        integer::dequeue
        op_result = .false.
        if(associated(this%head%next, this%tail)) then
            return
        end if
        delete_node => this%tail%prev
        this%tail%prev => delete_node%prev
        delete_node%prev%next => this%tail
        dequeue = delete_node%data_value
        delete_node%next => null()
        delete_node%prev => null()
        deallocate(delete_node)
        this%length = this%length - 1
        op_result = .true.
    end function dequeue

    function peek(this, op_result)
        class(Queue)::this
        logical, intent(inout) :: op_result
        integer::peek
        op_result = .false.
        if(associated(this%head%next, this%tail)) then
            return
        end if
        peek = this%tail%prev%data_value
        op_result = .true.
    end function peek
   
    function get_length(this)
        class(Queue)::this
        class(Node), pointer::current_node
        integer::get_length
        get_length = 0
        current_node => this%head%next
        do
            if(associated(current_node,this%tail)) then
                exit
            end if
            get_length = get_length + 1
            current_node => current_node%next
        end do
    end function get_length
 
    subroutine clear(this)
        class(Queue)::this
        integer::n
        logical::op_result
        do
            n = this%dequeue(op_result)
            if(.not. op_result) then
                exit
            end if
        end do
    end subroutine clear
    
    subroutine destroy(this)
        class(Queue)::this
        if( .not. associated(this%head)) then
            return
        end if
        if(this%length > 0) then
            call this%clear()
        end if
        
        this%head%prev => null()
        this%head%next => null()
        this%tail%prev => null()
        this%tail%next => null()
        deallocate(this%head)
        deallocate(this%tail)
    end subroutine destroy
 
    
    subroutine show(this)
        class(Queue)::this
        class(Node), pointer::current_node
        current_node => this%head%next
        write(*,'(A)',advance = 'NO') '['
        do
            if(associated(current_node,this%tail)) then
                exit
            end if
            write(*,'(i3,A)',advance = 'NO') current_node%data_value,' '
            current_node => current_node%next
        end do
        write(*,*)']'
    end subroutine show

end module queue_implementation
