package br.com.fiap.soat4.grupo48.telemed.commons.helpers;

public class CPFValidator {

    public static boolean isValidCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || cpf.matches(cpf.charAt(0) + "{11}")) {
            return false;
        }

        try {
            int[] digits = new int[11];
            for (int i = 0; i < 11; i++) {
                digits[i] = Integer.parseInt(String.valueOf(cpf.charAt(i)));
            }

            int sum1 = 0;
            for (int i = 0; i < 9; i++) {
                sum1 += digits[i] * (10 - i);
            }

            int checkDigit1 = (sum1 % 11 < 2) ? 0 : 11 - (sum1 % 11);

            int sum2 = 0;
            for (int i = 0; i < 9; i++) {
                sum2 += digits[i] * (11 - i);
            }
            sum2 += checkDigit1 * 2;

            int checkDigit2 = (sum2 % 11 < 2) ? 0 : 11 - (sum2 % 11);

            return checkDigit1 == digits[9] && checkDigit2 == digits[10];
        } catch (NumberFormatException e) {
            return false;
        }
    }
}