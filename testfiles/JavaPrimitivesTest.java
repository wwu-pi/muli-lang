public class JavaPrimitivesTest {
    public int whatisthis
    , another
            , fixednonfree;

    private void testfun() {
        int x , y = 0;
        boolean abc;
        // boolean abcd free = false;  Forbidden by compiler :)
        //boolean abc;

        while (y < 5) {
            if (!abc) continue;
            int a = 5 - y - 1;
            break;
        }

        // while (y < 6) int x free; Not possible! :)
    }

    public JavaPrimitivesTest() {
        Object z;
    }
}