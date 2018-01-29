class BinaryVsLinear
{

  private static int linearSearch(int key, int[] array)
  {
  	int comparisons = 0;
  	for (int i = 0; i < array.length; i += 1) {
  	  comparisons += 1;
  	  if (array[i] == key)
  	  	return comparisons;
  	}
  	return comparisons;
  }

  private static int binarySearch(int key, int[] array)
  {
    int comparisons = 0;
    int low = 0, high = array.length - 1;
    while (low < high) {
      int mid = (low + high) / 2;
      comparisons += 1;
      if (array[mid] == key)
      	return comparisons;
      comparisons += 1;
      if (array[mid] < key)
        low = mid + 1;
      else
        high = mid - 1;
    }
    return comparisons;
  }

  public static void main(String[] args)
  {
    for (int length = 1; length <= 30; length += 1)
    {
      int[] array = new int[length];
      for (int index = 0; index < length; index += 1)
      {
        array[index] = index;
      }

      double linearTotal = 0.0;
      double binaryTotal = 0.0;
      for (int element = 0; element < length; element += 1)
      {
        linearTotal += linearSearch(element, array);
        binaryTotal += binarySearch(element, array);
      }

      double linearAverage = linearTotal / length;
      double binaryAverage = binaryTotal / length;
      System.out.println(length + " " + linearAverage + " " + binaryAverage);
    }
  }
}

/* OUTPUT
1 1.0 0.0
2 1.5 1.5
3 2.0 1.6666666666666667
4 2.5 2.5
5 3.0 3.0
6 3.5 3.1666666666666665
7 4.0 3.2857142857142856
8 4.5 3.75
9 5.0 4.111111111111111
10 5.5 4.4
11 6.0 4.636363636363637
12 6.5 4.75
13 7.0 4.846153846153846
14 7.5 4.928571428571429
15 8.0 5.0
16 8.5 5.25
17 9.0 5.470588235294118
18 9.5 5.666666666666667
19 10.0 5.842105263157895
20 10.5 6.0
21 11.0 6.142857142857143
22 11.5 6.2727272727272725
23 12.0 6.391304347826087
24 12.5 6.458333333333333
25 13.0 6.52
26 13.5 6.576923076923077
27 14.0 6.62962962962963
28 14.5 6.678571428571429
29 15.0 6.724137931034483
30 15.5 6.766666666666667
*/