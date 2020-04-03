package encryptdecrypt;

class Unicode implements Encrypt, Decrypt {
    @Override
    public String encrypt(String text,String key) {
        String encryptText = "";
        for (int i = 0; i < text.length(); i++) {
            encryptText += (char) (text.charAt(i) + Integer.parseInt(key));
        }
        return encryptText;
    }

    @Override
    public String decrypt(String text,String key) {
        String decryptText = "";
        for (int i = 0; i < text.length(); i++) {
            decryptText += (char) (text.charAt(i) - Integer.parseInt(key));
        }
        return decryptText;
    }
}
