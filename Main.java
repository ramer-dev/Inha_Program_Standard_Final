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
        while (!isOver && flag) {
            try {
                init.printMenu();
                selection = sc.nextInt();
                switch (selection) {
                    case 1: // 메뉴 검색
                        recipeList.getRecipeList(1);

                        break;
                    case 2: // 메뉴 추가
                        recipeAdd.addRecipeInput();

//                        rc.addFood("달걀,ea",1);
//                        rc.addFood("밀가루,g",130);
//                        rc.addKnowHow("요리에 앞서 필요한 재료를 모두 꺼내 준비합니다.");
//                        rc.addKnowHow("젤 먼저 밀가루를 체에 쳐서 준비합니다.\n\t쿠키는 박력분이지만 보통 집에서 많이 사용하는 중력분을 사용해도 상관없어요.");
//                        rc.print();

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
            } catch (InputMismatchException e) {
                flag = false;
                System.out.println(Font.FONT_RED + "숫자를 입력해주세요." + Font.RESET);
            }
        }

        // Finalize
        sc.close();


    }


}
