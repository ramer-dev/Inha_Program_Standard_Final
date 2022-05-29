package recipe;

import util.Font;

import java.util.*;

public class Recipe {

    // n 인분 입력이 없다면 기본적으로 1인분
    protected Recipe(String name, Map<String, Double> food, List<String> knowHow) {
        this.name = name;
        this.food.putAll(food);
        this.knowHow.addAll(knowHow);
    }

    // n 인분 입력 받음
    protected Recipe(String name, Map<String, Double> food, List<String> knowHow, int serveSize) {
        this.name = name;
        this.food.putAll(food);
        this.knowHow.addAll(knowHow);
        this.serveSize = serveSize;
    }

    protected void printAll() {
        printServe();
        printFood();
        printRecipe();

        System.out.print(Font.FONT_GREEN + "\n메인 메뉴로 돌아갑니다. \n" + Font.RESET);
    }

    private void printServe() {
        System.out.printf(Font.FONT_GREEN +  "\n──────%d인분 기준──────\n" + Font.RESET, this.serveSize);
    }

    protected void printFood() {
        System.out.printf(Font.FONT_GREEN + "\n──────%s 재료──────\n" + Font.RESET, this.name);

        String[] keyset = this.food.keySet().toArray(new String[0]);
        Double[] value = this.food.values().toArray(new Double[0]);

        for (int i = 0; i < this.food.size(); i++) {
            String[] temp;
            temp = keyset[i].split(",");

            System.out.printf("%d.\t%s",i+1,temp[0]);
            System.out.print("\t|\t");
            System.out.printf("%.3f%s\n", Math.round(value[i]*100)/100.0, temp[1]);
        }
    }

    protected void printFood(int idx){
        String[] key = this.food.keySet().toArray(new String[0]);
        String[] split = key[idx].split(",");

        System.out.printf("%s |\t%f%s\n", split[0], this.food.get(key[idx]) ,split[1]);
    }

    protected void printRecipe() {

        System.out.printf(Font.FONT_GREEN + "\n──────%s 레시피──────\n" + Font.RESET, this.name);
        for (int i = 0; i < this.knowHow.size(); i++) {
            System.out.printf("%d.\t%s\n", i + 1, this.knowHow.get(i));
        }
    }

    protected void printRecipe(int i) {
        System.out.println(this.knowHow.get(i));
    }

    // 요리의 이름
    private String name;

    // String(이름,단위), Integer (단위량)
    // ex)  String(중력분,g), Integer(130)
    // ex2) String(달걀,ea), Integer(1)
    private Map<String, Double> food = new HashMap<>();

    // String("요리를 하는 방법을 서술합니다.")
    private List<String> knowHow = new ArrayList<>();

    // n 인분 | default 1
    private int serveSize = 1;

    // n인분
    public int getServeSize() {
        return serveSize;
    }

    public void setServeSize(int serveSize) {
        this.serveSize = serveSize;
    }

    // 요리의 이름 설정
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Food Map Return
    public Map<String, Double> getFood() {
        return this.food;
    }

    // Modify Food Map by key with values
    public void modifyFood(String food, String unit, double value, String key) {
        this.food.remove(key);
        this.food.put(food + "," + unit, value);
    }

    public String getKnowHow(int idx) {
        return this.knowHow.get(idx);
    }

    public void modifyKnowHow(String old_str, String new_str) {
        List<String> temp = new ArrayList<>();
        int idx = this.knowHow.indexOf(old_str);
        this.knowHow.remove(idx);
        this.knowHow.add(idx, new_str);
    }


    /*
    ****Not Used****
    public void addFood(String key, double value) {
        this.food.put(key, value);
    }

    //재료들의 손질 방법
    public void addKnowHow(String str) {
        this.knowHow.add(str);
    }

    public List<String> getKnowHow() {
        return this.knowHow;
    }
     */

}
