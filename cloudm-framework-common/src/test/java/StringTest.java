import com.cloudm.framework.common.util.HttpUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: Courser
 * @date: 2017/5/23
 * @version: V1.0
 */
public class StringTest {
//    public static void main(String[] args){
//
////        int a = (int)128.624;
//        Integer b = 13, c=232;
////        System.out.print(b==c);
////        int i  =  1 ;
////        int j = 4;
////        System.out.println(String.format("%0"+j+"d",i));
//
//        int fac = 13;
//        int faa = new forTest().forTest();
//
//        Integer bug = new Integer(13);
//        System.out.print((b == bug) + "------" + (b.equals(bug)));
//
//    }
//
//
//    private static class forTest {
//
//        public int forTest(){
//            return 13;
//        }
//
//    }


    public static void main(String[] args) { // Line 1
        int i=1; // Line 2
        Object obj = new Object(); // Line 3
        StringTest mem = new StringTest(); // Line 4
        mem.foo(obj); // Line 5

        List a = new ArrayList();

    } // Line 9

    private void foo(Object param) { // Line 6
        String str = param.toString(); //// Line 7
        System.out.println(str);
    } // Line 8

}
