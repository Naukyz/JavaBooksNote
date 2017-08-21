package ThinkInJava.c13.c6;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Pattern.matches() string.matches() matcher.matches() 方法 是匹配整个 输出boolean
 * matcher.lookingAt() 方法 字符串的开头前面部分 是否有匹配项
 * Pattern p = Pattern.compile(regex);
 * Matcher m = p.matcher(string);
 * while (m.find()) {
 * ....print("Match \"" + m.group() + "\" at positions " +m.start() + "-" + (m.end() - 1));
 * }
 * while(m.find(i)){ // 整数表示字符串中的位置,作为搜索的起点
 * ....print(m.group()+" ");
 * ....i++;
 * }
 * mather
 * int groupCount() // 返回该匹配器模式中的分组数目 0组不包括
 * String group() public String group(int i) 返回前一次匹配操作的匹配 默认是0组 如果指定组中没有匹配输入字符串的任何部分返回 null
 * int start(int group) 在前一次匹配操作中寻找到的组的起始索引 没有对应组为-1
 * int end(int group)   在前一次匹配操作中寻找到的组的最后一个索引加一的值 没有对应组为-1
 * public String replaceAll (String replacement)
 * public String replaceFirst (String replacement)
 * public StringBuffer appendTail (StringBuffer sb)
 * public Matcher appendReplacement (StringBuffer sb, String replacement)
 * <p>
 * 把正则表达式中匹配出来的字符串全部转换成大写字母 替换
 * while (m.find()){
 * ....m.appendReplacement(sbuf, m.group().toUpperCase());
 * }
 * m.appendTail(sbuf);
 * <p>
 * public Matcher reset(CharSequence input) 重新匹配另一个字符串 不带参数的是设置到字符序列的起始位置
 * <p>
 * pattern
 * String[] split(CharSequence input)
 * String[] split(CharSequence input, int limit) // 限制分割后字符串的数量
 * <p>
 * Pattern模式
 * CASE_INSENSITIVE (?i) 忽略大小写
 * DOTALL (?s) .匹配换行符 默认情况下 不匹配换行符
 * MULTILINE (?m) ^$ 匹配行首位 默认匹配字符串首尾
 */
public class Test {

    public static void main(String[] args) {
        String a = "Ab\ncd\naxxcd";
        Pattern p = Pattern.compile("a.{1,4}c", Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(a);

        while (m.find()) {
            System.out.println(m.group());
        }

    }
}