package ThinkInJava.c13.c6;//: strings/ReFlags.java

import java.util.regex.*;

public class ReFlags {
    public static void main(String[] args) {

        // 默认情况下 ^$ 匹配的是整个字符串的开头结尾  多行模式 ^$ 匹配的是每行的开头结尾
        Pattern p = Pattern.compile("^java",
                Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.COMMENTS);
        Matcher m = p.matcher(
                "#java has regex\nJava has regex\n" +
                        "JAVA has pretty good regular expressions\n" +
                        "Regular expressions are in Java");
        while (m.find())
            System.out.println(m.group());
    }
} /* Output:
java
Java
JAVA
*///:~
