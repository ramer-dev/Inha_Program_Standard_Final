import com.inha.finalexam.recipe.RecipeAdd;
import com.inha.finalexam.recipe.RecipeFileReader;
import com.inha.finalexam.recipe.RecipeList;
import com.inha.finalexam.recipe.RecipeModify;
import util.Font;
import util.Initializer;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isOver = false;
        boolean flag = true;
        int selection;

        // Initializer
        Initializer init = new Initializer();
        init.init();


        // class Loader
        RecipeList recipeList = new RecipeList(sc);
        RecipeAdd recipeAdd = new RecipeAdd(recipeList, sc);
        RecipeModify recipeModify = new RecipeModify(recipeList, sc);
        RecipeFileReader readRecipe = new RecipeFileReader(recipeList);
        readRecipe.fileRead();


        // main loop
        while (!isOver) {
            try {
                init.printMenu();
                selection = Integer.parseInt(sc.nextLine());
                switch (selection) {
                    case 1: // 메뉴 검색
                        recipeList.getRecipeList(1);
                        break;
                    case 2: // 메뉴 추가
                        recipeAdd.addRecipeInput();
                        break;
                    case 3: // 레시피 수정
                        recipeModify.modifyRecipyInput();
                        break;
                    case 4: // 나가기
                        isOver = true;
                        break;
                    default:
                        System.out.println(Font.FONT_RED + "잘못 입력하셨습니다." + Font.RESET);
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println(Font.FONT_RED + "숫자를 입력해주세요.\n" + Font.RESET);
            }
        }

        // Finalize
        sc.close();


    }


}
