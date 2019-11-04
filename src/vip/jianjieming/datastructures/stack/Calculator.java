package vip.jianjieming.datastructures.stack;

/**
 * 栈实现综合计算器(中缀表达式)
 * @Classname Calculator
 * @Date 2019/10/9 10:20
 * @Created by jianjieming
 */
public class Calculator {
    public static void main(String[] args) {
        // 表达式的运算
        String expression = "10/5+10+10-20+5*2"; // 15
        ArrayStack2 numStack = new ArrayStack2(10); // 数字栈
        ArrayStack2 operStack = new ArrayStack2(10); // 符号栈
        int index = 0; // 用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; // 将每次扫描得到的char保存到ch
        StringBuilder keepNum = new StringBuilder(); // 用于拼接多位数的
        // 循环扫描expression
        while (true) {
            // 依次得到 expression 的每一个字符
            ch = expression.substring(index, index + 1).charAt(0);
            // 判断ch是什么,然后做相应的处理
            if (operStack.isOper(ch)) { // 如果是运算符
                // 判断当前的符号栈是否为空
                if (!operStack.isEmpty()) {
                    /* 如果符号栈有操作符,就进行比较
                       如果当前的操作符的优先级小于或者等于栈中的操作符,就从数栈中pop出两个数,
                       在从符号栈中pop出一个符号,进行运算,将得到结果,入数栈,然后将当前的操作符入符号栈
                    */
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1, num2, oper);
                        // 把运算的结果入数栈
                        numStack.push(res);
                        // 把当前的操作符入符号栈
                        operStack.push(ch);
                    } else {
                        // 如果当前的操作符优先级大于栈中的操作符,就直接入符号栈
                        operStack.push(ch);
                    }
                } else {
                    // 为空,直接入符号栈
                    operStack.push(ch);
                }
            } else {
                // 如果是数字,则直接入数栈
//                numStack.push(ch - 48);
                /*
                    分析思路:
                    1.当处理多位数时,不能发现是一个数就立即入栈,因为他可能是多位数
                    2.在处理数,需要向expression的表达式的index后再看一位,如果是数,就进行扫描,如果是符号才入栈
                    3.因此需要定义一个变量字符串,用于拼接
                 */
                // 处理多位数
                keepNum.append(ch);

                // 如果ch已经是expression的最后一位了,就直接入栈
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum.toString()));
                } else {
                    // 判断下一个字符是不是数字,如果是数字,就继续扫描,如果是运算符则入数栈
                    // 注意是看后一位, 不是index++
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        // 如果后一位是运算符,则入栈
                        numStack.push(Integer.parseInt(keepNum.toString()));
                        // 清空keepNum
                        keepNum.setLength(0);
                    }
                }
            }
            // 让 index + 1, 并判断是否扫描到 expression 的最后
            index++;
            if (index >= expression.length()) {
                break;
            }
        }
        // 当表达式扫描完毕,就顺序的从数栈和符号栈中pop出相应的树和符号,并运算
        while (true) {
            // 如果符号栈为空,则说明计算到最后的结果,数栈中只有一个数字(结果)
            if (operStack.isEmpty()) {
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1, num2, oper);
            numStack.push(res); // 入栈
        }
        // 将数栈中最后的结果pop出
        int result = numStack.pop();
        System.out.printf("表达式: %s = %d", expression, result);
    }
}

// 创建一个栈
class ArrayStack2 {
    private int maxSize; // 栈的大小
    private int[] stack; // 数组,数组模拟栈,数据就放在该数组
    private int top = -1; // top表示栈顶,初始化为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 栈满
     *
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     *
     * @param value
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     * 将栈顶的数据返回
     *
     * @return
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空,没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 返回当前栈顶的值,但不是真正的pop
     *
     * @return 栈顶的值
     */
    public int peek() {
        return stack[top];
    }

    /**
     * 遍历栈
     * 遍历时,从栈顶开始显示数据
     */
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空,没有数据");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 返回运算符的优先级,优先级是自己来确定的,优先级使用数字表示,数字越大,优先级越高
     *
     * @param oper 运算符
     * @return int
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1; // 假定目前的表达式只有: +, -, *, /
        }
    }

    /**
     * 判断是不是一个运算符
     *
     * @param val 运算符
     * @return true || false
     */
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算方法
     *
     * @param num1 数字1
     * @param num2 数字2
     * @param oper 运算符
     * @return result
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0; // 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }

}
