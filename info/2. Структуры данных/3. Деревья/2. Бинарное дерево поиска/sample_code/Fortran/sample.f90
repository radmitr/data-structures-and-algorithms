program sample
use binary_tree_module
implicit none

type(BinaryTree)::bin_tree
character(len = 20)::test_data
logical::operation_result
integer::tr_size

call bin_tree%init()
call bin_tree%add(6, "Pear")
call bin_tree%add(4, "Apple");
call bin_tree%add(0, "Plum");
call bin_tree%add(5, "Orange");
call bin_tree%add(9, "Lemon");
call bin_tree%add(7, "Cherry");
call bin_tree%add(12, "Apricot");



call bin_tree%show_tree()

call bin_tree%get(0,test_data, operation_result)

if(operation_result) then
    write(*,*) test_data
else
    write(*,*) 'not found'
end if

call bin_tree%tree_size(tr_size)

write(*,*)'tree size = ', tr_size

call bin_tree%remove(9, operation_result)
call bin_tree%show_tree()

call bin_tree%tree_size(tr_size)
write(*,*)'tree size = ', tr_size


call bin_tree%destroy_tree()
call bin_tree%show_tree()
call bin_tree%tree_size(tr_size)
write(*,*)'tree size = ', tr_size


write(*,*) 'Work in progress'
end program sample
