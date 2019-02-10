package green.yeet.snacshot.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import clarifai2.api.ClarifaiBuilder;
import clarifai2.api.ClarifaiClient;
import clarifai2.api.ClarifaiResponse;
import clarifai2.dto.input.ClarifaiInput;
import clarifai2.dto.model.ConceptModel;
import clarifai2.dto.model.output.ClarifaiOutput;
import clarifai2.dto.prediction.Concept;
import okhttp3.OkHttpClient;

import green.yeet.snacshot.R;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class ItemValidationActivity extends AppCompatActivity {

    ImageView itemImage;
    TextView itemName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_validation);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button cameraBtn = (Button)findViewById(R.id.camera_btn);
        itemImage = (ImageView)findViewById(R.id.item_img);
        itemName = (TextView)findViewById(R.id.Item_Title);

        cameraBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        itemImage.setImageBitmap(bitmap);
        Barcode barcode = null;

        BarcodeDetector detector =
                new BarcodeDetector.Builder(getApplicationContext())
                        .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE | Barcode.UPC_A | Barcode.UPC_E)
                        .build();

        Frame frame = new Frame.Builder().setBitmap(bitmap).build();
        SparseArray<Barcode> barcodes = detector.detect(frame);
        if(!detector.isOperational()){
            Log.e("", "Detector failed to initialize.");
            return;
        }
        Log.e("", Integer.toString(barcodes.size()));
        if(barcodes.size()>0){
            barcode = barcodes.valueAt(0);
            Log.i("", barcode.rawValue);
            itemName.setText(barcode.rawValue);








        }else{
            final ClarifaiClient client = new ClarifaiBuilder("f40dfc5d59b145bdbcfcf04fd1589ed6")
                    .client(new OkHttpClient()) // OPTIONAL. Allows customization of OkHttp by the user
                    .buildSync(); // or use .build() to get a Future<ClarifaiClient>

            final List<ClarifaiOutput<Concept>> ret = new ArrayList<>();
            final RequestQueue queue = Volley.newRequestQueue(this);


            new AsyncTask<Void, Void, ClarifaiResponse<List<ClarifaiOutput<Concept>>>>() {
                @Override protected ClarifaiResponse<List<ClarifaiOutput<Concept>>> doInBackground(Void... params) {
                    // The default Clarifai model that identifies concepts in images
                    final ConceptModel foodModel = client.getDefaultModels().foodModel();

                    // Use this model to predict, with the image that the user just selected as the input
                    return foodModel.predict()
                            .withInputs(ClarifaiInput.forImage(getByteArray(bitmap)))
                            .executeSync();
                }

                @Override protected void onPostExecute(ClarifaiResponse<List<ClarifaiOutput<Concept>>> response) {
                    if (!response.isSuccessful()) {
                        return;
                    }
                    final List<ClarifaiOutput<Concept>> predictions = response.get();
                    if (predictions.isEmpty()) {
                        return;
                    }

                    for(ClarifaiOutput<Concept> concept: predictions){
                        ret.add(concept);
                    }

                    for(ClarifaiOutput<Concept> concept: ret){
                        for(Concept item: concept.data()){
                            Log.i("", item.name());
                        }

                    }
                    String url = String.format("https://api.nal.usda.gov/ndb/search/?format=json&q=%s&sort=n&max=25&offset=0&api_key=ppIHrzWv5bAtL13b3eGGk8sjxvro46UG7dDeBVEH", ret.get(0).data().get(0).name());
                    JsonObjectRequest request = new JsonObjectRequest(url, null,
                            new Response.Listener<JSONObject>(){
                                @Override
                                public void onResponse(JSONObject response) {
                                    if (null != response) {
                                        try{
                                            JSONObject jsonResponse = (JSONObject)response.get("list");
                                            JSONArray items = (JSONArray)jsonResponse.get("item");
                                            JSONObject item = (JSONObject) items.get(0);
                                            String ndbno = (String)item.get("ndbno");

                                            String url2 = String.format("https://api.nal.usda.gov/ndb/V2/reports?ndbno=%s&type=b&format=json&api_key=DEMO_KEY", ndbno);


                                            JsonObjectRequest request1 = new JsonObjectRequest(url2, null,
                                                    new Response.Listener<JSONObject>(){
                                                        @Override
                                                        public void onResponse(JSONObject response) {
                                                            if (null != response) {
                                                                try{
                                                                    JSONArray foods = (JSONArray) response.get("foods");
                                                                    JSONObject food = (JSONObject) foods.get(0);
                                                                    JSONObject food1 = (JSONObject) food.get("food");
                                                                    JSONArray nutrients = (JSONArray) food1.get("nutrients");
                                                                    for(int i = 0; i<nutrients.length(); i++){
                                                                        Log.e("MyApp", nutrients.get(i).toString());
                                                                    }

                                                                }catch (JSONException e){

                                                                }


                                                            }
                                                        }
                                                    }, new Response.ErrorListener() {

                                                @Override
                                                public void onErrorResponse(VolleyError error) {

                                                }
                                            });
                                            queue.add(request1);
                                        }catch (JSONException e){

                                        }



                                    }
                                }
                            }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(request);
                    itemName.setText(ret.get(0).data().get(0).name());

                }





            }.execute();
        }






    }

    public byte[] getByteArray(Bitmap bitmap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos);
        return bos.toByteArray();
    }



}
