package org.example.appDonovan.service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class Color {
    //https://stackoverflow.com/questions/4842424/list-of-ansi-color-escape-sequences

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BACKGROUND_RESET = "\u001B[0m";
    public static final String ANSI_BACKGROUND_BLACK = "\u001B[40m";
    public static final String ANSI_BACKGROUND_RED = "\u001B[41m";
    public static final String ANSI_BACKGROUND_GREEN = "\u001B[42m";
    public static final String ANSI_BACKGROUND_YELLOW = "\u001B[43m";
    public static final String ANSI_BACKGROUND_BLUE = "\u001B[44m";
    public static final String ANSI_BACKGROUND_PURPLE = "\u001B[45m";
    public static final String ANSI_BACKGROUND_CYAN = "\u001B[46m";
    public static final String ANSI_BACKGROUND_WHITE = "\u001B[47m";


    public static String randColorFont(Object object, int colorBack) {
        try {
            Method getter = object.getClass().getDeclaredMethod("getId");
            Object id = getter.invoke(object);
            int color = 7;
            if (id instanceof Long) {
                color = (Integer.parseInt(id.toString())%6)+1;
            }
            String colorFont = "";
            if (colorBack < 245) {
                colorFont = "\u001B[1;9" + color + "m";
            } else {
                colorFont = "\u001B[1;3" + color + "m";
            }
            return colorFont;
        } catch (Exception e) {
            System.out.println("randColor : " + e);
        }
        return "";
    }

    public static String randColorBack(Object object, String field) {
        List<Field> fields = Arrays.stream(object.getClass().getDeclaredFields()).toList();
        for (int i = 0; i < fields.size(); i++) {
            Field declaredField = fields.get(i);
            if (declaredField.getName().equalsIgnoreCase(field)) {
                int colorBack = i%2 == 0 ? 232 : 255;
                String colorFontFormat = randColorFont(object, colorBack);
                String colorBackFormat = "\033[48;5;" + colorBack + "m";
                return  colorFontFormat + colorBackFormat;
            }
        }
        return "";
    }

    public static String setSlugBasedOnString(String stringToSlug) {
        return stringToSlug.toLowerCase().replace(" ", "-");
    }
}
