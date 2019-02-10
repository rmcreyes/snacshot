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
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

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
