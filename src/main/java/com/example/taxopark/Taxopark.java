package com.example.taxopark;

import com.example.auto.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

public class Taxopark {
    private String name = null;
    private final HashMap<String, BaseAuto> autoMap = new HashMap<>();

    public Taxopark() {
    }

    public Taxopark(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public void addAuto(BaseAuto auto) {
        if(this.autoMap.containsKey(auto.getRegNumber())) {
            System.out.println("Таксопарк " + this.name + " має авто з реєстраційним номером " + auto.getRegNumber());
            return;
        }
        this.autoMap.put(auto.getRegNumber(), auto);
        System.out.println("Автомобіль успішно додано.");
    }
    public void delAuto(BaseAuto auto) {
        delAuto(auto.getRegNumber());
    }
    public void delAuto(String regNumber) {
        if(!this.autoMap.containsKey(regNumber)) {
            System.out.println("Таксопарк " + this.name + " не має авто з реєстраційним номером " + regNumber);
            return;
        }
        this.autoMap.remove(regNumber);
        System.out.println("Автомобіль успішно видалено.");
    }

    public double totalCost() {
        double total = 0;
        for(BaseAuto auto : this.autoMap.values()) {
            total += auto.getPrice();
        }
        return total;
    }

    public ArrayList<BaseAuto> velocityInRange(int minVel, int maxVel) {
        ArrayList<BaseAuto> result = new ArrayList<BaseAuto>();
        for(BaseAuto auto : this.autoMap.values()) {
            if(auto.velocityBetweenRange(minVel, maxVel)) {
                result.add(auto);
            }
        }
        return result;
    }

    public ArrayList<BaseAuto> sortedByUoF() {
        ArrayList<BaseAuto> result = new ArrayList<BaseAuto>(this.autoMap.values());
        result.sort(Comparator.comparing(BaseAuto::getUof));
        return result;
    }

    public ArrayList<BaseAuto> autoArrayList() {
        ArrayList<BaseAuto> result = new ArrayList<BaseAuto>(this.autoMap.values());
        result.sort(Comparator.comparing(BaseAuto::getRegNumber));
        return result;
    }

    public void addAuto() {
        String carType = null;
        Scanner sc = new Scanner(System.in);
        while(carType == null) {
            System.out.println("Виберіть тип автомобіля.");
            System.out.println("\t1 - легкове авто");
            System.out.println("\t2 - кросовер");
            System.out.println("\t3 - вантажний автомобіль");
            System.out.println("\t4 - вен");
            System.out.println("\t5 - повернутися у попереднє меню.");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    carType = "сar";
                    break;
                case 2:
                    carType = "suv";
                    break;
                case 3:
                    carType = "truck";
                    break;
                case 4:
                    carType = "van";
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Необхідно ввести або 1, або 2, або 3, або 4, або 5 варіант\n");
            }
        }

        System.out.println("Введіть марку автомобіля:");
        String brand = sc.next();

        System.out.println("Введіть модель автомобіля:");
        String model = sc.next();

        String reg_number = null;
        while(reg_number == null) {
            System.out.println("Введіть реєстраційний номер автомобіля:");
            reg_number = sc.next();

            if(this.autoMap.containsKey(reg_number)) {
                System.out.println("Таксопарк " + this.name + " вже має авто з реєстраційним номером " + reg_number);
                reg_number = null;
            }
        }

        int year = 0;
        int startYear = 1980;
        int curYear = Year.now().getValue();
        while (year < startYear || year > curYear) {
            System.out.println("Вкажіть рік випуску від " + startYear + " до " + curYear);
            year = sc.nextInt();
            if(year < startYear || year > curYear)
                System.out.println("Введено неправильне значення.\n");
        }
        int price = 0;
        while (price < 1 || price > 1000) {
            System.out.println("Вкажіть початкову вартість автомобіля в тис. у.о.");
            price = sc.nextInt();
            if(price < 1 || price > 1000)
                System.out.println("Введено неправильне значення.\n");
        }
        double uof = 0;
        while (uof < 2 || uof > 50) {
            System.out.println("Вкажіть витрати пального на 100км");
            uof  = sc.nextDouble();
            if(uof  < 2 || uof > 50)
                System.out.println("Введено неправильне значення.\n");
        }
        int avgVel = inputVelocity("Вкажіть середню швидкість за годину:");
        addAuto(carType, brand, model, reg_number, year, price, uof, avgVel);
    }

    public void addAuto(String carType, String brand, String model, String reg_number, int year, int price, double uof, int avgVel) {
        BaseAuto auto = null;
        switch (carType) {
            case "car":
                auto = new Car(brand, model, year, reg_number, price, uof, avgVel);
                break;
            case "suv":
                auto = new Suv(brand, model, year, reg_number, price, uof, avgVel);
                break;
            case "truck":
                auto = new Truck(brand, model, year, reg_number, price, uof, avgVel);
                break;
            case "van":
                auto = new Van(brand, model, year, reg_number, price, uof, avgVel);
                break;
        }
        this.addAuto(auto);
    }

