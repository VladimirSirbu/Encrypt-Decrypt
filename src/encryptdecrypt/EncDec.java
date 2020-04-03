package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class EncDec {
    public static String MODE;
    public static String KEY;
    public static String DATA;
    public static String IN;
    public static String OUT;
    public static String ALG;
    public static String chiperText;
    public static String originalText;
    public static HashMap<String,String> map;

    public EncDec(){
        this.MODE = "enc";
        this.KEY = "0";
        this.DATA = "";
        this.IN = "";
        this.OUT = "";
        this.ALG = "shift";
        this.chiperText = "";
        this.originalText = "";
        this.map = new HashMap<>();
    }

    public void ControlFlow(String args[]){
         // 1. Struct data in a MAP
         structData(args);
         // 2. Set Value for every argument
         setValues(map);
         // 3. Read Text for encrypt
         readText();
         // 4. Set mode ENCRYPT/DECRYPT
         setMode();
         // 5. Write text
         writeText();

    }

    private void structData(String[] args){
        for (int i = 0; i < args.length - 1; i=i+2) {
            map.put(args[i],args[i+1]);
        }
    }

    private void setValues(HashMap<String,String> map){
        for(String key: map.keySet()) {
        switch (key) {
            case "-mode":
                this.MODE = map.get(key);
                break;
            case "-in":
                this.IN = map.get(key);
                break;
            case "-out":
                this.OUT = map.get(key);
                break;
            case "-key":
                this.KEY = map.get(key);
                break;
            case "-data":
                this.DATA = map.get(key);
                break;
            case "-alg":
                this.ALG = map.get(key);
                break;
            default:
                System.out.println("You entered WRONG command!");;
        }
        }
        //Set other requirements
        if(DATA!="" && IN!="")
            this.IN = "";
    }

    private void readText(){
        if(IN != ""){
            try (Scanner in = new Scanner(new File(IN))) {
                String s = "";
                while (in.hasNext()) {
                    s = s + in.nextLine();
                }
                originalText = s;
            } catch (FileNotFoundException e) { System.out.println("Error! File " + IN + " not found!"); }
        }else {
            originalText = DATA;
        }
    }

    private void writeText(){
        if (OUT != "") {
            try (PrintWriter printWriter = new PrintWriter(new File(OUT))) {
                printWriter.print(chiperText);
            } catch (FileNotFoundException e) { System.out.println("Error! File " + OUT + " not found!"); }
        }else{
            System.out.println(chiperText);
        }
    }

    private void setMode(){
        if (MODE.equals("enc")){
            Encrypt enc = (Encrypt) choose(ALG);
            chiperText = enc.encrypt(originalText,KEY);
        }else if(MODE.equals("dec")) {
            Decrypt dec = (Decrypt) choose(ALG);
            chiperText = dec.decrypt(originalText,KEY);
        }
    }

    private Object choose(String type){
        switch (type){
            case "shift": return new Shift();
            case "unicode": return new Unicode();
            default:
                System.out.println("Wrong type of argument -alg !");
                return null;
        }
    }






}
