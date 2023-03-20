/*
 * Copyright (C) 2014 Benny Bobaganoosh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.base.game;

import com.base.engine.components.*;
import com.base.engine.core.*;
import com.base.engine.rendering.*;

public class TestGame extends Game
{
	public GameObject houseObject;
	public GameObject cameraObject;

	public void Init()
	{
		Mesh mesh = new Mesh("chalet.obj");
		houseObject = new GameObject();
		Material material = new Material(
				new Texture("chalet.jpg"),
				0,
				0,
				new Texture("bricks2_normal.png"),
				new Texture("bricks2_disp.jpg"),
				0.04f,
				-1.0f
		);

		MeshRenderer meshRenderer = new MeshRenderer(mesh, material);
		houseObject.AddComponent(meshRenderer);

		cameraObject = new GameObject();
		cameraObject.AddComponent(new FreeLook(0.25f));
		cameraObject.AddComponent(new FreeMove(5.0f));
		cameraObject.AddComponent(
				new Camera(
					new Matrix4f().InitPerspective(
							(float) Math.toRadians(70.0f),
							(float) Window.GetWidth() / (float) Window.GetHeight(),
							0.01f,
							1000.0f
					)
				)
		);

		cameraObject.GetTransform().SetPos(
				new Vector3f(0,2,1)
		);
		cameraObject.GetTransform().LookAt(
				new Vector3f(0.25f, 0.25f, 0.25f),
				new Vector3f( 0.0f, 0.0f, 1.0f)
		);

		AddObject(houseObject);
		AddObject(cameraObject);
	}

	public void Update(float delta) {

		houseObject.GetTransform().Rotate(
				new Vector3f(0,0,1.0f),
				(float)Math.toRadians(90) * delta
		);

	}
}
