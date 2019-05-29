package com.hanson.android.recipe;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.hanson.android.recipe.Helper.DBHelper;
import com.hanson.android.recipe.Helper.ImageHelper;
import com.hanson.android.recipe.Model.CategoryItem;
import com.hanson.android.recipe.Model.RecipeItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import static com.hanson.android.recipe.R.drawable.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    ImageHelper imageHelper = new ImageHelper();
    ArrayList<RecipeItem> bestList;
    ArrayList<RecipeItem> newList;

    Date today = new Date();

    public HomeFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check search recipes menu on the slide menu
                NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.nav_view);
                navigationView.setCheckedItem(R.id.nav_search);
                //connect to search recipes page
                FragmentManager manager = getActivity().getSupportFragmentManager();
                SearchFragment searchFragment = new SearchFragment();
                manager.beginTransaction().replace(R.id.root_layout, searchFragment, searchFragment.getTag()).addToBackStack(null).commit();
            }
        });
        GridView newGridView = (GridView)view.findViewById(R.id.GridView_New);

        //Connect DB
        DBHelper dbHelper = new DBHelper(getContext(), "Recipes.db", null, 1);

//        int userCount = dbHelper.user_Allcount();
//        if (userCount == 0)
//        {
//            dbHelper.user_Insert("samira","0000");
//        }

        ArrayList<RecipeItem> defaultDataList = dbHelper.recipes_SelectAll();
        if(defaultDataList == null || defaultDataList.size() == 0)
        {
            //Set Bibimap data
            Drawable drawable = getResources().getDrawable(R.drawable.bibimbap, getActivity().getTheme());
            byte[] bibimbap = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("Korea", "Bibimbap", "Alfira", today.toString(),
                    "1. Cincang halus semua bawang putih\n " +
                            "2. Panaskan panci yang berisi air mendidih untuk memblansir sayuran\n" +
                            "3. Untuk tauge, rebus hingga setengah matang kemudian angkat tauge dan campurkan tauge dengan sedikit kecap asin kemudian sisihkan.\n " +
                            "4. air yang mendidih, blansir bayam selama 1-2 menit kemudian angkat dan beri sedikit minyak wijen dan kecap asin.\n" +
                            "5. Potong wortel sebesar korek api dan iris tipis timun jepang.\n" +
                            "6. Taburi wortel dan timun jepang dengan sedikit garam diamkan sekitar 10 menit. " +
                            "Untuk timun jepang, peras perlahan timun jepang untuk mengeluarkan air dari timun Jepang.\n" +
                            "7. Panaskan wajan dengan sedikit minyak goreng. Tumis wortel beberapa menit kemudian angkat lakukan penumisan timun jepang dengan cara yang sama.\n" +
                            "8. Untuk telur, panaskan wajan yang berisi minyak kemudian goreng telur hingga kuning telur setengah matang.\n" +
                            "9. Untuk daging, panaskan wajan kemudian beri sedikit minyak goreng, setelah wajan panas masukkan sedikit bawang putih sampai harum. " +
                            "Setelah harum, masukkan daging yang telah digiling kemudian beri sedikit kecap asin, aduk terus hingga daging matang, " +
                            "setelah matang beri sedikit minyak wijen lalu aduk hingga merata kemudian sisihkan.\n" +
                            "10. Siapkan mangkuk kemudian oles dengan sedikit minyak wijen. Setelah dioles dengan minyak wijen, " +
                            "beri seporsi nasi putih dan tata tauge, bayam, wortel, timun jepang dan jangan lupa daging yang telah ditumis. " +
                            "Beri satu sendok gochujang kedalam nasi kemudian tata telur mata sapi yang telah digoreng setengah matang.\n" +
                            "11. Yang terakhir sedikit minyak wijen dan taburkan biji wijen.\n" +
                            "12. Untuk penyajian kamu bisa mencampur/ mengaduk semuanya menjadi satu. Bibimbap siap dihidangkan!"
                    , "Bibimbap biasanya berisi berbagai macam sayuran, daging, dan telur mata sapi yang disajikan di atas mangkuk. " +
                            "Bahan-bahan untuk membuat bibimbap sangat bervariasi. Kamu bisa menggunakan tauge, wortel, bayam, timun jepang, tauge, jamur dan lain-lain.",
                    bibimbap, bibimbap, 0);

            int bibmbapId = dbHelper.recipes_GetIdByName("Bibimbap");
            ArrayList<String> bibmbapIngre = new ArrayList<>();
            bibmbapIngre.add("Semangkuk nasi");
            bibmbapIngre.add("1 butirTelur");
            bibmbapIngre.add("4 siung bawang putih");
            bibmbapIngre.add("100 gr daging ayam/sapi giling");
            bibmbapIngre.add("1 buah wortel berukuran kecil ");
            bibmbapIngre.add("1/2 buah timun jepang");
            bibmbapIngre.add("1 genggam tauge");
            bibmbapIngre.add("25 gram bayam");
            bibmbapIngre.add("2 sdm kecap asin");
            bibmbapIngre.add("1 sdm gochujang");
            bibmbapIngre.add("1sdt minyak wijen");
            bibmbapIngre.add("Garam secukupnya");
            bibmbapIngre.add("Minyak goreng secukupnya");
            bibmbapIngre.add("Biji wijen secukupnya");

            for (int i = 0; i < bibmbapIngre.size(); i++)
            {
                dbHelper.ingredients_Insert(bibmbapId,bibmbapIngre.get(i));
            }

            //Set Bulgogi data
            drawable = getResources().getDrawable(R.drawable.bulgogi, getActivity().getTheme());
            byte[] bulgogi = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("Korea", "Bulgogi", "Alfira", today.toString(),
                    "1.\tRendam daging dengan bumbu marinasi, diamkan min 30 menit dalam kulkas, hingga meresap.\n" +
                            "2.\tTumis bawang putih, bawang bombay hingga wangi. Masukkan daging, tambahkan arak masak & wijen. " +
                            "Aduk rata, tambahkan air. Masak hingga meresap. Cek rasa\n" +
                            "3.\tMasukkan potongan paprika, aduk hingga matang\n" +
                            "4.\tAngkat & hidangkan, taburi wijen secukupnya",
                    "Bulgogi adalah campuran kecap asin dan gula ditambah rempah lain bergantung pada resep dan daerah di Korea. Sebelum dimakan, daun selada digunakan untuk membungkus bulgogi bersama kimchi, bawang putih, atau bumbu penyedap lain.\n" +
                            "\n" +
                            "Di Jepang, makanan yang sejenis disebut Yakiniku. Dibandingkan dengan Yakiniku, bumbu daging untuk bulgogi dibuat lebih manis. Air pada bumbu cukup banyak sehingga daging tidak dipanggang di atas plat besi (teppan), melainkan di atas panci datar.",
                    bulgogi, bulgogi, 4);

            int bulgogiId = dbHelper.recipes_GetIdByName("Bulgogi");
            ArrayList<String> bulgogiIngre = new ArrayList<>();
            bulgogiIngre.add("500 gr daging slice tipis");
            bulgogiIngre.add("1 bh bawang bombay, potong tipis");
            bulgogiIngre.add("3 siung bawang putih, geprek cincang");
            bulgogiIngre.add("1 bh paprika, potong tipis");
            bulgogiIngre.add("1 sdm arak masak (optional)");
            bulgogiIngre.add("1 sdt wijen");
            bulgogiIngre.add("2 sdm saus tiram");
            bulgogiIngre.add("1 sdm saus raja rasa/kecap asin");
            bulgogiIngre.add("2-3 sdm kecap manis");
            bulgogiIngre.add("1 sdm minyak wijen");
            bulgogiIngre.add("secukupnya Lada hitam");
            bulgogiIngre.add("secukupnya Gula, garam");

            for (int i = 0; i < bulgogiIngre.size(); i++)
            {
                dbHelper.ingredients_Insert(bulgogiId,bulgogiIngre.get(i));
            }

            //Set Bolognese data
            drawable = getResources().getDrawable(R.drawable.bolognese, getActivity().getTheme());
            byte[] bolognese = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("Italy", "Bolognese", "Ivfa", today.toString(),
                    "1.\tRebus tomat hingga kulitnya mengelupas. Tunggu dingin kemudian lepaskan kulitnya. Blender halus tomat yang sudah dikuliti.\n" +
                            "2.\tTumis bawang bombay menggunakan olive oil hingga harum, masukkan bawang putih cincang tumis lagi sebentar. Masukkan daging cincang, tumis hingga daging matang berwarna coklat. Boleh kasih air dikit aja biar ngga gosong.\n" +
                            "3.\tMasukkan jus tomat, saus tomat, bay leaves, oregano, parsley flakes, dan italian herbs.\n" +"" +
                            "4.\tMasak dengan api kecil hingga airnya berkurang banyak dan saus mengental, sampai warnanya merah tua.\n" +
                            "5.\tTambahkan gula, garam, merica.\n" +
                            "6.\tMasak spaghetti sesuai petunjuk, sisihkan dan beri minyak supaya tidak lengket.\n" +
                            "7.\tSajikan spaghetti bersama saus. Beri taburan keju. Kejunya boleh apa aja..cheddar, parmesan, mozarella. Tabur lg sama parsley flakes kalau ada.",
                    "Spaghetti Bolognese adalah salah satu varian spaghetti yang mendunia hingga saat ini.",
                    bolognese, bolognese, 6);

            int bologneseId = dbHelper.recipes_GetIdByName("Bolognese");
            ArrayList<String> bologneseIngre = new ArrayList<>();
            bologneseIngre.add("500 gr tomat merah");
            bologneseIngre.add("200-250 gr daging cincang");
            bologneseIngre.add("1 btr bawang bombay kecil, potong kotak kecil");
            bologneseIngre.add("1 siung bawang putih, cincang halus");
            bologneseIngre.add("1 sdt oregano bubuk");
            bologneseIngre.add("2 lembar bay leaves yg dikeringkan");
            bologneseIngre.add("8 sdm saus tomat Del Monte");
            bologneseIngre.add("1/2 sdt parsley flakes ");
            bologneseIngre.add("t1 sdt italian herbs");
            bologneseIngre.add("2 sdm olive oil");
            bologneseIngre.add("Merica");
            bologneseIngre.add("Gula");
            bologneseIngre.add("Garam");

            for (int i = 0; i < bologneseIngre.size(); i++)
            {
                dbHelper.ingredients_Insert(bologneseId,bologneseIngre.get(i));
            }

            //Set Chicken Cacciatore data
            drawable = getResources().getDrawable(R.drawable.chickencacciatore, getActivity().getTheme());
            byte[] chickencacciatore = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("Italy", "Chicken Cacciatore ", "Ivfa", today.toString(),
                    "1.\tTumis bawang putih dan bombai hingga harum.\n" +
                            "2.\tMasukkan ayam. Tambahkan tomat, pasta tomat, oregano serta kedua macam paprika.\n" +
                            "Beri kaldu ayam.\n" +
                            "3.\tBeri garam,gula pasir, merica, dan pala bubuk. Aduk\n" +
                            "4.\tMasak hingga ayam matang. Kalau kurang asam boleh tambahkan air perasan jeruk nipis/lemon.\n" +
                            "5.\tSajikan segera. Cocok disantap bersama makaroni, brokoli, wortel, peterseli/seledri",
                    "Makanan tradisional Italy.",
                    chickencacciatore, chickencacciatore, 2);

            int chickencacciatoreId = dbHelper.recipes_GetIdByName("Chicken Cacciatore ");
            ArrayList<String> chickencacciatoreIngre = new ArrayList<>();
            chickencacciatoreIngre.add("1 ekor ayam buras (potong 8)");
            chickencacciatoreIngre.add("2 buah tomat, potong dadu kecil");
            chickencacciatoreIngre.add("3 sdm pasta tomat");
            chickencacciatoreIngre.add("2 siung bawang putih, cincang");
            chickencacciatoreIngre.add("1 buah bawang bombai, cincang");
            chickencacciatoreIngre.add("1 sdt oregano");
            chickencacciatoreIngre.add("½ buah paprika merah, potong dadu 1 cm");
            chickencacciatoreIngre.add("½ buah paprika hijau, potong dadu 1 cm");
            chickencacciatoreIngre.add("garam");
            chickencacciatoreIngre.add("merica");
            chickencacciatoreIngre.add("pala bubuk");

            for (int i = 0; i < chickencacciatoreIngre.size(); i++)
            {
                dbHelper.ingredients_Insert(chickencacciatoreId,chickencacciatoreIngre.get(i));
            }

            //Set abzhorka data
            drawable = getResources().getDrawable(R.drawable.abzhorka, getActivity().getTheme());
            byte[] abzhorka = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("Russia", "Abzhorka", "Samira", today.toString(),
                    "1.\tPotong dadu paha ayam fillet dan potong korek api wortel, cuci bersih, sisihkan\n" +
                            "2.\tCuci beras dan tiriskan.\n" +
                            "3.\tPanaskan margarin, masukan ayam, tumis bersama 1/2 sdm garam, lada dan pala.\n" +
                            "4.\tMasukkan bawang bombay, tumis hingga bawang bombay layu dan agak kecoklatan, tambahkan wortel dan jinten.\n" +
                            "5.\tTambahkan 1 sdm garam dan tumis hingga wortel agak layu.\n" +
                            "6.\tSiapkan beras dalam panci rice cooker, tambahkan kaldu ayam, kemudian tambahkan bawang putih yg masih berkulit dan gula.\n" +
                            "7.\tTambahkan tumisan rempah wortel dan daging ayam. Tutup rice cooer dan tekan cook. Tunggu hingga matang. Kemudian aduk rata.\n" +
                            "8.\tRussian plov siap dinikmati.",
                    "Makanan tradisional Rusia.",
                    abzhorka, abzhorka, 3);

            int abzhorkaId = dbHelper.recipes_GetIdByName("Abzhorka");
            ArrayList<String> abzhorkaIngre = new ArrayList<>();
            abzhorkaIngre.add("400 gr beras");
            abzhorkaIngre.add("350 gr paha ayam fillet");
            abzhorkaIngre.add("200 gr wortel");
            abzhorkaIngre.add("50 gr bawang bombay, potong dadu");
            abzhorkaIngre.add("1/2 sdt pala bubuk");
            abzhorkaIngre.add("1/2 sdt lada bubuk");
            abzhorkaIngre.add("1/4 sdt jinten");
            abzhorkaIngre.add("4 butir bawang putih (jgn dikupas)");
            abzhorkaIngre.add("1 1/2 sdm garam");
            abzhorkaIngre.add("1/4 sdt gula");
            abzhorkaIngre.add("600 ml kaldu ayam");
            abzhorkaIngre.add("1 sdm margarin");


            for (int i = 0; i < abzhorkaIngre.size(); i++)
            {
                dbHelper.ingredients_Insert(abzhorkaId,abzhorkaIngre.get(i));
            }

            //Set beefstroganoff data
            drawable = getResources().getDrawable(R.drawable.beefstroganoff, getActivity().getTheme());
            byte[] beefstroganoff = imageHelper.getByteArrayFromDrawable(drawable);

            dbHelper.recipes_Insert("Russia", "Beef Stroganoff", "Samira", today.toString(),
                    "1.\tCincang kasar bawang bombay dan bawang putih. Iris2 sedang paprika kuning.\n"+
                            "2.\tIni saus tomat, jamur kalengan, dan krim encer yang gw pake.\n" +
                            "3.\tTumis bawang bombay dan bawang putih dengan mentega di atas penggorengan sampai harum, turunkan jamur, aduk, biarkan sampai air (yang jamur keluarkan) habis.\n"+
                            "4.\tMasukkan daging sapi, aduk, tambahkan sedikit air, tunggu beberapa menit.\n" +
                            "5.\tMasukkan jintan, garam dan merica, aduk lagi, tunggu beberapa saat.\n" +"" +
                            "6.\tMasukkan kaldu sapi bubuk, aduk terus.\n"+
                            "7.\tMasukkan sekarang paprika iris dan paprika bubuk, aduk lagi beberapa saat.\n" +
                            "8.\tMasukkan saus tomat, aduk.\n"+
                            "9.\tSekarang giliran krim encer, aduk.\n" +
                            "10.\tTerakhir masukkan mustard, tambahkan sedikit saja air dan tunggu sampai daging empuk di atas api kecil/sedang.\n" +
                            "11.\tHidangkan dengan nasi/pasta.",
                    "Stroganoff daging sapi adalah hidangan yang terdiri dari potongan daging sapi tanpa lemak yang ditumis dan disajikan dalam saus krim asam dengan bawang dan jamur. Legenda mengatakan bahwa ketika ia ditempatkan di Siberia terdalam, koki menemukan bahwa daging sapi itu beku begitu padat sehingga hanya bisa diatasi dengan memotongnya menjadi potongan-potongan yang sangat tipis.",
                    beefstroganoff, beefstroganoff, 8);

            int beefstroganoffId = dbHelper.recipes_GetIdByName("Beef Stroganoff");
            ArrayList<String> beefstroganoffIngre = new ArrayList<>();
            beefstroganoffIngre.add("150 gram Daging Sapi");
            beefstroganoffIngre.add("2 biji Bawang Bombay");
            beefstroganoffIngre.add("2 siung Bawang Putih");
            beefstroganoffIngre.add("2 buah Paprika Kuning");
            beefstroganoffIngre.add("230 gram Jamur Kancing (Champignon, Portabello)");
            beefstroganoffIngre.add("6 sendok makan Saus Tomat");
            beefstroganoffIngre.add("80 ml Krim Encer / sour cream");
            beefstroganoffIngre.add("secukupnya Mentega");
            beefstroganoffIngre.add("secukupnya Jintan");
            beefstroganoffIngre.add("secukupnya Garam");
            beefstroganoffIngre.add("secukupnya Lada (Merica)");
            beefstroganoffIngre.add("1 sendok teh Paprika Bubuk");
            beefstroganoffIngre.add("1 sendok teh Kaldu Sapi");
            beefstroganoffIngre.add("1 sendok teh Mustard (mustar)");

            for (int i = 0; i < beefstroganoffIngre.size(); i++)
            {
                dbHelper.ingredients_Insert(beefstroganoffId,beefstroganoffIngre.get(i));
            }
        }

        TextView resultTextView = (TextView) view.findViewById(R.id.txt_DBresult);
        //resultTextView.setText(tempcategory);

        newList = dbHelper.recipes_SelectNew();

        newGridView.setAdapter(new MainRecipeAdapter(this.getContext(), newList, R.layout.fragment_home_recipeitem));

        newGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                RecipeItem selectRecipe = newList.get(position);
                Intent intent = new Intent(getActivity(), RecipeActivity.class);
                intent.putExtra("recipe", selectRecipe.get_recipeName());
                startActivity(intent);
                //Toast.makeText(view.getContext(),selectRecipe.get_recipeName(),Toast.LENGTH_SHORT).show();
            }
        });


        //connect GrieView code to UI
        GridView bestGridView = (GridView)view.findViewById(R.id.GridView_Best);

        bestList = dbHelper.recipes_SelectBest();

        bestGridView.setAdapter(new MainRecipeAdapter(this.getContext(), bestList, R.layout.fragment_home_recipeitem));

        bestGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                RecipeItem selectRecipe = bestList.get(position);
                Intent intent = new Intent(getActivity(), RecipeActivity.class);
                intent.putExtra("recipe", selectRecipe.get_recipeName());
                startActivity(intent);
                //Toast.makeText(view.getContext(),selectRecipe.get_recipName(),Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
