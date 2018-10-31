package com.johnsproject.jpge.graphics;

import com.johnsproject.jpge.dto.Animation;
import com.johnsproject.jpge.dto.Scene;
import com.johnsproject.jpge.dto.SceneObject;

/**
 * The Animator class updates the frame of the current {@link Animation} 
 * of each {@link SceneObject} in a {@link Scene}.
 *
 * @author John´s Project - John Konrad Ferraz Salomon
 */
public class Animator {
	
	/**
	 * Updates the frame of the current {@link Animation} of each {@link SceneObject} in a {@link Scene}.
	 * 
	 * @param scene {@link Scene} to update.
	 */
	public void animate(Scene scene) {
		for (SceneObject sceneObject : scene.getSceneObjects()) {
			Animation animation = sceneObject.getMesh().getCurrentAnimation();
			animation.setCurrentFrame(animation.getCurrentFrame());
			if (animation.getCurrentFrame() >= animation.getFramesCount()) {
				animation.setCurrentFrame(0);
			}
		}
	}
	
}
