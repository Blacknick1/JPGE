package com.johnsproject.jpge.utils;

import org.junit.Test;

/**
 * Test class for {@link VectorMathUtils}.
 * 
 * @author John's Project - John Konrad Ferraz Salomon
 *
 */
public class VectorMathUtilsTest {
	
	// tests with 3d vectors
	@Test
	public void addVectorTest3D() throws Exception {
		int x1 = 2, y1 = 2, z1 = 2;
		int x2 = 3, y2 = 3, z2 = 3;
		long vector1 = Vector3Utils.convert(x1, y1, z1);
		long vector2 = Vector3Utils.convert(x2, y2, z2);
		long resultVector = VectorMathUtils.add(vector1, vector2);
		assert(Vector3Utils.getX(resultVector) == x1 + x2);
		assert(Vector3Utils.getY(resultVector) == y1 + y2);
		assert(Vector3Utils.getZ(resultVector) == z1 + z2);
	}
	
	@Test
	public void subtractVectorTest3D() throws Exception {
		int x1 = 2, y1 = 2, z1 = 2;
		int x2 = 3, y2 = 3, z2 = 3;
		long vector1 = Vector3Utils.convert(x1, y1, z1);
		long vector2 = Vector3Utils.convert(x2, y2, z2);
		long resultVector = VectorMathUtils.subtract(vector1, vector2);
		assert(Vector3Utils.getX(resultVector) == x1 - x2);
		assert(Vector3Utils.getY(resultVector) == y1 - y2);
		assert(Vector3Utils.getZ(resultVector) == z1 - z2);
	}
	
	@Test
	public void multiplyVectorTest3D() throws Exception {
		int x1 = 2, y1 = 2, z1 = 2;
		int x2 = 3, y2 = 3, z2 = 3;
		long vector1 = Vector3Utils.convert(x1, y1, z1);
		long vector2 = Vector3Utils.convert(x2, y2, z2);
		long resultVector = VectorMathUtils.multiply(vector1, vector2);
		assert(Vector3Utils.getX(resultVector) == x1 * x2);
		assert(Vector3Utils.getY(resultVector) == y1 * y2);
		assert(Vector3Utils.getZ(resultVector) == z1 * z2);
	}
	
	@Test
	public void divideVectorTest3D() throws Exception {
		int x1 = 2, y1 = 2, z1 = 2;
		int x2 = 3, y2 = 3, z2 = 3;
		long vector1 = Vector3Utils.convert(x1, y1, z1);
		long vector2 = Vector3Utils.convert(x2, y2, z2);
		long resultVector = VectorMathUtils.divide(vector1, vector2);
		assert(Vector3Utils.getX(resultVector) == x1 / x2);
		assert(Vector3Utils.getY(resultVector) == y1 / y2);
		assert(Vector3Utils.getZ(resultVector) == z1 / z2);
	}
	
	@Test
	public void addValueTest3D() throws Exception {
		int x1 = 2, y1 = 2, z1 = 2;
		int value = 5;
		long vector1 = Vector3Utils.convert(x1, y1, z1);
		long resultVector = VectorMathUtils.add(vector1, value);
		assert(Vector3Utils.getX(resultVector) == x1 + value);
		assert(Vector3Utils.getY(resultVector) == y1 + value);
		assert(Vector3Utils.getZ(resultVector) == z1 + value);
	}
	
	@Test
	public void subtractValueTest3D() throws Exception {
		int x1 = 2, y1 = 2, z1 = 2;
		int value = 5;
		long vector1 = Vector3Utils.convert(x1, y1, z1);
		long resultVector = VectorMathUtils.subtract(vector1, value);
		assert(Vector3Utils.getX(resultVector) == x1 - value);
		assert(Vector3Utils.getY(resultVector) == y1 - value);
		assert(Vector3Utils.getZ(resultVector) == z1 - value);
	}
	
	@Test
	public void multiplyValueTest3D() throws Exception {
		int x1 = 2, y1 = 2, z1 = 2;
		int value = 5;
		long vector1 = Vector3Utils.convert(x1, y1, z1);
		long resultVector = VectorMathUtils.multiply(vector1, value);
		assert(Vector3Utils.getX(resultVector) == x1 * value);
		assert(Vector3Utils.getY(resultVector) == y1 * value);
		assert(Vector3Utils.getZ(resultVector) == z1 * value);
	}
	
	@Test
	public void divideValueTest3D() throws Exception {
		int x1 = 2, y1 = 2, z1 = 2;
		int value = 5;
		long vector1 = Vector3Utils.convert(x1, y1, z1);
		long resultVector = VectorMathUtils.divide(vector1, value);
		assert(Vector3Utils.getX(resultVector) == x1 / value);
		assert(Vector3Utils.getY(resultVector) == y1 / value);
		assert(Vector3Utils.getZ(resultVector) == z1 / value);
	}
	
