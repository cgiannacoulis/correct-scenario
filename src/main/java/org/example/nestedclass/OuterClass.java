package org.example.nestedclass;

public class OuterClass {
	private String variable;

	public static class InnerStaticClass {
		public static void testStaticMethod() {
			System.out.println("hello from static");
		}

		public void testMethod() {
			System.out.println("hello");
		}
	}

	public class InnerClass {
		private String variable;

		public void testMethod() {
			System.out.println("hello");
		}

		public void testMethodWithParameter(String variable) {
			System.out.println(OuterClass.this.variable + this.variable + variable);
		}
	}
}
