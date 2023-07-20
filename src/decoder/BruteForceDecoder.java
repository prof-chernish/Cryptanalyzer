package decoder;

import code.CesarCode;

public class BruteForceDecoder extends Decoder {
    private final int numberOfAttempts = 100;
    private CesarCode cesarCode;
    @Override
    public String decode(String encryptedText) {
        int key = 0;
        cesarCode = new CesarCode(key);
        DecoderByKey decoderByKey = new DecoderByKey(cesarCode);
        while (key < numberOfAttempts) {
            String decryptedText = decoderByKey.decode(encryptedText);
            if (!isDecodingCorrect(decryptedText)) {
                key++;
                cesarCode = new CesarCode(key);
                decoderByKey.setCesarCode(cesarCode);
            } else {
                return decryptedText;
            }



        }
        return "Decryption Failed";

    }


}
