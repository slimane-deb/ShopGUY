package sd.shopguy.Main;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import sd.shopguy.Adapts.PagerAdapter;
import sd.shopguy.Adapts.PersoSpinnerAdapter;
import sd.shopguy.Adapts.ProdListAdapter;
import sd.shopguy.Frags.DetailFragment;
import sd.shopguy.Metier.ClassProduit;
import sd.shopguy.Metier.Command;
import sd.shopguy.Metier.Produit;
import sd.shopguy.Metier.ProduitPanier;
import sd.shopguy.Metier.Promotion;
import sd.shopguy.Service.AlarmService;
import sd.shopguy.Task.AsyncResponse;
import sd.shopguy.Task.GetProduitTask;
import sd.shopguy.Task.GetPromoTask;
import sd.shopguy.Util.UtilService;

public class MainActivity extends AppCompatActivity implements
         AsyncResponse {

    private Spinner spinner_nav;

    public static int spinnerIndex = -1;
    //public static int tabIndex = -1;

    private PagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    //-----------------------------------------
    public static Boolean login = false;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    static Boolean isTouched = false;
    ListView listView;
    List<Produit> mainProds ;
    public static ArrayList<ProduitPanier> produitPaniers = new ArrayList<>() ;
    static ArrayList<Command> commands = new ArrayList<>() ;
    List<Promotion> promotionsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        produitPaniers.clear();
        login =false ;
        CommandActivity.nbCmd =0 ;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //ProgressDialog pd = ProgressDialog.show(this,"Please Wait..","Loading...",true);

        initTabs();
        addItemsToSpinner();
        initDrawer(toolbar);


    }


    public static void addProdPanier(Context context,Produit product) {
        //ProduitPanier pp = new ProduitPanier(product,product.getQuantite());
        boolean q = MainActivity.produitPaniers.add(new ProduitPanier(product,1));
        if (q){
            Toast.makeText(context, "Produit ajouté au panier", Toast.LENGTH_SHORT).show();
        }else{
            //Toast.makeText(context, "Produit existant, quantité incrémentée "+q, Toast.LENGTH_SHORT).show();
        }
    }
    public static ArrayList<ProduitPanier> getPanier(){
        return produitPaniers ;
    }



    //**************************************************/
    //rechercher
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        //searchView.setOnQueryTextListener(MainActivity.class);
        return super.onCreateOptionsMenu(menu);
    }
    //---------------------------------- UI ---------------------------
    public void initDrawer(Toolbar toolbar) {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.naview);


        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        // mFragmentTransaction.replace(R.id.containerViewLayout, new TabFragment()).commit();

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                if (menuItem.getItemId() == R.id.nav_item_panier) {
                    FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

                    //fragmentTransaction.replace(R.id.containerViewLayout, new TabFragment()).commit();
                    Intent intent = new Intent(getApplicationContext(), PanierActivity.class);
                    startActivity(intent);
                }

                if (menuItem.getItemId() == R.id.nav_item_cmd) {
                    FragmentTransaction xfragmentTransaction = mFragmentManager.beginTransaction();
                    Intent intent = new Intent(getApplicationContext(), CommandActivity.class);
                    startActivity(intent);
                    //xfragmentTransaction.replace(R.id.containerViewLayout, new TabFragment()).commit();
                }
                if (menuItem.getItemId() == R.id.nav_item_settings) {
                    Intent intent = new Intent(getApplicationContext(), ParamActivity.class);
                    startActivity(intent);

                }
                if (menuItem.getItemId() == R.id.notifswitch) {


                }

                return false;
            }

        });

        //View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.switch_layout, null);
        if(MainActivity.login) {
/*            MenuItem menuItem = mNavigationView.getMenu().getItem(2);
            menuItem.setVisible(true);
            SpannableString s = new SpannableString(menuItem.getTitle());
            s.setSpan(new ForegroundColorSpan(Color.CYAN), 0, s.length(), 0);
            s.setSpan(new AbsoluteSizeSpan(20, true), 0, s.length(), 0);
            menuItem.setTitle(s);*/
        }else {
            //if (menuItem.getItemId() == R.id.nav_item_con)
            mNavigationView.getMenu().getItem(2).setVisible(false);
        }

        SwitchCompat mySwitch = (SwitchCompat) mNavigationView.getMenu().getItem(4).getActionView()
                .findViewById(R.id.switchMe);//(SwitchCompat) view.findViewById(R.id.switchMe);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isTouched = false;
                    new AlarmService().getNotifs(getApplicationContext(),5);

                    Toast.makeText(getApplicationContext(), "Notifs activées !!",
                            Toast.LENGTH_LONG).show();

                } else {
                    new AlarmService().cancelNotifs(getApplicationContext());
                    Toast.makeText(getApplicationContext(), "Notifs désactivées !!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        mySwitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                isTouched = true;
                return false;
            }
        });
    }




