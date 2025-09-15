public class HW7_TestLinkedListMethods {

    private static int testNumber = 0;

    public static void main(String[] args) {
        System.out.println("\nTesting contains(E e) for linked list");
        MyLinkedList<String> llCityNames = new MyLinkedList<>();
        testTrue(!llCityNames.contains("Bellevue")); // test 1: contains for empty linked list
        llCityNames.add("Lexington");
        llCityNames.add("Highland Heights");
        llCityNames.add("Cincinnati");
        llCityNames.add("Frankfort");
        testTrue(!llCityNames.contains("Bellevue")); // test 2: contains for linked list, with element not in list
        testTrue(llCityNames.contains("Lexington")); // test 3: contains for linked list, with element first in list
        testTrue(llCityNames.contains("Frankfort")); // test 4: contains for linked list, with element last in list
        //End of public boolean contains(E e) testing.

        System.out.println("\nTesting get(int index) for linked list");
        llCityNames.clear();
        try { // test 5: get(int index) for empty linked list
            llCityNames.get(0);
            throw new RuntimeException("get(int) failed to throw exception on empty list");
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.printf("Test %d successful\n", ++testNumber);
        }
        llCityNames.add("Lexington");
        llCityNames.add("Highland Heights");
        llCityNames.add("Cincinnati");
        llCityNames.add("Frankfort");

        try { // test 6: get(int index) for index out of bounds for non-empty linked list
            llCityNames.get(4);
            throw new RuntimeException("get(int) failed to throw exception on index out of bounds");
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.printf("Test %d successful\n", ++testNumber);
        }
        testEqual(llCityNames.get(0), "Lexington"); // test 7: get(int index) for index = 0 for linked list
        testEqual(llCityNames.get(3), "Frankfort"); // test 8: get(int index) for index > 0 for linked list
        //End of public E get(int index) testing

        System.out.println("\nTesting indexOf(E e) for linked list");
        llCityNames.clear();
        testEqual(""+llCityNames.indexOf("Lexington"), "-1"); // test 9: indexOf(E e) for empty linked list
        llCityNames.add("Lexington");
        llCityNames.add("Highland Heights");
        testEqual(""+llCityNames.indexOf("Lexington"), "0"); // test 10: indexOf(E e) for element present in linked list
        testEqual(""+llCityNames.indexOf("Highland Heights"), "1"); // test 11: indexOf(E e) for element present in linked list
        testEqual(""+llCityNames.indexOf("Dayton"), "-1"); // test 12: indexOf(E e) for element not in linked list
        //End of testing for public int indexOf(E e)

        System.out.println("\nTesting lastIndexOf(E e) for linked list");
        llCityNames.clear();
        testEqual(""+llCityNames.lastIndexOf("Lexington"), "-1"); // test 13: indexOf(E e) for empty linked list
        llCityNames.add("Lexington");
        llCityNames.add("Highland Heights");
        llCityNames.add("Lexington");
        testEqual(""+llCityNames.lastIndexOf("Lexington"), "2"); // test 14: indexOf(E e) for element present multiple times in linked list
        testEqual(""+llCityNames.lastIndexOf("Highland Heights"), "1"); // test 15: indexOf(E e) for element present once in linked list
        testEqual(""+llCityNames.lastIndexOf("Dayton"), "-1"); // test 16: indexOf(E e) for element not in linked list
        //End of testing for public int lastIndexOf(E e)

        System.out.println("\nTesting set(int index, E e) for linked list");
        llCityNames.clear();
        try { // test 17: set(int index, E e) for empty linked list
            llCityNames.set(0, "Bowling Green");
            throw new RuntimeException("set(int index, E e) failed to throw exception on empty list");
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.printf("Test %d successful\n", ++testNumber);
        }
        llCityNames.add("Lexington");
        llCityNames.add("Highland Heights");
        llCityNames.add("Cincinnati");
        llCityNames.add("Frankfort");
        try { // test 18: set(int index, E e) for index out of bounds for non-empty linked list
            llCityNames.set(4, "Bowling Green");
            throw new RuntimeException("set(int index, E e) failed to throw exception on index out of bounds");
        }
        catch (IndexOutOfBoundsException ex) {
            System.out.printf("Test %d successful\n", ++testNumber);
        }
        testEqual(llCityNames.set(0,"Bowling Green"), "Lexington"); // test 19: set(int index, E e) for index = 0 for linked list, check old element
        testEqual(llCityNames.get(0),"Bowling Green"); // test 20: set(int index, E e) for index = 0 for linked list, check new element
        testEqual(llCityNames.set(3,"Bowling Green"), "Frankfort"); // test 21: set(int index, E e) for index > 0 for linked list, check old element
        testEqual(llCityNames.get(3),"Bowling Green"); // test 22: set(int index, E e) for index > 0 for linked list, check new element
        //End of testing for public E set(int index, E e)

        System.out.println("\nTesting reverse() for linked list");
        llCityNames = new MyLinkedList<>();
        llCityNames.add("Lexington");
        llCityNames.reverse();
        testEqual(llCityNames.toString(), "[Lexington]"); // test 23: reversal of a linked list with 1 element
        llCityNames.add("Highland Heights");
        llCityNames.reverse();
        testEqual(llCityNames.toString(), "[Highland Heights, Lexington]"); // test 24: reversal of a linked list with 2 elements
        llCityNames.add("Cincinnati");
        llCityNames.add("Frankfort");
        llCityNames.reverse();
        testEqual(llCityNames.toString(), "[Frankfort, Cincinnati, Lexington, Highland Heights]"); // test 25: reversal of a linked list with an even number of elements
        llCityNames.add("Dayton");
        llCityNames.reverse();
        testEqual(llCityNames.toString(), "[Dayton, Highland Heights, Lexington, Cincinnati, Frankfort]"); // test 26: reversal of a linked list with an odd number of elements
        llCityNames.clear();
        testEqual(llCityNames.toString(), "[]"); // test 27: reversal of an empty linked list

    }


    private static void testEqual(String s1, String s2) {
        testNumber++;
        if (s1.equals(s2))
            System.out.printf("Test %d successful\n", testNumber);
        else {
            System.out.printf("Error: %s not equal to %s \n", s1, s2);
            throw new RuntimeException("Test " + testNumber + " failed");
        }
    }

    private static void testTrue(boolean b) {
        testNumber++;
        if (b)
            System.out.printf("Test %d successful\n", testNumber);
        else
            throw new RuntimeException("Test " + testNumber + " failed");
    }
}
