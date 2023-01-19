# PROJECT: https://github.com/BennyQBD/3DGameEngine

## COMMENTS: Could extend to other windowing libraries
## FILE: https://github.com/BennyQBD/3DGameEngine/blob/master/src/com/base/engine/rendering/Window.java
```
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

package com.base.engine.rendering;

import com.base.engine.core.Vector2f;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window 
{
	public static void CreateWindow(int width, int height, String title)
	{
		Display.setTitle(title);
		try 
		{
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			Keyboard.create();
			Mouse.create();
		} 
		catch (LWJGLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void Render()
	{
		Display.update();
	}
	
	public static void Dispose()
	{
		Display.destroy();
		Keyboard.destroy();
		Mouse.destroy();
	}
	
	public static boolean IsCloseRequested()
	{
		return Display.isCloseRequested();
	}
	
	public static int GetWidth()
	{
		return Display.getDisplayMode().getWidth();
	}
	
	public static int GetHeight()
	{
		return Display.getDisplayMode().getHeight();
	}
	
	public static String GetTitle()
	{
		return Display.getTitle();
	}

	public Vector2f GetCenter()
	{
		return new Vector2f(GetWidth()/2, GetHeight()/2);
	}
}
```

## COMMENTS: Synthesize OpenGL calls
## FILE: https://github.com/BennyQBD/3DGameEngine/blob/master/src/com/base/engine/rendering/RenderingEngine.java
```
public void Render(GameObject object)
{
    if (GetMainCamera() == null) System.err.println("Error! Main camera not found. This is very very big bug, and game will crash.");
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

    object.RenderAll(m_forwardAmbient, this);

    glEnable(GL_BLEND);
    glBlendFunc(GL_ONE, GL_ONE);
    glDepthMask(false);
    glDepthFunc(GL_EQUAL);

    for(BaseLight light : m_lights)
    {
        m_activeLight = light;
        object.RenderAll(light.GetShader(), this);
    }

    glDepthFunc(GL_LESS);
    glDepthMask(true);
    glDisable(GL_BLEND);
}

```

## COMMENTS: We could define a dedicated concept of shader and synthesize opengl version
## FILE: https://github.com/BennyQBD/3DGameEngine/blob/master/src/com/base/engine/rendering/Shader.java

------------------------------------------------------------------------------------------------------------

# PROJECT: https://github.com/libgdx/libgdx
## FILE: https://github.com/libgdx/libgdx/blob/master/gdx/src/com/badlogic/gdx/Graphics.java
```
public interface Graphics {
	/** Enumeration describing different types of {@link Graphics} implementations.
	 *
	 * @author mzechner */
	enum GraphicsType {
		AndroidGL, LWJGL, WebGL, iOSGL, JGLFW, Mock, LWJGL3
	}

	/** Describe a fullscreen display mode
	 *
	 * @author mzechner */
	class DisplayMode {
		/** the width in physical pixels **/
		public final int width;
		/** the height in physical pixels **/
		public final int height;
		/** the refresh rate in Hertz **/
		public final int refreshRate;
		/** the number of bits per pixel, may exclude alpha **/
		public final int bitsPerPixel;

		protected DisplayMode (int width, int height, int refreshRate, int bitsPerPixel) {
			this.width = width;
			this.height = height;
			this.refreshRate = refreshRate;
			this.bitsPerPixel = bitsPerPixel;
		}

		public String toString () {
			return width + "x" + height + ", bpp: " + bitsPerPixel + ", hz: " + refreshRate;
		}
	}
```

## COMMENTS: I think we can get rid of this interface entirely
## FILE: https://github.com/libgdx/libgdx/blob/master/gdx/src/com/badlogic/gdx/Screen.java

```
/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx;

/**
 * <p>
 * Represents one of many application screens, such as a main menu, a settings menu, the game screen and so on.
 * </p>
 * <p>
 * Note that {@link #dispose()} is not called automatically.
 * </p>
 * @see Game */
public interface Screen {

	/** Called when this screen becomes the current screen for a {@link Game}. */
	public void show ();

	/** Called when the screen should render itself.
	 * @param delta The time in seconds since the last render. */
	public void render (float delta);

	/** @see ApplicationListener#resize(int, int) */
	public void resize (int width, int height);

	/** @see ApplicationListener#pause() */
	public void pause ();

	/** @see ApplicationListener#resume() */
	public void resume ();

	/** Called when this screen is no longer the current screen for a {@link Game}. */
	public void hide ();

	/** Called when this screen should release all resources. */
	public void dispose ();
}
```

------------------------------------------------------------------------------------------------------------
# PROJECT: https://github.com/sriharshachilakapati/SilenceEngine
# COMMENTS: Current supports gwt, lwjgl and android backends. Good opportunity to synthesize cross cutting concerns 

# FILE: https://github.com/sriharshachilakapati/SilenceEngine/blob/development/backend-gwt/src/main/java/com/shc/silenceengine/backend/gwt/GwtDisplayDevice.java
# COMMENTS: The window class for gwt backend. Like other projects, good synthesis possibilities
