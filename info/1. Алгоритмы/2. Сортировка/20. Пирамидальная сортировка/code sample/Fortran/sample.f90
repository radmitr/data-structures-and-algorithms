program sample
implicit none

integer::array(5)

array = [5, 0, -2, 7, 3]

write(*,*) array

call heap_sort(array)

write(*,*) array



contains

function leafSearch(arr, i, last_index)
    implicit none
    integer,intent(in)::arr(:)
    integer,intent(in)::last_index
    integer,intent(in)::i
    integer::j
    integer::leafSearch
    j = i
    do
        if(2 * j + 1 >= last_index) then
            exit
        end if
        if (arr(2 * j) > arr (2 * j + 1)) then
            j = 2 * j
        else
            j = 2 * j + 1
        end if
    end do
    if (2 * j < last_index) then
        j = 2 * j
    end if
    leafSearch = j    
end function

subroutine silf_down(arr,i, last_index)
    implicit none
    integer, intent(inout)::arr(:)
    integer, intent(in)::i, last_index
    integer::j
    j = leafSearch(arr, i, last_index)
    do 
        if(arr(i) <= arr(j)) then
            exit
        end if
        j = j / 2
    end do
    
    do
        if(j <= i) then
            exit
        end if
        call swap(arr(i), arr(j))
        j=j/2
    end do

end subroutine silf_down

subroutine swap(first_val, second_val)
    implicit none
    integer, intent(inout)::first_val, second_val
    integer::temp
    temp = first_val
    first_val = second_val
    second_val = temp
end subroutine swap


subroutine heap_sort(arr)
    implicit none
    integer,intent(inout)::arr(:)
    integer::i, last_index, n
    n = size(arr, dim = 1)
    last_index = n + 1
    do i = n/2,1,-1
        call silf_down(arr,i, last_index)
    end do
    last_index = n
    do
        if(last_index == 1) then
            exit
        end if
        call swap(arr(1),arr(last_index))
        call silf_down(arr,1, last_index)
        last_index = last_index - 1
    end do    
end subroutine heap_sort

end program sample
