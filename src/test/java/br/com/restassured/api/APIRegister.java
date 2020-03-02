package br.com.restassured.api;

public class APIRegister {

	@SuppressWarnings("unchecked")
	private static <T> T getAPI(Class<?> clazz) {
		T api = null;
		try {
			api = (T) clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return api;
	}

	public static GetMethodClass getMethodClass() {
		return getAPI(GetMethodClass.class);
	}
	
	public static BookAPIClass bookAPIClass() {
		return getAPI(BookAPIClass.class);
	}
}
