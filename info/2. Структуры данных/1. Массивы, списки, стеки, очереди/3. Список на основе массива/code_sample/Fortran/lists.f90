module lists

implicit none


type Array_Based_List
    integer,allocatable::data_array(:)
    integer::l_size, capacity
    
    contains
        procedure,pass::init
        procedure,pass::add
        procedure,pass::up_resize
        procedure,pass::add_by_index
        procedure,pass::delete_by_index
        procedure,pass::get_size
        procedure,pass::get_by_index
        procedure,pass::set_by_index
        procedure,pass::trim_to_size
        procedure,pass::clear
        procedure,pass::show_list
end type Array_Based_List
        
contains

    subroutine init(this)
        class(Array_Based_List)::this
        if(.not. allocated(this%data_array)) then
            allocate(this%data_array(1))
            this%capacity = 1
            this%l_size = 1
        end if
    end subroutine init
    
    subroutine up_resize(this)
        class(Array_Based_List)::this
        integer,allocatable::temp_array(:)
        integer::new_capacity
        new_capacity = (this%capacity * 3)/2 + 1
        allocate(temp_array,source = this%data_array)
        deallocate(this%data_array)
        allocate(this%data_array(new_capacity))
        this%data_array(1:this%l_size-1) = temp_array
        this%capacity = new_capacity
        deallocate(temp_array)
    end subroutine up_resize
         

    subroutine add(this, data_value)
        class(Array_Based_List)::this
        integer, intent(in)::data_value
        if (this%l_size > this%capacity) then
            call this%up_resize()
        end if
        this%data_array(this%l_size) = data_value
        this%l_size = this%l_size + 1
    end subroutine add
    
    subroutine add_by_index(this, data_value, l_index, op_result)
        class(Array_Based_List)::this
        integer,intent(in)::data_value, l_index
        logical, intent(inout)::op_result
        if(l_index < 1 .or. l_index >= this%l_size) then
            op_result = .false.
            return
        end if
        
        if (this%l_size + 1 > this%capacity) then
            call this%up_resize()
        end if
        this%data_array(l_index+1:this%l_size+1) = this%data_array(l_index:this%l_size)
        this%data_array(l_index) = data_value
        this%l_size = this%l_size + 1
        op_result = .true.
    end subroutine add_by_index
    
    
    subroutine delete_by_index(this, l_index, op_result)
        class(Array_Based_List)::this
        integer,intent(in)::l_index
        logical, intent(inout)::op_result
        if(l_index < 1 .or. l_index >= this%l_size) then
            op_result = .false.
            return
        end if
        this%data_array(l_index:this%l_size-2) = this%data_array(l_index+1:this%l_size-1)
        this%l_size = this%l_size - 1
        op_result = .true.
    end subroutine delete_by_index

    integer function get_by_index(this, l_index, op_result)
        class(Array_Based_List)::this
        integer,intent(in)::l_index
        logical, intent(inout)::op_result
        if(l_index < 1 .or. l_index >= this%l_size) then
            op_result = .false.
            return
        end if
        get_by_index = this%data_array(l_index)
        op_result = .true.
    end function get_by_index
    
    subroutine set_by_index (this, l_index, data_value, op_result)
        class(Array_Based_List)::this
        integer,intent(in)::l_index, data_value
        logical, intent(inout)::op_result
        if(l_index < 1 .or. l_index >= this%l_size) then
            op_result = .false.
            return
        end if
        this%data_array(l_index) = data_value
        op_result = .true.
    end subroutine set_by_index

        
    integer function get_size(this)
        class(Array_Based_List)::this
        get_size = this%l_size - 1
    end function get_size


    subroutine trim_to_size(this)
        class(Array_Based_List)::this
        integer,allocatable::temp_array(:)
        allocate(temp_array,source = this%data_array(:this%l_size-1))
        this%data_array = temp_array
        this%capacity = size(this%data_array, dim = 1)
        deallocate(temp_array)
        
    end subroutine trim_to_size
        

    subroutine clear(this)
        class(Array_Based_List)::this
        if(allocated(this%data_array)) then
            deallocate (this%data_array)
            this%capacity = 0
            this%l_size = 0
        end if
    end subroutine clear

        
    
    subroutine show_list(this)
        class(Array_Based_List)::this
        integer::i
        do i = 1, this%l_size-1
            write(*,'(I5)',advance = 'NO') this%data_array(i)
        end do
        write(*,*) ''
    end subroutine show_list

    
   
end module lists
