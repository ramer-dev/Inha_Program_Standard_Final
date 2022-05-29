package recipe;

import util.Font;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipeFileReader {

    private RecipeList list;
    private String path = Recipe.class.getResource("").getPath();

    private BufferedReader reader;
    private String str;
    private String name = "";
    private Map<String, Double> map = new HashMap<>();
    private List<String> arrayList = new ArrayList<>();
    private int serveSize = 1;
    private boolean flag = false;

    public RecipeFileReader(RecipeList list) {
        this.list = list;

        fileRead();
    }

    public void fileRead() {

        /* txt file Rule,
        1. txt 파일을 \n 문자 단위로 split

        2. '@' 문자 뒤에는 레시피의 이름 ( 음식의 이름 )
           - 형식 : @식빵
        3. '#' 문자 뒤에는 레시피 인원 수 ( n 인분만큼의 음식 )
           - 형식 : #1
        4. '$' 문자 뒤에는 레시피 목록 불러오기
           - 형식 : $String("{재료명}, {단위}"), int({단위량})
           - ex ) $밀가루,g,130
        5. '%' 문자 뒤에는 조리 순서 불러오기
           - 형식 : %String("{조리 순서}")
           - 단 '|' 문자는 구분자로서 해당 문자가 나오면 \n\t로 대체
           - ex ) %밀가루를 우유를 넣어 반죽하세요.
        6. '^' 해당 Recipe의 Data 개행을 종료하고 클래스를 생성합니다.
        */


        try {
            reader = new BufferedReader(
                    new FileReader(path + "Recipe.txt")
            );
//            String str;
//            String name = "";
//            Map<String, Double> map = new HashMap<>();
//            List<String> arrayList = new ArrayList<>();
//            int serveSize = 1;
//            boolean flag = false;


            while ((str = reader.readLine()) != null) {

                // Skip Blank Line
                if (str.length() == 0) continue;

                char firstWord = str.charAt(0);
                switch (firstWord) {
                    case '@':
                        name = str.substring(1);

                        // Find Duplicate Recipe.name
                        // if it finds duplication, do break.
                        for (int i = 0; i < this.list.recipeList.size(); i++) {
                            if (this.list.recipeList.containsKey(name)) {
                                System.out.println("중복 클래스 발생\t" + name);
                                this.map.clear();
                                this.arrayList.clear();
                                break;
                            }
                        }
                        break;
                    case '#':
                        // flag means includes Serving
                        flag = true;
                        serveSize = Integer.parseInt(str.substring(1));
                        break;
                    case '$':
                        // put {"name,unit": value}
                        str = str.substring(1);
                        String[] a = str.split(",");
                        map.put(a[0] + "," + a[1], Double.parseDouble(a[2]));
                        break;
                    case '%':
                        // '|' letter means new line
                        this.arrayList.add(str.substring(1).replaceAll("[|]", "\n\t"));
                        break;
                    case '^':
                        // flag means it includes servings.
                        if (flag) {
                            this.list.addRecipeList(new Recipe(name, map, this.arrayList, serveSize));
                        } else {
                            this.list.addRecipeList(new Recipe(name, map, this.arrayList));
                        }
                        // delete all map, list items
                        this.map.clear();
                        this.arrayList.clear();
                        flag = false;
                        break;

                    default:
                        break;
                }

            }
            System.out.println(Font.FONT_GREEN + "파일로부터 레시피를 불러오는데 성공했습니다.\n" + Font.RESET);

        } catch (IOException e) {
            System.out.println(Font.FONT_RED + "파일 읽기에 실패하였습니다.");
            System.out.println("Recipe.txt의 경로가");
            System.out.println(path);
            System.out.println("안에 있는지 확인해주세요." + Font.RESET);
            System.out.print(Font.FONT_GREEN + "\n메인 메뉴로 돌아갑니다. \n" + Font.RESET);

            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Font.FONT_RED + "파일의 형식이 잘못되었습니다.");
            System.out.println(path + "Recipe.txt 파일 내 형식을 확인해주세요" + Font.RESET);
            System.out.print(Font.FONT_GREEN + "\n메인 메뉴로 돌아갑니다. \n" + Font.RESET);

            System.exit(0);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(Font.FONT_RED + "파일의 형식이 잘못되었습니다.");
            System.out.println(Font.FONT_RED + "빈 줄이 있는지 확인해주세요.");
            System.out.print(Font.FONT_GREEN + "\n메인 메뉴로 돌아갑니다. \n" + Font.RESET);
        }
    }

    public void fileRead(String target, int sep) {

        try {

            while (!(str = reader.readLine()).equals(target)) {

                // Skip Blank Line
                if (str.length() == 0) continue;

                char firstWord = str.charAt(0);
                switch (firstWord) {
                    case '@':
                        name = str.substring(1);

                        // Find Duplicate Recipe.name
                        // if it finds duplication, do break.
                        for (int i = 0; i < this.list.recipeList.size(); i++) {
                            if (this.list.recipeList.containsKey(name)) {
                                System.out.println("중복 클래스 발생\t" + name);
                                map.clear();
                                this.arrayList.clear();
                                break;
                            }
                        }
                        break;
                    case '#':
                        // flag means includes Serving
                        flag = true;
                        serveSize = Integer.parseInt(str.substring(1));
                        break;
                    case '$':
                        // put {"name,unit": value}
                        str = str.substring(1);
                        String[] a = str.split(",");
                        map.put(a[0] + "," + a[1], Double.parseDouble(a[2]));
                        break;
                    case '%':
                        // '|' letter means new line
                        this.arrayList.add(str.substring(1).replaceAll("[|]", "\n\t"));
                        break;
                    case '^':
//                        // flag means it includes servings.
//                        if (flag) {
//                            this.list.addRecipeList(new Recipe(name, map, this.arrayList, serveSize));
//                        } else {
//                            this.list.addRecipeList(new Recipe(name, map, this.arrayList));
//                        }
//                        // delete all map, list items
                        map.clear();
                        this.arrayList.clear();
                        flag = false;
                        break;

                    default:
                        break;
                }

            }
            System.out.println(Font.FONT_GREEN + "파일로부터 레시피를 불러오는데 성공했습니다.\n" + Font.RESET);
        } catch (IOException e) {
            System.out.println(Font.FONT_RED + "파일 읽기에 실패하였습니다.");
            System.out.println("Recipe.txt의 경로가");
            System.out.println(path);
            System.out.println("안에 있는지 확인해주세요." + Font.RESET);
            System.out.print(Font.FONT_GREEN + "\n메인 메뉴로 돌아갑니다. \n" + Font.RESET);

            System.exit(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Font.FONT_RED + "파일의 형식이 잘못되었습니다.");
            System.out.println(path + "Recipe.txt 파일 내 형식을 확인해주세요" + Font.RESET);
            System.out.print(Font.FONT_GREEN + "\n메인 메뉴로 돌아갑니다. \n" + Font.RESET);

            System.exit(0);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(Font.FONT_RED + "파일의 형식이 잘못되었습니다.");
            System.out.println(Font.FONT_RED + "빈 줄이 있는지 확인해주세요.");
            System.out.print(Font.FONT_GREEN + "\n메인 메뉴로 돌아갑니다. \n" + Font.RESET);
        }
    }
}
