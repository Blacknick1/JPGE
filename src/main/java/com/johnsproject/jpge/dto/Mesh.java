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
package com.johnsproject.jpge.dto;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Arrays;

import com.johnsproject.jpge.io.SOMImporter;

/**
 * The Mesh class contains the data of {@link SceneObject} meshes imported by {@link SOMImporter}.
 * 
 * @author John´s Project - John Salomon
 */
public class Mesh implements Externalizable {

	private static final long serialVersionUID = -5344798038852090833L;
	
	/**
	 * Path to default models under the resources folder.
	 * Load them with:
	 * <br>
	 * <br>
	 * <code>
	 * SOMImporter.load(getClass().getResourceAsStream(Mesh.RESOURCES_));
	 * </code>
	 */
	public static final String RESOURCES_CUBE = "/cube.som",
								RESOURCES_CONE = "/cone.som",
								RESOURCES_CYLINDER = "/cylinder.som",
								RESOURCES_SPHERE = "/sphere.som",
								RESOURCES_PLANE = "/plane.som",
								RESOURCES_MONKEY = "/monkey.som",
								RESOURCES_TORUS = "/torus.som",
								RESOURCES_ALL = "/meshes.som";
	
	private Vertex[] vertexes = new Vertex[0];
	private Face[] faces = new Face[0];
	private Material[] materials = new Material[0];
	private Animation[] animations = new Animation[0];
	private int currentAnimation = 0;
	
	public Mesh() {}
	
	/**
	 * Creates a new instance of the Mesh class filled with the given values.
	 * 
	 * @param vertexes an array of {@link Vertex Vertexes}.
	 * @param faces an array of faces.
	 * @param materials an array of {@link Material Materials}.
	 * @param animations an array of {@link Animation Animations}.
	 */
	public Mesh (Vertex[] vertexes, Face[] faces, Material[] materials, Animation[] animations) {
		this.vertexes = vertexes;
		this.faces = faces;
		this.materials = materials;
		this.animations = animations;
		this.currentAnimation = 0;
	}
	
	/**
	 * Returns all vertexes of this mesh.
	 * 
	 * @return all vertexes of this mesh.
	 */
	public Vertex[] getVertexes(){
		return vertexes;
	}
	
	/**
	 * Returns the vertex at the given index.
	 * 
	 * @param index index of vertex.
	 * @return vertex at the given index.
	 */
	public Vertex getVertex(int index){
		return vertexes[index];
	}
	
	/**
	 * Returns all faces of this mesh.
	 * 
	 * @return all faces of this mesh.
	 */
	public Face[] getFaces() {
		return faces;
	}
	
	/**
	 * Returns the faces at the given index.
	 * 
	 * @param index index of polygon.
	 * @return the faces at the given index.
	 */
	public Face getFace(int index) {
		return faces[index];
	}
	
	/**
	 * Returns all materials of this mesh.
	 * 
	 * @return all materials of this mesh.
	 */
	public Material[] getMaterials() {
		return materials;
	}
	
	/**
	 * Returns the materials at the given index.
	 * 
	 * @param index index of material.
	 * @return the material at the given index.
	 */
	public Material getMaterial(int index) {
		return materials[index];
	}
	
	/**
	 * Sets the animation with the given name as current animation of this mesh.
	 * 
	 * @param name name of animation.
	 */
	public void playAnimation(String name) {
		for (int i = 0; i < animations.length; i++) {
			if (animations[i].getName().equals(name))
				currentAnimation = i;
		}
	}
	
	/**
	 * Sets the animation with the given id as current animation of this mesh.
	 * 
	 * @param id id of animation.
	 */
	public void playAnimation(int id) {
		currentAnimation = id;
	}
	
	/**
	 * Returns the current animation of this mesh.
	 * 
	 * @return current animation of this mesh.
	 */
	public Animation getCurrentAnimation() {
		return animations[currentAnimation];
	}
	
	/**
	 * Returns the animation at the given index.
	 * 
	 * @param index index of animation.
	 * @return animation at the given index.
	 */
	public Animation getAnimation(int index) {
		return animations[index];
	}
	
	/**
	 * Returns the animation with the given name.
	 * 
	 * @param name name of animation.
	 * @return animation at the given index.
	 */
	public Animation getAnimation(String name) {
		for (int i = 0; i < animations.length; i++) {
			if (animations[i].getName().equals(name))
				return animations[i];
		}
		return null;
	}
	
	/**
	 * Returns all animations of this mesh.
	 * 
	 * @return all animations of this mesh.
	 */
	public Animation[] getAnimations() {
		return animations;
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		final int vertLength = vertexes.length;
		out.writeInt(vertLength);
		for (int i = 0; i < vertLength; i++) {
			out.writeObject(vertexes[i]);
		}
		final int faceLength = faces.length;
		out.writeInt(faceLength);
		for (int i = 0; i < faceLength; i++) {
			out.writeObject(faces[i]);
		}
		final int matLength = materials.length;
		out.writeInt(matLength);
		for (int i = 0; i < matLength; i++) {
			out.writeObject(materials[i]);
		}
		final int animLength = animations.length;
		out.writeInt(animLength);
		for (int i = 0; i < animLength; i++) {
			out.writeObject(animations[i]);
		}
		out.writeInt(currentAnimation);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		final int vertLength = in.readInt();
		vertexes = new Vertex[vertLength];
		for (int i = 0; i < vertLength; i++) {
			vertexes[i] = (Vertex) in.readObject();
		}
		final int faceLength = in.readInt();
		faces = new Face[faceLength];
		for (int i = 0; i < faceLength; i++) {
			faces[i] = (Face) in.readObject();
		}
		final int matLength = in.readInt();
		materials = new Material[matLength];
		for (int i = 0; i < matLength; i++) {
			materials[i] = (Material) in.readObject();
		}
		final int animLength = in.readInt();
		animations = new Animation[animLength];
		for (int i = 0; i < animLength; i++) {
			animations[i] = (Animation) in.readObject();
		}
		currentAnimation = in.readInt();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(animations);
		result = prime * result + currentAnimation;
		result = prime * result + Arrays.hashCode(faces);
		result = prime * result + Arrays.hashCode(materials);
		result = prime * result + Arrays.hashCode(vertexes);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mesh other = (Mesh) obj;
		if (!Arrays.equals(animations, other.animations))
			return false;
		if (currentAnimation != other.currentAnimation)
			return false;
		if (!Arrays.equals(faces, other.faces))
			return false;
		if (!Arrays.equals(materials, other.materials))
			return false;
		if (!Arrays.equals(vertexes, other.vertexes))
			return false;
		return true;
	}	
}
