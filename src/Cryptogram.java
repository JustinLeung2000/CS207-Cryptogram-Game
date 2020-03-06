public abstract class Cryptogram {

    protected String original; //phrase passed into cryptogram
    protected String encrypted;
    //protected String cryptogramAlphabet;

    public Cryptogram(String phrase) {
        this.original = phrase;
        encrypted = encrypt(phrase);
    }

    /*temporary*/
    public Cryptogram() {
    }

    public void getFrequencies(){

    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    protected abstract String encrypt(String input);

//    public HashMap<String, String> generateMapping (){
//        return null;
//    }

    //public void generateAlphabet(){}
}
