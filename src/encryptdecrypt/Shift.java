package encryptdecrypt;

class Shift implements Encrypt, Decrypt {
    @Override
    public String encrypt(String message,String key) {
        String encryptedMessage = "";
        char ch;
        for(int i = 0; i < message.length(); ++i){
            ch = message.charAt(i);

            if(Character.isLowerCase(ch)){
                ch = (char)(ch + Integer.parseInt(key));
                if(ch > 'z'){
                    ch = (char)(ch - 'z' + 'a' - 1);
                }
                encryptedMessage += ch;
            }
            else if(Character.isUpperCase(ch)){
                ch = (char)(ch + Integer.parseInt(key));
                if(ch > 'Z'){
                    ch = (char)(ch - 'Z' + 'A' - 1);
                }
                encryptedMessage += ch;
            }
            else {
                encryptedMessage += ch;
            }
        }
        return encryptedMessage;
    }

    @Override
    public String decrypt(String message,String key) {
        String decryptedMessage = "";
        char ch;
        for(int i = 0; i < message.length(); ++i){
            ch = message.charAt(i);

            if(Character.isLowerCase(ch)){
                ch = (char)(ch - Integer.parseInt(key));
                if(ch < 'a') {
                    ch = (char) (ch + 'z' - 'a' + 1);
                }
                decryptedMessage += ch;
            }
            else if(Character.isUpperCase(ch)){
                ch = (char)(ch - Integer.parseInt(key));
                if(ch < 'A'){
                    ch = (char)(ch + 'Z' - 'A' + 1);
                }
                decryptedMessage += ch;
            }
            else {
                decryptedMessage += ch;
            }
        }
        return decryptedMessage;
    }
}
