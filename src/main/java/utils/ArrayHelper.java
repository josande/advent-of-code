package utils;

public class ArrayHelper {
    public static <T> T[] reverseArray(T[] arr) {
        for(int i = 0; i < arr.length / 2; i++) {
            T temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }
        return arr;
    }
}
