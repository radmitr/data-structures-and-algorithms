module linked_list

implicit none

type Node
    integer::data_value
    class(Node),pointer::next=>null()
end type Node

type List
    class(Node), pointer::head=>null()
    
    contains
        procedure,pass::add_first
        procedure,pass::add_last
        procedure,pass::delete_first
        procedure,pass::delete_last
        procedure,pass::get_length
        procedure,pass::insert_by_index
        procedure,pass::delete_by_index
        procedure,pass::get_by_index
        procedure,pass::set_by_index
        procedure,pass::clear
        procedure,pass::show_list
end type List
        
contains


    subroutine add_first(this, data_value)
        class(List)::this
        integer, intent(in)::data_value
        class (Node), pointer::new_node
        new_node=>null()
        allocate(new_node)
        new_node%data_value = data_value
        new_node%next => this%head
        this%head => new_node
    end subroutine add_first
    
    subroutine add_last(this, data_value)
        class(List)::this
        integer, intent(in)::data_value
        class (Node), pointer::new_node
        class(Node), pointer::current_node
        if(.not. associated (this%head)) then
            call this%add_first(data_value)
            return
        end if
        allocate(new_node)
        new_node%data_value = data_value
        current_node => this%head
        do
            if(.not. associated (current_node%next)) then
                exit
            end if
            current_node => current_node%next
        end do
        current_node%next => new_node
    end subroutine add_last
    
    subroutine delete_first(this)
        class(List), intent(inout)::this
        class(Node), pointer::current_node=>null()
        if(.not. associated (this%head)) then
            return
        end if
        current_node => this%head%next
        this%head%next => null()
        deallocate (this%head)
        this%head => current_node
    end subroutine delete_first
    
    subroutine delete_last(this)
        class(List)::this
        class(Node),pointer::current_node
        class(Node),pointer::previous_node
        if(.not. associated (this%head)) then
            return
        end if
        current_node => this%head%next
        if(.not. associated (current_node)) then
            call this%delete_first()
            return
        end if
        previous_node => this%head
        do
            if(.not. associated (current_node%next)) then
                exit
            end if
            previous_node => current_node
            current_node => current_node%next
        end do
        previous_node%next => null()
        deallocate(current_node)
    end subroutine delete_last
    
    integer(8) function get_length(this)
        class(List)::this
        integer(8)::list_size
        class(Node),pointer::current_node
        current_node => this%head
        list_size = 0
        do
            if(.not. associated (current_node)) then
                exit
            end if
            list_size=list_size + 1_8
            current_node => current_node%next
        end do
        get_length = list_size
    end function get_length
    
    subroutine insert_by_index(this, data_value, indx, res)
        class(List)::this
        integer,intent(in)::data_value
        integer(8),intent(in)::indx
        logical,intent(inout)::res
        class(Node), pointer::current_node, previous_node
        class(Node), pointer::new_node
        integer(8)::node_index
        node_index = 1
        res = .false.
        if(indx == 1) then
            call this%add_first(data_value)
            res = .true.
            return
        end if
        node_index = 2
        previous_node => this%head
        current_node => this%head%next 
        do
            if(.not. associated (current_node)) then
                return
            end if
            if(node_index == indx) then
                allocate(new_node)
                new_node%data_value = data_value
                new_node%next => current_node
                previous_node%next => new_node
                res = .true.
                return
            end if
            node_index=node_index + 1_8
            previous_node => current_node
            current_node => current_node%next
        end do
    end subroutine insert_by_index
    
    subroutine delete_by_index(this, indx, res)
        class(List)::this
        integer(8),intent(in)::indx
        logical,intent(inout)::res
        class(Node), pointer::current_node, previous_node
        integer(8)::node_index
        node_index = 2
        res = .false.
        if (indx == 1) then
            call this%delete_first()
            res = .true.
            return
        end if
        previous_node => this%head
        current_node => this%head%next 
        do
            if(.not. associated (current_node)) then
                return
            end if
            if(node_index == indx) then
                previous_node%next => current_node%next
                current_node%next => null()
                deallocate(current_node)
                res = .true.
                return
            end if
            node_index=node_index + 1_8
            previous_node => current_node
            current_node => current_node%next
        end do
    end subroutine delete_by_index
       
    integer function get_by_index(this, indx, res)
        class(List)::this
        integer(8),intent(in)::indx
        logical,intent(inout)::res
        class(Node), pointer::current_node
        integer(8)::node_index
        node_index = 1
        current_node=> this%head
        do
            if(.not. associated (current_node)) then
                return
            end if
            if(node_index == indx) then
                get_by_index = current_node%data_value
                res = .true.
                return
            end if
            node_index=node_index + 1_8
            current_node => current_node%next
        end do
    end function get_by_index
   
    subroutine set_by_index(this, data_value, indx, res)
        class(List)::this
        integer(8),intent(in)::indx
        integer,intent(in)::data_value
        logical,intent(inout)::res
        class(Node), pointer::current_node
        integer(8)::node_index
        node_index = 1
        current_node=> this%head
        do
            if(.not. associated (current_node)) then
                return
            end if
            if(node_index == indx) then
                current_node%data_value = data_value
                res = .true.
                return
            end if
            node_index=node_index + 1_8
            current_node => current_node%next
        end do
    end subroutine set_by_index
    
    subroutine clear(this)
        class(List)::this
        do
            if(.not. associated (this%head)) then
                exit
            end if 
            call this%delete_first()
        end do
    end subroutine clear
    
    subroutine show_list(this)
        class(List)::this
        class(Node),pointer::current_node
        current_node => this%head
        do
            if(.not. associated (current_node)) then
                exit
            end if
            write(*,'(i3,A)',advance = 'NO') current_node%data_value,' -> '
            current_node => current_node%next
        end do
        write(*,*)''
    end subroutine show_list

end module linked_list
