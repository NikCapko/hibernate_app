package code.convert;

public class Convert implements Result {

    private String inputString;

    public Convert(String inputString) {
        this.inputString = inputString;
    }

    public String getInputString() {
        return inputString;
    }

    @Override
    public String getResult() {
        int sum = 0;
        String s = (new StringBuffer(inputString)).reverse().toString();
        for (int i = 0; i < s.length(); i++) {
            if (i > 0) {
                if (convert(s.charAt(i - 1)) > convert(s.charAt(i))) {
                    sum -= convert(s.charAt(i));
                } else {
                    sum += convert(s.charAt(i));
                }
            } else {
                sum += convert(s.charAt(i));
            }
        }
        return String.valueOf(sum);
    }

    private int convert(char r) {
        switch (r) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