	@Test
	public void maxTest3D() throws Exception {
		long vector1 = Vector3Utils.convert(2, 2, 2);
		long vector2 = Vector3Utils.convert(3, 3, 3);
		assert (VectorMathUtils.max(vector1, vector2) == vector2);
	}
	
	@Test
	public void minTest3D() throws Exception {
		long vector1 = Vector3Utils.convert(2, 2, 2);
		long vector2 = Vector3Utils.convert(3, 3, 3);
		assert (VectorMathUtils.min(vector1, vector2) == vector1);
	}
	
	@Test
	public void movePointByAngleZTest3D() throws Exception {
		int x1 = 100, y1 = 0, z1 = 0;
		int precision = 5;
		long vector = Vector3Utils.convert(x1, y1, z1);
		vector = VectorMathUtils.movePointByAngleZ(vector, 90);
		assert (Vector3Utils.getX(vector) == 0);
		assert (Vector3Utils.getY(vector) <= 100 + precision);
		assert (Vector3Utils.getY(vector) >= 100 - precision);
	}
	
	@Test
	public void movePointByAngleYTest3D() throws Exception {
		int x1 = 100, y1 = 0, z1 = 0;
		int precision = 5;
		long vector = Vector3Utils.convert(x1, y1, z1);
		vector = VectorMathUtils.movePointByAngleY(vector, 90);
		assert (Vector3Utils.getX(vector) == 0);
		assert (Vector3Utils.getZ(vector) <= 100 + precision);
		assert (Vector3Utils.getZ(vector) >= 100 - precision);
	}
	
	@Test
	public void movePointByAngleXTest3D() throws Exception {
		int x1 = 0, y1 = 100, z1 = 0;
		int precision = 5;
		long vector = Vector3Utils.convert(x1, y1, z1);
		vector = VectorMathUtils.movePointByAngleX(vector, 90);
		assert (Vector3Utils.getY(vector) == 0);
		assert (Vector3Utils.getZ(vector) <= 100 + precision);
		assert (Vector3Utils.getZ(vector) >= 100 - precision);
	}
	
	@Test
	public void movePointByScaleTest3D() throws Exception {
		int x1 = 100, y1 = 100, z1 = 100;
		int x2 = 10, y2 = 10, z2 = 10;
		long vector = Vector3Utils.convert(x1, y1, z1);
		long scale = Vector3Utils.convert(x2, y2, z2);
		vector = VectorMathUtils.movePointByScale(vector, scale);
		assert (Vector3Utils.getX(vector) == x1 * x2);
		assert (Vector3Utils.getY(vector) == y1 * y2);
		assert (Vector3Utils.getZ(vector) == z1 * z2);
	}
	
	@Test
	public void getDistanceTest3D() throws Exception {
		int x1 = 100, y1 = 100, z1 = 100;
		int x2 = 10, y2 = 10, z2 = 10;
		long vector1 = Vector3Utils.convert(x1, y1, z1);
		long vector2 = Vector3Utils.convert(x2, y2, z2);
		long dist = VectorMathUtils.getDistance(vector1, vector2);
		assert (Vector3Utils.getX(dist) == x1 - x2);
		assert (Vector3Utils.getY(dist) == y1 - y2);
		assert (Vector3Utils.getZ(dist) == z1 - z2);
	}
	
	// tests with 2d vectors
		@Test
		public void addVectorTest2D() throws Exception {
			int x1 = 2, y1 = 2;
			int x2 = 3, y2 = 3;
			long vector1 = Vector2Utils.convert(x1, y1);
			long vector2 = Vector2Utils.convert(x2, y2);
			long resultVector = VectorMathUtils.add(vector1, vector2);
			assert(Vector2Utils.getX((int)resultVector) == x1 + x2);
			assert(Vector2Utils.getY((int)resultVector) == y1 + y2);
		}
		
		@Test
		public void subtractVectorTest2D() throws Exception {
			int x1 = 2, y1 = 2;
			int x2 = 3, y2 = 3;
			long vector1 = Vector2Utils.convert(x1, y1);
			long vector2 = Vector2Utils.convert(x2, y2);
			long resultVector = VectorMathUtils.subtract(vector1, vector2);
			assert(Vector2Utils.getX((int)resultVector) == x1 - x2);
			assert(Vector2Utils.getY((int)resultVector) == y1 - y2);
		}
		
