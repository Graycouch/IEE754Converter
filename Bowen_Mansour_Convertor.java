package project2csc4101;

public class Bowen_Mansour_Convertor {

    public static String IEEE754(double number) {
        String IEEE754String = new String();

        // Sign Section
        IEEE754String = isNegative(number, IEEE754String);

        // Exp Section
        int Exp = ExponentFinder(number);
        int[] ExpBinary = BinaryDecimalConvertor(Exp);
        int i = 7;
        while (i > -1) {
            if (i < ExpBinary.length - 1) {
                IEEE754String += ExpBinary[i];
            } else {
                IEEE754String += "0";
            }
            i--;
        }
        IEEE754String += " | ";

        // Mantissa Section
        int[] Mantissa = MantissaFinder(number, Exp);

        for (i = 0; i < Mantissa.length; i++) {
            IEEE754String += Mantissa[i];
        }

        return IEEE754String;
    }

    public static String isNegative(double number, String IEEE754String) {
        if (number > 0) {
            IEEE754String += "0" + " | ";
        } else {
            IEEE754String += "1" + " | ";
        }

        return IEEE754String;
    }

    public static int[] MantissaFinder(double number, int Exp) {
        int[] binary = new int[23];
        int i = 0;

        if (number < 0) {
            number *= -1;
        }

        if (number < 1) {
            while (number < 1) {
                number = number * 2;
            }
            number = number - 1;

            while (i < binary.length) {
                number = number * 2;
                binary[i] = (int) number;

                if (number > 1) {
                    number--;
                }
                i++;
            }
        } else {
            double mantissaNumber = number / Math.pow(2, Exp - 127);
            double fractional = mantissaNumber - (int) mantissaNumber;

            while (fractional < 1 && i < binary.length) {
                fractional = fractional * 2;
                binary[i] = (int) fractional;

                if (fractional > 1) {
                    fractional--;
                }
                i++;
            }
        }

        return binary;
    }

    public static int[] BinaryDecimalConvertor(int number) {
        int[] binary = new int[50];
        int i = 0;

        if (number < 0) {
            number *= -1;
        }

        while (number > 0) {
            int binaryDigit = number % 2;
            binary[i] = binaryDigit;
            number = number / 2;
            i++;
        }

        return binary;
    }

    public static int ExponentFinder(double number) {
        int Exp = 0;

        if (number < 0) {
            number *= -1;
        }

        if (number > 1) {
            while (number > 1) {
                Exp++;
                number = number / 2;
            }
            Exp--;
        } else {
            while (number < 1) {
                Exp--;
                number = number * 2;
            }
        }
        Exp = Exp + 127;

        return Exp;
    }
}
