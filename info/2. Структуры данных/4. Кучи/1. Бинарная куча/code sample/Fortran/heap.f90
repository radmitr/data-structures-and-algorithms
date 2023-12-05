module heap

type Node
    integer::key
    character(len = 20)::data_value
end type Node

type BinaryHeap
    type(Node),pointer::nodes(:)
    integer::last_element_index
    
    contains
    procedure, pass::init
    procedure, pass::destroy
    procedure, pass::resize
    procedure, pass::add
    procedure, pass::sift_up
    procedure, pass::sift_down
    procedure, pass::heap_recovery
    procedure, pass::extract
    procedure, pass::extract_and_insert
    procedure, pass::find_index_by_key
    procedure, pass::delete_by_key
    procedure, pass::change_key
    procedure, pass::show_heap
    
end type BinaryHeap

contains
    subroutine init(this)
        class(BinaryHeap)::this
        allocate(this%nodes(100))
        this%last_element_index = 0
    end subroutine init
    
    subroutine destroy(this)
        class(BinaryHeap)::this
        if (associated(this%nodes)) then
            deallocate(this%nodes)
        end if
    end subroutine destroy
    
    subroutine resize(this)
        class(BinaryHeap)::this
        type(Node), pointer::new_pointer(:)
        allocate(new_pointer(size(this%nodes) * 2))
        new_pointer(1:this%last_element_index) = this%nodes(1:this%last_element_index)
        deallocate(this%nodes)
        this%nodes => new_pointer
    end subroutine resize
    
    subroutine add(this, key, data_value)
        class(BinaryHeap)::this
        integer,intent(in)::key
        character(len=*),intent(in)::data_value
        type(Node)::new_node
        if(this%last_element_index == size(this%nodes)) then
            call this%resize()
        end if
        this%last_element_index = this%last_element_index + 1
        new_node%key = key
        new_node%data_value = data_value
        this%nodes(this%last_element_index) = new_node
        call this%sift_up(this%last_element_index)
    end subroutine add
    
    subroutine extract(this, data_value, op_result)
        class(BinaryHeap)::this
        character(len=*),intent(inout)::data_value
        logical, intent(inout)::op_result
        op_result = .true.
        if(this%last_element_index == 0) then
            op_result = .false.
            return
        end if
        data_value = this%nodes(1)%data_value
        this%nodes(1) = this%nodes(this%last_element_index)
        this%last_element_index = this%last_element_index - 1
        call this%sift_down(1)
    end subroutine extract
    
    subroutine extract_and_insert (this, data_value_result, key, data_value, op_result)
        class(BinaryHeap)::this
        character(len=*), intent(inout)::data_value_result
        integer,intent(in)::key
        character(len=*),intent(in)::data_value
        logical,intent(inout)::op_result
        op_result = .true.
        if(this%last_element_index == 0) then
            op_result = .false.
        end if
        data_value_result = this%nodes(1)%data_value
        this%nodes(1)%data_value = data_value
        this%nodes(1)%key = key
        call this%sift_down(1)
    end subroutine extract_and_insert
    
    subroutine find_index_by_key(this, key, i)
        class(BinaryHeap)::this
        integer,intent(in)::key
        integer,intent(inout)::i
        integer::j
        i = -1
        do j = 1, this%last_element_index
            if(this%nodes(j)%key == key) then
              i = j
              exit
            end if
        end do
    end subroutine find_index_by_key
    
    subroutine delete_by_key(this, key)
        class(BinaryHeap)::this
        integer, intent(in)::key
        integer::i
        call this%find_index_by_key(key,i)
        if(i==-1) then
            return
        end if
        this%nodes(i) = this%nodes(this%last_element_index)
        this%last_element_index = this%last_element_index - 1
        call this%heap_recovery(i)
    end subroutine delete_by_key
    
    subroutine change_key(this, old_key, new_key)
        class(BinaryHeap)::this
        integer, intent(in)::old_key, new_key
        integer::i
        call this%find_index_by_key(old_key,i)
        if(i==-1) then
            return
        end if
        this%nodes(i)%key = new_key
        call this%heap_recovery(i)
    end subroutine change_key
    
    subroutine sift_up(this,i)
        class(BinaryHeap)::this
        integer,intent(in)::i
        integer::j
        type(Node)::temp_node
        j = i
        do
            if(j < 2) then
                exit
            end if
            if(this%nodes(j)%key > this%nodes(j/2)%key) then
                temp_node = this%nodes(j)
                this%nodes(j) = this%nodes(j/2)
                this%nodes(j/2) = temp_node
                j = j/2
            else
                exit
            end if           
        end do
    end
    
    subroutine sift_down(this, i)
        class(BinaryHeap)::this
        integer,intent(in)::i
        integer::j, left_j, right_j, next_j
        type(Node)::temp_node
        j = i
        next_j = i
        do
            left_j = 2 * j
            right_j = 2 * j + 1
            if (left_j <= this%last_element_index) then
                if(this%nodes(left_j)%key > this%nodes(j)%key) then
                    next_j = left_j
                end if
            end if
            
            if (right_j <= this%last_element_index) then
                if(this%nodes(right_j)%key > this%nodes(j)%key) then
                    next_j = right_j
                end if
            end if
            
            if (next_j /= j) then
                temp_node = this%nodes(next_j)
                this%nodes(next_j) = this%nodes(j)
                this%nodes(j) = temp_node
                j = next_j
            else
                exit
            end if
        end do
    end subroutine sift_down
    
    subroutine heap_recovery(this, i)
        class(BinaryHeap)::this
        integer,intent(in)::i
        integer::j, left_j, right_j, next_j
        j = i
        if (j>=2) then
            if(this%nodes(j)%key > this%nodes(j/2)%key) then
                call this%sift_up(j)
                return
            end if
        end if
       call this%sift_down(j)

    end subroutine heap_recovery
    
    
    subroutine show_heap(this)
        class(BinaryHeap)::this
        integer::i
        do i = 1, this%last_element_index
            write(*,*) this%nodes(i)%key,', ', this%nodes(i)%data_value
        end do
    end subroutine show_heap

end module heap
