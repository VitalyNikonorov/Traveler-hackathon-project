package net.nikonorov.bananashake;

import android.os.Bundle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.CardBoardAndroidApplication;
import com.badlogic.gdx.backends.android.CardBoardApplicationListener;
import com.badlogic.gdx.backends.android.CardboardCamera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.UBJsonReader;
import com.google.vrtoolkit.cardboard.Eye;
import com.google.vrtoolkit.cardboard.HeadTransform;
import com.google.vrtoolkit.cardboard.Viewport;

/**
 * Created by vitaly on 25.03.16.
 */
public class ActivityVR extends CardBoardAndroidApplication implements CardBoardApplicationListener {

    private CardboardCamera cam;
    private ModelInstance model;
    private ModelBatch batch;
    private Environment environment;
    private static final float Z_NEAR = 0.1f;
    private static final float Z_FAR = 1000.0f;
    private static final float CAMERA_Z = 0;//.1f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        initialize(this, config);
    }

    @Override
    public void create() {
        cam = new CardboardCamera();
        cam.position.set(0f, 0f, CAMERA_Z);
        cam.lookAt(0f,0f,-1f);
        cam.near = Z_NEAR;
        cam.far = Z_FAR;

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        batch = new ModelBatch();

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));

//        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 10f, 10f, 10f, 10f));
//        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 100f, 100f, 100f, 100f));
//        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, -1f, -1f, -1f, 1f));
//        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
//        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
//        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
//        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
//        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
//        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
//        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));
//        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));


//        environment.add(new DirectionalLight().set(Color.WHITE, -100f, -100f, -100f));
//        environment.add(new DirectionalLight().set(Color.WHITE, -100f, -100f, 100f));
//        environment.add(new DirectionalLight().set(Color.WHITE, -100f, 100f, -100f));
//        environment.add(new DirectionalLight().set(Color.WHITE, -100f, 100f, 100f));
//        environment.add(new DirectionalLight().set(Color.WHITE, 100f, -100f, -100f));
//        environment.add(new DirectionalLight().set(Color.WHITE, 100f, -100f, 100f));
//        environment.add(new DirectionalLight().set(Color.WHITE, 100f, 100f, -100f));
//        environment.add(new DirectionalLight().set(Color.WHITE, 100f, 100f, 100f));


//        environment.add(new DirectionalLight().set(Color.WHITE, -10f, -10f, -10f));
//        environment.add(new DirectionalLight().set(Color.WHITE, -10f, -10f, 10f));
//        environment.add(new DirectionalLight().set(Color.WHITE, -10f, 10f, -10f));
//        environment.add(new DirectionalLight().set(Color.WHITE, -10f, 10f, 10f));
//        environment.add(new DirectionalLight().set(Color.WHITE, 10f, -10f, -10f));
//        environment.add(new DirectionalLight().set(Color.WHITE, 10f, -10f, 10f));
//        environment.add(new DirectionalLight().set(Color.WHITE, 10f, 10f, -10f));
//        environment.add(new DirectionalLight().set(Color.WHITE, 10f, 10f, 10f));

        //environment.add(new DirectionalLight().set(Color.RED, -1f, -1f, 0f));

        //environment.add(new DirectionalLight().set(Color.BLUE, 1f, 1f, 0f));
        //environment.add(new DirectionalLight().set(Color.RED, -1f, 1f, 0f));

//        UBJsonReader reader = new UBJsonReader();
//        G3dModelLoader modelLoader = new G3dModelLoader(reader);
//
//        model = new ModelInstance(modelLoader.loadModel(Gdx.files.internal("mosc/moscow.g3db")));

        ObjLoader loader = new ObjLoader();

        model = new ModelInstance(loader.loadModel(Gdx.files.internal("auckland/moscow.obj")));

        model.transform.scl(0.5f);

        model.transform.translate(0f, 0f, 0f);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void onNewFrame(HeadTransform paramHeadTransform) {
        //model.transform.rotate(0, 1, 0, - Gdx.graphics.getDeltaTime() * 30);
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
    }

    @Override
    public void onDrawEye(Eye eye) {
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        cam.setEyeViewAdjustMatrix(new Matrix4(eye.getEyeView()));

        float[] perspective = eye.getPerspective(Z_NEAR, Z_FAR);
        cam.setEyeProjection(new Matrix4(perspective));
        cam.update();

        batch.begin(cam);

        batch.render(model, environment);

        batch.end();
    }

    @Override
    public void onFinishFrame(Viewport paramViewport) {

    }

    @Override
    public void onRendererShutdown() {

    }

    @Override
    public void onCardboardTrigger() {

    }

}
