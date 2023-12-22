program sample
implicit none
integer,dimension(3)::array
integer::k
array = [1,2,3]
k=3
call permutation(array,k)

   contains
      recursive subroutine permutation(array,k)
         integer,dimension(:),intent(inout)::array
         integer,intent(in)::k
         integer::i, temp
         if (k==1) then
            write(*,*) array
            return
         end if
         do i = 1,k
            call permutation(array,k-1)
            if(mod(k,2)==0) then
               temp = array(i)
               array(i) = array(k)
               array(k) = temp
            else
               temp = array(1)
               array(1) = array(k)
               array(k) = temp
            end if
         end do
      end subroutine permutation
end program sample
