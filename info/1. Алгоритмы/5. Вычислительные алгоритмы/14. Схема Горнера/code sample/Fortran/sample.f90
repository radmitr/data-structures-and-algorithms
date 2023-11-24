program sample
implicit none
real::poly(5)
real::x
real r

poly = [ 5, 1, -3, 2, 5]
x = 2.0

r = gorner_scheme(poly,x)
write(*,*) r

contains

function gorner_scheme(poly_coff,x)
    implicit none
    real, intent(in)::poly_coff(:)
    real, intent(in)::x
    real::gorner_scheme
    integer::i
    gorner_scheme = poly_coff(1)
    do i = 1, size(poly_coff) - 1
        gorner_scheme = gorner_scheme * x + poly_coff(i+1)
    end do
end function gorner_scheme
        
end program sample
