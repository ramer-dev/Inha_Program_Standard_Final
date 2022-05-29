package recipe;

import util.Font;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class RecipeList {

    private Scanner sc;
    protected Map<String, Recipe> recipeList = new HashMap<>();

    public RecipeAdd recipeAdd;
    public RecipeModify recipeModify;
    public RecipeFileWriter recipeFileWriter;
    public RecipeFileReader recipeFileReader;

    // Class Initializer
    // Scanner class imported from Class.Main
    public RecipeList(Scanner sc) {
        this.sc = sc;
        this.recipeFileReader = new RecipeFileReader(this);
        this.recipeFileWriter = new RecipeFileWriter(recipeFileReader);
        this.recipeAdd = new RecipeAdd(this, sc, this.recipeFileWriter);
        this.recipeModify = new RecipeModify(this, sc, this.recipeFileWriter);
    }

    // Adds to the RecipeList {Recipe.name, <class=Recipe>}
    public void addRecipeList(Recipe recipe) {
        this.recipeList.put(recipe.getName(), recipe);
    }

    // Get All RecipeList
    // argument "type" set the page which connects to the next page.
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
                this.recipeModify.RecipeModifyInput(address[temp - 1]);
                break;

        }

    }

}
