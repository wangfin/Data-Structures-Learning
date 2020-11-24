import java.util.Stack;

class Solution {
    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();
        // 检查传入字符串
        for (int i = 0; i < s.length(); i ++){
            // 遍历字符串中的每一个值
            char c = s.charAt(i);
            // 遇到左括号就入栈
            if(c == '(' || c == '[' || c == '{'){
                stack.push(c);
            }
            else {
                if(stack.isEmpty())
                    return false;
                // 遇到右括号就出栈，将最上面的左括号进行匹配
                char topChar = stack.pop();
                if(c == ')' && topChar != '(')
                    return false;
                if(c == ']' && topChar != '[')
                    return false;
                if(c == '}' && topChar != '{')
                    return false;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Boolean res = (new Solution()).isValid("()[])");
        System.out.println(res);
    }
}
