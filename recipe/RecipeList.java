package recipe;

import util.Font;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class RecipeList {

    private Scanner sc;

    public RecipeList(Scanner sc) {
        this.sc = sc;
    }

    Map<String, Recipe> recipeList = new HashMap<>();

    public void addRecipeList(Recipe recipe) {
        this.recipeList.put(recipe.getName(), recipe);
    }

    public void getRecipeList(int type) {
        String[] recipeName = this.recipeList.keySet().toArray(new String[0]);
        Recipe[] address = this.recipeList.values().toArray(new Recipe[0]);

        System.out.println(Font.FONT_GREEN + "\n─────레시피 목록입니다.─────" + Font.RESET);

        for (int i = 0; i < address.length; i++) {
            recipeName[i] = address[i].getName();
            System.out.printf("%d\t%s\n", i + 1, recipeName[i]);
        }

        int temp = Integer.parseInt(sc.nextLine());
        switch (type) { // 조회 페이지
            case 1:
                address[temp - 1].printAll();
                break;
            case 2: // 수정 페이지
                selection(address[temp - 1]);
                break;

        }

        //return this.recipeList;
    }

    void selection(Recipe recipe) {
        System.out.println(
                "┌────어떤 것을 수정하시겠어요?────┐\n" +
                        "│1. 레시피 이름　　　　　　　　　　　　　　│\n" +
                        "│2. 레시피 인원수　　　　　　　　　　　　　│\n" +
                        "│3. 식재료 　　　　　　　　　　　　　　　　│\n" +
                        "│4. 요리 순서　　　　　　　　　　　　　　　│\n" +
                        "│5. 나가기　 　　　　　　　　　　　　　　　│\n" +
                        "└────────────────────┘");
        int num = Integer.parseInt(sc.nextLine());


        int idx;
        String[] array;

        try {
            switch (num) {
                case 1:
                    System.out.println(Font.FONT_GREEN + "수정할 이름을 입력해주세요." + Font.RESET);
                    System.out.println(Font.FONT_GREEN + "기존 이름 :\t" + Font.FONT_CYAN + recipe.getName() + Font.RESET);
                    String new_name = sc.nextLine();
                    recipe.setName(new_name);
                    System.out.println(Font.FONT_GREEN + "변경 후 이름 :\t" + Font.FONT_CYAN + recipe.getName() + Font.RESET);
                    System.out.println(Font.FONT_GREEN + "적용이 완료되었습니다." + Font.RESET);
                    break;
                case 2:
                    System.out.println(Font.FONT_GREEN + "수정할 인원을 입력해주세요." + Font.RESET);
                    System.out.println(Font.FONT_GREEN + "기존 인원 :\t" + Font.FONT_CYAN + recipe.getServeSize() + Font.RESET);
                    int serve_size = Integer.parseInt(sc.nextLine());
                    recipe.setServeSize(serve_size);
                    System.out.println(Font.FONT_GREEN + "변경 후 인원 :\t" + Font.FONT_CYAN + recipe.getServeSize() + Font.RESET);
                    System.out.println(Font.FONT_GREEN + "적용이 완료되었습니다." + Font.RESET);
                    break;

                case 3:
                    System.out.println(Font.FONT_GREEN + "수정할 식재료의 숫자를 입력해주세요." + Font.RESET);
                    recipe.printFood();
                    idx = Integer.parseInt(sc.nextLine());
                    array = recipe.getFood().keySet().toArray(new String[0]);

                    System.out.print("수정 전 :");
                    recipe.printFood(idx - 1);

                    System.out.print(Font.FONT_GREEN + "재료 \t:" + Font.RESET);
                    String food = sc.nextLine();

                    System.out.printf(Font.FONT_GREEN + "%s 용량 \t:" + Font.RESET, food);
                    double value = Double.parseDouble(sc.nextLine());

                    System.out.printf(Font.FONT_GREEN + "%s %f 단위량 \t:" + Font.RESET, food, value);
                    String unit = sc.nextLine();

                    recipe.modifyFood(food, unit, value, array[idx]);
                    System.out.println(Font.FONT_GREEN + "적용이 완료되었습니다." + Font.RESET);
                    break;
                case 4:
                    System.out.println(Font.FONT_GREEN + "수정할 요리순서의 숫자를 입력해주세요." + Font.RESET);
                    recipe.printRecipe();

                    idx = Integer.parseInt(sc.nextLine());

                    System.out.print(Font.FONT_GREEN + "수정 전 :" + Font.RESET);
                    recipe.printRecipe(idx - 1);

                    System.out.print(Font.FONT_GREEN + "바꿀 내용 :" + Font.RESET);
                    String old_str = recipe.getKnowHow(idx - 1);
                    String new_str = sc.nextLine();
                    recipe.modifyKnowHow(old_str, new_str);
                    System.out.println(Font.FONT_GREEN + "적용이 완료되었습니다." + Font.RESET);

                    break;
                case 5:
                default:
                    break;
            }
        } catch (InputMismatchException | NumberFormatException e) {
        System.out.println(Font.FONT_RED + "숫자를 입력해주세요." + Font.RESET);
    }
    }
}