		@Test
		public void multiplyVectorTest2D() throws Exception {
			int x1 = 2, y1 = 2;
			int x2 = 3, y2 = 3;
			long vector1 = Vector2Utils.convert(x1, y1);
			long vector2 = Vector2Utils.convert(x2, y2);
			long resultVector = VectorMathUtils.multiply(vector1, vector2);
			assert(Vector2Utils.getX((int)resultVector) == x1 * x2);
			assert(Vector2Utils.getY((int)resultVector) == y1 * y2);
		}
		
		@Test
		public void divideVectorTest2D() throws Exception {
			int x1 = 2, y1 = 2;
			int x2 = 3, y2 = 3;
			long vector1 = Vector2Utils.convert(x1, y1);
			long vector2 = Vector2Utils.convert(x2, y2);
			long resultVector = VectorMathUtils.divide(vector1, vector2);
			assert(Vector2Utils.getX((int)resultVector) == x1 / x2);
			assert(Vector2Utils.getY((int)resultVector) == y1 / y2);
		}
		
		@Test
		public void addValueTest2D() throws Exception {
			int x1 = 2, y1 = 2;
			int value = 5;
			long vector1 = Vector2Utils.convert(x1, y1);
			long resultVector = VectorMathUtils.add(vector1, value);
			assert(Vector2Utils.getX((int)resultVector) == x1 + value);
			assert(Vector2Utils.getY((int)resultVector) == y1 + value);
		}
		
		@Test
		public void subtractValueTest2D() throws Exception {
			int x1 = 2, y1 = 2;
			int value = 5;
			long vector1 = Vector2Utils.convert(x1, y1);
			long resultVector = VectorMathUtils.subtract(vector1, value);
			assert(Vector2Utils.getX((int)resultVector) == x1 - value);
			assert(Vector2Utils.getY((int)resultVector) == y1 - value);
		}
		
		@Test
		public void multiplyValueTest2D() throws Exception {
			int x1 = 2, y1 = 2;
			int value = 5;
			long vector1 = Vector2Utils.convert(x1, y1);
			long resultVector = VectorMathUtils.multiply(vector1, value);
			assert(Vector2Utils.getX((int)resultVector) == x1 * value);
			assert(Vector2Utils.getY((int)resultVector) == y1 * value);
		}
		
		@Test
		public void divideValueTest2D() throws Exception {
			int x1 = 2, y1 = 2;
			int value = 5;
			long vector1 = Vector2Utils.convert(x1, y1);
			long resultVector = VectorMathUtils.divide(vector1, value);
			assert(Vector2Utils.getX((int)resultVector) == x1 / value);
			assert(Vector2Utils.getY((int)resultVector) == y1 / value);
		}
		
		@Test
		public void maxTest2D() throws Exception {
			long vector1 = Vector2Utils.convert(2, 2);
			long vector2 = Vector2Utils.convert(3, 3);
			assert (VectorMathUtils.max(vector1, vector2) == vector2);
		}
		
		@Test
		public void minTest2D() throws Exception {
			long vector1 = Vector2Utils.convert(2, 2);
			long vector2 = Vector2Utils.convert(3, 3);
			assert (VectorMathUtils.min(vector1, vector2) == vector1);
		}
		
		@Test
		public void movePointByAngleZTest2D() throws Exception {
			int x1 = 100, y1 = 0;
			int precision = 5;
			long vector = Vector2Utils.convert(x1, y1);
			vector = VectorMathUtils.movePointByAngleZ(vector, 90);
			assert (Vector2Utils.getX((int)vector) == 0);
			assert (Vector2Utils.getY((int)vector) <= 100 + precision);
			assert (Vector2Utils.getY((int)vector) >= 100 - precision);
		}
		
		@Test
		public void movePointByScaleTest2D() throws Exception {
			int x1 = 100, y1 = 100;
			int x2 = 10, y2 = 10;
			long vector = Vector2Utils.convert(x1, y1);
			long scale = Vector2Utils.convert(x2, y2);
			vector = VectorMathUtils.movePointByScale(vector, scale);
			assert (Vector2Utils.getX((int)vector) == x1 * x2);
			assert (Vector2Utils.getY((int)vector) == y1 * y2);
		}
		
		@Test
		public void getDistanceTest2D() throws Exception {
			int x1 = 100, y1 = 100;
			int x2 = 10, y2 = 10;
			long vector1 = Vector2Utils.convert(x1, y1);
			long vector2 = Vector2Utils.convert(x2, y2);
			long dist = VectorMathUtils.getDistance(vector1, vector2);
			assert (Vector2Utils.getX((int)dist) == x1 - x2);
			assert (Vector2Utils.getY((int)dist) == y1 - y2);
		}
}
