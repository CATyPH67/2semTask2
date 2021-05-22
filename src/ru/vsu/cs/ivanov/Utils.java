package ru.vsu.cs.ivanov;

public class Utils {
    public static MyLinkedList<Integer> strToListInt(String str) {
        String[] strArr = str.split("\\s+");
        MyLinkedList<Integer> newList = new MyLinkedList<>();
        for (String s : strArr) {
            newList.addTail(Integer.parseInt(s));
        }
        return newList;
    }
}
