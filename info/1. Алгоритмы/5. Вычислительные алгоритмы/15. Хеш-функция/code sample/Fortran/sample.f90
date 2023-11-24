program sample
use hash_service
implicit none
type(Pirson_hash)::pir_hash
character(len = 5)::text

text = 'Hello'

call pir_hash%init()

write(*,*) pir_hash%generate_hash(text)
write(*,'(B32)') pir_hash%generate_hash(text)

write(*,*) pir_hash%generate_four_byte_hash(text)
write(*,'(B32)') pir_hash%generate_four_byte_hash(text)

end program sample
