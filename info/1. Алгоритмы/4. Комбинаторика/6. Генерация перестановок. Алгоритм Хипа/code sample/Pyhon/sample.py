def heap_permutation(sequince,k):
   if k==1:
      print(sequince)
      return
   else:
      for i in range(k):
         heap_permutation(sequince,k-1)
         if k%2 == 0:
            sequince[i],sequince[k-1]=sequince[k-1],sequince[i]
         else:
            sequince[0],sequince[k-1]=sequince[k-1],sequince[0]

heap_permutation(["A","B","C"],3)
