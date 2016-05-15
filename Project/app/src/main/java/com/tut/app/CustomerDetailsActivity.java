package com.tut.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class CustomerDetailsActivity extends Activity {

    private EditText firstName;
    private EditText lastName;
    private EditText age;

    private int CustomerID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        this.firstName = (EditText) this.findViewById(R.id.firstName);
        this.lastName = (EditText) this.findViewById(R.id.lastName);
        this.age = (EditText) this.findViewById(R.id.age);


        // get Customer ID
        this.CustomerID = getIntent().getIntExtra("CustomerID", -1);

        if (this.CustomerID > 0)
        {
            // we have customer ID passed correctly.
            new GetCustomerDetails().execute(new ApiConnector());
        }


    }

    private class GetCustomerDetails extends AsyncTask<ApiConnector,Long,JSONArray>
    {
        @Override
        protected JSONArray doInBackground(ApiConnector... params) {

            // it is executed on Background thread

            return params[0].GetCustomerDetails(CustomerID);
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {

        try
        {
            JSONObject customer = jsonArray.getJSONObject(0);

            firstName.setText(customer.getString("FName"));
            lastName.setText(customer.getString("LName"));
            age.setText("" + customer.getInt("Age"));
        }
        catch (Exception e)
                {
                    e.printStackTrace();
                }

        }


        }
    }


