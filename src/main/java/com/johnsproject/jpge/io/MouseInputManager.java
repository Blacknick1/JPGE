package com.johnsproject.jpge.io;

import java.awt.AWTEvent;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.johnsproject.jpge.GameManager;
import com.johnsproject.jpge.Profiler;
import com.johnsproject.jpge.event.EventDispatcher;
import com.johnsproject.jpge.event.UpdateEvent;
import com.johnsproject.jpge.event.UpdateListener;
import com.johnsproject.jpge.event.UpdateEvent.UpdateType;
import com.johnsproject.jpge.graphics.event.CameraListener;
import com.johnsproject.jpge.utils.VectorUtils;

public class MouseInputManager implements UpdateListener {
	
	private static MouseInputManager instance;

	public static MouseInputManager getInstance() {
		if (instance == null) {
			instance = new MouseInputManager();
		}
		return instance;
	}
	
	private JPGEMouseEvent mouseEvent = new JPGEMouseEvent(null);
	private static final int vx = VectorUtils.X, vy = VectorUtils.Y;
	private static final byte LEFT = 0, MIDDLE = 1, RIGHT = 2;
	private List<JPGEMouseListener> mouseListeners = Collections.synchronizedList(new ArrayList<JPGEMouseListener>());
	private Map<Integer, int[]> pressedKeys = Collections.synchronizedMap(new HashMap<Integer, int[]>());

	private int[] cache = new int[2];
	public MouseInputManager() {
		EventDispatcher.getInstance().addUpdateListener(this);
		GameManager.getInstance();
		Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            public void eventDispatched(AWTEvent event) {
                if(event instanceof MouseEvent){
                	synchronized (pressedKeys) {
	                    MouseEvent evt = (MouseEvent)event;
	                    if(evt.getID() == MouseEvent.MOUSE_CLICKED){
	                    	cache[vx] = (int)evt.getX();
	                    	cache[vy] = (int)evt.getY();
	        				pressedKeys.put(evt.getButton()-1, cache);
	                    }
                	}
                }
            }
        }, AWTEvent.MOUSE_EVENT_MASK);
	}

	@Override
	public void update(UpdateEvent event) {
		if (event.getUpdateType() == UpdateType.input) {
			synchronized (mouseListeners) {
				synchronized (pressedKeys) {
					String data = "";
					for (int key : pressedKeys.keySet()) {
						mouseEvent.setPosition(pressedKeys.get(key));
						data += key + ", ";
						for (int i = 0; i < mouseListeners.size(); i++) {
							JPGEMouseListener mouseListener = mouseListeners.get(i);
							if (key == LEFT)
								mouseListener.leftClick(mouseEvent);
							if (key == MIDDLE)
								mouseListener.middleClick(mouseEvent);
							if (key == RIGHT)
								mouseListener.rightClick(mouseEvent);
						}
					}
					for (int i = 0; i < mouseListeners.size(); i++) {
						JPGEMouseListener mouseListener = mouseListeners.get(i);
						cache[vx] = (int) MouseInfo.getPointerInfo().getLocation().getX();
						cache[vy] = (int) MouseInfo.getPointerInfo().getLocation().getY();
						mouseEvent.setPosition(cache);
						mouseListener.positionUpdate(mouseEvent);
					}
					pressedKeys.clear();
					if (data.equals("")) data = "no mouse clicks";
					Profiler.getInstance().getData().setMouseData(data);
				}
			}
		}
	}
	
	public void addMouseListener(JPGEMouseListener listener) {
		synchronized (mouseListeners) {
			mouseListeners.add(listener);
		}
	}
}
