package org.regitiny.catiny.messenger.tools.sort.impl;

import org.regitiny.catiny.messenger.tools.sort.SortMaster;

import java.util.List;

public class SortMasterImpl implements SortMaster
{
  private static final int SORT_ASC = -1;
  private static final int SORT_DESC = 1;

  @Override
  public void quickSortASC(List objectsInput, List<String> sorterInput)
  {
    new QuickSort().sort(objectsInput, sorterInput, SORT_ASC);
  }

  @Override
  public void quickSortDESC(List objectsInput, List<String> sorterInput)
  {
    new QuickSort().sort(objectsInput, sorterInput, SORT_DESC);
  }


//-----------------------------------------------------------------------------------------------------------


  private class QuickSort
  {
    // mặc định = 1 thì sorter.get(j).compareTo(pivot) * sortDefaultIsDESC > 0  là giảm dần
    private int sortDefaultIsDESC = 1;

    private int partition(List objects, List<String> sorter, int low, int high)
    {
      String pivot = sorter.get(high);
      int i = (low - 1);
      for (int j = low; j < high; j++)
        if (sorter.get(j).compareTo(pivot) * sortDefaultIsDESC > 0)
          i = swap(objects, sorter, i, j);
      return swap(objects, sorter, i, high);
    }

    private int swap(List objects, List<String> sorter, int i, int j)
    {
      String temp = sorter.get(++i);
      sorter.set(i, sorter.get(j));
      sorter.set(j, temp);

      Object objecttemp = objects.get(i);
      objects.set(i, objects.get(j));
      objects.set(j, objecttemp);
      return i;
    }

    private void sort(List objects, List<String> sorter, int low, int high)
    {
      if (low < high)
      {
        int partition = partition(objects, sorter, low, high);
        sort(objects, sorter, low, partition - 1);
        sort(objects, sorter, partition + 1, high);
      }
    }

    private void sort(List objectsInput, List<String> sorterInput, int sortedByOrder)
    {
      this.sortDefaultIsDESC = sortedByOrder;
      sort(objectsInput, sorterInput, 0, sorterInput.size() - 1);
    }

  }

}

