import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;
	
public class PrimitiveVsObjectTest {
	
	private static final Collection<String> PRIMITIVE_TYPES = 
			new HashSet<>(Arrays.asList("byte", "short", "int", "long", "float", "double", "boolean", "char"));
	
	private static boolean isPrimitive(Type type) {
		return PRIMITIVE_TYPES.contains(type.getTypeName());
	}

	public int i1 = 34;
	public Integer i2 = 34;

	@Test
	public void primitive_type() throws NoSuchFieldException, SecurityException {
		Field i1Field = PrimitiveVsObjectTest.class.getField("i1");
		Type genericType1 = i1Field.getGenericType();
		assertEquals("int", genericType1.getTypeName());
		assertNotEquals("java.lang.Integer", genericType1.getTypeName());
		assertTrue(isPrimitive(genericType1));
	}

	@Test
	public void object_type() throws NoSuchFieldException, SecurityException {
		Field i2Field = PrimitiveVsObjectTest.class.getField("i2");
		Type genericType2 = i2Field.getGenericType();
		assertEquals("java.lang.Integer", genericType2.getTypeName());
		assertNotEquals("int", genericType2.getTypeName());
		assertFalse(isPrimitive(genericType2));
	}
}
