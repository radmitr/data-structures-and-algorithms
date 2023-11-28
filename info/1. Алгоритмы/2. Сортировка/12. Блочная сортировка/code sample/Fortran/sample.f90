program sample
implicit none
integer, parameter:: array_size = 10
integer:: array(array_size)

array = [12, 2, 4, 7, 5, 10, 8, 9, 11, 9]

write(*,*) array

call bucket_sort(array, 5)

write(*,*) array

contains

subroutine insertion_sort(array)
    integer, intent(inout)::array(:)
    integer:: i, j, insert_element
    do i = 1, size(array)
        j = i
        insert_element = array(j)
        do
            if (j==1 .or. array(j-1) <= insert_element) then
                exit
            end if
            array(j) = array(j - 1)
            j = j - 1
        end do
        array(j) = insert_element
    end do
end subroutine insertion_sort

function find_min_max(array)
    integer::find_min_max(2)
    integer, intent(in)::array(:)
    integer:: i
    if (size(array)==0) then
        find_min_max = [0,0]
        return
    end if
    find_min_max(1) = array(1)
    find_min_max(2) = array(1)
    do i = 1, size(array)
        if( array(i) < find_min_max(1)) then
            find_min_max(1) = array(i)
        end if
        if( array(i) > find_min_max(2)) then
            find_min_max(2) = array(i)
        end if
    end do
end function find_min_max

function calculate_bucket_size(array, n)
    integer, intent(in)::n
    integer, intent(in)::array(:)
    integer::calculate_bucket_size(n)
    integer::min_max(2)
    integer::i, add_index
    calculate_bucket_size = 0
    min_max = find_min_max(array)
    do i = 1, size(array)
        add_index = (n * (array(i) - min_max(1)))/(min_max(2) - min_max(1) + 1) + 1
        calculate_bucket_size(add_index) = calculate_bucket_size(add_index) + 1
    end do
end function calculate_bucket_size

recursive subroutine bucket_sort(array, n)
    type Bucket
        integer,allocatable::elements(:)
    end type Bucket
    integer, intent(inout)::array(:)
    integer,intent(in)::n
    type(Bucket)::buckets(n)
    integer::min_max(2)
    integer:: bucket_size(n), add_index(n)
    integer::i, j, insert_index
    min_max = find_min_max(array)
    if(min_max(1)==min_max(2)) then
        return
    end if
    add_index = 1
    bucket_size = calculate_bucket_size(array,n)
    do i = 1, n
        allocate(buckets(i)%elements(bucket_size(i)))
    end do
    do i = 1, size(array)
        j = (n * (array(i) - min_max(1)))/(min_max(2) - min_max(1) + 1) + 1
        buckets(j)%elements(add_index(j)) = array(i)
        add_index(j) = add_index(j) + 1
    end do
    
    do i = 1, n
        if (size(buckets(i)%elements) <= 32) then
            call insertion_sort(buckets(i)%elements)
        else
            call bucket_sort(buckets(i)%elements, n)
        end if
    end do
    
    insert_index = 1
    
    do i = 1, n
        do j = 1, size(buckets(i)%elements)
            array(insert_index) = buckets(i)%elements(j)
            insert_index = insert_index + 1
        end do
        deallocate(buckets(i)%elements)
    end do

end subroutine bucket_sort


end program sample
