package recipe;

import java.util.Scanner;

public class RecipeModify {
    RecipeList list;
    Scanner sc;

    public RecipeModify(RecipeList list, Scanner sc) {
        this.list = list;
        this.sc = sc;

//        list.getRecipeList(2);
    }

    public void modifyRecipyInput(){
        list.getRecipeList(2);
    }



}
