package recipe;

import util.Font;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RecipeFileWriter {
    String memory = "";
    RecipeFileReader reader;

    RecipeFileWriter(RecipeFileReader recipeFileReader) {
        this.reader = recipeFileReader;
    }

    public void fileWrite(int num, int sep) {
        try {

            char sep_ = chooseSeperator(sep);

            memory += (sep_ + num + "\n");

        } catch (IOException e) {
            System.out.println(Font.FONT_RED + "파일 쓰기에 실패하였습니다.");
            System.exit(0);
        }
    }

    public void fileWrite(String str, int sep) {
         /* txt file Rule
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
            char sep_ = chooseSeperator(sep);
            memory += (sep_ + str + "\n");
//            writer.write(sep_ + str + "\n");
//            writer.flush();
//            writer.close();

        } catch (IOException e) {
            System.out.println(Font.FONT_RED + "파일 쓰기에 실패하였습니다.");
            System.exit(0);
        }
    }

    char chooseSeperator(int sep) throws IOException {
        char sep_;
        switch (sep) {
            case 1:
                sep_ = '@';
                break;
            case 2:
                sep_ = '#';
                break;
            case 3:
                sep_ = '$';
                break;
            case 4:
                sep_ = '%';
                break;
            case 5:
                sep_ = '^';
                break;
            default:
                throw new IOException("에러 발생");
        }
        return sep_;

    }

    void flush() {
        String path = Recipe.class.getResource("").getPath();
        File file = new File(path + "Recipe.txt");

        try {
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter writer = new BufferedWriter(fw);
            writer.write(memory);
            writer.flush();
            writer.close();
            memory = "";

        } catch (IOException e) {
            System.out.println(Font.FONT_RED + "파일 쓰기에 실패하였습니다.");
            System.exit(0);
        }
    }
}
