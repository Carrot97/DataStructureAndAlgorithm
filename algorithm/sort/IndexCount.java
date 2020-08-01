package sort;
import sort.Student.*;

import java.io.*;
import java.util.Arrays;

public class IndexCount {
    public static void sort(Student[] students, int R) {
        int N = students.length;
        int[] count = new int[R + 1];
        Student[] aux = new Student[N];

        for (Student student : students) {
            try {
                count[student.getGroup() + 1]++;
            } catch (Exception e) {
            }
        }

        for (int r = 0; r < R; r++) {
            count[r + 1] += count[r];
        }

        for (int i = 0; i < N; i++) {
            aux[count[students[i].getGroup()]++] = students[i];
        }

        for (int i = 0; i < N; i++) {
            students[i] = aux[i];
        }
    }

//    public static void saveStudent(Student[] students) {
//        ObjectOutputStream oos = null;
//        try {
//            oos = new ObjectOutputStream(new FileOutputStream("student.data"));
//
//            oos.writeObject(students);
//            oos.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (oos != null) {
//                try {
//                    oos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static Student[] readStudent() {
//        ObjectInputStream ois = null;
//        Student[] students = null;
//        try {
//            ois = new ObjectInputStream(new FileInputStream("student.data"));
//            Object obj = ois.readObject();
//            students = (Student[]) obj;
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (ois != null) {
//                    ois.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return students;
//    }

    public static void main(String[] args) {
        Student[] students = new Student[20];
        students[0] = new Student("Anderson", 2);
        students[1] = new Student("Brown", 3);
        students[2] = new Student("Davis", 3);
        students[3] = new Student("Garcia", 4);
        students[4] = new Student("Harris", 1);
        students[5] = new Student("Jackson", 3);
        students[6] = new Student("Johnson", 4);
        students[7] = new Student("Jones", 3);
        students[8] = new Student("Martin", 1);
        students[9] = new Student("Martinez", 2);
        students[10] = new Student("Miller", 2);
        students[11] = new Student("Moore", 1);
        students[12] = new Student("Robinson", 2);
        students[13] = new Student("Smith", 4);
        students[14] = new Student("Taylor", 3);
        students[15] = new Student("Thomas", 4);
        students[16] = new Student("Thompson", 4);
        students[17] = new Student("White", 2);
        students[18] = new Student("Williams", 3);
        students[19] = new Student("Wilson", 4);

        IndexCount.sort(students, 4);
        System.out.println(Arrays.toString(students));

//        saveStudent(students);
//        Student[] students1 = readStudent();
//        System.out.println(Arrays.toString(students1));
    }
}
