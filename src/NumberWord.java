import java.util.ArrayList;
import java.util.Arrays;

public class NumberWord {
    public static final String ZERO = "không";
    public static final String ONE = "một";
    public static final String TWO = "hai";
    public static final String THREE = "ba";
    public static final String FOUR = "bốn";
    public static final String FIVE = "năm";
    public static final String SIX = "sáu";
    public static final String SEVEN = "bảy";
    public static final String EIGHT = "tám";
    public static final String NINE = "chín";
    public static final String TEN = "mười";
    public static final String DOZEN_FIVE = "lăm";
    public static final String HUNDRED_UNIT = "lẻ";
    public static final String DOZEN_TEN = "mươi";
    public static final String DOZEN_ONE = "mốt";
    public static final String HUNDRED = "trăm";
    public static final String THOUSAND = "nghìn";
    public static final String MILLION = "triệu";
    public static final String BILLION = "tỷ";
    public static final String DOZEN_FOUR = "tư";

    public static final String [] number = {ZERO, ONE, TWO, THREE,
            FOUR, FIVE, SIX, SEVEN, EIGHT, NINE};

    public static ArrayList<String> readNumber(String number) {
        ArrayList<String> result = new ArrayList<String>();
        ArrayList<String> listNumber = split(number,3);
        while (listNumber.size() != 0) {
            switch (listNumber.size() % 3) {
                case 1:
                    result.addAll(readThreeNumber(listNumber.get(0)));
                    break;
                case 2:
                    ArrayList<String> thousand = readThreeNumber(listNumber.get(0));
                    if (!thousand.isEmpty()) {
                        result.addAll(thousand);
                        result.add(THOUSAND);
                    }
                    break;
                case 0:
                    ArrayList<String> million = readThreeNumber(listNumber.get(0));
                    if (!million.isEmpty()) {
                        result.addAll(million);
                        result.add(MILLION);
                    }
                    break;
            }
            listNumber.remove(0);
        }
        return result;
    }

    public static ArrayList<String> readThreeNumber(String inputNumber) {
        ArrayList<String> result = new ArrayList<String>();
        int num = -1;
        try {
            num = Integer.parseInt(inputNumber);
        } catch (Exception ex) {
        }
        if (num == 0) {
            return result;
        }
        int hundred = -1;
        try {
            hundred = Integer.parseInt(inputNumber.substring(0,1));
        } catch (Exception ex) {
        }
        int dozen = -1;
        try {
            dozen = Integer.parseInt(inputNumber.substring(1,2));
        } catch (Exception ex) {
        }
        int unit = 1;
        try {
            unit = Integer.parseInt(inputNumber.substring(2,3));
        } catch (Exception ex) {
        }
        if (hundred != -1){
            result.add(number[hundred]);
            result.add(HUNDRED);
        }
        switch (dozen) {
            case -1:
                break;
            case 1:
                result.add(TEN);
                break;
            case 0:
                if (unit != 0) {
                    result.add(HUNDRED_UNIT);
                    break;
                }
            default:
                if (dozen == 0) {
                    break;
                }
                result.add(number[dozen]);
                result.add(DOZEN_TEN);
                break;
        }
        switch (unit) {
            case -1:
                break;
            case 1:
                if ((dozen != 0) && (dozen != 1) && (dozen != -1)) {
                    result.add(DOZEN_ONE);
                } else {
                    result.add(number[unit]);
                }
                break;
            case 5:
                if ((dozen != 0) && (dozen != -1)) {
                    result.add(DOZEN_FIVE);
                } else {
                    result.add(number[unit]);
                }
                break;
            case 0:
                if (result.isEmpty()) {
                    result.add(number[unit]);
                }
                break;
            default:
                if (result.size() > 1 && unit == 4) {
                    result.add(DOZEN_FOUR);
                } else {
                    result.add(number[unit]);
                }

                break;
        }
        return result;
    }

    public static ArrayList<String> split(String str, int chunkSize) {
        int residual = str.length() % chunkSize;
        if (residual != 0) {
            StringBuilder strBuilder = new StringBuilder(str);
            for (int i = 0; i < (chunkSize - residual); i++) {
                strBuilder.insert(0, "#");
            }
            str = strBuilder.toString();
        }
        return splitStringEvery(str, chunkSize);
    }

    public static ArrayList<String> splitStringEvery(String s, int interval) {
        int arrayLength = (int) Math.ceil(((s.length() / (double) interval)));
        String[] result = new String[arrayLength];
        int j = 0;
        int lastIndex = result.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            result[i] = s.substring(j, j + interval);
            j += interval;
        }
        result[lastIndex] = s.substring(j);
        return new ArrayList<String>(Arrays.asList(result));
    }

    public static void changeNumberToWord(int number ) {
                String stringNumber = "" + number;
                ArrayList<String> result = readNumber(stringNumber);
                StringBuilder convertResult = new StringBuilder();
                for (String s : result) {
                    convertResult.append(s).append(" ");
                }
                System.out.println(convertResult);
            }

    public static void main(String[] args) {
        changeNumberToWord(1234567);

    }
}
