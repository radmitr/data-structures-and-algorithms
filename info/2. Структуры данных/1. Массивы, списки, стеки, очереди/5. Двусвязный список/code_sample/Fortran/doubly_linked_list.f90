module doubly_linked_list

implicit none

type Node
    integer::data_value
    class(Node),pointer::next=>null()
    class(Node),pointer::prev=>null()
end type Node

type List
    class(Node),pointer::head => null()
    class(Node),pointer::tail => null()
    integer::length = 0    
    contains
        procedure,pass::init
        procedure,pass::add_first
        procedure,pass::add_last
        procedure,pass::delete_first
        procedure,pass::delete_last
        procedure,pass::get_length
        procedure,pass::get_node_by_index
        procedure,pass::insert_by_index
        procedure,pass::delete_by_index
        procedure,pass::get_by_index
        procedure,pass::set_by_index
        procedure,pass::show_list
        procedure,pass::clear
        procedure,pass::destroy
        
        
end type List
        
contains

    subroutine init(this)
        class(List)::this
        allocate(this%head)
        allocate(this%tail)
        this%head%next => this%tail
        this%tail%prev => this%head
    end subroutine init 

    subroutine add_first(this, data_value)
        class(List)::this
        integer, intent(in)::data_value
        class (Node), pointer::new_node
        allocate(new_node)
        new_node%data_value = data_value
        new_node%next => this%head%next
        new_node%prev => this%head
        new_node%next%prev => new_node
        this%head%next => new_node
        this%length = this%length + 1
    end subroutine add_first
    
    subroutine add_last(this, data_value)
        class(List)::this
        integer, intent(in)::data_value
        class (Node), pointer::new_node
        allocate(new_node)
        new_node%data_value = data_value
        new_node%next => this%tail
        new_node%prev => this%tail%prev
        new_node%prev%next => new_node
        this%tail%prev => new_node
        this%length = this%length + 1
    end subroutine add_last

    subroutine delete_first(this)
        class(List)::this
        class (Node), pointer::delete_node
        if(associated(this%head%next, this%tail)) then
            return
        end if
        delete_node => this%head%next
        this%head%next => delete_node%next
        delete_node%next%prev => delete_node%prev
        delete_node%next => null()
        delete_node%prev => null()
        deallocate(delete_node)
        this%length = this%length - 1
    end subroutine delete_first

    subroutine delete_last(this)
        class(List)::this
        class (Node), pointer::delete_node
        if(associated(this%head%next, this%tail)) then
            return
        end if
        delete_node => this%tail%prev
        this%tail%prev => delete_node%prev
        delete_node%prev%next => this%tail
        delete_node%next => null()
        delete_node%prev => null()
        deallocate(delete_node)
        this%length = this%length - 1
    end subroutine delete_last

    subroutine get_node_by_index(this, node_index, result_node)
        class(List)::this
        integer, intent(in)::node_index
        class (Node), pointer, intent(inout)::result_node
        integer::current_index
  
        if (node_index < 1 .or. node_index > this%length) then
            return
        end if
        
        if (node_index < this%length / 2) then
            current_index = 1
            result_node => this%head%next
            do
                if(current_index == node_index) then
                    exit
                end if
                current_index = current_index + 1
                result_node => result_node%next
            end do
        else
            current_index = this%length
            result_node => this%tail%prev
            do
                if(current_index == node_index) then
                    exit
                end if
                current_index = current_index - 1
                result_node => result_node%prev
            end do 
        end if
    end subroutine get_node_by_index

    function get_by_index(this, node_index, op_result)
        class(List)::this
        integer, intent(in):: node_index
        logical, intent(inout)::op_result
        integer::get_by_index
        class(Node), pointer::current_node      
        op_result = .false.
        current_node => null()
        call this%get_node_by_index(node_index, current_node)
        if(associated(current_node)) then
            get_by_index = current_node%data_value
            op_result = .true.
        end if
    end function get_by_index

    subroutine delete_by_index(this, node_index, op_result)
        class(List)::this
        integer, intent(in):: node_index
        logical, intent(inout)::op_result
        class(Node), pointer::current_node      
        op_result = .false.
        current_node => null()
        call this%get_node_by_index(node_index, current_node)
        if(associated(current_node)) then
            current_node%prev%next => current_node%next
            current_node%next%prev => current_node%prev
            current_node%next => null()
            current_node%prev => null()
            deallocate(current_node)
            this%length = this%length - 1
            op_result = .true.
        end if
    end subroutine delete_by_index

    subroutine set_by_index(this, node_index, data_value, op_result)
        class(List)::this
        integer, intent(in):: node_index
        logical, intent(inout)::op_result
        integer, intent(in)::data_value
        class(Node), pointer::current_node      
        op_result = .false.
        current_node => null()
        call this%get_node_by_index(node_index, current_node)
        if(associated(current_node)) then
            current_node%data_value = data_value
            op_result = .true.
        end if
    end subroutine set_by_index

    subroutine insert_by_index(this, node_index, data_value, op_result)
        class(List)::this
        integer, intent(in):: node_index
        logical, intent(inout)::op_result
        integer, intent(in)::data_value
        class(Node), pointer::current_node
        class(Node), pointer::new_node      
        op_result = .false.
        current_node => null()
        call this%get_node_by_index(node_index, current_node)
        if(associated(current_node)) then
            allocate(new_node)
            new_node%data_value = data_value
            new_node%next => current_node
            new_node%prev => current_node%prev
            current_node%prev%next => new_node
            current_node%prev =>new_node
            this%length = this%length + 1
            op_result = .true.
        end if
    end subroutine insert_by_index
    
    function get_length(this)
        class(List)::this
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
        class(List)::this
        do
            if(this%length == 0) then
                exit
            end if
            call this%delete_first()
        end do
    end subroutine clear
    
    subroutine destroy(this)
        class(List)::this
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
 
    
    subroutine show_list(this)
        class(List)::this
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
    end subroutine show_list

end module doubly_linked_list
