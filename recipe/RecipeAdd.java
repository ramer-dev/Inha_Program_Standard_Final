package recipe;

import util.Font;

import java.util.*;

public class RecipeAdd {
    private RecipeFileWriter writer;
    private RecipeList list;
    private Scanner sc;

    public RecipeAdd(RecipeList list, Scanner sc){
        this.list = list;
        this.writer = new RecipeFileWriter();
        this.sc = sc;
    }

    public void addRecipeInput() {

        System.out.print(Font.FONT_GREEN + "요리의 이름을 입력해주세요 : \t" + Font.RESET);
        String name = sc.nextLine();
        writer.fileWrite(name, 1);

        for (int i = 0; i<list.recipeList.size(); i++){
            if(list.recipeList.containsKey(name)){
                System.out.println(Font.FONT_RED + "중복 레시피 발생 : " + name + Font.RESET);
                System.out.println(Font.FONT_RED + "중복 레시피는 허용하지 않습니다." + Font.RESET);
                System.out.print(Font.FONT_GREEN + "\n메인 메뉴로 돌아갑니다. \n" + Font.RESET);
                return;
            }
        }

        System.out.print(Font.FONT_GREEN + "레시피는 몇인분 기준인가요? (숫자만 입력) : \t" + Font.RESET);
        int num = Integer.parseInt(sc.nextLine()); // bug 방지
        writer.fileWrite(num, 2);
        Map<String, Double> map = inputMap();
        List<String> lst = inputList();
        list.addRecipeList(new Recipe(name, map, lst, num));
        map.clear();
        lst.clear();
        writer.fileWrite("", 5);
        writer.flush();

    }

    Map<String, Double> inputMap() {

        int i = 1;
        Map<String, Double> map = new HashMap<>();
        System.out.println(Font.FONT_RED + "\"-s\"를 입력하여 입력을 그만 둘 수 있습니다." + Font.RESET);
        while (i < 100) {
            String food;
            double level;
            String unit;
            System.out.printf(Font.FONT_GREEN + "재료 %d\t:" + Font.RESET, i);
            food = sc.nextLine();
            if (food.equals("-s")) {
                break;
            }
            System.out.printf(Font.FONT_GREEN + "%s 용량 \t:" + Font.RESET, food);
            level = Double.parseDouble(sc.nextLine());
            System.out.printf(Font.FONT_GREEN + "%s %f 단위량 \t:" + Font.RESET, food, level);
            unit = sc.nextLine();
            if (unit.equals("-s")) {
                break;
            }
            map.put(food + "," + unit, level);
            writer.fileWrite(food + "," + unit + "," + level, 3);
            i++;
        }

        return map;
    }

    List<String> inputList() {
        List<String> list = new ArrayList<>();
        System.out.println(Font.FONT_RED + "\"-s\"를 입력하여 입력을 그만 둘 수 있습니다." + Font.RESET);

        int i = 1;
        String text;
        while (i < 200) {
            System.out.printf(Font.FONT_GREEN + "%d번째 조리 방법을 입력해주세요." + Font.RESET, i);
            text = sc.nextLine();
            if (text.equals("-s")) {
                break;
            }
            writer.fileWrite(text, 4);
            if (text.length() >= 1) {
                list.add(text);
            }
            i++;
        }

        return list;
    }

}
