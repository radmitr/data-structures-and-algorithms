module hash_table

implicit none

type Pair
    character(len = 30)::key
    integer::value
    logical::is_present
end type Pair


type HashTable
    type(Pair), allocatable::pair_table(:)
    integer::prime_number(10)
    integer::prime_index = 1
    integer::capacity
    integer:: h_size= 0    
    contains
        procedure,pass::init
        procedure,pass::put
        procedure,pass::calculate_hash
        procedure,pass::up_resize
        procedure,pass::get
        procedure,pass::remove
        procedure,pass::show
        procedure,pass::clear
        procedure,pass::destroy
            
end type HashTable
        
contains

    subroutine init(this)
        class(HashTable)::this
        this%prime_number = [17, 37, 101, 239, 571, 1019, 5171, 15031, 50033, 100003]
        this%capacity =  this%prime_number(this%prime_index)
        allocate(this%pair_table(this%capacity))
        this%pair_table%is_present = .false.
   end subroutine init 

    subroutine put(this, add_key, add_value,op_result)
        class(HashTable)::this
        character(len=*), intent(in)::add_key
        integer, intent(in)::add_value
        logical,intent(inout)::op_result
        integer::add_index, i
        integer::key_number_value
        integer::p
        
        if(this%h_size > this%capacity / 2) then
            call this%up_resize(op_result)
            if (.not. op_result) then
                return
            end if
        end if
        
        p = this%capacity
        key_number_value = this%calculate_hash(add_key)
        i = 0
        do
            add_index = mod((mod(key_number_value,p) + i * (1 + mod(key_number_value,p - 1))), p)
            if ( .not. this%pair_table(add_index)%is_present) then
                this%pair_table(add_index)%key = add_key
                this%pair_table(add_index)%value = add_value
                this%pair_table(add_index)%is_present = .true.
                this%h_size = this%h_size + 1
                op_result = .true.
                exit
            end if
            
            if (this%pair_table(add_index)%is_present) then
                if(this%pair_table(add_index)%key == add_key) then
                    this%pair_table(add_index)%value = add_value
                    op_result = .true.
                    exit
                end if
            else
                i = i + 1
            end if
        end do
    end subroutine put
    
    subroutine up_resize(this, op_res)
        class(HashTable)::this
        logical::op_res
        integer::i
        type(Pair), allocatable::temp_pair_table(:)
        if (this%prime_index == size(this%prime_number)) then
            op_res = .false.
            return
        end if
        this%prime_index = this%prime_index + 1
        temp_pair_table = this%pair_table
        this%capacity = this%prime_number(this%prime_index)
        allocate(this%pair_table(this%capacity))
        this%pair_table%is_present = .false.
        do i = 1, size(temp_pair_table)
            if (temp_pair_table(i)%is_present) then
                call this%put(temp_pair_table(i)%key, temp_pair_table(i)%value, op_res)
            end if
        end do
        deallocate(temp_pair_table)
        op_res = .true.
    end subroutine up_resize
    
    function calculate_hash(this, add_key)
        class(HashTable)::this
        character(len=*), intent(in)::add_key
        integer::calculate_hash
        integer::i
        calculate_hash = iachar(add_key(1:1))
        do i = 1, len_trim(add_key) - 1
            calculate_hash = calculate_hash * 31 + iachar(add_key(i+1:i+1))
        end do
        calculate_hash = abs(calculate_hash)
    end function calculate_hash

    subroutine get(this, key, get_value, op_result)
        class(HashTable)::this
        character(len=*), intent(in)::key
        integer, intent(inout)::get_value
        logical, intent(inout)::op_result
        integer::get_index, p, key_number_value, i
        p = this%capacity
        key_number_value = this%calculate_hash(key)
        i = 0
        do
            get_index = mod((mod(key_number_value,p) + i * (1 + mod(key_number_value,p - 1))), p)
            if ( .not. this%pair_table(get_index)%is_present) then
                op_result = .false.
                exit
            end if
            
            if (this%pair_table(get_index)%is_present) then
                if(this%pair_table(get_index)%key == key) then
                    get_value = this%pair_table(get_index)%value
                    op_result = .true.
                    exit
                end if
            else
                i = i + 1
            end if
        end do        
    end subroutine get
    
    
    subroutine remove(this, key)
        class(HashTable)::this
        character(len=*), intent(in)::key
        integer::remove_index, p, key_number_value, i, next_remove_index
        p = this%capacity
        key_number_value = this%calculate_hash(key)
        i = 0
        do
            remove_index = mod((mod(key_number_value,p) + i * (1 + mod(key_number_value,p - 1))), p)
            if ( .not. this%pair_table(remove_index)%is_present) then
                exit
            end if
            if (this%pair_table(remove_index)%is_present) then
                if(this%pair_table(remove_index)%key == key) then
                    this%pair_table(remove_index)%is_present = .false.
                    this%h_size = this%h_size - 1
                    do
                        i = i + 1
                        next_remove_index = mod((mod(key_number_value,p) + i * (1 + mod(key_number_value,p - 1))), p)
                        if (.not. this%pair_table(next_remove_index)%is_present) then
                            exit
                        end if
                        this%pair_table(remove_index) = this%pair_table(next_remove_index)
                        remove_index = next_remove_index
                    end do
                    exit
                end if
            else
                i = i + 1
            end if
        end do          
    end subroutine remove
    
    subroutine destroy(this)
        class(HashTable)::this
        if (allocated(this%pair_table)) then
            deallocate(this%pair_table)
        end if
    end subroutine destroy
 
    subroutine clear(this)
        class(HashTable)::this
        deallocate(this%pair_table)
        this%prime_index = 1
        this%capacity =  this%prime_number(this%prime_index)
        allocate(this%pair_table(this%capacity))
        this%pair_table%is_present = .false.
    end subroutine clear
    
    subroutine show(this)
        class(HashTable),intent(in)::this
        integer::i
        write(*,'(A)', advance = 'no') '{'
        do i = 1, this%capacity
            if(this%pair_table(i)%is_present) then
                write(*,'(A5,":",I5,", ")', advance = 'no') this%pair_table(i)%key,this%pair_table(i)%value
            end if
        end do
        write(*,*) '}'
    end subroutine show

end module hash_table
