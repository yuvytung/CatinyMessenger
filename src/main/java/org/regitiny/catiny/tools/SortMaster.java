package org.regitiny.catiny.tools;

import java.util.List;

public interface SortMaster<Type>
{
  void quickSortASC(List<Type> objectsInput, String sorterName);


  void quickSortDESC(List<Type> objectsInput, String sorterName);
}
