/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * @author lethi
 */
public class SetStringFormat {

    public static String ConvertBigDecimalToStringFormat(BigDecimal BigDecimal) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        if(BigDecimal==null){
            System.out.println("thien");
        }
        String formattedNumber = decimalFormat.format(BigDecimal);
        return formattedNumber;
    }
    
    public static BigDecimal ConvertStringtoBigDecimal(String bigdecimal){
        return BigDecimal.valueOf(Long.valueOf(bigdecimal.replace(",", "")));
    }
}
