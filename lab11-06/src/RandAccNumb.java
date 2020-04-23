import java.io.*;
import java.util.Scanner;

public class RandAccNumb {

    public static void main(String[] args) {
        try {
            File folder = new File("C:\\Users\\User\\Documents\\My");
            if (!folder.exists()) {
                folder.mkdir();
            }
            File f1 = new File("C:\\Users\\User\\Documents\\My\\Nums.txt");

            Scanner sc = new Scanner(System.in, "cp1251");
            System.out.print("Сколько чисел надо записать в файл? \n => ");
            int count = sc.nextInt();

            //открыть файл одновременно для чтения и записи
            RandomAccessFile rf = new RandomAccessFile(f1, "rw");
            System.out.println("Исходный размер файла в байтах = " + rf.length()
                    + ", указатель стоит на " + rf.getFilePointer() + "-м байте");

            System.out.println("Введите числа: ");
            for (int i = 0; i < count; i++) {
                rf.writeDouble(sc.nextDouble());
            }
            System.out.println("Новый размер файла в байтах = " + rf.length()
                    + ", указатель стоит на " + rf.getFilePointer() + "-м байте");
            System.out.println("Количество байт на 1 число = " + rf.length() / count);
            rf.close();
            //открыть только для чтения "r"
            rf = new RandomAccessFile(f1, "r");
            //прочитать числа из файла и вывести на экран
            System.out.println("\n Числа в файле: ");
            for (int i = 0; i < count; i++) {    // i - текущий номер числа
                rf.seek(i * 8);      //перевод указателя на текущее число i*8 байта

                System.out.println("Число " + i + ": " + rf.readDouble());
            }
            System.out.println("Числа в обратном порядке: ");
            for (int i = count - 1; i >= 0; i--) {
                rf.seek(i * 8);
                System.out.println("Число " + i + ": " + rf.readDouble());
            }
            rf.seek(rf.getFilePointer() - 8); //перевод указателя на последнее место
            System.out.println("Коичество чисел в файле = " + rf.length() / 8
                    + ", последнее число = " + rf.readDouble());

            //поиск заданного числа в файле и определение его номера(номеров)
            System.out.println("Введите число, которое нужно найти в файле => ");
            double x = sc.nextDouble();
            int kol = 0;        //количество искомых чисел в файле
            for (int i = 0; i < count; i++) {
                rf.seek(i * 8);
                double number = rf.readDouble();
                if (number == x) {
                    kol++;
                    System.out.print("Номер " + i + ", ");
                }
            }
            System.out.println("Количество искомых чисел = " + kol);
            rf.close();

            //сортировка чисел в файле методом "Пузырька"
            rf = new RandomAccessFile(f1, "rw"); //открыт для чтения и записи
            for (int k = 0; k < count; k++) {
                for (int i = 0; i < count - k - 1; i++) {
                    rf.seek(i * 8);
                    double number1 = rf.readDouble();
                    double number2 = rf.readDouble();
                    if (number1 > number2){
                        rf.seek(i * 8);
                        rf.writeDouble(number2);
                        rf.writeDouble(number1);
                    }
                }
            }
            System.out.println("\n Числа, отсортированные в файле:");
            for (int i = 0; i < count; i++) {
                rf.seek(i * 8);
                System.out.print(" " + rf.readDouble());
            }
            rf.close();
        } catch (IOException e) {
            System.out.println("End of file" + e);
        }
    }
}
