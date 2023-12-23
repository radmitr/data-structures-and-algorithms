program sample
implicit none

call print_combination(3,5)

contains

subroutine print_combination(k,n)
   implicit none
   integer,intent(in)::k,n
   integer::comb(1:k)
   integer::i,m
   comb = [(i,i=1,k)]
   do
      write(*,*) comb
      m = -1
      do i = k,1,-1
         if(comb(i)<n-k+i) then
            comb(i) = comb(i) + 1
            m = i
            exit
         end if
      end do
      if (m==-1) then
         exit
      end if
      do i = m+1,k
         comb(i) = comb(i-1) + 1
      end do
   end do
end subroutine print_combination

end program sample
