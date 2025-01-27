package jxdotool;

import lombok.SneakyThrows;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class xDoToolUtil {

    public static void moveMouse(int theta, int r) throws IOException {
        String[] command = {
                "xdotool",
                "mousemove_relative",
                "--polar",
                String.valueOf(theta),
                String.valueOf(r)
        };

        new ProcessBuilder(command).start();
    }

    @SneakyThrows
    public static void ffFwd() {
        String[] args = new String[]{"xdotool", "key", "Alt+Right"};
        new ProcessBuilder(args).start();
    }

    @SneakyThrows
    public static void ffBack() {
        String[] args = new String[]{"xdotool", "key", "Alt+Left"};
        new ProcessBuilder(args).start();
    }

    @SneakyThrows
    public static void click() {
        String[] args = new String[]{"xdotool", "click", "1"};
        new ProcessBuilder(args).start();
    }

    @SneakyThrows
    public static void pressCtrlT() {
        String[] args = new String[]{"xdotool", "key", "Ctrl+t"};
        new ProcessBuilder(args).start();
    }

    @SneakyThrows
    public static void pressCtrlW() {
        String[] args = new String[]{"xdotool", "key", "Ctrl+w"};
        new ProcessBuilder(args).start();
    }

    @SneakyThrows
    public static void pressF() {
        String[] args = new String[]{"xdotool", "key", "f"};
        new ProcessBuilder(args).start();
    }

    @SneakyThrows
    public static void pressSpace() {
        String[] args = new String[]{"xdotool", "key", "Space"};
        new ProcessBuilder(args).start();
    }

    @SneakyThrows
    public static void keyDown() {
        String[] args = new String[]{"xdotool", "key", "Down"};
        new ProcessBuilder(args).start();
    }

    @SneakyThrows
    public static void keyUp() {
        String[] args = new String[]{"xdotool", "key", "Up"};
        new ProcessBuilder(args).start();
    }

    @SneakyThrows
    public static void keyRight() {
        String[] args = new String[]{"xdotool", "key", "Right"};
        new ProcessBuilder(args).start();
    }

    @SneakyThrows
    public static void keyLeft() {
        String[] args = new String[]{"xdotool", "key", "Left"};
        new ProcessBuilder(args).start();
    }

    @SneakyThrows
    public static void tabSwitchOff() {
        String[] ctrlCmd = new String[]{"xdotool", "keyup", "ctrl"};
        new ProcessBuilder(ctrlCmd).start();
    }

    @SneakyThrows
    public static void tabSwitchOn() {
        String[] ctrlCmd = new String[]{"xdotool", "keydown", "ctrl", "&&", "xdotool", "key", "Tab"};
        new ProcessBuilder(ctrlCmd).start();
    }

    @SneakyThrows
    public static void pageDown() {
        String[] args = new String[]{"xdotool", "key", "Page_Down"};
        new ProcessBuilder(args).start();
    }

    @SneakyThrows
    public static void pageUp() {
        String[] args = new String[]{"xdotool", "key", "Page_Up"};
        new ProcessBuilder(args).start();
    }

    @SneakyThrows
    public static void setPointerLocation(Dimension dimension) {
        String[] args = new String[]{"xdotool", "mousemove", String.valueOf(dimension.getWidth()), String.valueOf(dimension.getHeight())};
        Process proc = new ProcessBuilder(args).start();
    }

    @SneakyThrows
    public static Integer[] getScreenDimensions() {
        String[] args = new String[]{"cat", "/sys/class/graphics/*/virtual_size"};
        Process proc = new ProcessBuilder(args).start();
        String line = readLine(proc.getInputStream());
        String[] d = line.split(",");

        return new Integer[]{Integer.valueOf(d[0]), Integer.valueOf(d[1])};
    }

    @SneakyThrows
    public static void togglePlayYoutube() {
        Integer winId = getYoutubeWindowIDs().getLast();

        String[] args = new String[]{"xdotool", "key", "--window", winId.toString(), "k"};
        new ProcessBuilder(args).inheritIO().start();
    }

    @SneakyThrows
    public static List<Integer> getYoutubeWindowIDs() {
        String[] args = new String[]{"xdotool", "search", "--name", "youtube"};
        Process proc = new ProcessBuilder(args).start();

        return read(proc.getInputStream());
    }

    @SneakyThrows
    public static String readLine(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream))
                .readLine();
    }

    @SneakyThrows
    public static List<Integer> read(InputStream inputStream) {
        List<Integer> winIds = new LinkedList<>();
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line = null;
        while ((line = reader.readLine()) != null) {
            winIds.add(Integer.valueOf(line));
        }
        reader.close();

        return winIds;
    }
}
