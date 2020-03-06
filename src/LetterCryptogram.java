public class LetterCryptogram extends Cryptogram {

    public LetterCryptogram(String file) {
        this.original = file;
        encrypted = encrypt(file);
    }

    /*temporary*/
    public LetterCryptogram() {
    }


    public char getPlainLetter(char cryptoLetter) {
        for(int i=0; i<original.length(); i++){
            if(encrypted.charAt(i)==cryptoLetter){
                return original.charAt(i);
            }
        }
        return '-';
    }



    protected String encrypt(String input){
        Cipher cipher = new Cipher();
        return cipher.caesar3(input);
    }

//    @Override
//    public HashMap<String, String> generateMapping() {
//        HashMap<String, String> alphabetMap = new HashMap<String, String>();
//        String plainAlphabet = populateAlphabet();
//        Cipher cipher = new Cipher();
//        String encryptedAlphabet = cipher.caesar3(plainAlphabet);
//        alphabetMap.put(plainAlphabet, encryptedAlphabet);
//        return alphabetMap;
//    }
//
//    public String populateAlphabet(){
//        Character curr = 'A';
//        String alphabet = "";
//        for(int i = 0; i<26; i++){
//            String append = curr.toString();
//            alphabet = alphabet + append;
//            curr++;
//        }
//        return alphabet;
//    }


}
