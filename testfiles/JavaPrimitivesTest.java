public class JavaPrimitivesTest {
    public int whatisthis, another, fixednonfree;

    private void testfun() {
        int x, y = 0;
        boolean abc; //Reserves a slot, but does not output variable into LocalVariablesTable (unless initialised)

        while (y < 5) {
            if (!abc) continue;
            int a = 5 - y - 1;
            break;
        }

    }

    public JavaPrimitivesTest() {
        Object z;
    }
}