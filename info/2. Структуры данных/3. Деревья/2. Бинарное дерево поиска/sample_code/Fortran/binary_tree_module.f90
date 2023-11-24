module binary_tree_module

type Node
    integer::node_key
    character(len = 20)::node_data
    type(Node), pointer::node_left => null()
    type(Node), pointer::node_right => null()
end type Node

type BinaryTree
    type(Node), pointer::root
    
    contains
        procedure,pass::init
        procedure,pass::add
        procedure,pass::get
        procedure,pass::remove
        procedure,pass::show_tree
        procedure,pass::tree_size
        procedure,pass::destroy_tree
        procedure,nopass,private::r_add
        procedure,nopass,private::r_show_tree
        procedure,nopass,private::r_get
        procedure,nopass,private::r_remove
        procedure,nopass,private::r_tree_size
        procedure,nopass,private::find_smallest_node
        procedure,nopass,private::r_destroy_tree
end type BinaryTree

contains

subroutine init(this)
    class(BinaryTree)::this
    this%root => null()
end subroutine init

recursive subroutine r_add(node_key, node_data, add_node)
    integer, intent(in)::node_key
    character(len=*), intent(in)::node_data
    type(Node),pointer,intent(inout)::add_node
    if (.not. associated(add_node)) then
        allocate(add_node)
        add_node%node_key = node_key
        add_node%node_data = node_data
        add_node%node_left => null()
        add_node%node_right => null()
        return
    end if
    if (add_node%node_key == node_key) then
        add_node%node_data = node_data
        return
    end if
    if(add_node%node_key > node_key) then
        call r_add(node_key, node_data, add_node%node_left)
    else
        call r_add(node_key, node_data, add_node%node_right)
    end if 
end subroutine r_add

recursive subroutine add(this, node_key, node_data)
    class(BinaryTree)::this
    integer, intent(in)::node_key
    character(len=*), intent(in)::node_data
    call r_add(node_key, node_data, this%root)    
end subroutine add

recursive subroutine r_get(node_key,r_node, result_data, operation_result)
    integer, intent(in)::node_key
    type(Node), pointer::r_node
    character(len=*), intent(inout)::result_data
    logical, intent(inout)::operation_result
    if (.not. associated(r_node)) then
        operation_result = .false.
        return
    end if
    if(r_node%node_key == node_key) then
        result_data = r_node%node_data
        operation_result = .true.
        return
    end if
    if(r_node%node_key > node_key) then
        call r_get(node_key, r_node%node_left, result_data, operation_result)
    else
        call r_get(node_key, r_node%node_right, result_data, operation_result)
    end if
end subroutine r_get

subroutine get(this, node_key, result_data, operation_result)
    class(BinaryTree)::this
    integer, intent(in)::node_key
    character(len=*), intent(inout)::result_data
    logical, intent(inout)::operation_result
    call r_get(node_key,this%root, result_data, operation_result)
end subroutine get


recursive subroutine r_remove(node_key ,r_node, operation_result)
    integer, intent(in)::node_key
    type(Node), pointer::r_node, temp_node
    logical, intent(inout)::operation_result
    
    if(.not. associated(r_node)) then
        operation_result = .false.
        return
    end if
    
    if (r_node%node_key == node_key) then
        if(.not. associated (r_node%node_left) .and. .not. associated(r_node%node_right)) then
            deallocate(r_node)
            r_node => null()
            operation_result = .true.
            return
        end if
        
        if (.not.associated (r_node%node_left)) then
            temp_node => r_node%node_right
            deallocate(r_node)
            r_node => temp_node
            operation_result = .true.
            return
        end if
        
        if (.not.associated (r_node%node_right)) then
            temp_node => r_node%node_left
            deallocate(r_node)
            r_node => temp_node
            operation_result = .true.
            return
        end if
        
        if(associated (r_node%node_left) .and. associated(r_node%node_right)) then
            call find_smallest_node(r_node%node_right, temp_node)
            r_node%node_key = temp_node%node_key
            r_node%node_data = temp_node%node_data
            call r_remove(temp_node%node_key,r_node%node_right,operation_result)
            return
        end if        
    end if
    
    if(r_node%node_key > node_key) then
        call r_remove(node_key ,r_node%node_left, operation_result)
    else
        call r_remove(node_key ,r_node%node_right, operation_result)
    end if
end subroutine r_remove

recursive subroutine find_smallest_node(r_node, temp_node)
    type(Node), pointer, intent(inout)::r_node, temp_node
    
    if(.not. associated(r_node%node_left)) then
        temp_node => r_node
        return
    else
        call find_smallest_node (r_node%node_left, temp_node)
    end if
end subroutine find_smallest_node


subroutine remove(this, node_key, operation_result)
    class(BinaryTree)::this
    integer, intent(in)::node_key
    logical, intent(inout)::operation_result
    
    call this%r_remove(node_key ,this%root, operation_result)
    
end subroutine remove

recursive subroutine r_tree_size(r_node, r_size)
    type(Node), pointer::r_node
    integer, intent(inout)::r_size
    if (.not. associated(r_node)) then
        return
    else
        r_size = r_size + 1
        call r_tree_size(r_node%node_left, r_size)
        call r_tree_size(r_node%node_right, r_size)
    end if
end subroutine r_tree_size

subroutine tree_size(this,r_size)
    class(BinaryTree)::this
    integer, intent(inout)::r_size
    r_size = 0
    call r_tree_size(this%root,r_size)
end subroutine tree_size

recursive subroutine r_destroy_tree(r_node)
    type(Node), pointer, intent(inout)::r_node
    if(.not. associated (r_node)) then
        return
    end if
    call r_destroy_tree(r_node%node_left)
    call r_destroy_tree(r_node%node_right)
    if(.not. associated (r_node%node_left) .and. .not. associated(r_node%node_right)) then
        deallocate(r_node)
        r_node => null()
        return
    end if
end subroutine r_destroy_tree

subroutine destroy_tree(this)
    class(BinaryTree)::this
    call r_destroy_tree(this%root)
end subroutine destroy_tree


recursive subroutine r_show_tree(r_node)
    type(Node),pointer::r_node
    if (.not. associated(r_node)) then
        write(*,'(A)',advance = 'no') ' '
        return
    else
        call r_show_tree(r_node%node_left)
        write(*,'(A,I3,A)',advance = 'no') ' ',r_node%node_key,' '
        call r_show_tree(r_node%node_right)
    end if
end subroutine r_show_tree

subroutine show_tree(this)
    class(BinaryTree)::this
    call r_show_tree(this%root)
    write(*,*)''
end subroutine show_tree

end module binary_tree_module
