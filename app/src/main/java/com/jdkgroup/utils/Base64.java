
package com.jdkgroup.utils;

public class Base64 {
    /**
     * Translates the specified byte array into a Base64 string as per
     * Preferences.put(byte[]).
     */
    static String byteArrayToBase64(byte[] a) {
        return byteArrayToBase64(a, false);
    }

    /**
     * 29      * Translates the specified byte array into an "aternate representation"
     * 30      * Base64 string. This non-standard variant uses an alphabet that does
     * 31      * not contain the uppercase alphabetic characters, which makes it
     * 32      * suitable for use in situations where case-folding occurs.
     * 33
     */
    public String byteArrayToAltBase64(byte[] a) {
        return byteArrayToBase64(a, true);
    }

    private static String byteArrayToBase64(byte[] a, boolean alternate) {
        int aLen = a.length;
        int numFullGroups = aLen / 3;
        int numBytesInPartialGroup = aLen - 3 * numFullGroups;
        int resultLen = 4 * ((aLen + 2) / 3);
        StringBuffer result = new StringBuffer(resultLen);
        char[] intToAlpha = (alternate ? intToAltBase64 : intToBase64);
        // Translate all full groups from byte array elements to Base64
        int inCursor = 0;
        for (int i = 0; i < numFullGroups; i++) {
            int byte0 = a[inCursor++] & 0xff;
            int byte1 = a[inCursor++] & 0xff;
            int byte2 = a[inCursor++] & 0xff;
            result.append(intToAlpha[byte0 >> 2]);
            result.append(intToAlpha[(byte0 << 4) & 0x3f | (byte1 >> 4)]);
            result.append(intToAlpha[(byte1 << 2) & 0x3f | (byte2 >> 6)]);
            result.append(intToAlpha[byte2 & 0x3f]);
        }

        // Translate partial group if present
        if (numBytesInPartialGroup != 0) {
            int byte0 = a[inCursor++] & 0xff;
            result.append(intToAlpha[byte0 >> 2]);
            if (numBytesInPartialGroup == 1) {
                result.append(intToAlpha[(byte0 << 4) & 0x3f]);
                result.append("==");
            } else {
                // assert numBytesInPartialGroup == 2;
                int byte1 = a[inCursor++] & 0xff;
                result.append(intToAlpha[(byte0 << 4) & 0x3f | (byte1 >> 4)]);
                result.append(intToAlpha[(byte1 << 2) & 0x3f]);
                result.append('=');
            }
        }
        // assert inCursor == a.length;
        // assert result.length() == resultLen;
        return result.toString();
    }

    /**
     * 79      * This array is a lookup table that translates 6-bit positive integer
     * 80      * index values into their "Base64 Alphabet" equivalents as specified
     * 81      * in Table 1 of RFC 2045.
     * 82
     */
    private static final char intToBase64[] = {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
            'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
    };

    /**
     * 92      * This array is a lookup table that translates 6-bit positive integer
     * 93      * index values into their "Alternate Base64 Alphabet" equivalents.
     * 94      * This is NOT the real Base64 Alphabet as per in Table 1 of RFC 2045.
     * 95      * This alternate alphabet does not use the capital letters. It is
     * 96      * designed for use in environments where "case folding" occurs.
     * 97
     */
   /* private static final char intToAltBase64[] = {
         '!', '"', '#', '$', '%', '&', '\'', '(', ')', ',', '-', '.', ':',
        ';', '<', '>', '@', '[', ']', '^', '`', '_', '{', '|', '}', '~',
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
         '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '?'
    };
 */


    private static final char intToAltBase64[] = {
            '!', '"', '#', '$', '%', '&', '\'', '(', ')', ',', '-', '.', ':',
            ';', '<', '>', '@', '[', ']', '^', '`', '_', '{', '|', '}', '~',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
            'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '?'
    };


    /**
     * 107      * Translates the specified Base64 string (as per Preferences.get(byte[]))
     * 108      * into a byte array.
     * 109      *
     * 110      * @throw IllegalArgumentException if <tt>s</tt> is not a valid Base64
     * 111      * string.
     * 112
     */
    public byte[] base64ToByteArray(String s) {
        return base64ToByteArray(s, false);
    }

    /**
     * 118      * Translates the specified "aternate representation" Base64 string
     * 119      * into a byte array.
     * 120      *
     * 121      * @throw IllegalArgumentException or ArrayOutOfBoundsException
     * 122      * if <tt>s</tt> is not a valid alternate representation
     * 123      * Base64 string.
     * 124
     */
    public byte[] altBase64ToByteArray(String s) {
        return base64ToByteArray(s, true);
    }

