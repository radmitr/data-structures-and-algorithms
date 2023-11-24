program sample
use graph_module
implicit none

type(Graph):: graph_1
logical::op_result
integer, allocatable::adjacency_nodes_index(:)

call graph_1%init()

call graph_1%add_node('a',1,op_result)
call graph_1%add_node('b',2,op_result)
call graph_1%add_node('c',3,op_result)
call graph_1%add_node('d',4,op_result)
call graph_1%add_node('e',5,op_result)

call graph_1%add_edge('a','b',op_result)
call graph_1%add_edge('a','c',op_result)
call graph_1%add_edge('a','e',op_result)
call graph_1%add_edge('a','d',op_result)
call graph_1%add_edge('b','c',op_result)


call graph_1%print_graph()
call graph_1%is_connected_graph(op_result)
write(*,*) 'Graph connected :', op_result

call graph_1%remove_edge('a','e',op_result)

call graph_1%print_graph()
call graph_1%is_connected_graph(op_result)
write(*,*) 'Graph connected :', op_result

end program sample
