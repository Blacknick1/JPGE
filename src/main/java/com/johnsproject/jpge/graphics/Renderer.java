package com.johnsproject.jpge.graphics;

import java.util.List;

import com.johnsproject.jpge.Profiler;
import com.johnsproject.jpge.dto.Animation;
import com.johnsproject.jpge.dto.Camera;
import com.johnsproject.jpge.dto.Face;
import com.johnsproject.jpge.dto.Light;
import com.johnsproject.jpge.dto.Mesh;
import com.johnsproject.jpge.dto.Scene;
import com.johnsproject.jpge.dto.SceneObject;
import com.johnsproject.jpge.dto.Vertex;
import com.johnsproject.jpge.utils.RenderUtils;

/**
 * The Renderer class renders the {@link Scene} assigned to the {@link SceneWindow}.
 * It takes the {@link SceneObject SceneObjects} in the view of all {@link Camera Cameras} in the {@link Scene}, 
 * transforms, projects and draws them.
 *
 * @author John´s Project - John Konrad Ferraz Salomon
 */
public class Renderer {
	
	/**
	 * Tells this scene renderer to render the given {@link Scene}.
	 * 
	 * @param scene {@link Scene} to render.
	 * @param zBuffer depth buffer to use.
	 */
	public void render(Scene scene, int[] zBuffer) {
		// reseting profiler data
		Profiler.getInstance().getData().setMaxFaces(0);
		Profiler.getInstance().getData().setRenderedFaces(0);
		synchronized (scene.getCameras()) {
			for (int i = 0; i < scene.getCameras().size(); i++) {
				// reset z buffer
				resetZBuffer(zBuffer);
				Camera camera = scene.getCameras().get(i);
				for (int j = 0; j < scene.getSceneObjects().size(); j++) {
					SceneObject sceneObject = scene.getSceneObjects().get(j);
					// check if object is active or has changed (no need to render if its the same)
					if (sceneObject.isActive() && (sceneObject.changed() || camera.changed())) {
						render(sceneObject, camera, scene.getLights(), zBuffer);
					}
				}
				camera.changed(false);
			}
		}
		synchronized (scene.getSceneObjects()) {
			for (int i = 0; i < scene.getSceneObjects().size(); i++) {
				SceneObject sceneObject = scene.getSceneObjects().get(i);
				sceneObject.changed(false);
			}
		}
	}
	
	/**
	 * Tells this scene renderer to render the given {@link SceneObject} 
	 * to the given {@link Camera} taking in account the given {@link Light Lights}.
	 * 
	 * @param sceneObject {@link SceneObject} to render.
	 * @param camera {@link Camera} that the {@link SceneObject} will be rendered to.
	 * @param lights {@link Light Lights} influencing the {@link SceneObject}.
	 * @param zBuffer depth buffer to use.
	 */
	void render(SceneObject sceneObject, Camera camera, List<Light> lights, int[] zBuffer) {
		Mesh mesh = sceneObject.getMesh();
		Animation animation = mesh.getCurrentAnimation();
		Shader shader = sceneObject.getShader();
		// reset mesh buffer
		mesh.resetBuffer();
		// animate and shade vertexes
		for (int i = 0; i < mesh.getBufferedVertexes().length; i++) {
			Vertex vertex = mesh.getBufferedVertex(i);
			vertex = RenderUtils.animate(vertex, animation);
			vertex = shader.shadeVertex(vertex, mesh, camera, sceneObject.getTransform(), lights);
		}
		// get profiler values to update
		int maxFaces = Profiler.getInstance().getData().getMaxFaces();
		int rendFaces = Profiler.getInstance().getData().getRenderedFaces();
		// shade faces
		for (int i = 0; i < mesh.getFaces().length; i++) {
			Face face = mesh.getFace(i);
			face = shader.shadeFace(face, mesh, camera, zBuffer, sceneObject.getTransform(), lights);
			maxFaces++;
			if (!face.isCulled()) rendFaces++;
		}
		// update profiler values
		Profiler.getInstance().getData().setMaxFaces(maxFaces);
		Profiler.getInstance().getData().setRenderedFaces(rendFaces);
	}
	
	/**
	 * Resets the depth buffer of this scene renderer.
	 * 
	 * @param zBuffer depth buffer to use.
	 */
	public void resetZBuffer(int[] zBuffer) {
		for (int i = 0; i < zBuffer.length; i++) {
			zBuffer[i] = Integer.MAX_VALUE;
		}
	}
}