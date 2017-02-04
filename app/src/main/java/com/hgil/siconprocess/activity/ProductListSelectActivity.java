package com.hgil.siconprocess.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.adapter.productSelection.ProductSelectAdapter;
import com.hgil.siconprocess.adapter.productSelection.ProductSelectModel;
import com.hgil.siconprocess.database.masterTables.ProductView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductListSelectActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String CUSTOMER_ID = "customer_id";
    private static final String CUSTOMER_NAME = "customer_name";
    private String customer_id, customer_name;

    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.rvProductList)
    RecyclerView rvProductList;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;
    @BindView(R.id.btnSave)
    Button btnSave;

    private ProductSelectAdapter itemListAdapter;
    private ProductView productView;
    private ArrayList<ProductSelectModel> arrProduct;
    private ArrayList<String> alreadyRejected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list_select);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        if (getIntent() != null) {
            customer_id = getIntent().getStringExtra(CUSTOMER_ID);
            customer_name = getIntent().getStringExtra(CUSTOMER_NAME);
            alreadyRejected = getIntent().getStringArrayListExtra("rejected_items");
        }

        ButterKnife.bind(this);

        tvCustomerName.setText(customer_name);

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvProductList.setLayoutManager(linearLayoutManager);

        productView = new ProductView(this);
        arrProduct = productView.getRejectionProductsAvailable(customer_id, alreadyRejected);

        itemListAdapter = new ProductSelectAdapter(this, arrProduct);
        rvProductList.setAdapter(itemListAdapter);

        btnSave.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (arrProduct.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvProductList.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvProductList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSave:
                //TODO
                // do stuff here
                // send only selected product
                Bundle bundle = new Bundle();
                bundle.putSerializable("selected_products", arrProduct);

                Intent resultIntent = new Intent();
                resultIntent.putExtras(bundle);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                break;
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy: activity");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        switch (item.getItemId()) {
            case android.R.id.home:
                finish(); // close this activity and return to preview activity (if there is any)
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
