program sample

use hash_table
implicit none

type(HashTable)::h_tab
logical::op_res
integer::val_result


call h_tab%init()
call h_tab%put('one', 1, op_res)
call h_tab%put('five', 5, op_res)
call h_tab%put('two', 2, op_res)
call h_tab%put('nine', 9, op_res)
call h_tab%put('four', 4, op_res)
call h_tab%put('ten', 10, op_res)

call h_tab%show()

call h_tab%get('five', val_result, op_res)

if (op_res) then
    write(*,*) val_result
else
    write(*,*) 'None'
end if

call h_tab%remove('ten')

call h_tab%show()

call h_tab%clear()

call h_tab%show()

end program sample
