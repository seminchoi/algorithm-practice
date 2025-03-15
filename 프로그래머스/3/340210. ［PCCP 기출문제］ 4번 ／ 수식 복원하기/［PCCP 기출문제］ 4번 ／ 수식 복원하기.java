import java.util.ArrayList;
import java.util.List;

class Solution {
    public static int minFormation = 2;
    public static boolean certain = false;

    //각 자리수를 탐색하면서 가장 큰 수를 탐색합니다.
    //가장 큰 자리수 + 1 이 최소 진수가 됩니다.
    //minFormation이 10이면 10진수 확정입니다.
    //+ 혹은 - 계산을 할 때 자리수 넘김이 발생하는지 확인합니다.
    //만약 자리수 넘김이 발생하고 최소 진수가 10이면 10진수 확정입니다.
    //자리수 넘김이 발생하면 진수를 계산할 수 있습니다.
    //최종적으로 판단할 때는 진수가 확정됐는지에 따라서 나뉩니다.
    //진수가 확정됐다면 해당 진수로 계산을 완료하면 됩니다.
    //진수가 확정되지 않았다면 다음 경우로 나뉩니다.
    //계산해서 자리수 넘김이 발생하면 계산이 불가능합니다.
    //계산해서 자리수 넘김이 발생하지 않으면 다음 단계로 나뉩니다.
    //최소 진수보다 숫자가 작으면 계산이 가능합니다.
    //최소 진수보다 숫자가 크면 계산이 불가능합니다.

    //구현해야할 기능
    //연산하기
    //10진수 연산 판단
    //n진수 연산 추적
    //자리수 넘어가는지 확인
    //10진수를 n진수로 변환
    //n진수를 10진수형태로 표현
    public static class Expression {
        int[][] digits = new int[3][];
        int[] numbers = new int[3];
        boolean plus;
        boolean comp;
        boolean jump;

        public Expression(String input) {
            String[] split = input.split("[-+=\\s]+");
            int minusOp = input.indexOf("-");
            if (minusOp == -1) {
                plus = true;
            }

            for (int i = 0; i < 3; i++) {
                if (i == 2 && split[i].equals("X")) {
                    comp = false;
                    break;
                } else {
                    comp = true;
                }
                digits[i] = new int[split[i].length()];
                for (int j = 0; j < digits[i].length; j++) {
                    digits[i][j] = split[i].charAt(j) - '0';
                    minFormation = Math.max(minFormation, digits[i][j] + 1);
                }
                numbers[i] = toInte(digits[i]);
            }

            if (minFormation == 9) {
                certain = true;
                return;
            }

            if (!comp) return;
            boolean f = checkJump(9, true);
            if (!f) return;
            calculateCheckFormation();
        }

        public int toInte(int[] i) {
            int res = 0;
            for (int j = 0; j < i.length; j++) {
                res *= 10;
                res += i[j];
            }
            return res;
        }


        public boolean checkJump(int formation, boolean flag) {
            int idx = Math.min(digits[0].length, digits[1].length);
            int aLength = digits[0].length - 1;
            int bLength = digits[1].length - 1;
            for (int i = 0; i < idx; i++) {
                if (plus && digits[0][aLength - i] + digits[1][bLength - i] >= formation) {
                    return true;
                } else if (!plus && digits[0][aLength - i] - digits[1][bLength - i] < 0) {
                    return true;
                }
            }
            if (flag && calculate(numbers[0], numbers[1]) != numbers[2]) {
                return true;
            }
            return false;
        }

        private void calculateCheckFormation() {
            for (int i = minFormation; i < 10; i++) {
                int aDecimal = toDecimal(numbers[0], i);
                int bDecimal = toDecimal(numbers[1], i);
                int cDecimal = toDecimal(numbers[2], i);
                if (calculate(aDecimal, bDecimal) == cDecimal) {
                    certain = true;
                    minFormation = i;
                    return;
                }
            }
        }

        public int calculate(int a, int b) {
            if (plus) return a + b;
            return a - b;
        }

        private int toDecimal(int i, int formation) {
            int result = 0;

            int mult = 1;
            while (i != 0) {
                int digit = i % 10;
                result += digit * mult;
                mult *= formation;
                i /= 10;
            }

            return result;
        }

        private int toFormation(int i, int formation) {
            int result = 0;
            int mult = 1;
            while (i != 0) {
                int digit = i % formation;
                result += digit * mult;
                mult *= 10;
                i /= formation;
            }

            return result;
        }

        public String makeResult() {
            char op = plus ? '+' : '-';
            if (certain) {
                int aDecimal = toDecimal(numbers[0], minFormation);
                int bDecimal = toDecimal(numbers[1], minFormation);
                int result = calculate(aDecimal, bDecimal);
                int formation = toFormation(result, minFormation);
                return String.format("%d %c %d = %d", numbers[0], op, numbers[1], formation);
            } else {
                boolean flag = checkJump(minFormation, false);
                if (flag) {
                    return String.format("%d %c %d = ?", numbers[0], op, numbers[1]);
                } else {
                    int result = calculate(numbers[0], numbers[1]);
                    return String.format("%d %c %d = %d", numbers[0], op, numbers[1], result);
                }
            }
        }
    }


    public String[] solution(String[] expressions) {
        List<Expression> nonCompEx = new ArrayList<>();

        for (String expression : expressions) {
            Expression ex = new Expression(expression);
            if (!ex.comp) {
                nonCompEx.add(ex);
            }
        }
        
        String[] answers = new String[nonCompEx.size()];
        for (int i = 0; i < nonCompEx.size(); i++) {
            answers[i] = nonCompEx.get(i).makeResult();
        }


        return answers;
    }
}