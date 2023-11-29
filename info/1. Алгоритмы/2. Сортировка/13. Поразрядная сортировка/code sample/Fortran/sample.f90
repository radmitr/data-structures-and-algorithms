program sample
    implicit none
    integer(4),dimension(5)::numbers
    integer(4)::i
    numbers = [121, 5, 24, 9, 32]
    call radix_sort(numbers)
    do i = 1, size(numbers)
        write(*,*) numbers(i)
    end do



contains

    function number_of_digits(c_number)
        implicit none
        integer(4),intent(in)::c_number
        integer(4)::number_of_digits
        integer(8)::pow
        pow = 10
        number_of_digits = 1
        do
            if(pow>c_number) then
                exit
            end if
            number_of_digits = number_of_digits + 1
            pow = pow * 10
        end do
    end function number_of_digits

    function get_digit(c_number,c_position)
        implicit none
        integer(4),intent(in)::c_number,c_position
        integer(4)::get_digit
        get_digit = mod(c_number,10**(c_position))/(10**(c_position-1))
    end function get_digit

    function get_max_number_of_digit(numbers) result(max_numbers)
        implicit none
        integer(4), dimension(:),intent(in)::numbers
        integer(4)::max_numbers,i
        max_numbers = 1
        do i = 1, size(numbers)
            if(number_of_digits(numbers(i))>max_numbers) then
                max_numbers = number_of_digits(numbers(i))
            end if
        end do
    end function get_max_number_of_digit

    function find_min_max_keys(numbers,c_position) result(min_max)
        implicit none
        integer(4),dimension(:),intent(in)::numbers
        integer(4),intent(in)::c_position
        integer(4),dimension(2)::min_max
        integer(4)::i, min_key, max_key,temp_key
        min_key = get_digit(numbers(1),c_position)
        max_key = min_key
        do i = 2,size(numbers)
            temp_key = get_digit(numbers(i),c_position)
            if (temp_key < min_key) then
                min_key = temp_key
            end if
            if (temp_key > max_key) then
                max_key = temp_key
            end if
        end do
        min_max = [min_key, max_key]
    end function find_min_max_keys

    subroutine counting_sort(numbers,c_position)
        implicit none
        integer(4),dimension(:),intent(inout)::numbers
        integer(4),intent(in)::c_position
        integer(4),dimension(size(numbers))::temp_array
        integer(4)::i, min_key, max_key,n,c_size,c_index
        integer(4), dimension(2)::min_max
        integer(4),dimension(:),allocatable::support
        min_max = find_min_max_keys(numbers,c_position)
        min_key = min_max(1)
        max_key = min_max(2)
        n = max_key - min_key + 1
        allocate(support(n))
        support = 0
        do i = 1,size(numbers)
            c_index = get_digit(numbers(i),c_position) - min_key + 1
            support(c_index) = support(c_index) + 1
        end do

        c_size = size(numbers) + 1
        do i = n, 1,-1
            c_size = c_size - support(i)
            support(i) = c_size
        end do
        do i = 1, size(numbers)
            c_index = get_digit(numbers(i),c_position) - min_key + 1
            temp_array(support(c_index)) = numbers(i)
            support(c_index) = support(c_index) + 1
        end do
        deallocate(support)
        do i = 1,size(numbers)
            numbers(i)=temp_array(i)
        end do
    end subroutine counting_sort

    subroutine radix_sort(numbers)
        implicit none
        integer(4),dimension(:),intent(inout)::numbers
        integer(4)::m_number_of_digits,i
        m_number_of_digits = get_max_number_of_digit(numbers)
        do i = 1,m_number_of_digits
            call counting_sort(numbers,i)
        end do
    end subroutine radix_sort

end program sample
