package com.api.usuario.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:application.properties")
public class StringUtils {

    public static String emailPattern = "^[a-z]+@[a-z]+\\.[a-z]{2,3}"; //Agrego la regex porque con el @Value no me recupera el valor de email.format.regexp

    public static boolean validateEmailFormat(String email){
        return email != null && email.matches(emailPattern);
    }

}
