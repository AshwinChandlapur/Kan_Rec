package vadeworks.vadekitchen;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.florent37.viewtooltip.ViewTooltip;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import vadeworks.vadekitchen.adapter.DatabaseHelper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    static int serverVersion, localVersion;
    ProgressDialog pd;
    DatabaseHelper myDBHelper;





            String [] d1= {"http://files.hungryforever.com/wp-content/uploads/2015/04/Featured-image-masala-dosa-recipe.jpg",
            "ಮಸಾಲ ದೋಸೆ",
            "20 ನಿಮಿಷಗಳು",
            "\n\n*ಉದ್ದಿನ ಬೇಳೆ - 1/4 ಕಪ್\n\n*ಅಕ್ಕಿ - 1 ಕಪ್ \n\n*ರುಚಿಗೆ ತಕ್ಕಷ್ಟು ಉಪ್ಪು\n\n*ಬಟಾಟೆ - 3 \n\n*ಈರುಳ್ಳಿ- 2\n\n *ಬೆಳ್ಳುಳ್ಳಿ- 1 ಸ್ಪೂನ್ (ಕತ್ತರಿಸಲಾಗಿರುವ)\n\n *ಶುಂಠಿ - 1 ಸ್ಪೂನ್ (ಕತ್ತರಿಸಲಾಗಿರುವ) \n\n* ಕೊತ್ತಂಬರಿ ಸೊಪ್ಪು - 1 ಸ್ಪೂನ್ (ಕತ್ತರಿಸಲಾಗಿರುವ)\n\n* ಹಸಿ ಮೆಣಸು - 2 \n\n* ಸಾಸಿವೆ ಕಾಳು - 1 ಸ್ಪೂನ್\n\n*ಟೊಮೆಟೊ - 2 (ಕತ್ತರಿಸಲಾಗಿರುವ)\n\n* ಒಗ್ಗರಣೆಗೆ ಬೇವಿನ ಸೊಪ್ಪು (ಸ್ವಲ್ಪ)",
            "\n\n 1. ದೊಡ್ಡ ಪಾತ್ರೆಯಲ್ಲಿ ಅಕ್ಕಿ ಮತ್ತು ಉದ್ದಿನ ಬೇಳೆಗಳನ್ನು ಸ್ವಲ್ಪ ಕಾಲ ನೆನೆಸಿ, ಒಂದು ರಾತ್ರಿಯ ವರೆಗೆ ಹುದುಗಲು ಇರಿಸಬೇಕು.\n\n2. ಬಟಾಟೆಯನ್ನು ಬೇಯಿಸಿ, ಅದರ ಸಿಪ್ಪೆಯನ್ನು ಸುಲಿದು ಸಣ್ಣ ಪದರಗಳಾಗಿ ಕತ್ತರಿಸಬೇಕು.\n\n3. ಬೆಳ್ಳುಳ್ಳಿ, ಕೊತ್ತೊಂಬರಿ ಸೊಪ್ಪು, ಹಸಿಮೆಣಸು ಹಾಗೂ ಸ್ವಲ್ಪ ಈರುಳ್ಳಿಯನ್ನು ಅರೆದು ಪೇಸ್ಟ್ ಮಾಡಿಕೊಳ್ಳಬೇಕು ಹಾಗೂ ಬಾಣಲೆಯಲ್ಲಿ ಎಣ್ಣಿ ಕಾಯಿಸಿ, ಸಾಸಿವೆ ಕಾಳುಗಳನ್ನು ಹಾಕಿ.\n\n 4. ಸಾಸಿವೆ ಚಟಪಟ ಶಬ್ದ ಆದಾಕ್ಷಣ ಉಳಿದ ಈರುಳ್ಳಿಯನ್ನು ಹಾಕಿ ಕಂದು ಬಣ್ಣ ಬರುವವರೆಗೆ ಹುರಿಯಬೇಕು.\n\n5. ತದನಂತರ ಪಾತ್ರೆಗೆ ಪೇಸ್ಟ್ ಅನ್ನು ಹಾಕಿ ಚೆನ್ನಾಗಿ ಬೆರಸಬೇಕು ಹಾಗೂ ಸ್ವಲ್ಪ ಹೊತ್ತು ಬೇಯಿಸಬೇಕು.\n\n6. ಇನ್ನು ಟೊಮೆಟೊ ಬಟಾಟೆ, ರುಚಿಗೆ ತಕ್ಕಷ್ಟು ಉಪ್ಪು ಹಾಗೂ ಒಗ್ಗರಣೆ ಸೊಪ್ಪನ್ನು ಹಾಕಿ ಸ್ಪಲ್ಪ ಹೊತ್ತು ಬೇಯಿಸಬೇಕು. \n\n7. ಆನಂತರ ಉರಿಯುವ ಒಲೆಯ ಮೇಲೆ ಕಾವಲಿ ಇಟ್ಟು, ಕಾವಲಿಗೆ ಎಣ್ಣೆ ಸವರಿ, ಒಂದು ಸೌಟಿನಷ್ಟು ಹಿಟ್ಟನ್ನು ಹೆಂಚಿನ ಮೇಲೆ ಹಾಕಿ, ದೋಸೆಯ ಸುತ್ತ ಎಣ್ಣೆ ಸುರಿದು ಚೆನ್ನಾಗಿ ಕಾಯಿಸಬೇಕು.\n\n 8 ಇನ್ನು ದೋಸೆಯ ಮೇಲೆ ಮಸಾಲ ಹಾಕಿ, ಹಾಗೂ ಅದರೊಂದಿಗೆ ಅದನ್ನು ನಿಧಾನವಾಗಿ ಸುತ್ತಿದರೆ ಬಿಸಿಬಿಸಿಯಾದ ಮಸಾಲೆ ದೋಸೆ ತಿನ್ನಲು ರೆಡಿ!."};


            String [] d2 = {"https://raw.githubusercontent.com/AshwinChandlapur/ImgLoader/gh-pages/hyderabadi%20biryani%20new-min.jpg",
            "ಹೈದರಾಬಾದಿ ಬಿರಿಯಾನಿ",
            "1 ಗಂಟೆ 10 ನಿಮಿಷಗಳು",
            "\n\n* 500 ಗ್ರಾಂ ಚಿಕನ್\n\n* 800 ಗ್ರಾಂ ಬಾಸುಮತಿ ಅಕ್ಕಿ\n\n* 1/2 ಕಪ್ ಮೊಸರು\n\n* 1 ಚಮಚ ಜೀರಿಗೆ\n\n* 1 ಕಟ್ಟು ಕೊತ್ತಂಬರಿ ಮತ್ತು ಪುದೀನಾ\n\n* 5 ಹಸಿರು ಮೆಣಸಿನಕಾಯಿ\n\n* 1 ಚಮಚ ಕೆಂಪು ಮೆಣಸಿನ ಪುಡಿ\n\n* 1 ಚಮಚ ಅರಿಶಿಣ\n\n* 1/2 ಕಪ್ ಶುಂಠಿ ಬೆಳ್ಳುಳ್ಳಿ ಪೇಸ್ಟ್\n\n* 1 ಚಮಚ ಗರಂ ಮಸಾಲಾ ಪುಡಿ\n\n* 2 ದೊಡ್ಡ ಈರುಳ್ಳಿ\n\n* ಸ್ವಲ್ಪ ಕೇಸರಿ\n\n* 1/4 ಕಪ್ ಹಾಲು\n\n* ಎಣ್ಣೆ, ಉಪ್ಪು",
            "* ನೀರಿನಲ್ಲಿ ಅಕ್ಕಿಯನ್ನು ನೆನೆಸಿ ಒಂದೆಡೆ ಇಡಬೇಕು.\n\n* ಚಿಕನನ್ನು ಚೆನ್ನಾಗಿ ತೊಳೆದಿಟ್ಟುಕೊಳ್ಳಬೇಕು.\n\n* ಈಗ ಉಪ್ಪು, ಕೆಂಪು ಮೆಣಸಿನಕಾಯಿ ಪುಡಿ, ಗರಂ ಮಸಾಲಾ ಪುಡಿ, ಬೆಳ್ಳುಳ್ಳಿ ಶುಂಠಿ ಪೇಸ್ಟ್, ಅರಿಶಿಣ ಮತ್ತು ಮೊಸರನ್ನು ಚೆನ್ನಾಗಿ ಕಲೆಸಬೇಕು.\n\n* ಕೊತ್ತಂಬರಿ, ಪುದೀನಾ ಮತ್ತು ಹಸಿರು ಮೆಣಸಿನಕಾಯಿಯನ್ನು ಪೇಸ್ಟ್ ಮಾಡಿಕೊಂಡು ಮಸಾಲೆ ಮಿಶ್ರಣಕ್ಕೆ ಬೆರೆಸಬೇಕು.\n\n* ಬಾಣಲೆಯಲ್ಲಿ ಎಣ್ಣೆ ಕಾಯಿಸಿ ಅದರಲ್ಲಿ ಈರುಳ್ಳಿಯನ್ನು ಕೆಂಪಗಾಗುವವರೆಗೆ ಹುರಿದುಕೊಂಡು ಚಿಕನ್ ಗೆ ಬೆರೆಸಿ ಒಂದು ಗಂಟೆ ಕಾಲ ಹಾಗೆಯೇ ಬಿಡಬೇಕು. ಮೇಲೆ ಹೇಳಿದ ಮಸಾಲೆ ಮಿಶ್ರಣವನ್ನು ಬೆರೆಸಬೇಕು.\n\n* ಪಾತ್ರೆಯಲ್ಲಿ ನೆನೆಸಿದ ಅಕ್ಕಿಗೆ ಸ್ವಲ್ಪ ಅರಿಶಿಣ, ಸ್ವಲ್ಪ ಗರಂ ಮಸಾಲೆ ಮತ್ತು ಬೇಯುವುದಕ್ಕೆ ಅಗತ್ಯದಷ್ಟು ನೀರು ಹಾಕಿ ಬೇಯಲು ಬಿಡಬೇಕು.\n\n* ಅಕ್ಕಿ ಅರ್ಧ ಬೇಯುವವರೆಗೂ ಇದ್ದು, ನೀರನ್ನು ಬಸಿಯಬೇಕು. ಅನ್ನವನ್ನು ಬೇರೆ ತೆಗೆದಿಟ್ಟುಕೊಳ್ಳಬೇಕು.\n\n* ಅದೇ ಪಾತ್ರೆಯಲ್ಲಿ ಸಣ್ಣ ಉರಿಯಲ್ಲಿ ಸ್ವಲ್ಪ ಚಿಕನ್ ಮಿಶ್ರಣ ಮತ್ತು ಸ್ವಲ್ಪ ಅನ್ನ, ಹೀಗೆ ಒಂದಾದರೊಂದಂತೆ ತುಂಬುತ್ತಾ ಬರಬೇಕು.\n\n* ಹಾಲಿನಲ್ಲಿ ಕೇಸರಿಯನ್ನು 1 ನಿಮಿಷ ನೆನೆಸಿ ಅನ್ನದ ಮೇಲೆ ಹಾಕಬೇಕು.\n\n* ಈಗ ಪಾತ್ರೆಗೆ ಮುಚ್ಚುಳ ಮುಚ್ಚಿ 20 ನಿಮಿಷ ಹಬೆ ಬರುವ ತನಕ ಬೇಯಿಸಿದರೆ ದಮ್ ಬಿರಿಯಾನಿ ತಿನ್ನಲು ರೆಡಿಯಾಗಿರುತ್ತೆ.\n\nಬಿರಿಯಾನಿ ಜೊತೆ ಬೇಯಿಸಿದ ಮೊಟ್ಟೆ ಮತ್ತು ರಾಯತ ಇದ್ದರೆ ರುಚಿ ಇನ್ನೂ ಜೋರು."};


            String [] d3 = {"https://s-media-cache-ak0.pinimg.com/originals/8f/27/73/8f27737bbc6d6bc623cb5321d0547f73.jpg",
            "ಆಲೂ ಪನ್ನೀರ್ ಕೋಫ್ತಾ ","45 ನಿಮಿಷಗಳು",
            "\n\n*ಕೋಫ್ತಾ ತಯಾರಿಸಲು: \n\n*ಪನ್ನೀರ್ - ಇನ್ನೂರು ಗ್ರಾಂ (ಚಿಕ್ಕದಾಗಿ ತುರಿದದ್ದು)\n\n*ಆಲುಗಡ್ಡೆ - ಮೂರು (ಚೆನ್ನಾಗಿ ಬೇಯಿಸಿ, ಸಿಪ್ಪೆ ನಿವಾರಿಸಿದ್ದು)\n\n*ಬಾದಾಮಿಯ ಪುಡಿ: 1 ದೊಡ್ಡಚಮಚ\n\n*ಕಾಳುಮೆಣಸಿನ ಪುಡಿ- ½ ಚಿಕ್ಕಚಮಚ \n\n*ಗರಂ ಮಸಾಲಾ ಪುಡಿ- ½ ಚಿಕ್ಕಚಮಚ \n\n*ಕೊತ್ತಂಬರಿ ಸೊಪ್ಪು- 2 ದೊಡ್ಡಚಮಚ (ದಂಟುಗಳನ್ನು ನಿವಾರಿಸಿ ಎಲೆಗಳನ್ನು ಚಿಕ್ಕದಾಗಿ ಹೆಚ್ಚಿದ್ದು) \n\n*ಮೆಕ್ಕೆಜೋಳದ ಹಿಟ್ಟು (cornflour)- 2 ದೊಡ್ಡಚಮಚ\n\n*ವಿವಿಧ ಒಣಫಲಗಳು- 4 ದೊಡ್ಡಚಮಚ (ಎಲ್ಲವನ್ನೂ ಚಿಕ್ಕ ಚಿಕ್ಕ ತುಂಡುಗಳನ್ನಾಗಿಸಿದ್ದು) \n\n*ಉಪ್ಪು: ರುಚಿಗನುಸಾರ\n\n*ಎಣ್ಣೆ : ಕರಿಯಲು ಅಗತ್ಯವಿದ್ದಷ್ಟು  \n\n\n\nಕರಿಗೆ ಬೇಕಾಗುವ ಸಾಮಾಗ್ರಿ\n\n*ಜೀರಿಗೆ: 1 ದೊಡ್ಡಚಮಚ\n\n*ದಾಲ್ಚಿನ್ನಿ ಎಲೆ- 1 \n\n*ಬೆಳ್ಳುಳ್ಳಿ ಪೇಸ್ಟ್- 1 ದೊಡ್ಡಚಮಚ\n\n*ಟೊಮೇಟೊ ಪ್ಯೂರಿ- 1 ದೊಡ್ಡಚಮಚ\n\n*ಬಾದಾಮಿ ಪುಡಿ - 2 ದೊಡ್ಡಚಮಚ \n\n*ಜೀರಿಗೆ ಪುಡಿ- 1 ದೊಡ್ಡಚಮಚ \n\n*ಚಾಟ್ ಮಸಾಲಾ - 1 ಚಿಕ್ಕಚಮಚ \n\n*ಕೆಂಪು ಮೆಣಸಿನ ಪುಡಿ- ½ ಚಿಕ್ಕಚಮಚ (ಕಾಶ್ಮೀರಿ ಚಿಲ್ಲಿ ಆದರೆ ಒಂದು ಚಿಕ್ಕಚಮಚ)\n\n*ಅರಿಶಿನ ಪುಡಿ - ½ ಚಿಕ್ಕಚಮಚ \n\n*ಗರಂ ಮಸಾಲಾ ಪುಡಿ- ½ ಚಿಕ್ಕಚಮಚ \n\n*ದೊಡ್ಡ ಜೀರಿಗೆ ಪುಡಿ (ಸೌಂಫ್)- ¼ ಚಿಕ್ಕಚಮಚ \n\n*ಉಪ್ಪು ರುಚಿಗನುಸಾರ \n\n*ಕೊತ್ತಂಬರಿ ಸೊಪ್ಪು- 2 ದೊಡ್ಡಚಮಚ (ದಂಟುಗಳನ್ನು ನಿವಾರಿಸಿ ಎಲೆಗಳನ್ನು ಚಿಕ್ಕದಾಗಿ ಹೆಚ್ಚಿದ್ದು) \n\n*ಎಣ್ಣೆ - 2 ದೊಡ್ಡಚಮಚ (ಆಲಿವ್, ಶೇಂಗಾ, ಮೆಕ್ಕೆಜೋಳ ಇತ್ಯಾದಿ. ಪಾಮ್, ಡಾಲ್ಡಾ ಬೇಡ)",
            "ಕೋಫ್ತಾ ಮಾಡುವ ವಿಧಾನ:\n\n\n\n1) ಒಂದು ಒಣಪಾತ್ರೆಯಲ್ಲಿ ಒಣಫಲ ಮತ್ತು ಎಣ್ಣೆಯನ್ನು ಬಿಟ್ಟು ಉಳಿದೆಲ್ಲಾ ಸಾಮಾಗ್ರಿಗಳನ್ನು ಚೆನ್ನಾಗಿ ಮಿಶ್ರಣ ಮಾಡಿ\n\n2) ಬೆಂದಿರುವ ಆಲುಗಡ್ಡೆಯನ್ನು ಮರದ ಚಮಚ ಉಪಯೋಗಿಸಿ ಚೆನ್ನಾಗಿ ಜಜ್ಜಿಕೊಳ್ಳಿ. ಎಲ್ಲೂ ಗಂಟುಗಳಿರದಂತೆ ನೋಡಿಕೊಳ್ಳಿ.\n\n3) ಜಜ್ಜಿದ ಆಲುಗಡ್ಡೆಯನ್ನು ಚಿಕ್ಕ ಚಿಕ್ಕ ಉಂಡೆಗಳಾಗಿ ಮಾಡಿ ಕೈಯಲ್ಲಿಯೇ ಒತ್ತಿ ಚಿಕ್ಕ ರೊಟ್ಟಿಯಾಗಿಸಿ. (ಕೈಗಳಿಗೆ ಕೊಂಚ ಎಣ್ಣೆ ಹಚ್ಚಿಕೊಂಡರೆ ಆಲುಗಡ್ಡೆ ಅಂಟಿಕೊಳ್ಳುವುದಿಲ್ಲ)\n\n4) ಈ ರೊಟ್ಟಿನ ನಡುವೆ ಒಂದು ಚಿಕ್ಕ ಚಮಚದಷ್ಟು ಒಣಫಲಗಳನ್ನು ಹಾಕಿ. \n\n5) ಬದಿಗಳನ್ನು ನಿಧಾನವಾಗಿ ಒತುತ್ತಾ ಒಣಫಲ ಒಳಬರುವಂತೆ ಮುಚ್ಚಿ.\n\n6) ಒಂದು ವೇಳೆ ಎಲ್ಲಾದರೂ ಒಣಫಲ ಹೊರಚಾಚಿದ್ದುದು ಕಂಡುಬಂದರೆ ಕೊಂಚ ಆಲುಗಡ್ಡೆಯನ್ನು ಸೇರಿಸಿ ಮುಚ್ಚಿ. ಒಟ್ಟಾರೆ ಎಲ್ಲೂ ಒಣಫಲ ಕಾಣದಂತಿರಲಿ.\n\n7) ಇದೇ ರೀತಿ ಎಲ್ಲಾ ಉಂಡೆಗಳನ್ನು ತಯಾರು ಮಾಡುವಷ್ಟರಲ್ಲಿ ಕರಿಯುವ ಎಣ್ಣೆಯನ್ನು ಬಿಸಿಯಾಗಿಸಿ. \n\n8) ಬಿಸಿ ಎಣ್ಣೆ ಸಿಡಿಯದಂತೆ ನಿಧಾನವಾಗಿ ಎಲ್ಲಾ ಉಂಡೆಗಳನ್ನು ಹಾಕಿ ಕರಿಯಿರಿ.\n\n9) ಮಧ್ಯಮ ಉರಿಯಲ್ಲಿ ಎಲ್ಲ ಉಂಡೆಗಳು ಬೇಯಲು ಸುಮಾರು ಮೂರರಿಂದ ನಾಲ್ಕು ನಿಮಿಷಗಳು ಬೇಕು. ಎಲ್ಲ ಉಂಡೆಗಳು ಸರಿಸುಮಾರು ಕಂದು ಬಣ್ಣ ಬಂದ ಬಳಿಕ ಎಣ್ಣೆಯಿಂದ ಹೊರತೆಗೆದು ಎಣ್ಣೆ ಬಸಿಯಿರಿ.\n\n10) ಈ ಉಂಡೆಗಳನ್ನು ಟಿಶ್ಯೂ ಪೇಪರ್ ಇರಿಸಿದ ತಟ್ಟೆಯ ಮೇಲಿರಿಸಿ ಒಣಗಲು ಬಿಡಿ.\n\n\n\nಕರಿ ಮಾಡುವ ವಿಧಾನ:\n\n1) ಒಂದು ದಪ್ಪತಳದ ಪಾತ್ರೆಯಲ್ಲಿ ಎಣ್ಣೆ ಬಿಸಿಮಾಡಿ ಜೀರಿಗೆ ದಾಲ್ಚಿನ್ನಿ ಎಲೆ ಹಾಕಿ ಒಂದು ನಿಮಿಷದವರೆಗೆ ಹುರಿಯಿರಿ.\n\n2) ಬಳಿಕ ಒಂದಾದ ಬಳಿಕ ಒಂದರಂತೆ ಶುಂಠಿ ಪೇಸ್ಟ್, ಅರಿಶಿನ ಪುಡಿ, ಕೆಂಪು ಮೆಣಸಿನ ಪುಡಿ, ಜೀರಿಗೆ ಪುಡಿ, ಚಾಟ್ ಮಸಾಲಾ, ಕರಿಮೆಣಸಿನ ಪುಡಿ, ದೊಡ್ಡಜೀರಿಗೆ ಪುಡಿ, ಗರಂ ಮಸಾಲಾ ಪುಡಿ ಹಾಕಿ ಮಧ್ಯಮ ಉರಿಯಲ್ಲಿ ಸುಮಾರು ಮೂರರಿಂದ ನಾಲ್ಕು ನಿಮಿಷ ಹುರಿಯಿರಿ.\n\n3) ಬಳಿಕ ಟೊಮೇಟೊ ಪ್ಯೂರಿ ಹಾಕಿ ಇನ್ನೊಂದು ನಾಲ್ಕು ನಿಮಿಷಗಳ ಕಾಲ ಹುರಿಯಿರಿ. ತಳ ಹಿಡಿಯದಂತೆ ಎಚ್ಚರವಹಿಸಿ.\n\n4) ಬಳಿಕ ಬಾದಾಮಿ ಪುಡಿ, ಉಪ್ಪು ಹಾಕಿ ಇನ್ನೊಂದು ಎರಡು ನಿಮಿಷ ಹುರಿಯಿರಿ.\n\n5) ಈಗ ಕೊಂಚವೇ ನೀರು ಹಾಕಿ ಕದಡಿ. ಬಳಿಕ ಉಂಡೆ ಕಟ್ಟಿದ್ದ ಅಷ್ಟೂ ಕೋಫ್ತಾಗಳನ್ನು ಹಾಕಿ.\n\n6) ಕೋಫ್ತಾ ಒಡೆಯದಂತೆ ಎಚ್ಚರಿಕೆ ವಹಿಸಿ ನಿಧಾನವಾಗಿ ತಿರುವಿ ಕುದಿಬರಲು ಬಿಡಿ.\n\n7) ಕರಿ ಕುದಿಯಲು ಪ್ರಾರಂಭವಾದೊಡನೆ ಉರಿ ನಂದಿಸಿ. ಕರಿಯ ಮೇಲೆ ಹೆಚ್ಚಿದ ಕೊತ್ತಂಬರಿ ಸೊಪ್ಪನ್ನು ಹಾಕಿ ಸಿಂಗರಿಸಿ. ಎಲ್ಲರ ಮನಗೆಲ್ಲಲು ಆಲು ಪನ್ನೀರ್ ಕೋಫ್ತಾ ಗಸಿ ತಯಾರಾಗಿದೆ. ಇದು ಎಲ್ಲಾ ರೀತಿಯ ರೊಟ್ಟಿಗಳೊಂದಿಗೂ ಅನ್ನವಿಲ್ಲದ ಊಟವೇ ಅಲ್ಲ ಎಂದು ಗಾಢವಾಗಿ ನಂಬಿದವರಿಗೆ ಅನ್ನದೊಂದಿಗೆ ಕಲಸಿಕೊಂಡು ತಿನ್ನಲೂ ಅತ್ಯುತ್ತಮವಾಗಿದೆ.\n\n\n\nಸಲಹೆ: \n\n*ಅನ್ನದೊಂದಿಗೆ ಸೇವಿಸುವುದಾದರೆ ನೀರನ್ನು ಕೊಂಚ ಹೆಚ್ಚಾಗಿ ಹಾಕಿ. ಕೊಂಚವೇ ಗಾಢವಾಗಲು ಅರ್ಧ ಚಮಚ ಅಥವಾ ಒಂದು ಚಮಚ ಅಕ್ಕಿಹಿಟ್ಟನ್ನು ಸೇರಿಸಿ ಕಲಕಿ. \n\n*ಪನ್ನೀರ್ ಮತ್ತು ಒಣಫಲಗಳ ಕಾಲ ಹೆಚ್ಚಿನ ಕ್ಯಾಲೋರಿಗಳನ್ನು ಹೊಂದಿರುವುದರಿಂದ ಸ್ಥೂಲಕಾಯದವರು ಹಬ್ಬದ ನೆಪ ಹೇಳಿ ಹೆಚ್ಚಿನ ಪ್ರಮಾಣದಲ್ಲಿ ಸೇವಿಸದಿರಿ."};


             String [] d4 = {"https://3.bp.blogspot.com/-91OIu4eo8Cg/Upx9VtAFybI/AAAAAAAACPo/hyzVy4AhJjQ/s1600/Mushroom+Babycorn+Masala.jpg",
            "ಮಶ್ರೂಮ್-ಬೇಬಿ ಕಾರ್ನ್ ಕರಿ","40 ನಿಮಿಷಗಳು",
            " \n\n\n\n*ಬಟನ್ ಅಣಬೆ (ತಾಜಾ) - 2 ಪ್ಯಾಕೆಟ್‌ಗಳಷ್ಟು (400 ಗ್ರಾ೦) \n\n*ತಾಜಾ ಬೇಬಿ ಕಾರ್ನ್ (ಎಳೆ ಜೋಳ) - 1 ಪ್ಯಾಕೆಟ್ ನಷ್ಟು (200 ಗ್ರಾ೦). \n\n*ಕರಿಬೇವಿನ ಸೊಪ್ಪು - ಒ೦ದು ಹಿಡಿಯಷ್ಟು \n\n*ಸ್ಪ್ರಿ೦ಗ್ ಈರುಳ್ಳಿ (ಹೆಚ್ಚಿಟ್ಟಿದ್ದು) - ಒ೦ದು ಕಪ್ ನಷ್ಟು \n\n*ಕೊತ್ತ೦ಬರಿ ಸೊಪ್ಪು - ಒ೦ದು ಹಿಡಿಯಷ್ಟು\n\n*ದೊಡ್ಡ ಗಾತ್ರದ ಎರಡು ಈರುಳ್ಳಿಗಳು (ಸಣ್ಣಸಣ್ಣದಾಗಿ ಚೌಕಾಕಾರದಲ್ಲಿ ಕತ್ತರಿಸಿದ) \n\n*ಬೆಳ್ಳುಳ್ಳಿ (ಚೆನ್ನಾಗಿ ಕತ್ತರಿಸಿದ) - ಎ೦ಟರಿ೦ದ ಹತ್ತು ದಳಗಳು \n\n*ಶು೦ಠಿ-ಬೆಳ್ಳಿಳ್ಳಿಯ ಹಿಟ್ಟು (ಪೇಸ್ಟ್) - ಎರಡು ಟೇಬಲ್ ಚಮಚಗಳಷ್ಟು \n\n*ಕೆ೦ಪು ಮೆಣಸಿನ ಹಿಟ್ಟು (ಪೇಸ್ಟ್) - ಎರಡು ಟೇಬಲ್ ಚಮಚಗಳಷ್ಟು \n\n*ಗರ೦ ಮಸಾಲಾ ಪುಡಿ - ಒ೦ದು ಟೇಬಲ್ ಚಮಚದಷ್ಟು \n\n*ಕಾಳುಮೆಣಸಿನ ಪುಡಿ - ಎರಡು ಟೇಬಲ್ ಚಮಚದಷ್ಟು \n\n*ಕೆ೦ಪು ಮೆಣಸಿನ ಪುಡಿ - ಒ೦ದು ಟೇಬಲ್ ಚಮಚದಷ್ಟು \n\n*ಅರಿಶಿನದ ಪುಡಿ - ಅರ್ಧ ಟೇಬಲ್ ಚಮಚದಷ್ಟು \n\n*ಉಪ್ಪು - ರುಚಿಗೆ ತಕ್ಕಷ್ಟು\n\n*ಟೊಮೇಟೊ ಕೆಚ್ ಅಪ್ - ನಾಲ್ಕು ಟೇಬಲ್ ಚಮಚದಷ್ಟು \n\n*ಸೋಯಾ ಸಾಸ್ - ಒ೦ದು ಟೇಬಲ್ ಚಮಚದಷ್ಟು \n\n*ಕೆ೦ಪು ಮೆಣಸಿನ schezuan ಸಾಸ್ - ನಾಲ್ಕು ಟೇಬಲ್ ಚಮಚಗಳಷ್ಟು \n\n*ಲಿ೦ಬೆ ರಸ - ಎರಡು ಟೇಬಲ್ ಚಮಚಗಳಷ್ಟು \n\n*ವಿನೇಗರ್ - ಒ೦ದು ಟೇಬಲ್ ಚಮಚದಷ್ಟು \n\n*ಜೋಳದ ಹಿಟ್ಟು - ಒ೦ದು ಟೇಬಲ್ ಚಮಚದಷ್ಟು \n\n*ಎಣ್ಣೆ - ಒ೦ದು ಟೇಬಲ್ ಚಮಚದಷ್ಟು",
            "\n\n1. ಆಳವಾದ ತಳವುಳ್ಳ ಪಾತ್ರೆಯೊ೦ದನ್ನು ಬಿಸಿ ಮಾಡಿರಿ ಹಾಗೂ ಅದಕ್ಕೆ ಚೆನ್ನಾಗಿ ತೊಳೆದ ಕರಿಬೇವಿನ ಸೊಪ್ಪನ್ನು ಹಾಕಿರಿ. ಕರಿಬೇವು ಒಣಗಿದ ನ೦ತರ ಅದಕ್ಕೆ ಎಣ್ಣೆಯನ್ನು ಸೇರಿಸಿರಿ (ಹೀಗೆ ಮಾಡುವುದರಿ೦ದ ಎಣ್ಣೆಯು ಸಿಡಿಯುವುದನ್ನು ತಪ್ಪಿಸಬಹುದು). ಕರಿಬೇವಿನ ಎಲೆಗಳನ್ನು ಹುರಿಯಿರಿ. \n\n2. ಚೆನ್ನಾಗಿ ಹೆಚ್ಚಿಟ್ಟಿರುವ ಬೆಳ್ಳುಳ್ಳಿಯನ್ನು ಸೇರಿಸಿ ಹುರಿಯಿರಿ. ಗರ೦ ಮಸಾಲಾ ಪುಡಿಯನ್ನು ಬೆರೆಸಿರಿ.\n\n 3. ಇನ್ನು ಈರುಳ್ಳಿಗಳನ್ನು ಸೇರಿಸಿ ಹಾಗೂ ಅವುಗಳು ಅರೆಪಾರದರ್ಶಕವಾಗುವವರೆಗೆ ಅವುಗಳನ್ನು ಹುರಿಯಿರಿ. ಅನ೦ತರ ಸ್ಪ್ರಿ೦ಗ್ ಈರುಳ್ಳಿ ಅಥವಾ ಗೊ೦ಚಲು ಈರುಳ್ಳಿಗಳನ್ನು ಸೇರಿಸಿ ಒ೦ದು ನಿಮಿಷದವರೆಗೆ ಹಾಗೆಯೇ ಬಿಡಿರಿ.\n\n4. ಶು೦ಠಿ-ಬೆಳ್ಳುಳ್ಳಿಯ ಹಿಟ್ಟು ಅಥವಾ ಪೇಸ್ಟ್ ಅನ್ನು ಹಾಗೂ ಕೆ೦ಪು ಮೆಣಸಿನ ಹಿಟ್ಟನ್ನು ಇದಕ್ಕೆ ಸೇರಿಸಿರಿ ಹಾಗೂ ಹುರಿಯುವುದನ್ನು ಮತ್ತೆರಡು ನಿಮಿಷಗಳ ಕಾಲ ಮು೦ದುವರೆಸಿರಿ.\n\n 5. ಎಲ್ಲಾ ಸಾಸ್‌ಗಳನ್ನು (ಸೋಯಾ ಸಾಸ್, ಟೊಮೇಟೊ ಕೆಚ್ ಅಪ್, ಕೆ೦ಪು ಮೆಣಸಿನ schezuan ಸಾಸ್) ಗಳನ್ನು ಇದಕ್ಕೆ ಸೇರಿಸಿರಿ. \n\n6. ಸ್ವಾದವರ್ಧಕಗಳಾದ ಕಾಳುಮೆಣಸಿನ ಪುಡಿ, ಅರಿಶಿನದ ಪುಡಿ, ಹಾಗೂ ಉಪ್ಪನ್ನು ಸೇರಿಸಿರಿ.\n\n 7. ವಿನೇಗರ್ ಹಾಗೂ ಲಿ೦ಬೆಯ ರಸವನ್ನು ಸೇರಿಸಿರಿ. \n\n8. ಅಣಬೆಗಳನ್ನು ಎರಡೆರಡಾಗಿ ಸೀಳಿರಿ ಹಾಗೂ ಎಳೆ ಜೋಳವನ್ನು ಸಣ್ಣ ಸಣ್ಣ ದು೦ಡನೆಯ ಚೂರುಗಳಾಗಿ ಕತ್ತರಿಸಿರಿ. ಇವುಗಳನ್ನು ಗ್ರೇವಿಯಲ್ಲಿ ಸೇರಿಸಿರಿ. ಪಾತ್ರೆಯನ್ನು ಮುಚ್ಚಳವೊ೦ದರಿ೦ದ ಮುಚ್ಚಿರಿ ಹಾಗೂ ಅದನ್ನು ಕೆಲಕಾಲದವರೆಗೆ ಬೇಯಲು ಬಿಡಿರಿ (ಸರಿಸುಮಾರು ಹತ್ತು ನಿಮಿಷಗಳವರೆಗೆ). \n\n9. ಜೋಳದ ಹಿಟ್ಟನ್ನು ಮೂರು ಟೇಬಲ್ ಚಮಚಗಳಷ್ಟು ನೀರಿನೊಡನೆ ಮಿಶ್ರಗೊಳಿಸಿರಿ. ಅನ೦ತರ ಈ ಮಿಶ್ರಣವನ್ನು ಮೇಲೋಗರದ ಪಾತ್ರೆಗೆ ಸೇರಿಸಿ ಹಾಗೂ ಸತತವಾಗಿ ಪಾತ್ರೆಯಲ್ಲಿರುವ ಮಿಶ್ರಣವನ್ನು ಕಲಕುತ್ತಾ ಇರಿ. ಮೇಲೋಗರವು ಸಾಸ್ ನ೦ತೆ ದಪ್ಪಗಾಗುವವರೆಗೆ ಕಲಕುವುದನ್ನು ಮು೦ದುವರೆಸಿರಿ. ಒ೦ದು ನಿಮಿಷದ ಬಳಿಕ ಉರಿಯನ್ನು ನ೦ದಿಸಿರಿ. \n\n10. ಮೇಲೋಗರವನ್ನು ಮತ್ತೊ೦ದು ಪಾತ್ರೆಗೆ ವರ್ಗಾಯಿಸಿ ಬಳಿಕ ಅದಕ್ಕೊ೦ದಿಷ್ಟು ಕೊತ್ತ೦ಬರಿ ಸೊಪ್ಪಿನ್ನು ಸಿ೦ಪಡಿಸಿರಿ. ಈಗ ಈ ವಿಸ್ಮಯಕರವಾದ ಅಪ್ಪಟ ಭಾರತೀಯ ಶೈಲಿಯ ಅಣಬೆ ಹಾಗೂ ಎಳೆಜೋಳದ ಮೇಲೋಗರವು ಹಸಿದ ಹೊಟ್ಟೆಗಳನ್ನು ತಣಿಸಲು ಸಿದ್ಧವಾಗಿದೆ.\n\n\n\nಪೋಷಕಾ೦ಶ ತತ್ವ \n\nನಿಮ್ಮ ಶರೀರದ ಸ್ವಾಸ್ಥ್ಯವನ್ನು ವರ್ಧಿಸುವ ಒ೦ದು ವಿಭಿನ್ನವಾದ ಆಹಾರವಸ್ತುವು ಇದಾಗಿದ್ದು, ನೀವಿದನ್ನು ಅಗತ್ಯವಾಗಿ ಸೇವಿಸಬಹುದು. ಅಣಬೆಯು ಕಬ್ಬಿಣಾ೦ಶ ಹಾಗೂ ಪೋಷಕಾಂಶಗಳ ಒ೦ದು ಉತ್ತಮ ಆಗರವಾಗಿದ್ದು, ಎಳೆ ಜೋಳವು ವಿಟಮಿನ್‌ಗಳು ಹಾಗೂ ನಾರಿನ೦ಶದಿ೦ದ ಸಮೃದ್ಧವಾಗಿದೆ. ಅರ್ಥಾತ್ ಒ೦ದೇ ಆಹಾರವಸ್ತುವು ಎರಡು ವಿಭಿನ್ನ ಪೋಷಕಾ೦ಶಗಳ ಸೆಲೆಯಾಗಿದೆ. ಈ ಭಾರತೀಯ ಶೈಲಿಯ ಮೇಲೋಗರವು ಖಾರವಾಗಿದ್ದರೂ ಕೂಡ, ಅಣಬೆ ಹಾಗೂ ಎಳೆ ಜೋಳದ ಕಾಳುಗಳು ನಿಮನ್ನು ರಕ್ಷಿಸುತ್ತವೆ. \n\n\n\nಸಲಹೆ \n\nಜೋಳದ ಹಿಟ್ಟಗೆ ನೀರನ್ನು ಮಿಶ್ರಗೊಳಿಸಿದ ಬಳಿಕ, ಮಿಶ್ರಣವನ್ನು ಚೆನ್ನಾಗಿ ಕಲಕಿರುವುದನ್ನು ಖಚಿತ ಪಡಿಸಿಕೊಳ್ಳಿರಿ. ಹೀಗೆ ಮಾಡುವುದರ ಮೂಲಕ ನೀವು ಮಿಶ್ರಣವು ಗ೦ಟುಗ೦ಟಾಗುವುದನ್ನು ತಡೆಯಬಹುದು."};



            String [] d5 = {"http://www.yummyfoodrecipes.in/resources/picture/org/Tasty-Besan-laddu.jpg",
            "ಬೇಸನ್ ಲಾಡು","1 ಗಂಟೆ",
            "\n\n*ಕಡಲೆ ಹಿಟ್ಟು - 1 ಕಪ್\n\n*ಪುಡಿ ಸಕ್ಕರೆ - 1/2 ಕಪ್\n\n*ತುಪ್ಪ - 1/4 ಕಪ್ + 3 ಚಮಚ (ಕರಗಿಸಿದ್ದು)\n\n*ಗೇರುಬೀಜ - ಮುಷ್ಟಿಯಷ್ಟು (ಕತ್ತರಿಸಿದ್ದು)\n\n*ಏಲಕ್ಕಿ ಪುಡಿ - 1/2 ಚಮಚ",
            "\n\n1. ಕಡಲೆ ಹಿಟ್ಟನ್ನು ಹುರಿದುಕೊಂಡು ಅದನ್ನು ಪಕ್ಕದಲ್ಲಿಡಿ.\n\n2. ಪಾತ್ರೆಯಲ್ಲಿ ಒಂದು ಚಮಚದಷ್ಟು ತುಪ್ಪವನ್ನು ಬಿಸಿ ಮಾಡಿಕೊಳ್ಳಿ ಮತ್ತು ಕತ್ತರಿಸಿದ ಗೇರುಬೀಜವನ್ನು ಹುರಿದುಕೊಳ್ಳಿ\n\n3. ನಾನ್ ಸ್ಟಿಕ್ ಪಾತ್ರೆಯಲ್ಲಿ 1/4 ಚಮಚದಷ್ಟು ತುಪ್ಪವನ್ನು ಬಿಸಿ ಮಾಡಿಕೊಳ್ಳಿ ಮತ್ತು ಹುರಿದ ಕಡಲೆ ಹಿಟ್ಟನ್ನು ಇದಕ್ಕೆ ಸೇರಿಸಿ.\n\n4. ಕಡಲೆ ಹಿಟ್ಟು ಚೆನ್ನಾಗಿ ಕಾದು ಚಿನ್ನದ ಬಣ್ಣಕ್ಕೆ ತಿರುಗುವವರೆಗೆ ಹಿಟ್ಟನ್ನು ಹುರಿಯಿರಿ.\n\n5. ಇದಕ್ಕೆ ಏಲಕ್ಕಿ ಪುಡಿಯನ್ನು ಸೇರಿಸಿ ಮಿಶ್ರ ಮಾಡಿಕೊಳ್ಳಿ.\n\n6. ಇನ್ನು ಗ್ಯಾಸ್ ಆರಿಸಿ ಮತ್ತು ಹುಡಿ ಸಕ್ಕರೆಯನ್ನು ಸೇರಿಸಿಕೊಳ್ಳಿ, ಮತ್ತು ಇದನ್ನು ಚೆನ್ನಾಗಿ ಮಿಶ್ರ ಮಾಡಿಕೊಳ್ಳಿ.\n\n7. ತದನಂತರ ಇದಕ್ಕೆ ಹುರಿದ ಗೇರುಬೀಜವನ್ನು ಸೇರಿಸಿ ಮತ್ತು 2 ಚಮಚದಷ್ಟು ಕರಗಿಸಿದ ತುಪ್ಪವನ್ನು ಹಾಕಿ. ಚೆನ್ನಾಗಿ ಮಿಶ್ರ ಮಾಡಿಕೊಂಡ ನಂತರ ಇದನ್ನು ನಂತರ ಒಂದು ಪಾತ್ರೆಗೆ ವರ್ಗಾಯಿಸಿ.\n\n8. ನಿಮ್ಮ ಕೈಗಳಿಗೆ ಸ್ವಲ್ಪ ತುಪ್ಪ ಹಚ್ಚಿಕೊಳ್ಳಿ ಮತ್ತು ಹಿಟ್ಟಿನಿಂದ ಲಾಡಿನ ಉಂಡೆಗಳನ್ನು ಕಟ್ಟಲು ಪ್ರಾರಂಭಿಸಿ.\n\n9. ನಿಮ್ಮ ಲಾಡಿನ ಉಂಡೆ ನಿಂಬೆ ಹಣ್ಣಿನ ಆಕಾರದಲ್ಲಿರಲಿ.\n\nಬೇಸನ್ ಲಾಡು ಬಡಿಸಲು ಸಿದ್ಧವಾಗಿದೆ. ಗಾಳಿಯಾಡದ ಡಬ್ಬಗಳಲ್ಲಿ ಈ ಲಾಡುಗಳನ್ನು ಎರಡು ತಿಂಗಳವರೆಗೆ ನಿಮಗೆ ಜೋಪಾನ ಮಾಡಬಹುದು"};



            String [] d6 = {"https://i.ytimg.com/vi/ouQGGCIGBSQ/maxresdefault.jpg",
            "ತರಕಾರಿ ಕುರ್ಮಾ","40 ನಿಮಿಷಗಳು",
            "\n\nಆಲೂಗಡ್ಡೆ: 3 (ಸಿಪ್ಪೆ ತೆಗೆಯಬೇಕು) \n\nಬಟಾಣಿ: ¼ ಕಪ್ಪು \n\nಕ್ಯಾರೆಟ್: 2 \n\nಹಸಿ ಹುರುಳಿಕಾಯಿ: 10 (ಸಣ್ಣಗೆ ಹೆಚ್ಚಿಕೊಳ್ಳಬೇಕು)\n\n ಉಪ್ಪು: ರುಚಿಗೆ ತಕ್ಕಷ್ಟು \n\nಮೊಸರು:½ ಕಪ್ಪು \n\nಯಾವುದಾದರೂ ಹಿಟ್ಟು: 1ಟೇಬಲ್ ಚಮಚ \n\nಬೆಣ್ಣೆ: 1 ಟೇಬಲ್ ಚಮಚ\n\n\n\nರುಬ್ಬಿಕೊಳ್ಳುವ ಪದಾರ್ಥಗಳು: \n\n\n\nಕೊಬ್ಬರಿ: ¼ ಕಪ್ಪು \n\nಗಸಗಸೆ: 1 ಟೇಬಲ್ ಚಮಚ \n\nಬೇಯಿಸದ ಗೋಡಂಬಿ: 8 \n\nಈರುಳ್ಳಿ: ½ \n\nಬೆಳ್ಳುಳ್ಳಿ: 1 \n\nಹಸಿ ಮೆಣಸಿನಕಾಯಿ: 3 (ಖಾರ ಹೆಚ್ಚ್ಚಿಗೆ ಬೇಕೆನ್ನುವವರು ಇನ್ನೊಂದು) \n\nಕೊತ್ತಂಬರಿ ಸೊಪ್ಪು: ½ ಹಿಡಿ",
            "ಎಲ್ಲ ತರಕಾರಿಗಳನ್ನು ಸಮಗಾತ್ರದಲ್ಲಿ ಹೆಚ್ಚಿಕೊಳ್ಳಿ. ಒಂದು ಕಪ್ ನೀರಿನಲ್ಲಿ ಎಲ್ಲ ತರಕಾರಿಗಳನ್ನು ಚೆನ್ನಾಗಿ ಕುದಿಸಿ. ಉಪ್ಪು, ಹಿಟ್ಟು, ರುಬ್ಬಿಕೊಂಡ ಮಸಾಲೆ ಮತ್ತು ಮೊಸರನ್ನು ಸೇರಿಸಿ ಚೆನ್ನಾಗಿ ಕಲಸಿ. ಸಣ್ಣ ಉರಿಯಲ್ಲಿ ಕೆಲ ನಿಮಿಷಗಳ ಕಾಲ ಹಾಗೆ ಇಡಿ. ತರಕಾರಿಗಳೆಲ್ಲಾ ಚೆನ್ನಾಗಿ ಬೆಂದು ಮಸಾಲೆ ವಾಸನೆ ಮೂಗಿಗೆ ಅಡರುತ್ತಿದ್ದಂತೆ ಬೆಣ್ಣೆಯನ್ನು ಸೇರಿಸಿ. ಒಂದೆರಡು ನಿಮಿಷ ಬೇಯಿಸಿದ ನಂತರ ತರಕಾರಿ ಕುರ್ಮಾ ಸಿದ್ಧವಾದಂತೆ. ಇದನ್ನು ಅನ್ನ, ಚಪಾತಿ ಅಥವಾ ರೊಟ್ಟಿಯೊಂದಿಗೆ ನಂಜಿಕೊಂಡು ತಿನ್ನಬಹುದು. ತರಕಾರಿ  ಪಲ್ಯಗಳ ಬದಲಾಗಿ ಕುರ್ಮಾವನ್ನೂ ಬಳಸಬಹುದು"};


            String [] d7 = {"https://s3.amazonaws.com/images.chefinyou.com/desserts/sabudana-kheer-recipe/main-img4.JPG",
            "ಸಾಬುದಾನ ಪಾಯಸ","30 ನಿಮಿಷಗಳು",
            "\n\nಸಾಬುದಾನ 1 ಕಪ್\n\nಹಾಲು 2 ಕಪ್\n\nಬೆಲ್ಲ 1 ಕಪ್\n\nಏಲಕ್ಕಿ ಅರ್ಧ ಚಮಚ\n\nಒಣದ್ರಾಕ್ಷಿ 10\n\nಬಾದಾಮಿ 10\n\nಗೋಡಂಬಿ 10\n\nತುಪ್ಪ 1 ಚಮಚ\n\nಚಿಟಿಕೆಯಷ್ಟು ಕೇಸರಿ",
            "\n* ಚಿಕ್ಕಗಾತ್ರದ ಸಾಬುದಾನವನ್ನು 1-2 ಗಂಟೆಗಳ ನೀರಿನಲ್ಲಿ ನೆನೆ ಹಾಕಿ.\n\n* ಹಾಲನ್ನು ಕುದಿಸಿ, ಇದೇ ಸಮಯದಲ್ಲಿ ಬೆಲ್ಲದ ಪಾಕ ರೆಡಿ ಮಾಡಿ, ಹಾಲು ಚೆನ್ನಾಗಿ ಕುದಿ ಬರುವಾಗ ಬೆಲ್ಲದ ಪಾಕ ಹಾಕಿ. ಈಗ ಸಾಬುದಾನ ಹಾಕಿ 10-15 ನಿಮಿಷ ಬೇಯಿಸಿ.\n\n* ಈಗ ತುಪ್ಪವನ್ನು ಮತ್ತೊಂದು ಪ್ಯಾನ್ ಗೆ ಹಾಕಿ ಅದರಲ್ಲಿ ಒಣ ದ್ರಾಕ್ಷಿ ಮತ್ತು ಬಾದಾಮಿ, ಗೋಡಂಬಿ ಹಾಕಿ 2 ನಿಮಿಷ ರೋಸ್ಟ್ ಮಾಡಿ, ಪಾಯಸಕ್ಕೆ ಹಾಕಿ, ಏಲಕ್ಕಿ ಪುಡಿ ಮತ್ತು ಕೇಸರಿ ಹಾಕಿ ಮಿಕ್ಸ್ ಮಾಡಿ ಉರಿಯಿಂದ ಇಳಿಸಿದರೆ ಸಾಬುದಾನ ಪಾಯಸ ರೆಡಿ."};



            String [] d8 = {"http://cdn3.foodviva.com/static-content/food-images/chutney-raita-recipes/chilli-chutney-recipe/chilli-chutney-recipe.jpg",
            "ಕೆಂಪು ಮೆಣಸಿನಕಾಯಿ ಕಾರ ಚಟ್ನಿ", "30 ನಿಮಿಷಗಳು",
            "\n\nಬ್ಯಾಡಗಿ ಒಣಮೆಣಸಿನಕಾಯಿ - 1 ಕಪ್\n\nಹುಣಸೇಹಣ್ಣು - ಬೆಟ್ಟದ ನೆಲ್ಲಿಕಾಯಿ ಗಾತ್ರ\n\nಜೀರಿಗೆ - 1 ಸ್ಪೂನ್\n\nಬೆಳ್ಳುಳ್ಳಿ ಎಸಳು - 10\n\nಒಣ ಕೊಬ್ಬರಿ - ಸ್ವಲ್ಪ\n\nಕರಿಬೇವು - ಸ್ವಲ್ಪ\n\nಬೆಲ್ಲ - ಸ್ವಲ್ಪ\n\nಉಪ್ಪು ರುಚಿಗೆ ತಕ್ಕಷ್ಟು",
            "\n\nಮೊದಲಿಗೆ ಬ್ಯಾಡಗಿ ಒಣಮೆಣಸಿನಕಾಯಿಯನ್ನು 2 ಗಂಟೆ ನೀರಿನಲ್ಲಿ ನೆನೆಸಿ ತೆಗೆದಿಟ್ಟುಕೊಳ್ಳಿ. ನಂತರ ಮಿಕ್ಸಿ ಜಾರಿಗೆ ನೆನೆಸಿದ ಒಣಮೆಣಸಿನಕಾಯಿ, ಒಣ ಕೊಬ್ಬರಿ, ಬೆಳ್ಳುಳ್ಳಿ, ಹುಣಸೇಹಣ್ಣು, ಜೀರಿಗೆ, ಕರಿಬೇವು, ಬೆಲ್ಲ, ಉಪ್ಪು, ಎಲ್ಲಾ ಹಾಕಿ ನೀರು ಹಾಕದೆ ತರಿ ತರಿಯಾಗಿ ರುಬ್ಬಿದರೆ ಕೆಂಪು ಚಟ್ನಿ ರೆಡಿ.\n\nಕೆಂಪು ಚಟ್ನಿ ನೀರುದೋಸೆಯೊಡನೆ ತಿನ್ನಲು ಚೆನ್ನಾಗಿರುತ್ತದೆ.\n\n\n\nರಂಜಕ : ಬ್ಯಾಡಗಿ ಮೆಣಸಿನಕಾಯಿಯಿಂದ ಕಣ್ಣಲ್ಲಿ ನೀರು ಬರಿಸುವ ಕಾರಕಾರ ಚಟ್ನಿ ಮಾಡುವುದು ಹೇಗೆಂದು ತಿಳಿಯುವಿರಿ ಮುಂದಿನ ಲೇಖನದಲ್ಲಿ. ಈ ಕೆಂಪು ಚಟ್ನಿಯನ್ನು ಉತ್ತರ ಕರ್ನಾಟಕದಲ್ಲಿ ರಂಜಕ ಅಂತಲೂ ಕರೆಯುತ್ತಾರೆ. ರಂಜಕ ತಿಂದು ಕಣ್ಣಲ್ಲಿ ನೀರು ಬರಿಸಿಕೊಂಡ ರೋಚಕ ಕಥಾನಕಗಳಿದ್ದರೆ ನಮಗೆ ಬರೆದು ತಿಳಿಸಿ."};



            String [] d9  = {"http://www.dishcovery.in/sites/default/files/styles/cuisine_individual_list/public/recipes/Mangalore-Buns_thumbnail.jpg",
             "ಮಂಗಳೂರು ಬನ್ಸ್ ","30 ನಿಮಿಷಗಳು",
            "\n\n*ಬಾಳೆಹಣ್ಣು -2 \n\n*ಮೈದಾ- 1 ಕಪ್ \n\n*ಸಕ್ಕರೆ - 3 ಚಮಚ \n\n*ಉಪ್ಪು- ರುಚಿಗೆ ತಕ್ಕಷ್ಟು \n\n*ಎಳ್ಳು- 1 ಚಮಚ   \n\n*ಓಮ- 1 ಚಮಚ \n\n*ಜೀರಿಗೆ - 1 ಚಮಚ\n\n*ಮೊಸರು - 1 ಕಪ್ \n\n*ಕರಿಯಲು ಎಣ್ಣೆ",
            "\n*ಬಾಳೆಹಣ್ಣಿನ ಸಿಪ್ಪೆ ತೆಗೆದು, ಅದನ್ನು ಚೆನ್ನಾಗಿ ಕೈಯಿಂದ ಹಿಚುಕಬೇಕು. ನಂತರ ಮೇಲಿನ ಎಲ್ಲ ಸಾಮಗ್ರಿಗಳನ್ನು ಹಾಕಿ ಚಪಾತಿ ಹಿಟ್ಟಿನ ಹದಕ್ಕೆ ಕಲಸಿ 5-6 ಗಂಟೆ ಮುಚ್ಚಿ ಇಡಬೇಕು. ಸಂಜೆಯ ಚಹಾ ಜೊತೆ ಮಾಡಬೇಕಾದಲ್ಲಿ ಎಂಟು ಗಂಟೆ ಮೊದಲು ಹಿಟ್ಟು ತಯಾರಿಸಿ ಇಡಬೇಕು.\n\n* ಹಿಟ್ಟನ್ನು ಸಣ್ಣ ಗಾತ್ರದ ಉಂಡೆಗಳನ್ನಾಗಿ ಮಾಡಿ ಹಾಗೂ ಮೈದಾಹಿಟ್ಟಿನಲ್ಲಿ ಹೊರಳಿಸಿ ಪೂರಿ ಆಕಾರಕ್ಕೆ ತನ್ನಿ. ಲಟ್ಟಣಿಗೆ ಏನೂ ಬೇಕಾಗಿಲ್ಲ, ಕೈಯಲ್ಲೇ ತಟ್ಟಿಕೊಳ್ಳಬಹುದು. ಹೆಚ್ಚು ತೆಳ್ಳಗಾಗುವ ಅಗತ್ಯವೂ ಇಲ್ಲ.\n\n* ಬಾಣಲೆಗೆ ಎಣ್ಣೆ ಎರೆದು ಒಲೆಯ ಮೇಲೆ ಇಡಿ. ಬಿಸಿ ಎಣ್ಣೆಗೆ ಒಂದೊಂದೇ ಹಾಕಿ. ಉಬ್ಬಿ ಬರುತ್ತಿದ್ದ ಹಾಗೇ ಸೌಟಿನಿಂದ ಬಿಸಿ ಎಣ್ಣೆ ಚಿಮುಕಿಸುತ್ತಿರಿ. ಕೆಂಪಗಾಗಿ ಉಬ್ಬಿ ಬಂದ ಮೇಲೆ ಕವುಚಿ ಹಾಕಿ ಕೂಡಲೇ ತೆಗೆಯಿರಿ. ಎಲ್ಲವನ್ನೂ ಹೀಗೆ ಒಂದೊಂದೇ ಬೇಯಿಸಿ.\n\n* ಬಿಸಿ ಬಿಸಿಯಾಗಿರುಬ ಬನ್ಸ್ ಅನ್ನು ತೆಂಗಿನ ಕಾಯಿ ಚಟ್ನಿ ಅಥವಾ ಸಾಂಬರ್ ಜೊತೆ ಸವಿಯಿರಿ."};



            String [] d10 ={"https://raw.githubusercontent.com/AshwinChandlapur/ImgLoader/gh-pages/mango-lassi-aam-lassi-2-min.jpg",
            "ಮ್ಯಾಂಗೋ ಲಾಸ್ಸಿ","10 ನಿಮಿಷಗಳು",
            "\n\n1 ಕಪ್ ಮೊಸರು\n\n3 ಮಾವಿನ ಹಣ್ಣು\n\nಅರ್ಧ ಚಮಚ ರೋಸ್ ವಾಟರ್\n\n3 ಚಮಚ ಸಕ್ಕರೆ\n\nಐಸ್ ಕ್ಯೂಬ್ಸ್",
            "\n\n* ಮಾವಿನ ಹಣ್ಣನ್ನು ಸಿಪ್ಪೆ ಸುಲಿದು ಕತ್ತರಿಸಿ. ನಂತರ ಅದನ್ನು ಮೊಸರು ಜೊತೆ ಹಾಕಿ, ಸಕ್ಕರೆ ಹಾಕಿ, ರೋಸ್ ವಾಟರ್ ಹಾಕಿ ಚೆನ್ನಾಗಿ ಆಡಿಸಿ. ನಂತರ ಐಸ್ ಕ್ಯೂಬ್ಸ್ ಹಾಕಿ ಮತ್ತೊಮ್ಮೆ ಆಡಿಸಿ.\n\n* ನಂತರ ಅದನ್ನು ಸೋಸಿ ಗ್ಲಾಸ್ ಗೆ ಹಾಕಿದರೆ ಮ್ಯಾಂಗೋ ಲಸ್ಸಿ ರೆಡಿ."};



            String [] d11 = {"http://farm4.static.flickr.com/3387/3180713986_6a00d17187.jpg",
            "ಮಂಗಳೂರು ಬಜ್ಜಿ","40 ನಿಮಿಷಗಳು",
            "\n\n* ಮೈದಾ ಹಿಟ್ಟು - ಒಂದು ಬಟ್ಟಲು\n\n* ಮೊಸರು - ಒಂದು ಬಟ್ಟಲು/ ಕಲೆಸಲು ಬೇಕಾಗುವಷ್ಟು\n\n* ಸಕ್ಕರೆ ಪುಡಿ - ಅರ್ಧ ಚಮಚಸೋಡ- ಚಿಟಿಕೆ\n\n* ಉಪ್ಪು- ರುಚಿಗೆ ಬೇಕಾಗುವಷ್ಟು\n\n* ಎಣ್ಣೆ - ಕರಿಯಲು",
            "* ಬಾಣಲೆಗೆ ಎಣ್ಣೆ ಹಾಕಿ ಕಾಯಲು ಇಡಿ.\n\n* ಮೈದಾಗೆ ಸೋಡಾ ಪುಡಿ, ಉಪ್ಪು, ಸಕ್ಕರೆಪುಡಿ ಹಾಕಿ ಚೆನ್ನಾಗಿ ಬೆರೆಸಿ, ಅದಕ್ಕೆ ಮೊಸರು ಹಾಕಿ ಗಟ್ಟಿಯಾಗಿ ಕಲೆಸಿ. ತುಂಬಾ ತೆಳ್ಳಗೆ ಇರಬಾರದು, ಇಡ್ಲಿ ಹಿಟ್ಟಿಗಿಂತ ಗಟ್ಟಿ ಇರಬೇಕು. ಕೈಯಲ್ಲಿ ತೆಗೆದುಕೊಂಡು ಬಿಡುವಂತಿರಬೇಕು.\n\n* ಎಣ್ಣೆ ಕಾದ ನಂತರ ಕಲೆಸಿದ ಮಿಶ್ರಣವನ್ನು ಕೈನಲ್ಲಿ ತೆಗೆದುಕೊಂಡು ಒಂದೊಂದಾಗಿ ಚಿಕ್ಕದಾಗಿ ಉಂಡೆ ಉಂಡೆಯಂತೆ ಎಣ್ಣೆಯಲ್ಲಿ ಬಿಡಿ. ಬೋಂಡಾ ತರಹ ಹಾಕಿ, ಕೆಂಪು ಬಣ್ಣ ಬರುವವರೆಗೆ ಕರಿದು, ತೆಗೆಯಿರಿ, ರುಚಿ ರುಚಿಯಾದ ಗೋಳಿ ಬಜೆ ರೆಡಿ! ಇನ್ನು ತೆಂಗಿನ ಕಾಯಿ ಚಟ್ನಿ ಜೊತೆ ಸರ್ವ್ ಮಾಡಿ."};



            String [] d12 = {"http://cdn1.foodviva.com/static-content/food-images/roti-paratha-recipes/palak-paratha-recipe/palak-paratha-recipe.jpg",
            "ಅಕ್ಕಿರೊಟ್ಟಿ", "30 ನಿಮಿಷಗಳು",
            "\n\n*ಅಕ್ಕಿಹಿಟ್ಟು: ಎರಡು ಕಪ್\n\n*ಮೆಂತೆ ಸೊಪ್ಪು : ಒಂದು ಕಪ್ (ಚಿಕ್ಕದಾಗಿ ಹೆಚ್ಚಿದ್ದು) - ತಾಜಾ ಎಲೆಗಳಾಗಿರಬೇಕು\n\n*ಪಾಲಕ್ ಸೊಪ್ಪು: ಒಂದು ಕಪ್ (ಚಿಕ್ಕದಾಗಿ ಹೆಚ್ಚಿದ್ದು)- ತಾಜಾ ಎಲೆಗಳಾಗಿರಬೇಕು, ಸಾಧ್ಯವಾದಷ್ಟು ದಂಟು ಉಪಯೋಗಿಸಬೇಡಿ.\n\n*ಜೀರಿಗೆ - ಒಂದು ಚಿಕ್ಕ ಚಮಚ*\n\nಕಾಯಿತುರಿ: ಒಂದು ಕಪ್*\n\nಹಸಿಮೆಣಸು: ಸುಮಾರು ಮೂರು\n\n*ಕೊತ್ತಂಬರಿ ಸೊಪ್ಪು: ಅರ್ಧ ಕಟ್ಟು, ಚಿಕ್ಕದಾಗಿ ಹೆಚ್ಚಿದ್ದು.\n\n*ಅಡುಗೆ ಎಣ್ಣೆ - ಎರಡು ದೊಡ್ಡ ಚಮಚ*ಉಪ್ಪು - ರುಚಿಗನುಸಾರ",
            "\n\n1) ಒಂದು ಪಾತ್ರೆಯಲ್ಲಿ ಮೆಂತೆ ಸೊಪ್ಪು, ಪಾಲಕ್ ಸೊಪ್ಪು, ಜೀರಿಗೆ, ಕಾಯಿತುರಿ, ಹಸಿಮೆಣಸು, ಕೊತ್ತಂಬರಿ ಸೊಪ್ಪು, ಉಪ್ಪು ಸೇರಿಸಿ ಕೈಗಳಿಂದ ಚೆನ್ನಾಗಿ ಕಲಕಿ.\n\n2) ಈಗ ಇದಕ್ಕೆ ಅಕ್ಕಿ ಹಿಟ್ಟುಹಾಕಿ ಕಲಸುತ್ತಾ ಹೋಗಿ. ನಡುನಡುವೆ ಸ್ವಲ್ಪಸ್ವಲ್ಪವಾಗಿಯೇ ನೀರು ಸೇರಿಸುತ್ತಾ ಹೋಗಿ ರೊಟ್ಟಿ ತಟ್ಟುವಷ್ಟು ಹದ ಬರುವವರೆಗೆ ಕಲಸುತ್ತಾ ಹೋಗಿ. ನೀರು ಹೆಚ್ಚಾಗದಂತೆ ಎಚ್ಚರವಹಿಸಿ, ಹೆಚ್ಚಾದರೆ ತಟ್ಟಲು ಬರುವುದಿಲ್ಲ, ಬಂದರೂ ಕಾವಲಿಯ ಮೇಲೆ ಇಡುವಾಗ ತುಂಡುತುಂಡಾಗುತ್ತದೆ. ಈ ಹಿಟ್ಟನ್ನು ಒಂದು ಬದಿಗಿಡಿ. \n\n3) ಒಂದು ದಪ್ಪತಳದ ಕಾವಲಿಯನ್ನು ಮಧ್ಯಮ ಉರಿಯಲ್ಲಿ ಬಿಸಿಮಾಡಿ ಎಣ್ಣೆ ಸವರಿ. \n\n4) ಅಕ್ಕಿಹಿಟ್ಟಿನ ಒಂದು ಚಿಕ್ಕ ಮುದ್ದೆಯನ್ನು ಬಾಳೆ ಎಲೆ ಅಥವಾ ಸ್ವಚ್ಛಗೊಳಿಸಿದ ದಪ್ಪ ಪ್ಲಾಸ್ಟಿಕ್ ಹಾಳೆಯ ಮೇಲೆ ಎಣ್ಣೆ ಸವರಿ ರೊಟ್ಟಿಯನ್ನು ಅತಿ ದಪ್ಪವೂ ಅಲ್ಲ, ಅತಿ ತೆಳ್ಳಗೂ ಅಲ್ಲವೆನ್ನುವಷ್ಟು ದಪ್ಪಗೆ ತಟ್ಟಿ. \n\n5) ಈ ಹಿಟ್ಟನ್ನು ಕಾವಲಿಯ ಮೇಲೆ ಉಲ್ಟಾ ಹಾಕಿ ಬಾಳೆಯೆಲೆ ಅಥವಾ ಪ್ಲಾಸ್ಟಿಕ್ ಹಾಳೆಯನ್ನು ಒಂದು ಕಡೆಯಿಂದ ಎತ್ತುತ್ತಾ ಬನ್ನಿ6\n\n) ಒಂದೆರಡು ನಿಮಿಷದ ಬಳಿಕ ರೊಟ್ಟಿಯನ್ನು ತಿರುವಿ ಇನ್ನೊಂದು ಬದಿಯನ್ನು ಬೇಯಿಸಿ \n\n7) ಕಾವಲಿಗೆ ಮುಚ್ಚಳ ಮುಚ್ಚಬೇಡಿ, ಮುಚ್ಚಿದರೆ ರೊಟ್ಟಿ ಸೀದು ಹೋಗಬಹುದು. \n\n8) ಬಿಸಿಬಿಸಿಯಿದ್ದಂತೆಯೇ ನಿಮ್ಮ ನೆಚ್ಚಿನ ಸಾರು, ಚಟ್ನಿ ಅಥವಾ ಇತರ ವ್ಯಂಜನದೊಂದಿಗೆ ಬಡಿಸಿ.\n\n\n\nಸಲಹೆ: ಪೌಷ್ಟಿಕವಾದ ಈ ಆಹಾರವನ್ನು ಎಲ್ಲಾ ವಯಸ್ಸಿನವರು ಸೇವಿಸಬಹುದು. ತೂಕ ಇಳಿಸುವವರೂ ಅಲ್ಪ ಪ್ರಮಾಣದಲ್ಲಿ ಸೇವಿಸಬಹುದು."};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Code To ask for User Permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        //Code to ask for User Permissions Ends


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if(!(android.os.Build.VERSION.SDK_INT <21))
            setSupportActionBar(toolbar);

                if(isNetworkConnected()) {
                    SharedPreferences preferences = getSharedPreferences("only_once", Context.MODE_PRIVATE);
                    if(preferences.getInt("first", 0) == 0) // First Open of App.
                    {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putInt("first", 1);
                        editor.commit();
                        Toast.makeText(getApplicationContext(), R.string.PleaseWait, Toast.LENGTH_SHORT).show();
                        localVersion = preferences.getInt("version", 0);
                        new baseNewsVersion().execute("https://raw.githubusercontent.com/AshwinChandlapur/ImgLoader/gh-pages/base_version.json");
                        new baseFile().execute("https://raw.githubusercontent.com/AshwinChandlapur/ImgLoader/gh-pages/kannada_recipes.json");
                    }
                }
                else
                {
                    SharedPreferences preferences = getSharedPreferences("only_once", Context.MODE_PRIVATE);
                    if (preferences.getBoolean("firstrun", true)) {
                        Toast.makeText(this, R.string.Connect, Toast.LENGTH_LONG).show();
                        preferences.edit().putBoolean("firstrun", false).commit();
                    }

                }
        pd = new ProgressDialog(this);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (R.id.fab){
                    case R.id.fab:
                        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawer.openDrawer(GravityCompat.START);
                        break;
                    default:
                        DrawerLayout drawers = (DrawerLayout) findViewById(R.id.drawer_layout);
                        drawers.closeDrawer(Gravity.START);
                        break;
                }
            }
        });

        ViewTooltip.on(fab).autoHide(true, 4000 ).corner(0).position(ViewTooltip.Position.LEFT).text("ಹೆಚ್ಚು ರೆಸಿಪಿಗಳಿಗಾಗಿ ಚೆಫ್ ಬಟ್ಟನ್ ಒತ್ತಿರಿ").show();

        Typeface regular_font =Typeface.createFromAsset(this.getAssets(),"fonts/Aller_Rg.ttf");

        HorizontalScrollView h1 = (HorizontalScrollView)findViewById(R.id.h1);
        h1.setBackground(getResources().getDrawable(R.drawable.h1));

        HorizontalScrollView h2 = (HorizontalScrollView)findViewById(R.id.h2);
        h2.setBackground(getResources().getDrawable(R.drawable.h2));

        HorizontalScrollView h3 = (HorizontalScrollView)findViewById(R.id.h3);
        h3.setBackground(getResources().getDrawable(R.drawable.h3));
        setSupportActionBar(toolbar);
        // Picasso.with(this).load("https://images6.alphacoders.com/336/336514.jpg").placeholder(R.drawable.background).centerCrop().into(h1);


        CardView c1 =(CardView)findViewById(R.id.c1);
        ImageView i1 = (ImageView)findViewById(R.id.i1);
        TextView t1 = (TextView)findViewById(R.id.t1);
        t1.setTypeface(regular_font);
        t1.setText(d1[1]);
        Picasso.with(this).load(R.drawable.d1).placeholder(R.drawable.background).into(i1);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), offlineRecipeDisplayActivity.class);
                //intent.putExtra("img_id",1);
                intent.putExtra("name",d1[1]);
                intent.putExtra("time",d1[2]);
                intent.putExtra("ingredients",d1[3]);
                intent.putExtra("directions",d1[4]);
                intent.putExtra("img",d1[0]);
                intent.putExtra("sr",d1[0]);
                startActivity(intent);
            }
        });


        CardView c2 =(CardView)findViewById(R.id.c2);
        ImageView i2 = (ImageView)findViewById(R.id.i2);
        TextView t2 =(TextView)findViewById(R.id.t2);
        t2.setTypeface(regular_font);
        t2.setText(d2[1]);
        Picasso.with(this).load(R.drawable.d2).placeholder(R.drawable.background).into(i2);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), offlineRecipeDisplayActivity.class);
                //intent.putExtra("img_id",1);
                intent.putExtra("name",d2[1]);
                intent.putExtra("time",d2[2]);
                intent.putExtra("ingredients",d2[3]);
                intent.putExtra("directions",d2[4]);
                intent.putExtra("img",d2[0]);
                intent.putExtra("sr",d2[0]);
                startActivity(intent);

            }
        });

        CardView c3 =(CardView)findViewById(R.id.c3);
        ImageView i3 = (ImageView)findViewById(R.id.i3);
        TextView t3 = (TextView)findViewById(R.id.t3);
        t3.setTypeface(regular_font);
        t3.setText(d3[1]);
        Picasso.with(this).load(R.drawable.d3).placeholder(R.drawable.background).into(i3);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), offlineRecipeDisplayActivity.class);
                //intent.putExtra("img_id",1);
                intent.putExtra("name",d3[1]);
                intent.putExtra("time",d3[2]);
                intent.putExtra("ingredients",d3[3]);
                intent.putExtra("directions",d3[4]);
                intent.putExtra("img",d3[0]);
                intent.putExtra("sr",d3[0]);
                startActivity(intent);

            }
        });

        CardView c4 =(CardView)findViewById(R.id.c4);
        ImageView i4 = (ImageView)findViewById(R.id.i4);
        TextView t4 = (TextView)findViewById(R.id.t4);
        t4.setTypeface(regular_font);
        t4.setText(d4[1]);
        Picasso.with(this).load(R.drawable.d4).placeholder(R.drawable.background).into(i4);
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), offlineRecipeDisplayActivity.class);
                //intent.putExtra("img_id",1);
                intent.putExtra("name",d4[1]);
                intent.putExtra("time",d4[2]);
                intent.putExtra("ingredients",d4[3]);
                intent.putExtra("directions",d4[4]);
                intent.putExtra("img",d4[0]);
                intent.putExtra("sr",d4[0]);
                startActivity(intent);
            }
        });


        CardView c5 =(CardView)findViewById(R.id.c5);
        ImageView i5 = (ImageView)findViewById(R.id.i5);
        TextView t5 = (TextView)findViewById(R.id.t5);
        t5.setTypeface(regular_font);
        t5.setText(d5[1]);
        Picasso.with(this).load(R.drawable.d5).placeholder(R.drawable.background).into(i5);
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), offlineRecipeDisplayActivity.class);
                //intent.putExtra("img_id",1);
                intent.putExtra("name",d5[1]);
                intent.putExtra("time",d5[2]);
                intent.putExtra("ingredients",d5[3]);
                intent.putExtra("directions",d5[4]);
                intent.putExtra("img",d5[0]);
                intent.putExtra("sr",d5[0]);
                startActivity(intent);
            }
        });


        CardView c6 =(CardView)findViewById(R.id.c6);
        ImageView i6 = (ImageView)findViewById(R.id.i6);
        TextView t6 = (TextView)findViewById(R.id.t6);
        t6.setTypeface(regular_font);
        t6.setText(d6[1]);
        Picasso.with(this).load(R.drawable.d6).placeholder(R.drawable.background).into(i6);
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), offlineRecipeDisplayActivity.class);
                //intent.putExtra("img_id",1);
                intent.putExtra("name",d6[1]);
                intent.putExtra("time",d6[2]);
                intent.putExtra("ingredients",d6[3]);
                intent.putExtra("directions",d6[4]);
                intent.putExtra("img",d6[0]);
                intent.putExtra("sr",d6[0]);
                startActivity(intent);
            }
        });


        CardView c7 =(CardView)findViewById(R.id.c7);
        ImageView i7 = (ImageView)findViewById(R.id.i7);
        TextView t7 = (TextView)findViewById(R.id.t7);
        t7.setTypeface(regular_font);
        t7.setText(d7[1]);
        Picasso.with(this).load(R.drawable.d7).placeholder(R.drawable.background).into(i7);
        c7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), offlineRecipeDisplayActivity.class);
                //intent.putExtra("img_id",1);
                intent.putExtra("name",d7[1]);
                intent.putExtra("time",d7[2]);
                intent.putExtra("ingredients",d7[3]);
                intent.putExtra("directions",d7[4]);
                intent.putExtra("img",d7[0]);
                intent.putExtra("sr",d7[0]);
                startActivity(intent);
            }
        });


        CardView c8 =(CardView)findViewById(R.id.c8);
        ImageView i8 = (ImageView)findViewById(R.id.i8);
        TextView t8 = (TextView)findViewById(R.id.t8);
        t8.setTypeface(regular_font);
        t8.setText(d8[1]);
        Picasso.with(this).load(R.drawable.d8).placeholder(R.drawable.background).into(i8);
        c8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), offlineRecipeDisplayActivity.class);
                //intent.putExtra("img_id",1);
                intent.putExtra("name",d8[1]);
                intent.putExtra("time",d8[2]);
                intent.putExtra("ingredients",d8[3]);
                intent.putExtra("directions",d8[4]);
                intent.putExtra("img",d8[0]);
                intent.putExtra("sr",d8[0]);
                startActivity(intent);
            }
        });


        CardView c9 =(CardView)findViewById(R.id.c9);
        ImageView i9 = (ImageView)findViewById(R.id.i9);
        TextView t9 = (TextView)findViewById(R.id.t9);
        t9.setTypeface(regular_font);
        t9.setText(d9[1]);
        Picasso.with(this).load(R.drawable.d9).placeholder(R.drawable.background).into(i9);
        c9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), offlineRecipeDisplayActivity.class);
                //intent.putExtra("img_id",1);
                intent.putExtra("name",d9[1]);
                intent.putExtra("time",d9[2]);
                intent.putExtra("ingredients",d9[3]);
                intent.putExtra("directions",d9[4]);
                intent.putExtra("img",d9[0]);
                intent.putExtra("sr",d9[0]);
                startActivity(intent);
            }
        });

        CardView c10 =(CardView)findViewById(R.id.c10);
        ImageView i10 = (ImageView)findViewById(R.id.i10);
        TextView t10 = (TextView)findViewById(R.id.t10);
        t10.setTypeface(regular_font);
        t10.setText(d10[1]);
        Picasso.with(this).load(R.drawable.d10).placeholder(R.drawable.background).into(i10);
        c10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), offlineRecipeDisplayActivity.class);
                //intent.putExtra("img_id",1);
                intent.putExtra("name",d10[1]);
                intent.putExtra("time",d10[2]);
                intent.putExtra("ingredients",d10[3]);
                intent.putExtra("directions",d10[4]);
                intent.putExtra("img",d10[0]);
                intent.putExtra("sr",d10 [0]);
                startActivity(intent);
            }
        });

        CardView c11 =(CardView)findViewById(R.id.c11);
        ImageView i11 = (ImageView)findViewById(R.id.i11);
        TextView t11 = (TextView)findViewById(R.id.t11);
        t11.setTypeface(regular_font);
        t11.setText(d11[1]);
        Picasso.with(this).load(R.drawable.d11).placeholder(R.drawable.background).into(i11);
        c11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), offlineRecipeDisplayActivity.class);
                //intent.putExtra("img_id",1);
                intent.putExtra("name",d11[1]);
                intent.putExtra("time",d11[2]);
                intent.putExtra("ingredients",d11[3]);
                intent.putExtra("directions",d11[4]);
                intent.putExtra("img",d11[0]);
                intent.putExtra("sr",d11[0]);
                startActivity(intent);
            }
        });

        CardView c12 =(CardView)findViewById(R.id.c12);
        ImageView i12 = (ImageView)findViewById(R.id.i12);
        TextView t12 = (TextView)findViewById(R.id.t12);
        t12.setTypeface(regular_font);
        t12.setText(d12[1]);
        Picasso.with(this).load(R.drawable.d12).placeholder(R.drawable.background).into(i12);
        c12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), offlineRecipeDisplayActivity.class);
                //intent.putExtra("img_id",1);
                intent.putExtra("name",d12[1]);
                intent.putExtra("time",d12[2]);
                intent.putExtra("ingredients",d12[3]);
                intent.putExtra("directions",d12[4]);
                intent.putExtra("img",d12[0]);
                intent.putExtra("sr",d12[0]);
                startActivity(intent);
            }
        });



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);


    }



    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public class baseNewsVersion extends AsyncTask<String, String, String> {
        HttpURLConnection connection;
        BufferedReader reader;

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                return builder.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final String  s) {
            super.onPostExecute(s);
                    try {
                        JSONObject parent = new JSONObject(s);
                        JSONObject base_version = parent.getJSONObject("base_version");
                        serverVersion = base_version.getInt("version");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //Compare Server Version of JSON and local version of JSON if not same download the new JSON content
                    if (localVersion != serverVersion) {
                        pd.setMessage("ಎಲ್ಲ ಅಡುಗೆಗಳು ತಯಾರಾಗುತ್ತಿವೆ.....ದಯವಿಟ್ಟು ಕಾಯಿರಿ!");
                        pd.setIcon(R.mipmap.ic_launcher);
                        pd.setCancelable(false);
                        pd.show();
                        // new baseFile().execute("https://raw.githubusercontent.com/AshwinChandlapur/ImgLoader/gh-pages/base.json");
                        new baseFile().execute("https://raw.githubusercontent.com/AshwinChandlapur/ImgLoader/gh-pages/kannadaRec.json");
                        //new baseFile().execute("http://nammakarnataka.net23.net/general/base.json");
                    }
                    else {
                            Log.d("OKOK","OKOK");
                        Toast.makeText(getApplicationContext(), "All Recipes are up to date!", Toast.LENGTH_SHORT).show();
                    }
        }
    }

    public class baseFile extends AsyncTask<String, String, String> {

        HttpURLConnection connection;
        BufferedReader reader;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                String str = builder.toString();
                saveJsonFile(str);
                return str;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);

                    pd.setMessage("ಡಾಟಬೇಸ್ ಅಪ್ ಡೇಟ್ ಪ್ರಗತಿಯಲ್ಲಿದೆ");


                    try {
                        JSONObject parent = new JSONObject(s);
                        JSONArray items = parent.getJSONArray("list");

                        if (items != null){
                            if (pd.isShowing())
                                pd.dismiss();

                            myDBHelper = new DatabaseHelper(getApplicationContext());
                            myDBHelper.deleteTables();

                            for (int i = 0; i < items.length(); i++) {
                                JSONObject child = items.getJSONObject(i);
                                JSONArray images = child.getJSONArray("image");

                                for (int j = 0; j < images.length(); j++) {
                                    myDBHelper.insertIntoImages(child.getInt("id"),images.getString(j));
                                }
                                // myDBHelper.insertIntoPlace(child.getInt("id"), child.getString("name"), child.getString("description"), child.getString("district"), child.getString("bestSeason"), child.getString("additionalInformation"), child.getString("nearByPlaces"), child.getDouble("latitude"), child.getDouble("longitude"), child.getString("category"));
                                myDBHelper.insertIntoRecipe(child.getInt("id"), child.getString("name"), child.getString("time"), child.getString("ingredients"), child.getString("directions"), child.getString("category"),child.getString("inenglish"));
                            }

                            SharedPreferences preferences = getSharedPreferences("base_version", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("version", serverVersion);
                            editor.commit();





                        }else {
                            SharedPreferences preferences = getSharedPreferences("base_version", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putInt("version", localVersion);
                            editor.commit();
                            Toast.makeText(getApplicationContext(),"Reatining The Same List",Toast.LENGTH_SHORT).show();
                        }

                        if(pd.isShowing())
                            pd.dismiss();
                        Toast.makeText(getApplicationContext(),"ಯಶಸ್ವಿಯಾಗಿ   ಡಾಟಾಬೇಸ್ ಅಪ್ ಡೇಟ್ ಆಗಿದೆ",Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
        }

    }

    private void saveJsonFile(String data) {
        FileOutputStream stream = null;
        try {
            //File path = new File("/data/data/smartAmigos.com.nammakarnataka/general.json");
            File path = new File("/data/data"+getApplicationContext().getPackageName()+"/"+"general.json");
            stream = new FileOutputStream(path);
            stream.write(data.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stream != null)
                    stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        MenuItemCompat.setOnActionExpandListener(searchMenuItem,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionExpand(MenuItem menuItem) {
                        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenuItem);
                        final SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
                        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
                        searchView.setOnQueryTextListener(
                                new SearchView.OnQueryTextListener(){

                                    @Override
                                    public boolean onQueryTextSubmit(String query) {
                                        myDBHelper = new DatabaseHelper(getApplicationContext());
                                        Cursor cursor = myDBHelper.getRecipeByEnglishName(query);
                                        Fragment fragment = new SearchResults(cursor);
                                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                        //ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                                        ft.replace(R.id.app_bar, fragment);
                                        ft.commit();

                                        return false;
                                    }

                                    @Override
                                    public boolean onQueryTextChange(String newText) {
                                        myDBHelper = new DatabaseHelper(getApplicationContext());
                                        Cursor cursor = myDBHelper.getRecipeByEnglishName(newText);
                                        Fragment fragment = new SearchResults(cursor);
                                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                        // ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                                        ft.replace(R.id.app_bar, fragment);
                                        ft.commit();

                                        return false;
                                    }

                                }
                        );
                        return true;
                    }
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem menuItem) {


                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        //((Activity) getApplicationContext()).overridePendingTransition(0,0);
                    //Press Search and then If u press back, then the below mentioned fargment is loaded
                      //  Fragment fragment = new breakfastFragment();
                       // FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        //ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                       // ft.replace(R.id.app_bar, fragment);
                       // ft.hide(fragment);
                       // ft.detach(fragment);
                       // ft.commit();

                        // Do whatever you need
                        return true; // OR FALSE IF YOU DIDN'T WANT IT TO CLOSE!
                        // When the action view is collapsed, reset the query
                       // townList.setVisibility(View.INVISIBLE);
                        // Return true to allow the action view to collapse

                    }
                });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.






        int id = item.getItemId();

        switch (item.getItemId()) {


            case android.R.id.home:
                Toast.makeText(getApplicationContext(),"Back button clicked", Toast.LENGTH_SHORT).show();
                break;


            case R.id.action_share:
                String str = "https://play.google.com/store/apps/details?id=" + getPackageName();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "One Stop for your Indian Cusine Dishes\nDownload Now:\n" + str);
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;


            case R.id.action_refresh:
                if (isNetworkConnected()) {
                    Toast.makeText(getApplicationContext(), R.string.PleaseWait, Toast.LENGTH_SHORT).show();
                    SharedPreferences preferences = getSharedPreferences("base_version", Context.MODE_PRIVATE);
                    localVersion = preferences.getInt("version", 0);
                    //new baseNewsVersion().execute("http://nammakarnataka.net23.net/general/base_version.json");
                    new baseFile().execute("https://raw.githubusercontent.com/AshwinChandlapur/ImgLoader/gh-pages/kannadaRec.json");
                } else {
                    Toast.makeText(getApplicationContext(),R.string.Connect, Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
            Fragment fragment;
            FragmentTransaction ft;
            Intent intent;
            int id = item.getItemId();

        switch (id) {
            case R.id.nav_home:
                intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.nav_breakfast:
                fragment = new breakfastFragment();
                ft = getSupportFragmentManager().beginTransaction();
//                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.app_bar, fragment);
                //ft.disallowAddToBackStack()
                ft.addToBackStack(null);
                ft.commit();
                break;

            case R.id.nav_appetizers:
                fragment = new appetizersFragment();
                //fragment = new riceitemsFragment();
                ft = getSupportFragmentManager().beginTransaction();
//                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.app_bar, fragment);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case R.id.nav_main_course:
                fragment = new maincourseFragment();
                // fragment = new lunchFragment();
                ft = getSupportFragmentManager().beginTransaction();
//                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                ft.replace(R.id.app_bar, fragment);
                ft.addToBackStack(null);
                ft.commit();
                break;



//            case R.id.nav_dessert:
//                fragment = new dessertFragment();
//                //fragment = new dessertsFragment();
//                ft = getSupportFragmentManager().beginTransaction();
////                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
//                ft.replace(R.id.app_bar, fragment);
//                ft.addToBackStack(null);
//                ft.commit();
//                break;

            case R.id.nav_snacks:
                fragment = new snacksFragment();
                //fragment = new snacksFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.app_bar, fragment);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case R.id.nav_festive:
                fragment = new festiveFragment() ;
               // fragment = new curryFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.app_bar, fragment);
                ft.addToBackStack(null);
                ft.commit();
                break;

            case R.id.nav_favourites:
                fragment = new favoritesFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.app_bar, fragment);
                ft.addToBackStack(null);
                ft.commit();
                break;



            case R.id.feedback:
                Intent intent3 = new Intent(getApplicationContext(), feedback.class);
                startActivity(intent3);
                //Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "ashwinchandlapur@gmail.com"));
              //  intent1.putExtra(Intent.EXTRA_SUBJECT,"Kitchen Feedback");
              //  startActivity(intent1);
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }




}
