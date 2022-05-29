package util;


import java.util.List;
import java.util.Map;


public class Initializer {
    public void init() {
        banner();

    }

    public void flushMap(Map<String, Double> map, List<String> list) {
        map.clear();
        list.clear();
    }


    public void printMenu() {
        System.out.println(
                "┌──────신희상의 레시피──────┐\n" +
                        "│1. 레시피 조회　　　　　　　　　　　　　│\n" +
                        "│2. 레시피 추가　　　　　　　　　　　　　│\n" +
                        "│3. 레시피 수정　　　　　　　　　　　　　│\n" +
                        "│4. 나가기　 　　　　　　　　　　　　　　│\n" +
                        "└───────────────────┘\n");
    }

    void banner() {
        System.out.println(Font.BACKGROUND_WHITE + Font.FONT_BLACK + "\n" +
                "8 888888888o.   8 8888888888       ,o888888o.     8 8888 8 888888888o   8 8888888888   \n" +
                "8 8888    `88.  8 8888            8888     `88.   8 8888 8 8888    `88. 8 8888         \n" +
                "8 8888     `88  8 8888         ,8 8888       `8.  8 8888 8 8888     `88 8 8888         \n" +
                "8 8888     ,88  8 8888         88 8888            8 8888 8 8888     ,88 8 8888         \n" +
                "8 8888.   ,88'  8 888888888888 88 8888            8 8888 8 8888.   ,88' 8 888888888888 \n" +
                "8 888888888P'   8 8888         88 8888            8 8888 8 888888888P'  8 8888         \n" +
                "8 8888`8b       8 8888         88 8888            8 8888 8 8888         8 8888         \n" +
                "8 8888 `8b.     8 8888         `8 8888       .8'  8 8888 8 8888         8 8888         \n" +
                "8 8888   `8b.   8 8888            8888     ,88'   8 8888 8 8888         8 8888         \n" +
                "8 8888     `88. 8 888888888888     `8888888P'     8 8888 8 8888         8 888888888888 " +
                "\n\n" + Font.RESET +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tDev by. Shin Hee Sang\n");
    }
}
