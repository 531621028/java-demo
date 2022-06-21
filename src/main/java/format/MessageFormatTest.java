package format;

import java.text.DecimalFormat;

/**
 * @author kang
 * @date 2022/6/17
 */
public class MessageFormatTest {

    /**
     *  整数部分：
     *      0和#都可用于取出全部整数部分
     *      0的个数决定整数部分长度，不够高位补0；#则无此约束，N多个#是一样的效果
     *  小数部分：
     *     可保留小数点后N位(0和#效果一样)
     *     若小数点后位数不够，若使用的0那就低位补0，若使用#就不补（该是几位就是几位）
     *  数字(1-9)：并不建议模版里直接写1-9这样的数字，了解下即可
     */

    public static void main(String[] args) {
        System.out.println(new DecimalFormat("000000").format(1));
    }

}
