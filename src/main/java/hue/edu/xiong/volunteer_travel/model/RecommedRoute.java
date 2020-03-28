package hue.edu.xiong.volunteer_travel.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RecommedRoute {
    private String sex;

    private String age;

    private String job;

    private String income;

    private String route;


    public void WriteFile() {
        try {
            String str="@data" + "\n" + sex + "," + age + "," + job + "," + income;
           // System.out.println(str);
            File file =new File("F:/travel/src/main/java/hue/edu/xiong/volunteer_travel/file/predict.arff");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWritter = new FileWriter(file,false);
            fileWritter.write(str);
            fileWritter.close();
            ID3 inst = new ID3();
            inst.train("route", "F:/travel/src/main/java/hue/edu/xiong/volunteer_travel/file/train.arff");
            inst.writeXML("F:/travel/src/main/java/hue/edu/xiong/volunteer_travel/file/ID3_Tree.xml");
            String[] preLable = inst.predict("F:/travel/src/main/java/hue/edu/xiong/volunteer_travel/file/predict.arff");
            route = preLable[0];
            System.out.println(route);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public RecommedRoute(){

    }

    public RecommedRoute(String sex, String age, String job, String income, String route) {
        this.sex = sex;
        this.age = age;
        this.job = job;
        this.income = income;
        this.route = route;
    }

    public String getSex() {
        return sex;
    }

    public String getAge() {
        return age;
    }

    public String getJob() {
        return job;
    }

    public String getIncome() {
        return income;
    }

    public String getRoute() {
        return route;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setIncome(String income) {
        this.income = income;
    }

    public void setRoute(String route) {
        this.route = route;
    }
}
