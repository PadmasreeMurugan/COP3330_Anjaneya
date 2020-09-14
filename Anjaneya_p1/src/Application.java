public class Application {

    public static void main(String[] args) {

        Encrypter myencrypter = new Encrypter();

        String encryptedValue = myencrypter.encrypt("1234");

        System.out.println("Encrypted Value:" + encryptedValue);

        Decrypter mydecrypter = new Decrypter();

        String decryptedValue = mydecrypter.decrypt("0189");

        System.out.println("Decrypted Value:" + decryptedValue);
    }
}

