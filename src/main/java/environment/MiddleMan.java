/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package environment;

import java.io.InputStream;

public class MiddleMan {
    private static InputStream inputStream;

    public static InputStream getInputStream() {

        return inputStream;
    }

    public static void setInputStream(InputStream inputStream) {
        new MiddleMan();
        MiddleMan.inputStream = inputStream;
    }
}
