package com.hogwarts.framework.podemo;

import com.hogwarts.framework.podemo.BasePage;
import com.hogwarts.framework.podemo.MainPage;
import org.junit.jupiter.api.Test;

public class SearchTest extends BasePage {

    @Test
    void search(){
        MainPage main = new MainPage();
        main.search().search("selenium");
    }
}
