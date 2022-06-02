import recipe.RecipeAdd;
import recipe.RecipeFileReader;
import recipe.RecipeList;
import recipe.RecipeModify;
import util.Font;
import util.Initializer;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isOver = false;
        int selection;

        // Initializer
        Initializer init = new Initializer();
        init.banner();

        // class Loader
        RecipeList recipeList = new RecipeList(sc);


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
                        recipeList.recipeAdd.addRecipeInput();
                        break;
                    case 3: // 레시피 수정
                        recipeList.getRecipeList(2);
                        break;
                    case 4: // 나가기
                        isOver = true;
                        System.out.println(Font.FONT_RED + "레시피 서비스를 종료합니다.");
                        break;
                    default:
                        System.out.println(Font.FONT_RED + "잘못 입력하셨습니다." + Font.RESET);
                }
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println(Font.FONT_RED + "숫자를 입력해주세요.\n" + Font.RESET);
                System.out.print(Font.FONT_GREEN + "\n메인 메뉴로 돌아갑니다. \n" + Font.RESET);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(Font.FONT_RED + "범위 밖의 숫자를 입력하셨습니다.\n" + Font.RESET);
                System.out.print(Font.FONT_GREEN + "\n메인 메뉴로 돌아갑니다. \n" + Font.RESET);
            }
        }

        // Finalize
        sc.close();

    }


}
