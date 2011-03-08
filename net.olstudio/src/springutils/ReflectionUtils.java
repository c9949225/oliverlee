package springutils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 此类摘自Spring
 * Simple utility class for working with the reflection API and handling
 * reflection exceptions.
 *
 * <p>Only intended for internal use.
 *
 * @author Juergen Hoeller
 * @author Rob Harrop
 * @author Rod Johnson
 * @author Costin Leau
 * @author Sam Brannen
 * @since 1.2.2
 */
// Oliver 于 2008-6-2 下午04:12:17 作出注释:
// 清单: 
//     1, findField : 获取指定Class的Field
//     2, setField : 设定对象的属性值
//     3, findMethod : 获取无参方法
//     4, findMethod : 获取任意方法
//     5, invokeMethod : 调用无参方法
//     6, invokeMethod : 调用任意方法
//     7, declaresException : 判断指定的方法是否声明了指定的异常(包括父类)
//     8, isPublicStaticFinal : 
//     9, getAllDeclaredMethods : 得到一个类的所有方法包括父类
//     10, shallowCopyFieldState : 将源对象的所有属性的值赋给同类型对象的对应的属性
public abstract class ReflectionUtils {

	/**
	 * 获取指定Class的Field
	 * @param clazz 指定的类对象
	 * @param name 指定的属性对象名
	 * @param type 指定的属性对象类型
	 * @return 属性对象
	 */
	public static Field findField(final Class clazz, final String name, final Class type) {
		Assert.notNull(clazz, "The 'class to introspect' supplied to findField() can not be null.");
		Assert.hasText(name, "The field name supplied to findField() can not be empty.");
		Assert.notNull(type, "The field type supplied to findField() can not be null.");
		Class searchType = clazz;
		while (!Object.class.equals(searchType) && searchType != null) {
			final Field[] fields = searchType.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				if (name.equals(field.getName()) && type.equals(field.getType())) {
					return field;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}

	/**
	 * 设定对象的属性值
	 * @param field 要设值得属性
	 * @param target 目标对象
	 * @param value 具体值
	 */
	public static void setField(Field field, Object target, Object value) {
		try {
			field.set(target, value);
		}
		catch (IllegalAccessException ex) {
			handleReflectionException(ex);
			throw new IllegalStateException("Unexpected reflection exception - " + ex.getClass().getName() + ": " +
					ex.getMessage());
		}
	}
	
	/**
	 * 获取无参方法
	 * @param clazz 指定对象
	 * @param name 指定名称
	 * @return
	 */
	public static Method findMethod(Class clazz, String name) {
		return findMethod(clazz, name, new Class[0]);
	}

	/**
	 * 获取方法
	 * @param clazz 指定对象
	 * @param name 指定名称
	 * @param paramTypes 指定参数
	 * @return
	 */
	public static Method findMethod(Class clazz, String name, Class[] paramTypes) {
		Assert.notNull(clazz, "Class must not be null");
		Assert.notNull(name, "Method name must not be null");
		Class searchType = clazz;
		while (!Object.class.equals(searchType) && searchType != null) {
			Method[] methods = (searchType.isInterface() ? searchType.getMethods() : searchType.getDeclaredMethods());
			for (int i = 0; i < methods.length; i++) {
				Method method = methods[i];
				if (name.equals(method.getName()) && Arrays.equals(paramTypes, method.getParameterTypes())) {
					return method;
				}
			}
			searchType = searchType.getSuperclass();
		}
		return null;
	}

	/**
	 * 调用指定对象的无参方法
	 * @param method 指定访法
	 * @param target 目标对象
	 * @return
	 */
	public static Object invokeMethod(Method method, Object target) {
		return invokeMethod(method, target, null);
	}

	/**
	 * 调用指定对象的方法
	 * @param method 指定方法
	 * @param target 目标对象
	 * @param args 指定参数
	 * @return
	 */
	public static Object invokeMethod(Method method, Object target, Object[] args) {
		try {
			return method.invoke(target, args);
		}
		catch (Exception ex) {
			handleReflectionException(ex);
		}
		throw new IllegalStateException("Should never get here");
	}

	/**
	 * 针对传入的目标对象调用特点的JDBC API方法
	 * @param method
	 * @param target
	 * @return
	 * @throws SQLException
	 */
	public static Object invokeJdbcMethod(Method method, Object target) throws SQLException {
		return invokeJdbcMethod(method, target, null);
	}

	/**
	 * Invoke the specified JDBC API {@link Method} against the supplied
	 * target object with the supplied arguments.
	 * @param method the method to invoke
	 * @param target the target object to invoke the method on
	 * @param args the invocation arguments (may be <code>null</code>)
	 * @return the invocation result, if any
	 * @throws SQLException the JDBC API SQLException to rethrow (if any)
	 * @see #invokeMethod(java.lang.reflect.Method, Object, Object[])
	 */
	public static Object invokeJdbcMethod(Method method, Object target, Object[] args) throws SQLException {
		try {
			return method.invoke(target, args);
		}
		catch (IllegalAccessException ex) {
			handleReflectionException(ex);
		}
		catch (InvocationTargetException ex) {
			if (ex.getTargetException() instanceof SQLException) {
				throw (SQLException) ex.getTargetException();
			}
			handleInvocationTargetException(ex);
		}
		throw new IllegalStateException("Should never get here");
	}

	/**
	 * Handle the given reflection exception. Should only be called if
	 * no checked exception is expected to be thrown by the target method.
	 * <p>Throws the underlying RuntimeException or Error in case of an
	 * InvocationTargetException with such a root cause. Throws an
	 * IllegalStateException with an appropriate message else.
	 * @param ex the reflection exception to handle
	 */
	// Oliver 于 2008-6-2 下午03:48:27 作出注释:
	// 处理各种反射异常 
	public static void handleReflectionException(Exception ex) {
		if (ex instanceof NoSuchMethodException) {
			throw new IllegalStateException("Method not found: " + ex.getMessage());
		}
		if (ex instanceof IllegalAccessException) {
			throw new IllegalStateException("Could not access method: " + ex.getMessage());
		}
		if (ex instanceof InvocationTargetException) {
			handleInvocationTargetException((InvocationTargetException) ex);
		}
		if (ex instanceof RuntimeException) {
			throw (RuntimeException) ex;
		}
		handleUnexpectedException(ex);
	}

	/**
	 * Handle the given invocation target exception. Should only be called if
	 * no checked exception is expected to be thrown by the target method.
	 * <p>Throws the underlying RuntimeException or Error in case of such
	 * a root cause. Throws an IllegalStateException else.
	 * @param ex the invocation target exception to handle
	 */
	public static void handleInvocationTargetException(InvocationTargetException ex) {
		rethrowRuntimeException(ex.getTargetException());
	}

	/**
	 * Rethrow the given {@link Throwable exception}, which is presumably the
	 * <em>target exception</em> of an {@link InvocationTargetException}.
	 * Should only be called if no checked exception is expected to be thrown by
	 * the target method.
	 * <p>Rethrows the underlying exception cast to an {@link RuntimeException}
	 * or {@link Error} if appropriate; otherwise, throws an
	 * {@link IllegalStateException}.
	 * @param ex the exception to rethrow
	 * @throws RuntimeException the rethrown exception
	 */
	public static void rethrowRuntimeException(Throwable ex) {
		if (ex instanceof RuntimeException) {
			throw (RuntimeException) ex;
		}
		if (ex instanceof Error) {
			throw (Error) ex;
		}
		handleUnexpectedException(ex);
	}

	/**
	 * Rethrow the given {@link Throwable exception}, which is presumably the
	 * <em>target exception</em> of an {@link InvocationTargetException}.
	 * Should only be called if no checked exception is expected to be thrown by
	 * the target method.
	 * <p>Rethrows the underlying exception cast to an {@link Exception} or
	 * {@link Error} if appropriate; otherwise, throws an
	 * {@link IllegalStateException}.
	 * @param ex the exception to rethrow
	 * @throws Exception the rethrown exception (in case of a checked exception)
	 */
	public static void rethrowException(Throwable ex) throws Exception {
		if (ex instanceof Exception) {
			throw (Exception) ex;
		}
		if (ex instanceof Error) {
			throw (Error) ex;
		}
		handleUnexpectedException(ex);
	}

	/**
	 * Throws an IllegalStateException with the given exception as root cause.
	 * @param ex the unexpected exception
	 */
	// Oliver 于 2008-6-2 下午03:49:31 作出注释:
	// 抛出一个非法状态异常并将传入的异常作为根异常
	private static void handleUnexpectedException(Throwable ex) {
		// Needs to avoid the chained constructor for JDK 1.4 compatibility.
		IllegalStateException isex = new IllegalStateException("Unexpected exception thrown");
		isex.initCause(ex);
		throw isex;
	}

	/**
	 * Determine whether the given method explicitly declares the given exception
	 * or one of its superclasses, which means that an exception of that type
	 * can be propagated as-is within a reflective invocation.
	 * @param method the declaring method
	 * @param exceptionType the exception to throw
	 * @return <code>true</code> if the exception can be thrown as-is;
	 * <code>false</code> if it needs to be wrapped
	 */
	// Oliver 于 2008-6-2 下午03:50:20 作出注释:
	// 判断指定的方法是否声明了指定的异常,或者父类是否声明过
	public static boolean declaresException(Method method, Class exceptionType) {
		Assert.notNull(method, "Method must not be null");
		Class[] declaredExceptions = method.getExceptionTypes();
		for (int i = 0; i < declaredExceptions.length; i++) {
			Class declaredException = declaredExceptions[i];
			if (declaredException.isAssignableFrom(exceptionType)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断指定Field是否是"public static final"常量
	 */
	public static boolean isPublicStaticFinal(Field field) {
		int modifiers = field.getModifiers();
		return (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers));
	}

	/**
	 * 使一个Field可见
	 */
	public static void makeAccessible(Field field) {
		// 如果Field不是public的 或者 Field所在的类不是public的
		if (!Modifier.isPublic(field.getModifiers()) ||
				!Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			// 设置该Field可见
			field.setAccessible(true);
		}
	}

	/**
	 * 使一个Method可见
	 */
	public static void makeAccessible(Method method) {
		if (!Modifier.isPublic(method.getModifiers()) ||
				!Modifier.isPublic(method.getDeclaringClass().getModifiers())) {
			method.setAccessible(true);
		}
	}

	/**
	 * Make the given constructor accessible, explicitly setting it accessible if necessary.
	 * The <code>setAccessible(true)</code> method is only called when actually necessary,
	 * to avoid unnecessary conflicts with a JVM SecurityManager (if active).
	 * @param ctor the constructor to make accessible
	 * @see java.lang.reflect.Constructor#setAccessible
	 */
	public static void makeAccessible(Constructor ctor) {
		if (!Modifier.isPublic(ctor.getModifiers()) ||
				!Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) {
			ctor.setAccessible(true);
		}
	}


	/**
	 * Perform the given callback operation on all matching methods of the
	 * given class and superclasses.
	 * <p>The same named method occurring on subclass and superclass will
	 * appear twice, unless excluded by a {@link MethodFilter}.
	 * @param targetClass class to start looking at
	 * @param mc the callback to invoke for each method
	 * @see #doWithMethods(Class, MethodCallback, MethodFilter)
	 */
	public static void doWithMethods(Class targetClass, MethodCallback mc) throws IllegalArgumentException {
		doWithMethods(targetClass, mc, null);
	}

	/**
	 * Perform the given callback operation on all matching methods of the
	 * given class and superclasses.
	 * <p>The same named method occurring on subclass and superclass will
	 * appear twice, unless excluded by the specified {@link MethodFilter}.
	 * @param targetClass class to start looking at
	 * @param mc the callback to invoke for each method
	 * @param mf the filter that determines the methods to apply the callback to
	 */
	public static void doWithMethods(Class targetClass, MethodCallback mc, MethodFilter mf)
			throws IllegalArgumentException {

		// Keep backing up the inheritance hierarchy.
		do {
			Method[] methods = targetClass.getDeclaredMethods();
			for (int i = 0; i < methods.length; i++) {
				if (mf != null && !mf.matches(methods[i])) {
					continue;
				}
				try {
					mc.doWith(methods[i]);
				}
				catch (IllegalAccessException ex) {
					throw new IllegalStateException(
							"Shouldn't be illegal to access method '" + methods[i].getName() + "': " + ex);
				}
			}
			targetClass = targetClass.getSuperclass();
		}
		while (targetClass != null);
	}

	/**
	 * 得到一个类的所有方法包括父类
	 */
	public static Method[] getAllDeclaredMethods(Class leafClass) throws IllegalArgumentException {
		final List list = new ArrayList(32);
		doWithMethods(leafClass, new MethodCallback() {
			public void doWith(Method method) {
				list.add(method);
			}
		});
		return (Method[]) list.toArray(new Method[list.size()]);
	}


	/**
	 * Invoke the given callback on all fields in the target class,
	 * going up the class hierarchy to get all declared fields.
	 * @param targetClass the target class to analyze
	 * @param fc the callback to invoke for each field
	 */
	public static void doWithFields(Class targetClass, FieldCallback fc) throws IllegalArgumentException {
		doWithFields(targetClass, fc, null);
	}

	/**
	 * Invoke the given callback on all fields in the target class,
	 * going up the class hierarchy to get all declared fields.
	 * @param targetClass the target class to analyze
	 * @param fc the callback to invoke for each field
	 * @param ff the filter that determines the fields to apply the callback to
	 */
	public static void doWithFields(Class targetClass, FieldCallback fc, FieldFilter ff)
			throws IllegalArgumentException {

		// Keep backing up the inheritance hierarchy.
		do {
			// Copy each field declared on this class unless it's static or file.
			Field[] fields = targetClass.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				// Skip static and final fields.
				if (ff != null && !ff.matches(fields[i])) {
					continue;
				}
				try {
					fc.doWith(fields[i]);
				}
				catch (IllegalAccessException ex) {
					throw new IllegalStateException(
							"Shouldn't be illegal to access field '" + fields[i].getName() + "': " + ex);
				}
			}
			targetClass = targetClass.getSuperclass();
		}
		while (targetClass != null && targetClass != Object.class);
	}

	/**
	 * 将源对象的所有属性的值赋给同类型对象的对应的属性
	 */
	public static void shallowCopyFieldState(final Object src, final Object dest) throws IllegalArgumentException {
		if (src == null) {
			throw new IllegalArgumentException("Source for field copy cannot be null");
		}
		if (dest == null) {
			throw new IllegalArgumentException("Destination for field copy cannot be null");
		}
		if (!src.getClass().isAssignableFrom(dest.getClass())) {
			throw new IllegalArgumentException("Destination class [" + dest.getClass().getName() +
					"] must be same or subclass as source class [" + src.getClass().getName() + "]");
		}
		doWithFields(src.getClass(), new FieldCallback() {
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				makeAccessible(field);
				Object srcValue = field.get(src);
				field.set(dest, srcValue);
			}
		}, COPYABLE_FIELDS);
	}


	/**
	 * Action to take on each method.
	 */
	public static interface MethodCallback {

		/**
		 * Perform an operation using the given method.
		 * @param method the method to operate on
		 */
		void doWith(Method method) throws IllegalArgumentException, IllegalAccessException;
	}


	/**
	 * Callback optionally used to method fields to be operated on by a method callback.
	 */
	public static interface MethodFilter {

		/**
		 * Determine whether the given method matches.
		 * @param method the method to check
		 */
		boolean matches(Method method);
	}


	/**
	 * Callback interface invoked on each field in the hierarchy.
	 */
	public static interface FieldCallback {

		/**
		 * Perform an operation using the given field.
		 * @param field the field to operate on
		 */
		void doWith(Field field) throws IllegalArgumentException, IllegalAccessException;
	}


	/**
	 * Callback optionally used to filter fields to be operated on by a field callback.
	 */
	public static interface FieldFilter {

		/**
		 * Determine whether the given field matches.
		 * @param field the field to check
		 */
		boolean matches(Field field);
	}


	/**
	 * Pre-built FieldFilter that matches all non-static, non-final fields.
	 */
	public static FieldFilter COPYABLE_FIELDS = new FieldFilter() {
		public boolean matches(Field field) {
			return !(Modifier.isStatic(field.getModifiers()) ||
					Modifier.isFinal(field.getModifiers()));
		}
	};

}
