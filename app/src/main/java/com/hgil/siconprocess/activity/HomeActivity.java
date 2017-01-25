package com.hgil.siconprocess.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.database.tables.CrateCollectionView;
import com.hgil.siconprocess.database.tables.CrateOpeningTable;
import com.hgil.siconprocess.database.tables.CreditOpeningTable;
import com.hgil.siconprocess.database.tables.CustomerRouteMappingView;
import com.hgil.siconprocess.database.tables.DemandTargetTable;
import com.hgil.siconprocess.database.tables.DepotEmployeeView;
import com.hgil.siconprocess.database.tables.DepotInvoiceView;
import com.hgil.siconprocess.database.tables.FixedSampleTable;
import com.hgil.siconprocess.database.tables.PriceGroupView;
import com.hgil.siconprocess.database.tables.RejectionTargetTable;
import com.hgil.siconprocess.database.tables.RouteView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends NavBaseActivity {

    @BindView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initialiseDBObj();
        StringBuffer sb = new StringBuffer();
        sb.append("\nRouteView : " + dbRouteView.numberOfRows());
        sb.append("\nRouteMapView : " + dbRouteMapView.numberOfRows());
        sb.append("\nPriceGroup : " + dbPriceGroup.numberOfRows());
        sb.append("\nCreditOpening : " + dbCreditOpening.numberOfRows());
        sb.append("\nCrateOpening : " + dbCrateOpening.numberOfRows());
        sb.append("\nCrateCollection : " + dbCrateCollection.numberOfRows());
        sb.append("\nInvoice : " + dbInvoice.numberOfRows());
        sb.append("\nDemandTarget : " + dbDemandTarget.numberOfRows());
        sb.append("\nFixedSample : " + dbFixedSample.numberOfRows());
        sb.append("\nRejectionTarget : " + dbRejectionTarget.numberOfRows());
        sb.append("\nEmployee : " + dbEmployee.numberOfRows());

        ButterKnife.bind(this);
        tv.setText(sb);
    }

    private RouteView dbRouteView;
    private CustomerRouteMappingView dbRouteMapView;
    private PriceGroupView dbPriceGroup;
    private CreditOpeningTable dbCreditOpening;
    private CrateOpeningTable dbCrateOpening;
    private CrateCollectionView dbCrateCollection;
    private DepotInvoiceView dbInvoice;
    private DemandTargetTable dbDemandTarget;
    private FixedSampleTable dbFixedSample;
    private RejectionTargetTable dbRejectionTarget;
    private DepotEmployeeView dbEmployee;

    private void initialiseDBObj() {
        dbRouteView = new RouteView(this);
        dbRouteMapView = new CustomerRouteMappingView(this);
        dbPriceGroup = new PriceGroupView(this);
        dbCreditOpening = new CreditOpeningTable(this);
        dbCrateOpening = new CrateOpeningTable(this);
        dbCrateCollection = new CrateCollectionView(this);
        dbInvoice = new DepotInvoiceView(this);
        dbDemandTarget = new DemandTargetTable(this);
        dbFixedSample = new FixedSampleTable(this);
        dbRejectionTarget = new RejectionTargetTable(this);
        dbEmployee = new DepotEmployeeView(this);
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
