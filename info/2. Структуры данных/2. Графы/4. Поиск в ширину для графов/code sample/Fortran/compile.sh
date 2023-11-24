gfortran -std=f2008 queue.f90
gfortran -std=f2008 graph_module.f90 queue.f90
gfortran -std=f2008 sample.f90 graph_module.f90 queue.f90


