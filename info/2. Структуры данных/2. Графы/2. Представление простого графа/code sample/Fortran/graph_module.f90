module graph_module
implicit none

type Node
    character(len=10)::node_id
    integer::node_data
    logical::is_present
end type Node

type Graph
    type(Node), allocatable::nodes(:)
    integer, allocatable::adjacency_matrix(:,:)
    integer::matrix_size
    contains
    
    procedure,pass::init
    procedure,pass::add_node
    procedure,pass::add_edge
    procedure, pass::find_node_index_by_id
    procedure, pass::remove_node
    procedure, pass::remove_edge
    procedure, pass::print_graph
    procedure, pass::is_node_adjacency
    procedure, pass::get_adjacency_nodes_index
end type Graph

contains

    subroutine init(this)
        class(Graph)::this
        this%matrix_size = 100
        allocate(this%nodes(this%matrix_size))
        allocate(this%adjacency_matrix(this%matrix_size,this%matrix_size))
        this%nodes%is_present = .false.
        this%adjacency_matrix = 0
    end subroutine init

    subroutine find_node_index_by_id(this, node_id, node_index)
        class(Graph)::this
        character(len=*), intent(in)::node_id
        integer, intent(inout)::node_index
        integer::i
        node_index = -1
        do i = 1, size(this%nodes)
            if (this%nodes(i)%is_present .and. this%nodes(i)%node_id == node_id) then
                node_index = i
                exit
            end if
        end do
    end subroutine find_node_index_by_id

    subroutine add_node(this, node_id, node_data, op_result)
        class(Graph)::this
        character(len=*), intent(in)::node_id
        integer, intent(in)::node_data
        logical, intent(inout)::op_result
        integer::i
        op_result = .false.
        call this%find_node_index_by_id(node_id,i)
        if(i /= -1) then
            return
        end if
        do i = 1, size(this%nodes)
            if (.not.this%nodes(i)%is_present) then
                this%nodes(i)%is_present = .true.
                this%nodes(i)%node_id = node_id
                this%nodes(i)%node_data = node_data
                op_result = .true.
                exit
            end if
        end do
    end subroutine add_node
    
    subroutine add_edge(this, node_id_from, node_id_to, op_result)
        class(Graph)::this
        character(len=*),intent(in)::node_id_from, node_id_to
        logical, intent(inout)::op_result
        integer::i, j
        i = -1
        j = -1
        op_result = .false.
        call this%find_node_index_by_id(node_id_from, i)
        call this%find_node_index_by_id(node_id_to, j)
        if(i == -1 .or. j == -1) then
            return
        end if
        this%adjacency_matrix(i,j) = 1
        this%adjacency_matrix(j,i) = 1
        op_result = .true.
    end subroutine add_edge
    
    subroutine remove_node(this, node_id, op_result)
        class(Graph)::this
        character(len=*),intent(in)::node_id
        logical, intent(inout)::op_result
        integer::i
        i = -1
        op_result = .false.
        call this%find_node_index_by_id(node_id, i)
        if (i == -1) then
            return
        end if
        this%nodes(i)%is_present = .false.
        this%adjacency_matrix(i,:) = 0
        this%adjacency_matrix(:,i) = 0
        op_result = .true.    
    end subroutine remove_node
    
    subroutine remove_edge(this, node_id_from, node_id_to, op_result)
        class(Graph)::this
        character(len=*), intent(in)::node_id_from, node_id_to
        logical, intent(inout)::op_result
        integer::i, j
        i = -1
        j = -1
        op_result = .false.
        call this%find_node_index_by_id(node_id_from, i)
        call this%find_node_index_by_id(node_id_to, j)
        if(i == -1 .or. j == -1) then
            return
        end if
        this%adjacency_matrix(i,j) = 0
        this%adjacency_matrix(j,i) = 0
        op_result = .true.
    end subroutine remove_edge
    
    subroutine is_node_adjacency(this, node_id_from, node_id_to, op_result)
        class(Graph)::this
        character(len=*), intent(in)::node_id_from, node_id_to
        logical, intent(inout)::op_result
        integer::i, j
        i = -1
        j = -1
        op_result = .false.
        call this%find_node_index_by_id(node_id_from, i)
        call this%find_node_index_by_id(node_id_to, j)
        if(i == -1 .or. j == -1) then
            return
        end if
        if (this%adjacency_matrix(i,j)/=0) then
            op_result = .true.
        end if
    end subroutine is_node_adjacency
    
    subroutine get_adjacency_nodes_index (this, node_id_from, adjacency_nodes_index, op_result)
        class(Graph)::this
        character(len=*), intent(in)::node_id_from
        integer,allocatable::adjacency_nodes_index(:)
        logical, intent(inout)::op_result
        integer::i, j, n, insert_index
        op_result = .false.
        call this%find_node_index_by_id(node_id_from, i)
        if(i == -1) then
            op_result = .false.
        end if
        n = count(this%adjacency_matrix(i,:) > 0)
        allocate(adjacency_nodes_index(n))
        insert_index = 1
        do j = 1, this%matrix_size
            if(this%adjacency_matrix(i,j) /=0) then
                adjacency_nodes_index(insert_index) = j
                insert_index = insert_index + 1
            end if
        end do
        op_result = .true.
    end subroutine get_adjacency_nodes_index

    subroutine print_graph(this)
        class(Graph)::this
        integer::i,j
        write(*,'(10X)',advance = 'NO')
        do i = 1, this%matrix_size
            if(this%nodes(i)%is_present) then
                write(*,'(A10)',advance = 'NO') adjustr(this%nodes(i)%node_id)
            end if
        end do
        write(*,*)
        
        do i = 1, this%matrix_size
            if(this%nodes(i)%is_present) then
                write(*,'(A10)',advance = 'NO') adjustr(this%nodes(i)%node_id)
                do j = 1, this%matrix_size
                    if(this%nodes(j)%is_present) then
                        write(*,'(I10)',advance = 'NO') this%adjacency_matrix(i,j)
                    end if
                end do
                write(*,*)
            end if
        end do
    end subroutine print_graph

end module graph_module
