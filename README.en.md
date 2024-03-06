   public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder result = new StringBuilder();
        int index = 0;
        int count = 0;

        while (index < s.length()) {
            char ch = s.charAt(index);
            if (Character.isDigit(ch)) {
                while (Character.isDigit(s.charAt(index))) {
                    count = count * 10 + (s.charAt(index) - '0');
                    index++;
                }
            } else if (ch == '{') {
                countStack.push(count);
                stringStack.push(result);
                result = new StringBuilder();
                count = 0;
                index++;
            } else if (ch == '}') {
                int repeatCount = countStack.pop();
                StringBuilder temp = stringStack.pop();
                for (int i = 0; i < repeatCount; i++) {
                    temp.append(result);
                }
                result = temp;
                index++;
            } else {
                result.append(ch);
                index++;
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        dod solution = new dod();
        String s = "2{ab3{ac}}";
        String decodedString = solution.decodeString(s);
        System.out.println(decodedString); // 输出 acac
    }