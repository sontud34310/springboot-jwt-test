package com.sontud.jwt.util;

import com.sontud.jwt.model.UserDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class CommonUtil {
    public String doGenerateRefId(UserDto userDto){
        Date date = new Date();
        String today = new SimpleDateFormat("yyyyMMdd").format(date);
        String phoneNo = userDto.getPhoneNo().substring(0, 10);
        String refId = today.concat(phoneNo.substring(6, 10));
        return refId;
    }

    public String checkMemberType(BigDecimal salary) {
        String result = "Reject";
        if(salary.compareTo(new BigDecimal(50000)) == 1){
            result = "Platinum";
        }else if(salary.compareTo(new BigDecimal(29999)) == 1){
            result = "Gold";
        }else if(salary.compareTo(new BigDecimal(15000)) == 1){
            result = "Silver";
        }
        return result;
    }
}
