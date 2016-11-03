package testfiles;
import java.util.function.Supplier;

class MethodRefMinimal {
	public static <A> boolean search(A genericobj, Supplier<Boolean> pointer) {
		return false;
	}

	public static void main(String[] args) {
		boolean b = search(new Integer(), MethodRefMinimal::methodref);
	}

	public static Boolean methodref() {
		return true;
	}
}