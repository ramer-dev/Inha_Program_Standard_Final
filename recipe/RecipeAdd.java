package recipe;

import com.sun.istack.internal.NotNull;
import util.Font;

import java.util.*;

public class RecipeAdd {
    private RecipeFileWriter writer;
    private RecipeList list;
    private Scanner sc;

    // Class initializer
    // Scanner imported from Class.Main => Class.RecipeList
    public RecipeAdd(RecipeList list, Scanner sc, RecipeFileWriter writer){
        this.list = list;
        this.writer = writer;
        this.sc = sc;
    }

    // Add Recipe Function
    public void addRecipeInput() {
        // Input Name
        System.out.print(Font.FONT_GREEN + "요리의 이름을 입력해주세요 : \t" + Font.RESET);
        String name = sc.nextLine();
        writer.fileWrite(name, 1);

        // If already has same name, breaks out.
        for (int i = 0; i<list.recipeList.size(); i++){
            if(list.recipeList.containsKey(name)){
                System.out.println(Font.FONT_RED + "중복 레시피 발생 : " + name + Font.RESET);
                System.out.println(Font.FONT_RED + "중복 레시피는 허용하지 않습니다." + Font.RESET);
                System.out.print(Font.FONT_GREEN + "\n메인 메뉴로 돌아갑니다. \n" + Font.RESET);
                return;
            }
        }

        // get Servings
        System.out.print(Font.FONT_GREEN + "레시피는 몇인분 기준인가요? (숫자만 입력) : \t" + Font.RESET);
        int num = Integer.parseInt(sc.nextLine());
        writer.fileWrite(num, 2);

        // get Foods
        Map<String, Double> map = inputMap();

        // get KnowHows
        List<String> lst = inputList();

        // Add new Class and append to the RecipeList
        list.addRecipeList(new Recipe(name, map, lst, num));
        writer.fileWrite("", 5);

        // clear memory to prevent interference
        map.clear();
        lst.clear();
        writer.flush();

    }

    // Get Foods in map
    private Map<String, Double> inputMap() {
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

    // input Knowhow in List
    private List<String> inputList() {

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
