program sample
implicit none

call combination(3,5)


contains

subroutine check_first_element(c,k,step,j)
   integer,intent(inout)::c(0:)
   integer,intent(in)::k
   integer,intent(inout)::step,j
   if(mod(k,2)/=0) then
      if(c(0)+1<c(1)) then
         c(0) = c(0) + 1
         step = 2
      else
         j = 1
         step = 4
      end if
   else
      if(c(0)>0) then 
         c(0) = c(0) - 1
         step = 2
      else
         j = 1
         step = 5
      end if
   end if
end subroutine check_first_element

subroutine decreas_element(c,step,j)
   integer,intent(inout)::c(0:)
   integer,intent(inout)::step,j
   if(c(j) > j) then
      c(j) = c(j-1)
      c(j-1) = j-1
      step = 2
   else
      j = j + 1
      step = 5
   end if
end subroutine decreas_element

subroutine enlargement_element(c,k,step,j)
   integer,intent(inout)::c(0:)
   integer,intent(in)::k
   integer,intent(inout)::step,j
   if(c(j) + 1 <= c(j+1)) then
      c(j-1) = c(j)
      c(j) = c(j) + 1
      step = 2
   else
      j = j + 1
      if (j<k) then
         step = 4
      else
         step = -1
      end if
   end if
end subroutine enlargement_element


subroutine combination(k,n)
    integer,intent(in)::k,n
    integer::c(0:k)
    integer::j, step
    do j = 0,k-1
        c(j) = j
    end do
    c(k) = n-1
    step = 2
    do
        select case(step)
            case(2)
                write(*,*) c(0:k-1)
                step = 3
            case(3)
                call check_first_element(c,k,step,j)
            case(4)
                call decreas_element(c,step,j)
            case(5)
                call enlargement_element(c,k,step,j)
            case default
                exit
        end select
    end do    
end subroutine combination 

end program sample
