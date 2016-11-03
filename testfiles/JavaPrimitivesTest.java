package testfiles;
public class JavaPrimitivesTest {
    public int whatisthis, another, fixednonfree;

    private void testfun() {
        int x, y = 0, z;
        boolean abc = false; //Reserves a slot, but does not output variable into LocalVariablesTable (unless initialised)

        while (y < 5) {
            if (!abc) continue;
            int a = 5 - y - 1;
            break;
        }
    }

    public void moretest() {
        String f;
    }


    public JavaPrimitivesTest() {
        Object z;
    }
}