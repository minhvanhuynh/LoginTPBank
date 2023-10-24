/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.Locale;
import java.util.ResourceBundle;
import view.Validation;
/**
 *
 * @author Admin
 */
public class Login {
    private Validation validation;
    private char[] chars = {'1', 'A', 'a', 'B', 'b', 'C',
        'c', '2', 'D', 'd', 'E', 'e', 'F', 'f', '3', 'G', 'g', 'H', 'h',
        'I', 'i', 'J', 'j', 'K', 'k', 'L', 'l', '4', 'M', 'm', 'N', 'n',
        'O', 'o', '5', 'P', 'p', 'Q', 'q', 'R', 'r', 'S', 's', 'T', 't',
        '6', '7', 'U', 'u', 'V', 'v', 'U', 'u', 'W', 'w', '8', 'X', 'x',
        'Y', 'y', 'Z', 'z', '9'};

    public Login(){
        validation= new Validation();
    }
    
    
    public void login(Locale language) {       
        int accountNumber = checkInputAccount(language);        
        String passString = checkInputPassword(language);        
        while (true) {
            String captchaGenerated = generateCaptchaText();
            if (checkInputCaptcha(captchaGenerated, language)) {
                System.out.println(getWordLanguage(language, "loginSuccess"));               
                return;
            } else {
                System.out.println(getWordLanguage(language, "errCaptchaIncorrect"));                
            }
        }
    }
    
    public String getWordLanguage(Locale curLocate, String key) {
        ResourceBundle words
                = ResourceBundle.getBundle("Language/" + curLocate, curLocate);
        String value = words.getString(key);
        return value;
    }
    
    public int checkInputAccount(Locale language) {
        while (true) {
            System.out.print(getWordLanguage(language, "enterAccountNumber"));
            String result = validation.checkInputString();
            if (result.matches("^\\d{10}$")) {
                return Integer.parseInt(result);
            }
            System.out.println(getWordLanguage(language, "errCheckInputAccount"));                       
        }
    }
    
    public String checkInputString(Locale language) {
        while (true) {
            String result = validation.checkInputString();
            if (result.length() == 0) {
                System.out.println(getWordLanguage(language, "errCheckInputIntLimit"));
                System.out.println();
            } else {
                return result;
            }
        }
    }
    
    public String checkInputPassword(Locale language) {
        String result;
        while (true) {
            System.out.print(getWordLanguage(language, "enterPassword"));
            result = checkInputString(language);
            if (isValidPassword(result, language)) {
                return result;
            }
        }
    }
    
    public boolean isValidPassword(String password, Locale language) {
        int lengthPassword = password.length();
        if (lengthPassword < 8 || lengthPassword > 31) {
            System.out.println(getWordLanguage(language, "errCheckLengthPassword"));        
            return false;
        } else {
            int countDigit = 0;
            int countLetter = 0;
            for (int i = 0; i < lengthPassword - 1; i++) {
                if (Character.isDigit(password.charAt(i))) {
                    countDigit++;
                } else if (Character.isLetter(password.charAt(i))) {
                    countLetter++;
                }
            }
            if (countDigit < 1 || countLetter < 1) {
                System.out.println(getWordLanguage(language, "errCheckAlphanumericPassword"));               
                return false;
            }
        }
        return true;
    }

    public boolean checkInputCaptcha(String captchaGenerated, Locale language) {
        System.out.println(captchaGenerated);
        System.out.print(getWordLanguage(language, "enterCaptcha"));
        String captchaInput = checkInputString(language);
        for (int i = 0; i < captchaInput.length(); i++) {
            if (!captchaGenerated.contains(Character.toString(captchaInput.charAt(i)))) {
                return false;
            }
        }
        return true;
    }


    
    public String generateCaptchaText() {
        String randomStrValue = "";
        int LENGTH = 6;
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (int i = 0; i < LENGTH; i++) {
            index = (int) (Math.random() * (chars.length - 1));
            sb.append(chars[index]);
        }
        return sb.toString();
    }
}
