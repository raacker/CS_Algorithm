package sorting;

public class Sorting
{
	static int mergeCallCount = 0;

	private Sorting(){}

	public static int[] insertionSort(int[] list, int iterations) {
		for (int j = 1; j < iterations; j++) {
			int key = list[j];
			int i = j - 1;
			while (i >= 0 && list[i] > key) {
				list[i + 1] = list[i];
				i--;
			}
			list[i + 1] = key;
		}
		return list;
	}

	public static int[] insertionSortInBinarySearch(int[] list, int iterations) {
		for (int j = 1; j < iterations; j++) {
			int key = list[j];
			int start = 0;
			int end = j-1;
			int targetIndex = 0;

			if (key <= list[0])
				targetIndex = 0;
			else if(key > list[end])
				targetIndex = end+1;
			else {
				int mid;
				while(start+1 < end) {
					mid = (start + end) / 2;
                    if(list[mid] > key)
						end = mid;
					else
						start = mid;
                    targetIndex = mid;
				}
				if (list[end] >= key && list[start] <= key)
				    targetIndex = end;
			}

			list = moveBackwardList(list, targetIndex, j-1);
			list[targetIndex] = key;
		}
		return list;
	}

	private static int[] moveBackwardList(int[] list, int targetIndex, int endPoint) {
		while (endPoint >= targetIndex) {
			list[endPoint + 1] = list[endPoint];
			endPoint--;
		}
		return list;
	}

	public static int[] mergeSort(int[] list, int start, int end) {
		if (start == end) {
            int[] newList = { list[start] };

			return newList;
		}
        int[] leftList = mergeSort(list, start, (start + end) / 2);
        int[] rightList = mergeSort(list, (start + end) / 2 + 1, end);
        mergeCallCount++;
		return mergeTwoList(leftList, rightList);
	}

	private static int[] mergeTwoList(int[] leftList, int[] rightList) {
		int leftIndex = 0, rightIndex = 0;
        int[] list = new int[leftList.length + rightList.length];
		int count = 0;
		
		while (leftIndex < leftList.length && rightIndex < rightList.length) {
			if (leftList[leftIndex] > rightList[rightIndex])
				list[count++] = rightList[rightIndex++];
			else
				list[count++] = leftList[leftIndex++];
		}
		if (leftIndex == leftList.length) {
			for (int i = rightIndex; i < rightList.length; i++)
				list[count++] = rightList[i];
		} 
		else
			for (int i = leftIndex; i < leftList.length; i++)
				list[count++] = leftList[i];
		return list;
	}

    public static int[] mergeSortInThreeWay(int[] list, int start, int end) {
        if ((end - start) == 1)
            return mergeSort(list, start, end);

        if (start >= end) {
            int[] newList = { list[start] };

            return newList;
        }

        int thirdWay = (int)Math.ceil((end - start + 1) / 3);
        int[] leftList = mergeSortInThreeWay(list, start, start + thirdWay - 1);
        int[] middleList = mergeSortInThreeWay(list, start + thirdWay, (start + thirdWay * 2) - 1);
        int[] rightList = mergeSortInThreeWay(list, start + thirdWay * 2, end);
        mergeCallCount++;
        return mergeThreeList(leftList, middleList, rightList);
    }

    private static int[] mergeThreeList(int[] leftList, int[] middleList, int[] rightList) {
        int[] firstAttempt = mergeTwoList(leftList, middleList);
        return mergeTwoList(firstAttempt, rightList);
    }
}