package webadv.s17201107.p02;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import org.apache.commons.codec.digest.DigestUtils;

public class App {
    private static String account = null;
    private static String passwordSha256Hex = null;
    private static boolean logged = true;

    public static void main(String[] args) {
        init();
        Scanner input = new Scanner(System.in);
        while (logged) {
            System.out.print("请输入账号：");
            String accountInputed = input.nextLine();
            System.out.print("请输入密码：");
            String passwordInputed = input.nextLine();
            loginCheck(accountInputed, passwordInputed);
        }
    }

    public static void loginCheck(String accountInputed, String passwordInputed) {
        if (account.equals(accountInputed) && passwordSha256Hex.equals(sha256hex(passwordInputed))) {
            System.out.println("登录成功！");
            logged = false;
        } else {
            System.out.println("登录失败，账号或密码错误！");
        }
    }
    public static void init() {
        FileInputStream fis = null;
        BufferedReader bufferedReader = null;
        try {
            fis = new FileInputStream("userData.text");
            bufferedReader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while((line = bufferedReader.readLine()) != null) {
                account = line.split(",")[0];
                passwordSha256Hex = line.split(",")[1];
                //System.out.println(line);
            }
            bufferedReader.close();
            fis.close();
        } catch (FileNotFoundException e) {
             e.printStackTrace();
        } catch (IOException e) {
             e.printStackTrace();
        }
    }
    public static String sha256hex(String input) {
        return DigestUtils.sha256Hex(input);
    }
}
