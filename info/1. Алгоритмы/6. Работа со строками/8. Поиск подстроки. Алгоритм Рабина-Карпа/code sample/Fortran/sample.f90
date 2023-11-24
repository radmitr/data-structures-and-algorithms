program sample
implicit none
integer, parameter::base = 31
integer, parameter::q = 2147483647

character(len = 20)::text
character(len = 20)::sub_text

text = 'Awersome apple'
sub_text = 'some'

write(*,*) sub_text_search(text, sub_text)

contains

function gorner_scheme(text,s,e)
    character(len = *), intent(in)::text
    integer, intent(in)::s, e
    integer::gorner_scheme
    integer::i
    gorner_scheme = iachar(text(s:s))
    do i = s, e - 1
        gorner_scheme = gorner_scheme * base + iachar(text(i+1:i+1))
    end do
end function gorner_scheme
    
function calculate_hash(text,s,e)
    character(len = *), intent(in)::text
    integer, intent(in)::s, e
    integer::calculate_hash
    calculate_hash = MOD(gorner_scheme(text,s,e),q)
end function calculate_hash

function sub_text_search(text, sub_text)
    character(len=*), intent(in)::text
    character(len=*), intent(in)::sub_text
    integer::sub_text_search
    integer::s,e,m, pow_coeff
    integer::sub_text_hash
    integer::text_part_hash
    sub_text_search = -1
    m = len_trim(sub_text)
    sub_text_hash = calculate_hash(sub_text,1,m)
    text_part_hash = calculate_hash(text,1,m)
    pow_coeff = base ** (m - 1)
    s = 1
    e = s + m - 1
    do
        if (sub_text_hash == text_part_hash) then
            if(trim(sub_text)== text(s:e)) then
                sub_text_search = s
                exit
            end if
        end if
        s = s + 1
        e = e + 1
        if (e > len_trim(text)) then
            exit
        end if
        text_part_hash = (text_part_hash - iachar(text(s-1:s-1)) * pow_coeff) * base + iachar(text(e:e))
        text_part_hash = MOD (text_part_hash,q)
    end do
end function sub_text_search

end program sample
