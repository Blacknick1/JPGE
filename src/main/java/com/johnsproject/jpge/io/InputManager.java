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
package com.johnsproject.jpge.io;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import java.util.ArrayList;
import java.util.List;

/**
 * The InputManager class provides realtime mouse informations. The Engine
 * should be used to access this class.
 * 
 * @author John´s Project - John Salomon
 */
public class InputManager {

	private List<JPGEKeyListener> keyListeners = new ArrayList<JPGEKeyListener>();
	private KeyEvent[] keyEvents = new KeyEvent[8];
	private List<JPGEMouseListener> mouseListeners = new ArrayList<JPGEMouseListener>();
	private MouseEvent[] mouseEvents = new MouseEvent[4];
	private List<MouseMotionListener> motionListeners = new ArrayList<MouseMotionListener>();
	private List<MouseWheelListener> wheelListeners = new ArrayList<MouseWheelListener>();
	private Point mouseLocation = new Point();
	private Point mouseLocationOnScreen = new Point();

	public InputManager() {
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
			public void eventDispatched(AWTEvent event) {
				if (event instanceof KeyEvent) {
					KeyEvent e = (KeyEvent) event;
					handleKeyEvent(e);
				}
				if (event instanceof MouseEvent) {
					MouseEvent e = (MouseEvent) event;
					mouseLocation = e.getPoint();
					mouseLocationOnScreen = e.getLocationOnScreen();
					handleMouseEvent(e);
					handleMouseMotionEvent(e);
				}
				if (event instanceof MouseWheelEvent) {
					MouseWheelEvent e = (MouseWheelEvent) event;
					handleMouseWheelEvent(e);
				}
			}
		}, AWTEvent.KEY_EVENT_MASK + AWTEvent.MOUSE_EVENT_MASK + AWTEvent.MOUSE_MOTION_EVENT_MASK
				+ AWTEvent.MOUSE_WHEEL_EVENT_MASK);
	}

	private void handleKeyEvent(KeyEvent e) {
		switch (e.getID()) {
		case KeyEvent.KEY_PRESSED:
			for (int i = 0; i < keyListeners.size(); i++) {
				keyListeners.get(i).keyPressed(e);
			}
			for (int i = 0; i < keyEvents.length; i++) {
				KeyEvent keyEvent = keyEvents[i];
				if ((keyEvent != null) && (keyEvent.getKeyCode() == e.getKeyCode())) {
					return;
				}
			}
			for (int i = 0; i < keyEvents.length; i++) {
				KeyEvent keyEvent = keyEvents[i];
				if (keyEvent == null) {
					keyEvents[i] = e;
					return;
				}
				if (keyEvent.getKeyCode() == e.getKeyCode())
					return;
			}
			break;

		case KeyEvent.KEY_RELEASED:
			for (int i = 0; i < keyListeners.size(); i++) {
				keyListeners.get(i).keyReleased(e);
			}
			for (int i = 0; i < keyEvents.length; i++) {
				KeyEvent keyEvent = keyEvents[i];
				if ((keyEvent != null) && (keyEvent.getKeyCode() == e.getKeyCode())) {
					keyEvents[i] = null;
				}
			}
			break;

		case KeyEvent.KEY_TYPED:
			for (int i = 0; i < keyListeners.size(); i++) {
				keyListeners.get(i).keyTyped(e);
			}
			break;
		}
	}

	private void handleMouseEvent(MouseEvent e) {
		switch (e.getID()) {
		case MouseEvent.MOUSE_PRESSED:
			for (int i = 0; i < mouseListeners.size(); i++) {
				mouseListeners.get(i).mousePressed(e);
			}
			for (int i = 0; i < mouseEvents.length; i++) {
				MouseEvent mouseEvent = mouseEvents[i];
				if (mouseEvent == null) {
					mouseEvents[i] = e;
					return;
				}
				if (mouseEvent.getButton() == e.getButton())
					return;
			}
			break;

		case MouseEvent.MOUSE_RELEASED:
			for (int i = 0; i < mouseListeners.size(); i++) {
				mouseListeners.get(i).mouseReleased(e);
			}
			for (int i = 0; i < mouseEvents.length; i++) {
				MouseEvent mouseEvent = mouseEvents[i];
				if ((mouseEvent != null) && (mouseEvent.getButton() == e.getButton())) {
					mouseEvents[i] = null;
				}
			}
			break;

		case MouseEvent.MOUSE_CLICKED:
			for (int i = 0; i < mouseListeners.size(); i++) {
				mouseListeners.get(i).mouseClicked(e);
			}
			break;

		case MouseEvent.MOUSE_EXITED:
			for (int i = 0; i < mouseListeners.size(); i++) {
				mouseListeners.get(i).mouseExited(e);
			}
			break;

		case MouseEvent.MOUSE_ENTERED:
			for (int i = 0; i < mouseListeners.size(); i++) {
				mouseListeners.get(i).mouseEntered(e);
			}
			break;
		}
	}

	private void handleMouseMotionEvent(MouseEvent e) {
		switch (e.getID()) {
		case MouseEvent.MOUSE_MOVED:
			for (int i = 0; i < motionListeners.size(); i++) {
				motionListeners.get(i).mouseMoved(e);
			}
			break;

		case MouseEvent.MOUSE_DRAGGED:
			for (int i = 0; i < motionListeners.size(); i++) {
				motionListeners.get(i).mouseDragged(e);
			}
			break;
		}
	}

	private void handleMouseWheelEvent(MouseWheelEvent e) {
		for (int i = 0; i < wheelListeners.size(); i++) {
			wheelListeners.get(i).mouseWheelMoved(e);
		}
	}

	/**
	 * This method is called by the Engine to update input. It's not recommended to
	 * call this method, just let the Engine handle everything.
	 */
	public void update() {
		for (int i = 0; i < keyEvents.length; i++) {
			KeyEvent keyEvent = keyEvents[i];
			if (keyEvent != null) {
				for (int j = 0; j < keyListeners.size(); j++) {
					keyListeners.get(j).keyDown(keyEvent);
				}
			}
		}
		for (int i = 0; i < mouseEvents.length; i++) {
			MouseEvent mouseEvent = mouseEvents[i];
			if (mouseEvent != null) {
				mouseEvent = new MouseEvent((Component) mouseEvent.getSource(),
														mouseEvent.getID(),
														mouseEvent.getWhen(),
														mouseEvent.getModifiers(),
														(int) mouseLocation.getX(),
														(int) mouseLocation.getY(),
														mouseEvent.getClickCount(), false);
				for (int j = 0; j < mouseListeners.size(); j++) {
					mouseListeners.get(j).mouseDown(mouseEvent);
				}
			}
		}
	}

	/**
	 * Returns all keys being pressed.
	 * 
	 * @return all keys being pressed.
	 */
	public KeyEvent[] getPressedKeys() {
		return keyEvents;
	}

	/**
	 * Returns all mouse buttons being pressed.
	 * 
	 * @return all mouse buttons being pressed.
	 */
	public MouseEvent[] getPressedMouseButtons() {
		return mouseEvents;
	}

	/**
	 * Returns the location of the mouse relative to the source component.
	 * 
	 * @return location of the mouse relative to the source component.
	 */
	public Point getMouseLocation() {
		return mouseLocation;
	}
	
	/**
	 * Returns the location of the mouse on screen.
	 * 
	 * @return location of the mouse on screen.
	 */
	public Point getMouseLocationOnScreen() {
		return mouseLocationOnScreen;
	}

	/**
	 * Adds the given {@link JPGEKeyListener} to the listeners of this class.
	 * 
	 * @param listener listener to add.
	 */
	public void addKeyListener(JPGEKeyListener listener) {
		keyListeners.add(listener);
	}

	/**
	 * Removes the given {@link JPGEKeyListener} from the listeners of this class.
	 * 
	 * @param listener listener to remove.
	 */
	public void removeKeyListener(JPGEKeyListener listener) {
		keyListeners.remove(listener);
	}

	/**
	 * Adds the given {@link JPGEMouseListener} to the listeners of this class.
	 * 
	 * @param listener listener to add.
	 */
	public void addMouseListener(JPGEMouseListener listener) {
		mouseListeners.add(listener);
	}

	/**
	 * Removes the given {@link JPGEMouseListener} from the listeners of this class.
	 * 
	 * @param listener listener to remove.
	 */
	public void removeMouseListener(JPGEMouseListener listener) {
		mouseListeners.remove(listener);
	}

	/**
	 * Adds the given {@link MouseMotionListener} to the listeners of this class.
	 * 
	 * @param listener listener to add.
	 */
	public void addMouseMotionListener(MouseMotionListener listener) {
		motionListeners.add(listener);
	}

	/**
	 * Removes the given {@link MouseMotionListener} from the listeners of this
	 * class.
	 * 
	 * @param listener listener to remove.
	 */
	public void removeMouseMotionListener(MouseMotionListener listener) {
		motionListeners.remove(listener);
	}

	/**
	 * Adds the given {@link MouseWheelListener} to the listeners of this class.
	 * 
	 * @param listener listener to add.
	 */
	public void addMouseWheelListener(MouseWheelListener listener) {
		wheelListeners.add(listener);
	}

	/**
	 * Removes the given {@link MouseWheelListener} from the listeners of this
	 * class.
	 * 
	 * @param listener listener to remove.
	 */
	public void removeMouseWheelListener(MouseWheelListener listener) {
		wheelListeners.remove(listener);
	}
}
