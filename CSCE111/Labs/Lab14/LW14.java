class LW14 {

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
    }

    public static boolean isPalindrome(String text) {
        if (text == null) {
            return false;
        }

        String cleaned = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (Character.isLetter(c)) {
                cleaned += c;
            }
        }

        // reverse the string
        String reversed = "";
        for (int i = cleaned.length() - 1; i >= 0; i--) {
            reversed += cleaned.charAt(i);
        }
        cleaned = cleaned.toLowerCase();
        reversed = reversed.toLowerCase();
        if (cleaned.equals(reversed)) {
            return true;
        } else return false;
    }
}
