program sample
    implicit none
    type::Cat
        character(len=10)::c_name
        integer(4)::age
    end type Cat

    type(Cat)::cat1,cat2,cat3,cat4,cat5,cat6
    type(Cat),dimension(6)::cats, result_cats
    integer(4)::i

    cat1 = Cat("Vaska", 2)
    cat2 = Cat("Umka", 12)
    cat3 = Cat("Luska", 6)
    cat4 = Cat("Kuzia", 4)
    cat5 = Cat("Murka", 5)
    cat6 = Cat("Barsik", 6)
    cats = [cat1, cat2, cat3, cat4, cat5, cat6]

    result_cats = counting_sort(cats)
    do i = 1,size(result_cats)
        write(*,*) 'name = ',result_cats(i)%c_name,' age = ',result_cats(i)%age
    end do

contains

    function find_min_max_keys(cats) result(min_max)
        type(Cat),dimension(:),intent(in)::cats
        integer(4),dimension(2)::min_max
        integer(4)::i, min_key, max_key
        min_key = cats(1)%age
        max_key = cats(1)%age
        do i = 2,size(cats)
            if (cats(i)%age < min_key) then
                min_key = cats(i)%age
            end if
            if (cats(i)%age > max_key) then
                max_key = cats(i)%age
            end if
        end do
        min_max = [min_key, max_key]
    end function find_min_max_keys

    function counting_sort(cats) result(cat_res)
        type(Cat),dimension(:),intent(in)::cats
        type(Cat),dimension(size(cats))::cat_res
        integer(4)::i, min_key, max_key,n,cats_size
        integer(4), dimension(2)::min_max
        integer(4),dimension(:),allocatable::support
        min_max = find_min_max_keys(cats)
        min_key = min_max(1)
        max_key = min_max(2)
        n = max_key - min_key + 1
        allocate(support(n))
        support = 0
        do i = 1,size(cats)
            support(cats(i)%age - min_key + 1) = support(cats(i)%age - min_key + 1) + 1
        end do
        cats_size = size(cats) + 1
        do i = n, 1,-1
            cats_size = cats_size - support(i)
            support(i) = cats_size
        end do
        do i = 1, size(cats)
            cat_res(support(cats(i)%age - min_key + 1)) = cats(i)
            support(cats(i)%age - min_key + 1) = support(cats(i)%age - min_key + 1) + 1
        end do
        deallocate(support)
    end function counting_sort
end program sample
