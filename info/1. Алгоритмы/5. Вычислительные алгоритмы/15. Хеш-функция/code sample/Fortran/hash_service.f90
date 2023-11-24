module hash_service
implicit none

type Pirson_hash
    integer(4)::transition_table(256)
    
    contains
    
    procedure::generate_hash
    procedure::init
    procedure::generate_four_byte_hash
    
end type Pirson_hash

contains

subroutine init(this)
    class(Pirson_hash), intent(inout)::this
    this%transition_table = [98,  6, 85,150, 36, 23,112,164,135,207,169,  5, 26, 64,165,219,&
    61, 20, 68, 89,130, 63, 52,102, 24,229,132,245, 80,216,195,115,& 
    90,168,156,203,177,120,  2,190,188,  7,100,185,174,243,162, 10,& 
    237, 18,253,225,  8,208,172,244,255,126,101, 79,145,235,228,121,&
    123,251, 67,250,161,  0,107, 97,241,111,181, 82,249, 33, 69, 55,& 
    59,153, 29,  9,213,167, 84, 93, 30, 46, 94, 75,151,114, 73,222,& 
    197, 96,210, 45, 16,227,248,202, 51,152,252,125, 81,206,215,186,& 
    39,158,178,187,131,136,  1, 49, 50, 17,141, 91, 47,129, 60, 99,& 
    154, 35, 86,171,105, 34, 38,200,147, 58, 77,118,173,246, 76,254,& 
    133,232,196,144,198,124, 53,  4,108, 74,223,234,134,230,157,139,& 
    189,205,199,128,176, 19,211,236,127,192,231, 70,233, 88,146, 44,& 
    183,201, 22, 83, 13,214,116,109,159, 32, 95,226,140,220, 57, 12,& 
    221, 31,209,182,143, 92,149,184,148, 62,113, 65, 37, 27,106,166,& 
    3, 14,204, 72, 21, 41, 56, 66, 28,193, 40,217, 25, 54,179,117,& 
    238, 87,240,155,180,170,242,212,191,163, 78,218,137,194,175,110,& 
    43,119,224, 71,122,142, 42,160,104, 48,247,103, 15, 11,138, 239]
end subroutine init

function generate_hash (this, text)
    character(len = *), intent(in)::text
    class(Pirson_hash), intent(in)::this
    integer(4)::generate_hash
    integer::i, new_index
    generate_hash = 0
    do i = 1, len(trim(text))
        new_index = MOD(generate_hash + iachar(text(i:i)), size(this%transition_table)) + 1
        generate_hash = this%transition_table(new_index)
    end do
    
end function generate_hash

function generate_four_byte_hash (this, text)
    character(len = *), intent(in)::text
    class(Pirson_hash), intent(in)::this
    integer::generate_four_byte_hash, hash
    integer::j, i
    character(len = :), allocatable :: temp_text
    generate_four_byte_hash = 0
    temp_text = trim(text)
    j = 1
    do i = 1, 3
        hash = this%generate_hash (temp_text)
        generate_four_byte_hash = IOR(generate_four_byte_hash, hash)
        generate_four_byte_hash = ISHFT(generate_four_byte_hash, 8)
        temp_text(j:j) = achar(iachar(temp_text(j:j)) + 1)
        j = j + 1
        if(j > len(temp_text)) then
            j = 1
        end if
    end do
    hash = this%generate_hash (temp_text)
    generate_four_byte_hash = IOR(generate_four_byte_hash, hash)
    deallocate(temp_text)
end function generate_four_byte_hash



end module hash_service