//*************************************************************************/


   /* public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable("d", mainProds);
        tabIndex = viewPager.getCurrentItem();
        outState.putInt("tabIndex", tabIndex);
        outState.putInt("spinnerIndex", spinnerIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mainProds = (ArrayList<Produit>) savedInstanceState.getSerializable("d");
            tabIndex = savedInstanceState.getInt("tabIndex");
            spinnerIndex = savedInstanceState.getInt("spinnerIndex");
            listView = (ListView) findViewById(R.id.listView);
            pagerAdapter = new PagerAdapter(getSupportFragmentManager(), GetProduitTask.separProdByClass(0));
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

            viewPager.setAdapter(pagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
            if (tabIndex > -1) {
                tabLayout.getTabAt(tabIndex).select();

            }

        }
        //listView.onRestoreInstanceState(savedInstanceState);
    }*/

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //mNavigationView.getMenu().getItem(2).setVisible(true);
        if(login) {
            MenuItem menuItem = mNavigationView.getMenu().getItem(2);
            menuItem.setVisible(true);
            SpannableString s = new SpannableString(menuItem.getTitle());
            s.setSpan(new ForegroundColorSpan(Color.CYAN), 0, s.length(), 0);
            s.setSpan(new AbsoluteSizeSpan(18, true), 0, s.length(), 0);
            menuItem.setTitle(s);
        }
    }

    public void initTabs() {
//        AppCompatActivity activity = (AppCompatActivity) ;

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new PagerAdapter(
                getSupportFragmentManager() ) ;
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        //tabLayout.setVisibility(View.VISIBLE);
        tabLayout.setupWithViewPager(viewPager);



    }
    public void addItemsToSpinner() {

        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < ClassProduit.values().length; i++)
            list.add(ClassProduit.values()[i].toString());

        spinner_nav = (Spinner) findViewById(R.id.spinner_nav);
        PersoSpinnerAdapter spinAdapter = new PersoSpinnerAdapter(this, list);

        spinner_nav.setAdapter(spinAdapter);
        if (spinnerIndex > -1) spinner_nav.setSelection(spinnerIndex);

        spinner_nav.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {

                // String item = adapter.getItemAtPosition(position).toString();
                //Toast.makeText(getApplicationContext(), "Catégorie  : " + item + " sélectionnée",
                //      Toast.LENGTH_LONG).show();

                spinnerIndex = position;
                pagerAdapter.separByCategory(position);
                pagerAdapter.notifyDataSetChanged();

            }


            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    public  ArrayList<Produit> separProdByClass(List<Produit> mainProds ,int pos) {

        ArrayList<Produit> classP = new ArrayList<Produit>();
        for (int u = 0; u < mainProds.size(); u++) {

            if (mainProds.get(u).getClassProduit() == (ClassProduit.values()[pos])) {
                classP.add(mainProds.get(u));
            }

        }
        return classP;


    }



    public void showView (Produit p) {
        //isLandView
        View v  = findViewById(R.id.frameLayout);
        if (v!=null) {
// si le layout d'affichage (ContainerView) de detail existe

            DetailFragment detailFragment = new DetailFragment();
            Bundle bundle = new Bundle();
            bundle.putSerializable("produit", p);
            detailFragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frameLayout, detailFragment);

            ft.commit();

        } else {
            Intent intent = new Intent(this, DetailActivity.class);
            intent.putExtra("produit", p);
            startActivity(intent);
        }
    }

    @Override
    public void processFinish(ArrayList<Produit> produits) {

        int i  = produits.size() ;
        int k ;

    }
    public String getScreenDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        String density ="";
        switch(metrics.densityDpi){
            case DisplayMetrics.DENSITY_LOW:
                density="ldpi";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                density= "mdpi";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                density="hdpi";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                density= "xhdpi";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                density= "xxhdpi";
            case DisplayMetrics.DENSITY_XXXHIGH:
                density= "xxxhdpi";
                break;
        }

        return density;

    }

}
