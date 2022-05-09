package Jade;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private int width;
    private int height;
    private String title;
    private static Window window = null;
    private long glfwWindow;

    // private constructor for the window
    private Window() {
        this.width = 1920;
        this. height = 1080;
        this.title = "Mario";
    }

    // public get method to create an instance of a window
    // done this way, so we only ever have one window
    public static Window get() {
        if (Window.window == null) {
            Window.window = new Window();
        }
        return Window.window;
    }

    public void run(){
        System.out.println("Hello LWJGL" + Version.getVersion() + "!");

        init();
        loop();
    }

    public void init() {
        // set up an error callback
        GLFWErrorCallback.createPrint(System.err).set();

        //Initialize GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW.");
        }

        //Configure GLFW
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        //create the window
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window");
        }

        //Make the openGL context current
        glfwMakeContextCurrent(glfwWindow);
        //enable v-sync
        glfwSwapInterval(1);

        //make the window visible
        glfwShowWindow(glfwWindow);

        //This line is critical for LWJGL's interoperation with GLFW's
        //OpenGL context, or any context that is managed externally.
        //LWJGL detects the context that is current in the current thread,
        //creates the GLCapabilities instance and makes the OpenGL
        //bindings available for use
        GL.createCapabilities();
    }

    public void loop() {
        while (!glfwWindowShouldClose(glfwWindow)) {
            //poll events
            glfwPollEvents();

            glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow);

        }
    }
}