    public void showList() {
        System.out.println("Автомобілі таксопарку " + this.name + ":");
        for(BaseAuto auto:this.autoMap.values()) {
            System.out.println(auto);
        }
    }

    public void sortList() {
        ArrayList<BaseAuto> sorted = this.sortedByUoF();
        System.out.println("Автомобілі таксопарку " + this.name + ", відсортовані за витратою пального:");
        for(BaseAuto auto:sorted) {
            System.out.println(auto);
        }
    }

    public void searchAuto() {
        int minVelocity = inputVelocity("Вкажіть мінімальну середню швидкість за годину:");
        int maxVelocity = inputVelocity("Вкажіть максимальну середню швидкість за годину:");
        searchAuto(minVelocity, maxVelocity);
    }

    private void searchAuto(int minVelocity, int maxVelocity) {
        ArrayList <BaseAuto> selected = this.velocityInRange(minVelocity, maxVelocity);
        if(selected.size()==0) {
            System.out.println("Не знайдено автомобілів у заданому діапазоні швидкостей.");
            return;
        }
        System.out.println("Знайдено автомобілі:");
        for(BaseAuto auto:selected) {
            System.out.println(auto);
        }
    }

    private int inputVelocity(String txt) {
        Scanner sc = new Scanner(System.in);
        int velocity = 0;
        while (velocity < 30 || velocity > 150) {
            System.out.println(txt);
            velocity  = sc.nextInt();
            if(velocity  < 30 || velocity > 150)
                System.out.println("Введено неправильне значення.\n");
        }
        return velocity;
    }

    public void create() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть назву таксопарку:");
        String parkname = sc.next();
        this.create(parkname);
    }

    public void showCost() {
        System.out.println("Вартість таксопарку "+ this.name + ": "  + this.totalCost());
    }

    public void removeAuto() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введіть реєстраційний номер автомобіля:");
        String reg_number = sc.next();
        removeAuto(reg_number);
    }

    private void removeAuto(String reg_number) {
        if(!this.autoMap.containsKey(reg_number)) {
            System.out.println("Таксопарк " + this.name + " не має авто з реєстраційним номером " + reg_number);
            return;
        }
        this.autoMap.remove(reg_number);
        System.out.println("Автомобіль успішно вилучено.");
    }

    public void addAuto(String[] params) {
        if(!(params.length == 8)) {
            System.out.println("Має бути 8 параметрів.");
            return;
        }
        String carType = params[0];
        if(!carType.equals("car") && !carType.equals("suv") && !carType.equals("truck") && !carType.equals("van")) {
            System.out.println("Неправильний тип автомобіля.");
            return;
        }
        String brand = params[1], model = params[2], reg_number = params[3];
        int year = 0, price = 0, avgVel = 0;
        double uof = 0;
        try {
            year = Integer.parseInt(params[4]);
            price = Integer.parseInt(params[5]);
            avgVel= Integer.parseInt(params[7]);
        }
        catch (NumberFormatException ex) {
            System.out.println("Рік випуску, вартість, середня швидкість мають бути цілим числом.");
            return;
        }
        try {
            uof = Double.parseDouble(params[6]);
        }
        catch (NumberFormatException ex) {
            System.out.println("Витрата пального має бути дробовим числом.");
            return;
        }
        addAuto(carType, brand, model, reg_number, year, price, uof, avgVel);
    }

    public void create(String[] params) {
        if(params.length == 0) {
            System.out.println("Не вказано назву таксопарку.");
            return;
        }
        this.create(params[0]);
    }

    private void create(String parkname) {
        if(this.name != null) {
            System.out.println("Таксопарк вже створено.");
            return;
        }
        this.name = parkname;
        System.out.println("Створено таксопарк " + parkname);
    }

    public void removeAuto(String[] params) {
        if(params.length == 0) {
            System.out.println("Не вказано реєстраційний номер.");
            return;
        }
        this.removeAuto(params[0]);
    }

    public void searchAuto(String[] params) {
        if(!(params.length == 2)) {
            System.out.println("Вкажіть мінімальну і максимальну швидкості для пошуку.");
            return;
        }
        int minVel = 0, maxVel = 0;
        try{
            minVel = Integer.parseInt(params[0]);
            maxVel = Integer.parseInt(params[1]);
        }
        catch (NumberFormatException ex){
            System.out.println("Швидкості мають бути цілим числом.");
            return;
        }
        searchAuto(minVel, maxVel);
    }

    public void showCost(String[] params) {
        this.showCost();
    }

    public void showList(String[] params) {
        this.showList();
    }

    public void sortList(String[] params) {
        this.sortList();
    }
}


