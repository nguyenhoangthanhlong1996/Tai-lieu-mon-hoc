/*Code ban đầu
public class Tricky  -> thiếu {
    public static main(String args) {  -> thiếu void, []
        System.out.println(Hello world);    -> Hello world phải nằm trong ""
        system.out.Pritnln("Do you like this program"?);  -> system không in hoa chữ đầu, Pritnln sai, ? nằm ngoài ""
        System.out.println()  -> thiếu ;
   	->  thiếu }
->  thiếu }
*/

//Code sau khi sửa
public class BT2 {
    public static void main(String[] args) {
        System.out.println("Hello world");
        System.out.println("Do you like this program ?");
        System.out.println();
    }
}