package com.hogwarts.framework.potest;

import org.junit.jupiter.api.Test;

public class SearchTest extends BasePage {

    @Test
    void search(){
        MainPage main = new MainPage();
        main.search().search("selenium");
    }
}
