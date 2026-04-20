public class Lab20 {
    
    public static String ssnValidation(String ssn) throws Exception {

        if (ssn.length() != 9 && ssn.length() != 11) {
            throw new IllegalArgumentException("Invalid ssn length");
        }

        if (ssn.length() == 9) {
            for (int i = 0; i < ssn.length(); i++) {
                if (!Character.isDigit(ssn.charAt(i))) {
                    throw new RuntimeException("Invalid character in 9-digit format");
                }
            }
            return ssn;
        }

        // length == 11: expect format ###-##-####
        for (int j = 0; j < ssn.length(); j++) {
            if (j == 3 || j == 6) {
                if (ssn.charAt(j) != '-') {
                    throw new Exception("Invalid character in 11-character format.");
                }
            } else {
                if (!Character.isDigit(ssn.charAt(j))) {
                    throw new Exception("Invalid character in 11-character format.");
                }
            }
        }

        // Strip dashes and return 9-digit version
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < ssn.length(); k++) {
            if (Character.isDigit(ssn.charAt(k))) {
                sb.append(ssn.charAt(k));
            }
        }
        return sb.toString();
    }
}