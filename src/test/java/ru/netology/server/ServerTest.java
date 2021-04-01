package ru.netology.server;

import junit.framework.TestCase;
import org.mockito.Mockito;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;

public class ServerTest extends TestCase {
    private static String SETTING_TEST_PATH = "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Settings.txt";

    public void testGetSetting() {
        //given:
        Setting setting = new Setting("localhost", 23334);

        //then:
        assertThat(Server.getSetting(SETTING_TEST_PATH), is(equalTo(setting)));
        assertThat(Server.getSetting(""), is(equalTo(null)));
    }
}