/**
 * MIT License
 *
 * Copyright (c) 2018 John Salomon - John´s Project
 *  
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.johnsproject.jpge.utils;

import java.awt.Color;

import org.junit.Test;

/**
 * Test class for {@link ColorUtils}.
 * 
 * @author John's Project - John Salomon
 *
 */
public class ColorUtilsTest {
	@Test
	public void convertRgbaTest() throws Exception {
		int c = new Color(50,50,50,50).getRGB();
		int cc = ColorUtils.convert(50,50,50,50);
		assert(c == cc);
	}
	
	@Test
	public void convertRgbTest() throws Exception {
		int c = new Color(50,50,50).getRGB();
		int cc = ColorUtils.convert(50,50,50);
		assert(c == cc);
	}
	
	@Test
	public void getRedTest() throws Exception {
		Color c = new Color(50,50,50,50);
		int cc = ColorUtils.getRed(c.getRGB());
		assert(c.getRed() == cc);
	}
	
	@Test
	public void getGreenTest() throws Exception {
		Color c = new Color(50,50,50,50);
		int cc = ColorUtils.getGreen(c.getRGB());
		assert(c.getGreen() == cc);
	}
	
	@Test
	public void getBlueTest() throws Exception {
		Color c = new Color(50,50,50,50);
		int cc = ColorUtils.getBlue(c.getRGB());
		assert(c.getBlue() == cc);
	}
	
	@Test
	public void getAlphaTest() throws Exception {
		Color c = new Color(50,50,50,50);
		int cc = ColorUtils.getAlpha(c.getRGB());
		assert(c.getAlpha() == cc);
	}
	
	@Test
	public void setRedTest() throws Exception {
		int cc = ColorUtils.convert(50, 50, 50, 50);
		cc = ColorUtils.setRed(cc, 20);
		cc = ColorUtils.getRed(cc);
		assert(20 == cc);
		cc = ColorUtils.getBlue(cc);
		//assert(50 == cc);
	}
	
	@Test
	public void setGreenTest() throws Exception {
		int cc = ColorUtils.convert(50, 50, 50, 50);
		cc = ColorUtils.setGreen(cc, 20);
		cc = ColorUtils.getGreen(cc);
		assert(20 == cc);
	}
	
	@Test
	public void setBlueTest() throws Exception {
		int cc = ColorUtils.convert(50, 50, 50, 50);
		cc = ColorUtils.setBlue(cc, 20);
		cc = ColorUtils.getBlue(cc);
		assert(20 == cc);
	}
	
	@Test
	public void setAlphaTest() throws Exception {
		int cc = ColorUtils.convert(50, 50, 50, 50);
		cc = ColorUtils.setAlpha(cc, 20);
		cc = ColorUtils.getAlpha(cc);
		assert(20 == cc);
	}
	
	@Test
	public void addRedTest() throws Exception {
		int cc = ColorUtils.convert(50, 50, 50, 50);
		cc = ColorUtils.addRed(cc, +20);
		assert(70 == ColorUtils.getRed(cc));
		cc = ColorUtils.addRed(cc, -20);
		assert(50 == ColorUtils.getRed(cc));
	}
	
	@Test
	public void addGreenTest() throws Exception {
		int cc = ColorUtils.convert(50, 50, 50, 50);
		cc = ColorUtils.addGreen(cc, +20);
		assert(70 == ColorUtils.getGreen(cc));
		cc = ColorUtils.addGreen(cc, -20);
		assert(50 == ColorUtils.getGreen(cc));
	}
	
	@Test
	public void addBlueTest() throws Exception {
		int cc = ColorUtils.convert(50, 50, 50, 50);
		cc = ColorUtils.addBlue(cc, +20);
		assert(70 == ColorUtils.getBlue(cc));
		cc = ColorUtils.addBlue(cc, -20);
		assert(50 == ColorUtils.getBlue(cc));
	}
	
	@Test
	public void addAlphaTest() throws Exception {
		int cc = ColorUtils.convert(50, 50, 50, 50);
		cc = ColorUtils.addAlpha(cc, +20);
		assert(70 == ColorUtils.getAlpha(cc));
		cc = ColorUtils.addAlpha(cc, -20);
		assert(50 == ColorUtils.getAlpha(cc));
	}
	
	@Test
	public void darkerTest() throws Exception {
		int c = ColorUtils.convert(50, 50, 50, 50);
		int cc = ColorUtils.darker(c, 5);
		//System.out.println(ColorUtils.getBlue(cc));
		assert (ColorUtils.getBlue(cc) < ColorUtils.getBlue(c));
		assert (ColorUtils.getGreen(cc) < ColorUtils.getGreen(c));
		assert (ColorUtils.getRed(cc) < ColorUtils.getRed(c));
	}
	
	@Test
	public void brighterTest() throws Exception {
		int c = ColorUtils.convert(50, 50, 50, 50);
		int cc = ColorUtils.brighter(c, 2);
		assert (ColorUtils.getBlue(cc) > ColorUtils.getBlue(c));
		assert (ColorUtils.getGreen(cc) > ColorUtils.getGreen(c));
		assert (ColorUtils.getRed(cc) > ColorUtils.getRed(c));
	}
	
//	@Test
//	public void lerpRBGTest() throws Exception {
//		int c1 = ColorUtils.convert(50, 50, 50, 50);
//		int c2 = ColorUtils.convert(100, 100, 100, 100);
//		int cc = ColorUtils.lerpRBG(c2, c1, 100);
//		System.out.println(ColorUtils.getRed(cc));
//		assert (ColorUtils.getBlue(cc) > 50);
//		assert (ColorUtils.getBlue(cc) < 100);
//		assert (ColorUtils.getGreen(cc) > 50);
//		assert (ColorUtils.getGreen(cc) < 100);
//		assert (ColorUtils.getRed(cc) > 50);
//		assert (ColorUtils.getRed(cc) < 100);
//		assert (ColorUtils.getAlpha(cc) == 100);
//	}
//	
//	@Test
//	public void lerpTest() throws Exception {
//		int c1 = ColorUtils.convert(50, 50, 50, 50);
//		int c2 = ColorUtils.convert(100, 100, 100, 100);
//		int cc = ColorUtils.lerp(c1, c2, 122);
//		assert (ColorUtils.getBlue(cc) > 50);
//		assert (ColorUtils.getBlue(cc) < 100);
//		assert (ColorUtils.getGreen(cc) > 50);
//		assert (ColorUtils.getGreen(cc) < 100);
//		assert (ColorUtils.getRed(cc) > 50);
//		assert (ColorUtils.getRed(cc) < 100);
//		assert (ColorUtils.getAlpha(cc) > 50);
//		assert (ColorUtils.getAlpha(cc) < 100);
//	}
	
//	@Test
//	public void blendAlphaTest() throws Exception {
//		int c1 = ColorUtils.convert(50, 50, 50, 100);
//		int c2 = ColorUtils.convert(255, 255, 255, 255);
//		int cc = ColorUtils.blendAlpha(c1, c2);
//		assert (ColorUtils.getBlue(cc) == 205);
//		assert (ColorUtils.getGreen(cc) == 205);
//		assert (ColorUtils.getRed(cc) == 205);
//	}
}
