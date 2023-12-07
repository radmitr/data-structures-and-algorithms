module stacks

implicit none


type Array_Based_Stack
    integer,allocatable::data_array(:)
    integer::l_size, capacity
    
    contains
        procedure,pass::init
        procedure,pass::push
        procedure,pass::up_resize
        procedure,pass::pop
        procedure,pass::peek
        procedure,pass::get_size
        procedure,pass::trim_to_size
        procedure,pass::clear
        procedure,pass::show
end type Array_Based_Stack
        
contains

    subroutine init(this)
        class(Array_Based_Stack)::this
        if(.not. allocated(this%data_array)) then
            allocate(this%data_array(1))
            this%capacity = 1
            this%l_size = 1
        end if
    end subroutine init
    
    subroutine up_resize(this)
        class(Array_Based_Stack)::this
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
         

    subroutine push(this, data_value)
        class(Array_Based_Stack)::this
        integer, intent(in)::data_value
        if (this%l_size > this%capacity) then
            call this%up_resize()
        end if
        this%data_array(this%l_size) = data_value
        this%l_size = this%l_size + 1
    end subroutine push
    
   

    integer function pop(this,op_result)
        class(Array_Based_Stack)::this
        logical, intent(inout)::op_result
        if(this%l_size == 1) then
            op_result = .false.
            return
        end if
        this%l_size = this%l_size - 1
        pop = this%data_array(this%l_size)
        op_result = .true.
    end function pop
    
    integer function peek (this, op_result)
        class(Array_Based_Stack)::this
        logical, intent(inout)::op_result
        if(this%l_size == 1) then
            op_result = .false.
            return
        end if
        peek = this%data_array(this%l_size - 1)
        op_result = .true.
    end function peek

        
    integer function get_size(this)
        class(Array_Based_Stack)::this
        get_size = this%l_size - 1
    end function get_size


    subroutine trim_to_size(this)
        class(Array_Based_Stack)::this
        integer,allocatable::temp_array(:)
        allocate(temp_array,source = this%data_array(:this%l_size-1))
        this%data_array = temp_array
        this%capacity = size(this%data_array, dim = 1)
        deallocate(temp_array)
        
    end subroutine trim_to_size
        

    subroutine clear(this)
        class(Array_Based_Stack)::this
        if(allocated(this%data_array)) then
            deallocate (this%data_array)
            this%capacity = 0
            this%l_size = 0
        end if
    end subroutine clear

        
    
    subroutine show(this)
        class(Array_Based_Stack)::this
        integer::i
        do i = 1, this%l_size-1
            write(*,'(I5)',advance = 'NO') this%data_array(i)
        end do
        write(*,*) ''
    end subroutine show

    
   
end module stacks