    public static byte[] base64ToByteArray(String s, boolean alternate) {
        //System.out.println("in Base 11111111111111111111111111111111");
        byte[] alphaToInt = (alternate ? altBase64ToInt : base64ToInt);
        int sLen = s.length();
        int numGroups = sLen / 4;
        if (4 * numGroups != sLen)
            //System.out.println("in Base 222222222222222222222");
            throw new IllegalArgumentException(
                    "String length must be a multiple of four.");
        int missingBytesInLastGroup = 0;
        int numFullGroups = numGroups;
        //System.out.println("in Base 3333333333333333333333333");
        if (sLen != 0) {
            if (s.charAt(sLen - 1) == '=') {
                missingBytesInLastGroup++;
                numFullGroups--;
            }
            if (s.charAt(sLen - 2) == '=')
                missingBytesInLastGroup++;
        }
        byte[] result = new byte[3 * numGroups - missingBytesInLastGroup];
//System.out.println("in Base 444444444444444444444444");
        // Translate all full groups from base64 to byte array elements
        int inCursor = 0, outCursor = 0;
        for (int i = 0; i < numFullGroups; i++) {
            //System.out.println("in Base 5555555555555555");
            int ch0 = base64toInt(s.charAt(inCursor++), alphaToInt);
            int ch1 = base64toInt(s.charAt(inCursor++), alphaToInt);
            int ch2 = base64toInt(s.charAt(inCursor++), alphaToInt);
            int ch3 = base64toInt(s.charAt(inCursor++), alphaToInt);
            result[outCursor++] = (byte) ((ch0 << 2) | (ch1 >> 4));
            result[outCursor++] = (byte) ((ch1 << 4) | (ch2 >> 2));
            result[outCursor++] = (byte) ((ch2 << 6) | ch3);
        }
        //System.out.println("in Base 666666666666666666666666");
        // Translate partial group, if present
        if (missingBytesInLastGroup != 0) {
            //System.out.println("in Base 777777777777777777777777");
            int ch0 = base64toInt(s.charAt(inCursor++), alphaToInt);
            //System.out.println("in Base 99999999999");
            int ch1 = base64toInt(s.charAt(inCursor++), alphaToInt);
            //System.out.println("in Base aaaaaaaaaaaaaaaaa");
            result[outCursor++] = (byte) ((ch0 << 2) | (ch1 >> 4));
            //System.out.println("in Base bbbbbbbbbbbbbbbbbbbb");

            if (missingBytesInLastGroup == 1) {
                //System.out.println("in Base cccccccccccccc");
                int ch2 = base64toInt(s.charAt(inCursor++), alphaToInt);
                //System.out.println("in Base ddddddddddddddd");
                result[outCursor++] = (byte) ((ch1 << 4) | (ch2 >> 2));
            }
        }
        //System.out.println("in Base 888888888888888888");
        // assert inCursor == s.length()-missingBytesInLastGroup;
        // assert outCursor == result.length;
        return result;
    }

    /**
     * 177      * Translates the specified character, which is assumed to be in the
     * 178      * "Base 64 Alphabet" into its equivalent 6-bit positive integer.
     * 179      *
     * 180      * @throw IllegalArgumentException or ArrayOutOfBoundsException if
     * 181      * c is not in the Base64 Alphabet.
     * 182
     */
    public static int base64toInt(char c, byte[] alphaToInt) {
        int result = alphaToInt[c];
        if (result < 0)
            throw new IllegalArgumentException("Illegal character " + c);
        return result;
    }

    /**
     * 191      * This array is a lookup table that translates unicode characters
     * 192      * drawn from the "Base64 Alphabet" (as specified in Table 1 of RFC 2045)
     * 193      * into their 6-bit positive integer equivalents. Characters that
     * 194      * are not in the Base64 alphabet but fall within the bounds of the
     * 195      * array are translated to -1.
     * 196
     */
    private static final byte base64ToInt[] = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54,
            55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4,
            5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
            24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34,
            35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51
    };

    /**
     * 208      * This array is the analogue of base64ToInt, but for the nonstandard
     * 209      * variant that avoids the use of uppercase alphabetic characters.
     * 210
     */
    private static final byte altBase64ToInt[] = {
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 1,
            2, 3, 4, 5, 6, 7, 8, -1, 62, 9, 10, 11, -1, 52, 53, 54, 55, 56, 57,
            58, 59, 60, 61, 12, 13, 14, -1, 15, 63, 16, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, 17, -1, 18, 19, 21, 20, 26, 27, 28, 29, 30, 31, 32, 33,
            34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
            51, 22, 23, 24, 25
    };

    public static void main(String args[]) {

        byte[] byteLoginName = null;

        //http://kickjava.com/src/java/util/prefs/Base64.java.htm


        //String password = new String(bytePassword, 0, bytePassword.length, "UTF8");//in string
        String encryptedLoginName = "";
        String loginName = "sachin13";
        try {
            byteLoginName = loginName.getBytes("UTF8");//in byte array
            Base64 Base64_password = new Base64();

            byte[] byteVerifyID = null;
            Base64 Base64_verifyID = new Base64();
            String decryptedVerifyID = "";


            encryptedLoginName = Base64_password.byteArrayToAltBase64(byteLoginName);

            //System.out.println("----------encryptedLoginName---------"+encryptedLoginName);


            byteVerifyID = Base64_verifyID.altBase64ToByteArray(encryptedLoginName);
            decryptedVerifyID = new String(byteVerifyID, "UTF-8");
            //System.out.println("----------decryptedVerifyID---------"+decryptedVerifyID);


        } catch (Exception e) {

            //  logger.info("\n\n-------------Exception ( collegeSignupAction)-----"+e);
        }



	/*   int numRuns = Integer.parseInt(args[0]);
         int numBytes = Integer.parseInt(args[1]);
         java.util.Random  rnd = new java.util.Random ();
         for (int i=0; i<numRuns; i++) {
             for (int j=0; j<numBytes; j++) {
                 byte[] arr = new byte[j];
                 for (int k=0; k<j; k++)
                     arr[k] = (byte)rnd.nextInt();

                 String  s = byteArrayToBase64(arr);
                 byte [] b = base64ToByteArray(s);
                 if (!java.util.Arrays.equals(arr, b))
                     System.out.println("Dismal failure!");

                 s = byteArrayToAltBase64(arr);
                 b = altBase64ToByteArray(s);
                 if (!java.util.Arrays.equals(arr, b))
                     System.out.println("Alternate dismal failure!");
             }
         }*/
    }
}
 
